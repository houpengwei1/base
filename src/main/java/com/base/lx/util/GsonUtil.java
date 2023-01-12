package com.base.lx.util;

import com.google.gson.*;
import com.google.gson.internal.LinkedTreeMap;

import java.util.ArrayList;
import java.util.List;

/**
 * gson工具类
 *
 * @author zhaosensen
 * createdBy  zhaosensen 20/01/30
 */
public class GsonUtil {

    public static final Gson GSON = new Gson();

    /**
     * 可序列化空值的gson
     */
    public static final Gson GSON_WITH_NULL = new GsonBuilder().serializeNulls().create();

    /**
     * 隐藏公共构造函数
     */
    private GsonUtil() {
        throw new IllegalStateException("GsonUtil class");
    }

    /**
     * object转json
     *
     * @param object
     * @return
     */
    public static String getGsonString(Object object) {
        if (object != null) {
            return GSON.toJson(object);
        }
        return null;
    }

    /**
     * 可序列化null值
     * @param object
     * @return
     */
    public static String getGsonWithNull(Object object){
        if (object != null) {
            return GSON_WITH_NULL.toJson(object);
        }
        return null;
    }
    /**
     * Json转成对象
     *
     * @param gsonString
     * @param cls
     * @return 对象
     */
    public static <T> T gsonToBean(String gsonString, Class<T> cls) {
        return GSON.fromJson(gsonString, cls);
    }

    /**
     * json转成list<T>
     *
     * @param gsonString
     * @param cls
     * @return list<T>
     */
    public static <T> List<T> gsonToList(String gsonString, Class<T> cls) {
        if (gsonString == null) {
            return null;
        }
        List<T> list = new ArrayList<>();
        JsonArray array = new JsonParser().parse(gsonString).getAsJsonArray();
        for (JsonElement jsonElement : array) {
            list.add(GSON.fromJson(jsonElement, cls));
        }
        return list;
    }

    /**
     * 判断字符串是否可以转化为JSON数组
     *
     * @param content
     * @return
     */
    public static boolean isJsonArray(String content) {
        try {
            new JsonParser().parse(content).getAsJsonArray();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 判断字符串是否可以转化为JSON对象
     *
     * @param content
     * @return
     */
    public static boolean isJsonObject(String content) {
        try {
            new JsonParser().parse(content).getAsJsonObject();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static <T> T convert(LinkedTreeMap linkedTreeMap, Class<T> cls){
        Gson gson = new GsonBuilder().enableComplexMapKeySerialization().create();
        String jsonString = gson.toJson(linkedTreeMap);
        T bean = gson.fromJson(jsonString,cls);
        return bean;
    }
}
