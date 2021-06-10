package com.ren.oldr.common.utils;


import com.ren.oldr.common.Snowflake;

/**
 * @Author: target
 * @Date: 2020/3/30 11:51
 * @Description:
 */
public class IDUtil {

    public static String nextStrId() {
        Snowflake snowflake = Snowflake.getInstanceSnowflake();
        return String.valueOf(snowflake.nextId());
    }

    public static Long nextLongId() {
        Snowflake snowflake = Snowflake.getInstanceSnowflake();
        return snowflake.nextId();
    }


}
