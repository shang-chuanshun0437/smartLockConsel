package com.mutong.smartlock.service.spi;

import com.mutong.smartlock.common.*;
import com.mutong.smartlock.controller.request.*;
import com.mutong.smartlock.controller.response.*;
import com.mutong.smartlock.dao.entity.UserInfo;
import com.mutong.smartlock.service.UserManage;
import com.mutong.smartlock.service.exception.UserManageExiception;
import com.mutong.smartlock.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Map;
import java.util.UUID;

@Service
public class UserManageSpi implements UserManage
{
    private Logger logger = LoggerFactory.getLogger(UserManageSpi.class);

    @Autowired
    private UserInfoServiceSpi userInfoServiceSpi;

    @Autowired
    private StringRedisTemplate redisTemplate;

    /*
     * 在注册用户时
     * 1、校验用户名是否为手机号
     * 2、校验用户名是否已经注册
     * 3、校验短信验证码
     * 4、注册成功
     * */
    @Override
    public AddUserResponse addUser(AddUserRequest request)
    {
        AddUserResponse addUserResponse = new AddUserResponse();

        Result result = new Result();
        addUserResponse.setResult(result);

        String phoneNum = request.getPhoneNum();
        try
        {
            //校验用户名
            checkUserName(phoneNum);

            //注册时生成token，存入数据库，为永久token，只有在密码修改的时候才会变动
            String token = UUID.randomUUID().toString();

            //将token存入Redis,采用map方式存储
            redisTemplate.opsForHash().put(phoneNum ,Constant.TOKEN,token);

            //将用户信息存入数据库
            UserInfo userInfo = new UserInfo();

            userInfo.setCreateTime(DateUtil.yyyyMMddHHmm());
            userInfo.setPhoneNum(request.getPhoneNum());
            userInfo.setUserName(request.getUserName());
            userInfo.setPassword(request.getPassword());
            userInfo.setToken(token);
            userInfo.setTombTime("0");

            userInfoServiceSpi.save(userInfo);
        }
        catch (UserManageExiception e)
        {
            result.setRetcode(e.getCode());
            result.setRetmsg(e.getMsg());
        }

        catch (Exception e)
        {
            result.setRetcode(ErrorCode.DEFAULT_ERROR);
            result.setRetmsg("add user failed");
        }

        return addUserResponse;
    }

    /*
    *使用手机号和密码登录
     */
    @Override
    public LoginResponse login(LoginRequest request)
    {
        if( logger.isDebugEnabled() )
        {
            logger.debug("inter login the request is:{}",request.toString());
        }

        LoginResponse response = new LoginResponse();
        Result result = new Result();
        result.setRetmsg("success");
        result.setRetcode(ErrorCode.SUCCESS);
        response.setResult(result);

        //校验用户和密码
        String password = request.getPassword();
        String phoneNum = request.getPhoneNum();
        String terminalId = request.getTerminalId();

        UserInfo dbUserInfo = userInfoServiceSpi.findByPhoneNum(phoneNum);
        if(dbUserInfo == null)
        {
            result.setRetcode(ErrorCode.USERPHONE_NOT_EXIST);
            result.setRetmsg("user phone not exist in mysql!");
            logger.error("user phone not exist in mysql!user name :{}",phoneNum);
            return response;
        }
        else if(!password.equals(dbUserInfo.getPassword()))
        {
            result.setRetcode(ErrorCode.PASSWORD_ERROR);
            result.setRetmsg("password is not right.");
            logger.error("password is not right.!user phone :{}",phoneNum);
            return response;
        }

        //若数据库中token为空，则生成新的token
        String token = dbUserInfo.getToken();
        if(StringUtils.isEmpty(token))
        {
            token = UUID.randomUUID().toString();
            dbUserInfo.setToken(token);
            userInfoServiceSpi.save(dbUserInfo);
            redisTemplate.opsForHash().put(phoneNum,Constant.TOKEN,token);
        }
        //获取上一次登录记录:手机ID、登录时间
        Map<Object,Object> resultMap= redisTemplate.opsForHash().entries(phoneNum);
        String lastTerminalId = (String)resultMap.get(Constant.LAST_TERMINALID);
        String lastTime = (String)resultMap.get(Constant.LAST_TIME);
        if(!StringUtils.isEmpty(lastTerminalId) || !StringUtils.isEmpty(lastTime))
        {
            NamedParmeter[] namedParmeters = new NamedParmeter[2];
			
			namedParmeters[0] = new NamedParmeter();
            namedParmeters[0].setKey(Constant.LAST_TERMINALID);
            namedParmeters[0].setValue(lastTerminalId);
			
			namedParmeters[1] = new NamedParmeter();
            namedParmeters[1].setKey(Constant.LAST_TIME);
            namedParmeters[1].setValue(lastTime);

            result.setExtendsInfo(namedParmeters);
        }
        //设置本次登录的时间和手机id
        redisTemplate.opsForHash().put(phoneNum,Constant.LAST_TERMINALID,request.getTerminalId());
        redisTemplate.opsForHash().put(phoneNum,Constant.LAST_TIME,DateUtil.yyyyMMddHHmmss());

        response.setToken(token);

        logger.debug("exit login(),login success,user name:{}",request.getPhoneNum());
        return response;
    }

    //
    @Override
    public ModifyPwdResponse modifyPwd(ModifyPwdRequest request)
    {
        //修改密码的时候，生成新的token

        if( logger.isDebugEnabled() )
        {
            logger.debug("inter modifyPwd(), the user name is:{}",request.getPhoneNum());
        }

        ModifyPwdResponse response = new ModifyPwdResponse();

        Result result = new Result();
        result.setRetcode(ErrorCode.SUCCESS);
        result.setRetmsg("success");

        response.setResult(result);

        //去数据库校验当前密码是否正确
        UserInfo userInfo = userInfoServiceSpi.findByPhoneNum(request.getPhoneNum());
        LockAssert.isTrue(userInfo != null,ErrorCode.USERPHONE_NOT_EXIST,"user not exist!");
        LockAssert.isTrue(userInfo.getPassword().equals(request.getOldpassword()),ErrorCode.PASSWORD_ERROR,"password is error");

        //生成新的token
        String token = UUID.randomUUID().toString();
        //将token写入redis
        redisTemplate.opsForHash().put(request.getPhoneNum(),Constant.TOKEN,token);
        //将token和新密码写入数据库
        userInfo.setPassword(request.getNewpassword());
        userInfo.setToken(token);
        userInfoServiceSpi.save(userInfo);
        //将token返回
        response.setToken(token);

        return response;
    }

    private void checkUserName(String phoneNum)
    {
        if(!phoneNum.matches("^[0-9]*$"))
        {
            throw new UserManageExiception(ErrorCode.PHONE_NUM_INVALIDED,"phone num is not legal");
        }

        UserInfo userInfo = userInfoServiceSpi.findByPhoneNum(phoneNum);

        if(userInfo != null)
        {
            throw new UserManageExiception(ErrorCode.PHONE_NUM_EXIST,"phone num has alreday exits");
        }
    }

    /*private String getToken()
    {
        //为了节省内存空间，只取前15位
        String uuid = UUID.randomUUID().toString();
        String temp = uuid.substring(0,15);

        return temp;
    }*/

    public UserInfoServiceSpi getUserInfoServiceSpi() {
        return userInfoServiceSpi;
    }

    public void setUserInfoServiceSpi(UserInfoServiceSpi userInfoServiceSpi) {
        this.userInfoServiceSpi = userInfoServiceSpi;
    }

    public StringRedisTemplate getRedisTemplate() {
        return redisTemplate;
    }

    public void setRedisTemplate(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }
}
