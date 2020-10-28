package com.fb.xujimanage.entity;

public class Constants {
    /**
     * redis 分布式锁 key
     */
    public static final String USER_MANAGE_SYS_LOCK = "USER_MANAGE_SYS_KEY";
    public static final String RESTAURANT_LOCK = "RESTAURANT_KEY";

    /**
     * 登录鉴权相关 key
     */
    public static final String TOKEN_KEY = "token_key:";
    public static final long TOKEN_EXPIRES_TIME = 30 * 60;

    public static final Integer ZERO = 0;
    public static final Integer ONE = 1;
    public static final Integer TWO = 2;

    public static final long EXPIRE_TIME = 1;
    public static final String TOKEN_SECRET = "SzLnyj@djfk8765p&$dsjfhjdfjGft";

    public static final String USER = "lnyjAlbum";
    public final static String PWORD = "lnyj@zhihua@2019";

    public final static int LAST_UPDATE = 0x01;
    public final static int BEST_RECOMMEND = 0x02;
    public final static int HOT_ALBUM = 0x03;
}
