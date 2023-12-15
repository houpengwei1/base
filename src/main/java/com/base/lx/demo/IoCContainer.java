package com.base.lx.demo;

import java.lang.reflect.Constructor;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class IoCContainer {

    private Map<String, Object> beans = new ConcurrentHashMap<>();

    public Object getBean(String beanId) {
        return beans.get(beanId);
    }

    public void setBean(Class<?> clazz, String beanId, String... paramBeanIds) {
        try {
        Object[] paramValues = new Object[paramBeanIds.length];
        for (int i = 0; i < paramBeanIds.length; i++) {
            paramValues[i] = beans.get(paramBeanIds[i]);
        }
        Object bean = null;
        for (Constructor<?> constructor : clazz.getConstructors()) {
            bean = constructor.newInstance(paramValues);
        }
        if(bean == null){
            throw new RuntimeException("失败");
        }
        beans.put(beanId,bean);
        }catch (Exception e){
            throw new RuntimeException("异常");
        }

    }

}
