package com.oasystem.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class UrlUtils {


    public static String DEV_SERVER = "http://192.168.101.56:8080/OASystem/api";//服务器IP地址
    public static String PROD_SERVER = "http://121.4.112.228:8008/OASystem/api";//服务器IP地址
    public static String SERVER = PROD_SERVER;//服务器IP地址

    public static String LOGIN = SERVER + "/user/login";
    public static String CATEGORY_QUERY_ALL = SERVER + "/category/queryall";
    public static String PRODUCT_QUERY = SERVER + "/news/query";
    public static String PIC_URL = SERVER + "/file/fetch";

    public static String getPicUrl(String name) {
        return  PIC_URL + '/' + name;
    }

    public static String login(String userName, String password) {//用户登录的URL
        try {
            StringBuilder builder = new StringBuilder();
            builder.append(SERVER).append("/user/login?userName=")
                    .append(URLEncoder.encode(userName, "UTF-8"))
                    .append("&password=").append(URLEncoder.encode(password, "UTF-8"));
            return builder.toString();
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    public static String getAllClassifyUrl() {//获取架子所有分类的URL
        try {
            StringBuilder builder = new StringBuilder();
            builder.append("http://").append(SERVER).append("/OASystem/classify/getAll");
            return builder.toString();
        } catch (Exception e) {
            return null;
        }
    }

    public static String getNewsListByType(String typeId) {//根据分类获取信息的URL
        try {
            StringBuilder builder = new StringBuilder();
            builder.append("http://").append(SERVER).append("/OASystem/news/getByTypeId?typeId=")
                    .append(URLEncoder.encode(typeId, "UTF-8"));
            return builder.toString();
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    public static String getPictuireUrl(String pictureName) {//加载图片的URLr
        try {
            StringBuilder builder = new StringBuilder();
            builder.append("http://").append(SERVER).append("/OASystem/file/getPicture?pictureName=")
                    .append(URLEncoder.encode(pictureName, "UTF-8"));
            return builder.toString();
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

}
