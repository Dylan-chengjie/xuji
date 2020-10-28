package com.fb.xujimanage.aspect;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fb.xujimanage.util.CommonResult;
import com.fb.xujimanage.util.Md5SignUtils;
import com.fb.xujimanage.util.StringUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;


/**** AOP+Aspect 打印请求路劲 +响应参数 （用于接口对接依据）
 * @author summer.chou
 *
 */
@Component
@Aspect
public class LogAndValidAop {

    @Value("${org.api.key}")
    private String API_KEY;
    private Logger logger = LoggerFactory.getLogger(LogAndValidAop.class);

    //定义切点
    @Pointcut("execution(public * com.fb.xujimanage.apicontroller.*.*(..))||execution(public * com.fb.xujimanage.controller.*.*(..))")
    public void pointCutRestDef() {
    }

    @Around("pointCutRestDef()")
    public Object processRst(ProceedingJoinPoint point) throws Throwable {
        Object returnValue = null;
        ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = sra.getRequest();
        Object[] args = point.getArgs();
        String parameter = "";
        if (args.length == 0) {
            parameter = "null";
        } else {
            for (int i = 0; i < args.length; i++) {
                if (args[i] == null) {
                    continue;
                }
                String subParameter = args[i].toString();
                try {
                    JSONObject jsonObject = JSONObject.parseObject(subParameter);
                    JSONObject object = new JSONObject();
                    for (Map.Entry<String, Object> entry : jsonObject.entrySet()) {
                        String key = entry.getKey();
                        Object value = entry.getValue();
                        object.put(key, value);

                    }
                    subParameter = object.toJSONString();
                } catch (Exception e) {
//                    e.printStackTrace();
                }
                parameter = parameter + subParameter + ",";
            }
        }
        Long startTime = System.currentTimeMillis();
        try {
            //获取返回值
            returnValue = point.proceed(point.getArgs());
        } catch (Exception e) {
            // 请求异常处理
            throw e;
        }
        String url = request.getRequestURI();//获取接口路劲
        String method = request.getMethod();

        Long endTime = System.currentTimeMillis();
        StringBuffer logString = new StringBuffer();
        logString.append("\n" + " 请求方法 method :" + method);
        logString.append("\n" + " 响应时间 time:" + (endTime - startTime));
        logString.append("\n" + " 请求路径 url:" + url);
        logString.append("\n" + " 请求参数 param:" + parameter);
        logString.append("\n" + " 返回结果 json:" + objTtoJson(returnValue));
        logger.info(logString.toString());

        //对外接口以/api命名 做一个全局的参数验签
        if (url.contains("/api/") && StringUtil.isNotBlank(parameter)) {
            JSONObject json = JSON.parseObject(parameter);
            String sign = json.getString("sign");
            logger.info("api参数签名 sign:{},内部加密Md5Sign:{}", sign, Md5SignUtils.Md5Sign(API_KEY, json));
            if (!Md5SignUtils.checkSign(json, API_KEY, sign)) {
                return CommonResult.fail("参数签名未通过");
            }
        }
        return returnValue;
    }

    public String objTtoJson(Object returnValue) {
        JSONObject object = new JSONObject();
        JSONObject json = (JSONObject) JSON.toJSON(returnValue);

        for (Map.Entry<String, Object> entry : json.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            object.put(key, value);
        }
        return object.toJSONString();
    }
}
