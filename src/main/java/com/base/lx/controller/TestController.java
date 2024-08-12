package com.base.lx.controller;

import com.alibaba.dubbo.common.json.JSON;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@Controller
public class TestController {
    public static void main(String[] args) throws MalformedURLException {
        // 设置请求地址
        String url = "https://api.binjie.fun/api/generateStream?refer__1360=n4AxRD2D9Dg73DKPGNBexUx2DB0xcGxxhl2YD";

        // 准备请求参数
        Map<String, Object> map = new HashMap<>();
        map.put("prompt", "还有吗");
        map.put("userId", "#/chat/1719819963035");
        map.put("network", false);
        map.put("system", "");
        map.put("withoutContext", false);
        map.put("stream", false);

        // 将请求参数转换为 JSON 格式的字符串
        String postData = toJson(map);

        // 创建 RestTemplate 对象
        RestTemplate restTemplate = new RestTemplate();

        // 设置请求头
        HttpHeaders headers = new HttpHeaders();
        headers.add("Referer", "https://chat18.aichatos8.com/");
        headers.add("Origin", "https://chat18.aichatos8.com");
        headers.add("Content-Type","application/json");
        headers.add("Content-Length", String.valueOf(postData.getBytes(StandardCharsets.UTF_8).length));


        System.out.println("1231:" + headers);
        // 创建 HttpEntity 封装请求体和请求头
        HttpEntity<String> requestEntity = new HttpEntity<>(postData, headers);

        // 发送 POST 请求
        String response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class).getBody();

        // 输出响应内容（这里可以根据实际情况处理返回结果）
        String utf8String = convertToUTF8(response);

        // 输出转换后的 UTF-8 字符串
        System.out.println(utf8String);
    }


    public static String convertToUTF8(String str) {
        String utf8String = null;
        try {
            utf8String = new String(str.getBytes("ISO-8859-1"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return utf8String;
    }

    // 辅助方法：将 Map 转换为 JSON 字符串
    private static String toJson(Map<String, Object> map) {
        StringBuilder json = new StringBuilder();
        json.append("{");
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            json.append("\"").append(entry.getKey()).append("\":");
            if (entry.getValue() instanceof String) {
                json.append("\"").append(entry.getValue()).append("\",");
            } else {
                json.append(entry.getValue()).append(",");
            }
        }
        json.deleteCharAt(json.length() - 1); // 移除末尾多余的逗号
        json.append("}");
        return json.toString();
    }

}
