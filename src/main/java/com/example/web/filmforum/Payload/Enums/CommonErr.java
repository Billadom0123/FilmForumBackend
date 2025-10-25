package com.example.web.filmforum.Payload.Enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
* 常用错误返回
* 避免手打message和code，造成打错的情况
* @author 好像是学线祖传，当然kiss加工过很多
* @since 2024/11/1
* last update: 2024/11/1
* */

@Getter
@AllArgsConstructor
public enum CommonErr {

    //------------------------------------------------访问控制------------------------------------------------//

    REQUEST_NOT_ALLOW(10000, "当前条件或时间不允许〒▽〒"),

    REQUEST_FREQUENTLY(10001, "请求繁忙，请稍后再试"),

    METHOD_NOT_ALLOW(10002, "方法不允许"),

    NO_AUTHORITY(10003,"当前用户权限不允许使用该功能!"),

    OPERATE_REPEAT(10004,"重复的操作"),

    NO_AUTHENTICATION(10005,"未经授权的操作！"),

    UNKNOWN_LOGIN_ERROR(10020,"未知的登陆错误！"),

    NAME_OR_PASSWORD_WRONG(10021,"用户名或密码输入错误！"),

    USER_ALREADY_EXISTS(10030,"该用户已经存在！"),

    USERNAME_ALREADY_EXISTS(10031,"该用户名已经被注册！"),

    EMAIL_ALREADY_EXISTS(10032,"该邮箱已经被注册！"),

    UNKNOWN_REGISTER_ERROR(10033,"未知的注册错误！"),




    //------------------------------------------------连接异常------------------------------------------------//

    NETWORK_WRONG(20000, "网络错误"),

    CONNECT_TO_MYSQL_FAILED(20001,"数据获取异常了惹T_T"),

    //------------------------------------------------检查控制------------------------------------------------//

    PARAM_WRONG(30000, "参数范围或格式错误"),

    TOKEN_CHECK_FAILED(30001,"token检查有误"),

    SQL_NOT_ALLOWED_IN_STRING(30002,"包含不允许的字符!"),

    UNKNOWN_DATABASE_OPERATE_ERROR(30003,"未知的数据库操作错误!"),

    INVALID_AWARD_TARGET_TYPE(31000,"获奖目标类型错误!"),

    AWARD_NOT_FOUND(31001,"未找到对应的奖项!"),

    //------------------------------------------------数据异常------------------------------------------------//

    RESOURCE_NOT_FOUND(40000, "你要找的东西好像走丢啦X﹏X"),

    RESOURCE_IS_DELETE(40001,"你寻找的资源好像已经被删除了呢"),

    NO_DATA(40002,"没有找到结果哦QwQ"),

    THIS_IS_LAST_PAGE(40003, "这是最后一页，再怎么找也没有啦"),

    THIS_IS_FIRST_PAGE(40004, "没有上一页啦"),

    //------------------------------------------------文件流控制------------------------------------------------//

    FILE_FORMAT_ERROR(50000,"文件格式错误"),

    FILE_OUT_OF_LIMIT(50001,"文件大小超出限制!"),

    FILE_OPERATOR_ERR(50002,"文件操作失败!");


    private final int code;
    private String message;

    public CommonErr setMsg(String msg) {
        message = msg;
        return this;
    }
}
