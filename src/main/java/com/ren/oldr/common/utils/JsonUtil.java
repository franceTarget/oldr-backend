package com.ren.oldr.common.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

/**
 * @author canove
 * @date 2020/12/25 16:55
 * @description
 */
@Slf4j
public class JsonUtil {

    public static String writeValueAsString(Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
        }
        return null;
    }
}
