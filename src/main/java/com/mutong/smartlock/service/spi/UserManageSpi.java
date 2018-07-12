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
     * 1、校验手机号
     * 2、校验短信验证码的凭据
     * 3、注册成功
     * */
    @Override
    public AddUserResponse addUser(AddUserRequest request)
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("inter addUser(),",request.toString());
        }

        AddUserResponse addUserResponse = new AddUserResponse();

        Result result = new Result();
        addUserResponse.setResult(result);

        String phoneNum = request.getPhoneNum();

        //校验用户名
        checkUserName(phoneNum);
        //校验验证码凭据
        String voucher = redisTemplate.opsForValue().get(request.getPhoneNum() + Constant.VERIFY_VOUCHER);
        LockAssert.isTrue(request.getVoucher().equals(voucher),ErrorCode.VERIFY_VOUCHER_ERROR,"verify voucher error");
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

        UserInfo dbUserInfo = userInfoServiceSpi.findByPhoneNum(phoneNum);
        LockAssert.isTrue(dbUserInfo != null,ErrorCode.USERPHONE_NOT_EXIST,"user phone not exist in mysql!");
        LockAssert.isTrue(password.equals(dbUserInfo.getPassword()),ErrorCode.PASSWORD_ERROR,"password is not right.");

        //若数据库中token为空，则生成新的token
        String token = dbUserInfo.getToken();
        if(StringUtils.isEmpty(token))
        {
            token = UUID.randomUUID().toString();
            dbUserInfo.setToken(token);
            userInfoServiceSpi.save(dbUserInfo);
            redisTemplate.opsForHash().put(phoneNum,Constant.TOKEN,token);
        }

        response.setToken(token);
        response.setUserName(dbUserInfo.getUserName());
        if (logger.isDebugEnabled())
        {
            logger.debug("exit login(),login success,user name:{}",request.getPhoneNum());
        }

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

    @Override
    public ModifyUserNameResponse modifyUserName(String phoneNum,String newName)
    {
        ModifyUserNameResponse response = new ModifyUserNameResponse();
        Result result = new Result();
        response.setResult(result);

        UserInfo dbUserInfo = userInfoServiceSpi.findByPhoneNum(phoneNum);
        LockAssert.isTrue(dbUserInfo != null,ErrorCode.USERPHONE_NOT_EXIST,"user not exist.");

        dbUserInfo.setUserName(newName);

        userInfoServiceSpi.save(dbUserInfo);
        return response;
    }

    private void checkUserName(String phoneNum)
    {
        LockAssert.isTrue(phoneNum.matches("^[0-9]*$"),ErrorCode.PHONE_NUM_INVALIDED,"phone num is not legal");

        UserInfo userInfo = userInfoServiceSpi.findByPhoneNum(phoneNum);
        LockAssert.isTrue(userInfo == null,ErrorCode.PHONE_NUM_EXIST,"phone num has alreday exits");
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
