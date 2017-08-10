/**
 * ZhaoOnline.com Inc.
 * Copyright (c) 2004-2012 All Rights Reserved.
 */
package com.oa.util;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;


/**
 * JSON操作帮助类
 * @author 
 * @version v 0.1 2013-8-27 下午10:04:16
 */
public class JSONUtils {
    private static final ObjectMapper mapper = new ObjectMapper();
    private static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final Log logger = LogFactory.getLog(JSONUtils.class);
    
    /**
     * Java对象转为JSON字符串
     * @param target
     * @return
     */
    public static String toJSONString(Object target) {
        //经测试jackson > fastjson > json-lib > flexjson
        mapper.setDateFormat(format);
        mapper.configure(SerializationConfig.Feature.WRITE_NULL_MAP_VALUES, false);
        mapper.configure(JsonGenerator.Feature.QUOTE_FIELD_NAMES, true);
        mapper.setSerializationInclusion(JsonSerialize.Inclusion.NON_NULL);
        String _json = null;
        try {
            _json = mapper.writeValueAsString(target);
        } catch (JsonGenerationException e) {
            logger.error("", e);
        } catch (JsonMappingException e) {
            logger.error("", e);
        } catch (IOException e) {
            logger.error("", e);
        }
        return _json.replaceAll("(\r|\n|\t)", "");
    }
    
    /**
     * JSON字符串转java对象
     * @param jsonStr
     * @param clazz
     * @return
     */
    public static Object toJava(String jsonStr, Class<?> clazz) {
        //经测试fastjson > jackson > json-lib > flexjson
        return JSON.parseObject(jsonStr, clazz);
    }
    
    /**
     * json转list加java对象
     * @param objects
     * @return 
     * @return
     */
    public static <T> List<T> toList(String str, Class<T> clazz) {
        return JSON.parseArray(str,clazz);
    }

    
    /**
     * list转json字符串
     * @param list
     * @return
     */
    public static String toJSONString(Collection<?> list) {
        if(!ArrayUtils.hasLength(list)) return null;
        //经测试fastjson > jackson > flexjson > json-lib
        return JSON.toJSONStringWithDateFormat(list, "yyyy-MM-dd HH:mm:ss", SerializerFeature.WriteDateUseDateFormat);
    }
    
    /**
     * 数组转json字符串
     * @param objects
     * @return
     */
    public static String toJSONString(Object[] objects) {
        if(!ArrayUtils.hasLength(objects)) return null;
        //经测试fastjson > jackson > flexjson > json-lib
        return JSON.toJSONString(objects);
    }
}
