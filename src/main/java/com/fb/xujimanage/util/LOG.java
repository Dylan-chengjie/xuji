package com.fb.xujimanage.util;



import org.apache.log4j.Logger;
import org.apache.log4j.Priority;

import java.io.IOException;

/**
 * @author xiawei
 * @version 1.0
 * @date 2020/8/13 09:06
 * 日志工具类
 */
public class LOG{

    private static final Logger LOG = Logger.getRootLogger();

    private static final Logger DBLOG=Logger.getLogger("dbLogger");

    public static void info(Object message) {
        LOG.info(message);
        DBLOG.info(message+getLocationInfo(null));
    }

    public static void info(Object message, Throwable t) {
        LOG.info(message, t);
        DBLOG.info(message+getLocationInfo(t), t);
    }

    public static void debug(Object message) {
        LOG.debug(message);
        DBLOG.debug(message+getLocationInfo(null));
    }

    public static void debug(Object message, Throwable t) {
        LOG.debug(message, t);
        DBLOG.debug(message+getLocationInfo(t), t);
    }

    public static void error(Object message) {
        LOG.error(message);
        DBLOG.error(message+getLocationInfo(null));
    }

    public static void error(Object message, Throwable t) {
        LOG.error(message, t);
        DBLOG.error(message+getLocationInfo(t), t);
    }

    public static void warn(Object message) {
        LOG.warn(message);
        DBLOG.warn(message+getLocationInfo(null));
    }

    public static void warn(Object message, Throwable t) {
        LOG.warn(message, t);
        DBLOG.warn(message+getLocationInfo(t), t);
    }

    public static void custom(Priority priority,Object message){
        LOG.log(priority,message);
    }

    public static String getLocationInfo(Throwable t){
        try {
            if(t!=null){
                return StringUtil.exception2String(t);
            }else {
                return "";
            }
        } catch (IOException e) {
            e.printStackTrace();
            LOG.error("记录错误信息失败");
            return "记录错误信息失败";
        }
    }
}
