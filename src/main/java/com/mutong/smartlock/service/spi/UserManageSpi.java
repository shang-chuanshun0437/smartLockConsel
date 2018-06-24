package com.mutong.smartlock.service.spi;

import com.mutong.smartlock.common.Constant;
import com.mutong.smartlock.common.ErrorCode;
import com.mutong.smartlock.common.NamedParmeter;
import com.mutong.smartlock.common.Result;
import com.mutong.smartlock.controller.request.LoginRequest;
import com.mutong.smartlock.controller.response.LoginResponse;
import com.mutong.smartlock.dao.entity.UserInfo;
import com.mutong.smartlock.service.UserManage;
import com.mutong.smartlock.service.exception.UserManageExiception;
import com.mutong.smartlock.controller.request.AddUserRequest;
import com.mutong.smartlock.controller.request.DeleteUserRequest;
import com.mutong.smartlock.controller.request.ModifyUserRequest;
import com.mutong.smartlock.controller.response.AddUserResponse;
import com.mutong.smartlock.controller.response.DeleteUserResponse;
import com.mutong.smartlock.controller.response.ModifyUserResponse;
import com.mutong.smartlock.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.AssociationOverride;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

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
        result.setRetmsg("add user success");
        addUserResponse.setResult(result);

        String userName = request.getUserName();
        try
        {
            //校验用户名
            checkUserName(userName);

            //注册时生成token，存入数据库，为永久token
            String token = this.getToken();

            //将token存入Redis,采用map方式存储
            redisTemplate.opsForHash().put(userName ,Constant.TOKEN,token);

            //将用户信息存入数据库
            UserInfo userInfo = new UserInfo();

            userInfo.setCreateTime(DateUtil.yyyyMMddHHmm());
            userInfo.setUserName(userName);
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

    @Override
    public DeleteUserResponse deleteUser(DeleteUserRequest request)
    {
        return null;
    }

    @Override
    public ModifyUserResponse modifyUser(ModifyUserRequest request)
    {
        return null;
    }

    /*
    *使用用户名和密码登录
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
        String userName = request.getUserName();
        String terminalId = request.getTerminalId();

        UserInfo dbUserInfo = userInfoServiceSpi.findByUserName(userName);
        if(dbUserInfo == null)
        {
            result.setRetcode(ErrorCode.USERNAME_NOT_EXIST);
            result.setRetmsg("user name not exist in mysql!");
            logger.error("user name not exist in mysql!user name :{}",userName);
            return response;
        }
        else if(!password.equals(dbUserInfo.getPassword()))
        {
            result.setRetcode(ErrorCode.PASSWORD_ERROR);
            result.setRetmsg("password is not right.");
            logger.error("password is not right.!user name :{}",userName);
            return response;
        }

        //若数据库中token为空，则生成新的token
        String token = dbUserInfo.getToken();
        if(StringUtils.isEmpty(token))
        {
            token = this.getToken();
            dbUserInfo.setToken(token);
            userInfoServiceSpi.save(dbUserInfo);
            redisTemplate.opsForHash().put(userName,Constant.TOKEN,token);
        }
        //获取上一次登录记录:手机ID、登录时间
        Map<Object,Object> resultMap= redisTemplate.opsForHash().entries(userName);
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
        redisTemplate.opsForHash().put(userName,Constant.LAST_TERMINALID,request.getTerminalId());
        redisTemplate.opsForHash().put(userName,Constant.LAST_TIME,DateUtil.yyyyMMddHHmmss());

        response.setToken(token);

        logger.debug("exit login,login success,user name:{}",request.getUserName());
        return response;
    }

    private void checkUserName(String userName)
    {
        if(!userName.matches("^[0-9]*$"))
        {
            throw new UserManageExiception(ErrorCode.USERNAME_INVALIDED,"userName is not legal");
        }

        UserInfo userInfo = userInfoServiceSpi.findByUserName(userName);

        if(userInfo != null)
        {
            throw new UserManageExiception(ErrorCode.USERNAME_EXIST,"userName is has alreday exits");
        }
    }

    private String getToken()
    {
        //为了节省内存空间，只取前15位
        String uuid = UUID.randomUUID().toString();
        String temp = uuid.substring(0,15);

        return temp;
    }

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