package com.makcent.common;

public class Const {

    public static final String  CURRENT_MEMBER = "current_member";

    public static final String EMAIL = "email";
    public static final String USERNAME = "username";

    public interface Role{
        short ROLE_CUSTOMER = 0; //普通用户
        short ROLE_ADMIN = 1;//管理员
    }

}
