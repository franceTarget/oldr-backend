package com.ren.oldr.web;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ren.oldr.common.annotation.ParamToBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.Iterator;
import java.util.LinkedHashMap;

/**
 * @Author canove
 * @Description 增加方法注入，将含有 @ParamToBody的方法中的requestparam转换为指定类型的对象
 * @Date 19:20 2020/10/14
 **/
@Slf4j
public class ParamToBodyArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(ParamToBody.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        Iterator<String> paramNames = webRequest.getParameterNames();
        LinkedHashMap<String, Object> paramsMap = new LinkedHashMap();
        while (paramNames.hasNext()) {
            String paramName = paramNames.next();
            paramsMap.put(paramName, webRequest.getParameter(paramName));
        }
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        Object obj = mapper.convertValue(paramsMap, parameter.getParameterType());
        return obj;
    }
}
