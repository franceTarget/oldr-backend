package com.ren.oldr.web;

import com.alibaba.fastjson.JSONObject;
import com.ren.oldr.common.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName MethodLogAspect
 * @Description TODO
 * @Author canove
 * @Date 2020/9/27 18:32
 * @Version 1.0
 */
@Slf4j
@Component
@Aspect
public class MethodLogAspect {

    @Pointcut("execution(public * com.ren.wwzq.controller.*.*(..))")
    public void pointCut() {
    }

    @Before(value = "pointCut()")
    public void beforeMethod(JoinPoint jp) {
        ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = sra.getRequest();
        String servletPath = request.getServletPath();
        log.info("global request path:[{}],parameter:{}", servletPath, JsonUtil.writeValueAsString(jp.getArgs()));
    }

    @AfterReturning(value = "pointCut()", returning = "result")
    public void afterReturning(Object result) {
        ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = sra.getRequest();
        String servletPath = request.getServletPath();
        log.info("global request path:[{}],return:{}", servletPath, JSONObject.toJSONString(result));
    }

}
