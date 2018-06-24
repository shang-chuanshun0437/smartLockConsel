package com.mutong.smartlock.common;

public class ErrorCode
{
    public static String SUCCESS = "000000";

    //用户名不合法
    public static String USERNAME_INVALIDED = "100003";

    //用户名已存在
    public static String USERNAME_EXIST = "100004";

    public static String DEFAULT_ERROR = "100005";

    //用户不存在
    public static String USERNAME_NOT_EXIST = "100006";

    //密码错误
    public static String PASSWORD_ERROR = "100007";

    //redis中token为null
    public static String TOKEN_IS_NULL = "100008";

    //用户未登录
    public static String NOT_LOGIN = "100009";

    //redis中refreshToken和请求中的refreshToken不一致
    public static String REFRESHTOKEN_IS_INVALID = "100010";

    public static class DeviceErrorCode
    {
        //数据库中不存在该设备
        public static String DEVICE_NOT_EXIT = "200001";

        //数据库中，设备的主用户和请求中传来的用户不匹配
        public static String MAIN_USER_MISSMATCH = "200002";
    }
}
