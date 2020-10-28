package com.fb.xujimanage.util;

public class IdUtil {
    private static long machineId = 1;

    private static long datacenterId = 1;

    /**
     * 单例模式创建学法算法对象
     */
    private enum SnowFlakeSingleton {
        Singleton;
        private SnowFlake snowFlake;

        SnowFlakeSingleton() {
            snowFlake = new SnowFlake(datacenterId, machineId);
        }

        public SnowFlake getInstance() {
            return snowFlake;
        }
    }

    public static long getUUID() {
        return SnowFlakeSingleton.Singleton.getInstance().nextId();
    }
}
