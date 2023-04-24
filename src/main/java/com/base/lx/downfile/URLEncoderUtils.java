package com.base.lx.downfile;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;


/**
 * @author FuYuChuang
 * @version 1.0
 * @date 2023/4/24 16:49
 * @desc
 */
public class URLEncoderUtils {
    public static String encode(String url) {
        try {
            String resultURL = "";
            //遍历字符串
            for (int i = 0; i < url.length(); i++) {
                char charAt = url.charAt(i);
                //只对汉字处理
                if (isChineseChar(charAt)) {
                    String encode = URLEncoder.encode(charAt + "", "UTF-8");
                    resultURL += encode;
                } else {
                    resultURL += charAt;
                }
            }
            return resultURL;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    //判断汉字的方法,只要编码在\u4e00到\u9fa5之间的都是汉字
    public static boolean isChineseChar(char c) {
        return String.valueOf(c).matches("[\u4e00-\u9fa5]");
    }

}
