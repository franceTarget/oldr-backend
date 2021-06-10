package com.ren.oldr.common;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.SystemUtils;

import java.net.Inet4Address;
import java.net.UnknownHostException;

/**
 * @author canove
 * @date 2021/5/7 14:47
 * @description
 */
@Slf4j
public class Snowflake {
    /**
     * 开始时间截 (2015-01-01)
     */
    private static final long TWEPOCH = 1489111610226L;

    // 机器标识位数
    private static final long WORKER_ID_BITS = 5L;

    // 数据中心标识位数
    private static final long DATA_CENTER_ID_BITS = 5L;

    // 机器ID最大值 31
    private static final long MAX_WORKER_ID = -1L ^ (-1L << WORKER_ID_BITS);

    // 数据中心ID最大值 31
    private static final long MAX_DATA_CENTER_ID = -1L ^ (-1L << DATA_CENTER_ID_BITS);

    // 毫秒内自增位
    private static final long SEQUENCE_BITS = 12L;

    // 机器ID偏左移12位
    private static final long WORKER_ID_SHIFT = SEQUENCE_BITS;

    private static final long DATA_CENTER_ID_SHIFT = SEQUENCE_BITS + WORKER_ID_BITS;

    // 时间毫秒左移22位
    private static final long TIMESTAMP_LEFT_SHIFT = SEQUENCE_BITS + WORKER_ID_BITS + DATA_CENTER_ID_BITS;

    private static final long SEQUENCE_MASK = -1L ^ (-1L << SEQUENCE_BITS);

    private long lastTimestamp = -1L;

    private long sequence = 0L;
    private final long workerId;
    private final long dataCenterId;
    private volatile static Snowflake snowflake = null;


    public Snowflake(long workerId, long dataCenterId) {
        if (workerId > MAX_WORKER_ID || workerId < 0) {
            throw new IllegalArgumentException(String.format("workerId can't be greater than %d or less than 0", MAX_WORKER_ID));
        }

        if (dataCenterId > MAX_DATA_CENTER_ID || dataCenterId < 0) {
            throw new IllegalArgumentException(String.format("%s 数据中心ID最大值 必须是 %d 到 %d 之间", dataCenterId, 0, MAX_DATA_CENTER_ID));
        }
        this.workerId = workerId;
        this.dataCenterId = dataCenterId;
    }

    /**
     * 获取单列
     *
     * @return
     */
    public static Snowflake getInstanceSnowflake() {
        if (snowflake == null) {
            synchronized (Snowflake.class) {
                snowflake = new Snowflake(getWorkerId(), getDataCenterId());
            }
        }
        return snowflake;
    }

    public synchronized long nextId() {
        long timestamp = time();
        if (timestamp < lastTimestamp) {
            throw new RuntimeException("时钟向后移动，拒绝生成id  " + (lastTimestamp - timestamp) + " milliseconds");
        }

        if (lastTimestamp == timestamp) {
            // 当前毫秒内，则+1
            sequence = (sequence + 1) & SEQUENCE_MASK;
            if (sequence == 0) {
                // 当前毫秒内计数满了，则等待下一秒
                timestamp = tilNextMillis(lastTimestamp);
            }
        } else {
            sequence = 0;
        }
        lastTimestamp = timestamp;

        // ID偏移组合生成最终的ID，并返回ID
        long nextId = ((timestamp - TWEPOCH) << TIMESTAMP_LEFT_SHIFT)
                | (dataCenterId << DATA_CENTER_ID_SHIFT) | (workerId << WORKER_ID_SHIFT) | sequence;

        return nextId;
    }

    private long tilNextMillis(final long lastTimestamp) {
        long timestamp = this.time();
        while (timestamp <= lastTimestamp) {
            timestamp = this.time();
        }
        return timestamp;
    }

    private long time() {
        return System.currentTimeMillis();
    }


    /**
     * @author canove
     * @date 2021/4/12 10:42
     * @description 机器id
     */
    private static Long getWorkerId() {
        try {
            String hostAddress = Inet4Address.getLocalHost().getHostAddress();
            int[] ints = StringUtils.toCodePoints(hostAddress);
            int sums = 0;
            for (int b : ints) {
                sums += b;
            }
            return (long) (sums % 32);
        } catch (UnknownHostException e) {
            // 如果获取失败，则使用随机数备用
            return RandomUtils.nextLong(0, 31);
        }
    }

    /**
     * @author canove
     * @date 2021/4/12 10:42
     * @description 数据中心id
     */
    private static Long getDataCenterId() {
        int[] ints = StringUtils.toCodePoints(SystemUtils.getHostName());
        int sums = 0;
        for (int i : ints) {
            sums += i;
        }
        return (long) (sums % 32);
    }
}