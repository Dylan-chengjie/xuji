package com.fb.xujimanage.common.task;

import com.fb.xujimanage.util.HttpClientUtil;
import com.fb.xujimanage.util.SignUtil;
import lombok.extern.log4j.Log4j;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author xiawei
 * @version 1.0
 * @date 2020/9/1 15:46
 * @description:定时任务
 */
@Log4j
public class ScheduleTask {

    private int fixedDelayCount = 1;
    private int fixedRateCount = 1;
    private int initialDelayCount = 1;
    private int cronCount = 1;

    public static void main(String[] args) throws Exception {
        testFixDelay();
    }

    @Scheduled(fixedDelay = 5000)        //fixedDelay = 5000表示当前方法执行完毕5000ms后，Spring scheduling会再次调用该方法
    public static void testFixDelay() throws Exception {
        String url = "https://api.acewill.net/shop/list";
        //String[] req = new String[1];
        Map param = new LinkedHashMap();
        String req = "{}";
        //String s = JSON.toJSONString(param);
        //String encrypt = MD5.encrypt(s);
        // String s2 = encrypt.toLowerCase();
        param.put("appid", "dp3bSdu1fAdHUkvuruIKKoi");
        param.put("appkey", "fc422f07af0b867b3765c156abd09853");
        param.put("v", "2.0");
        param.put("ts", "1599120042");
        param.put("sig", "b8f8aa6aaf4c7b79eedf583a3c5adeda");
        param.put("req", "[]");
        //String s1 = MD5.encrypt(req);
        String sign = SignUtil.createSign(param);

        String post = HttpClientUtil.post(url, sign);
        //System.out.println(s1);
        System.out.println(post);
    }

    @Scheduled(fixedRate = 5000)        //fixedRate = 5000表示当前方法开始执行5000ms后，Spring scheduling会再次调用该方法
    public void testFixedRate() {
        log.info("===fixedRate: 第{}次执行方法:" + fixedRateCount++);
    }

    @Scheduled(initialDelay = 1000, fixedRate = 5000)   //initialDelay = 1000表示延迟1000ms执行第一次任务
    public void testInitialDelay() {
        log.info("===initialDelay: 第{}次执行方法:" + initialDelayCount++);
    }

    @Scheduled(cron = "0 0/1 * * * ?")  //cron接受cron表达式，根据cron表达式确定定时规则
    public void testCron() {
        log.info("===initialDelay: 第{}次执行方法:" + cronCount++);
    }

}
