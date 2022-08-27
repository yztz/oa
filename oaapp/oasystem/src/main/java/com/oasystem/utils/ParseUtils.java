package com.oasystem.utils;



import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.oasystem.bean.Result;
import com.oasystem.bean.User;



import java.util.ArrayList;
import java.util.List;

public class ParseUtils {

   public static Result parseInfor(String infor){//无返回数据解析

       Result bean = new Result();

       //{"success":true,"message":"登录成功!","errorCode":0}

       try {
           JSONObject object = JSONObject.parseObject(infor);
           boolean res = object.getBoolean("success");
           String message = object.getString("message");
           int errorCode = object.getInteger("errorCode");
           bean.setSuccess(res);
           bean.setMessage(message);
           bean.setErrorCode(String.valueOf(errorCode));
           return bean;

       } catch (Exception e) {
          // e.printStackTrace();
           bean.setSuccess(false);
           bean.setMessage("数据解析异常!");
       }
       return bean;
   }

    public static Result parseLoginInfor(String infor){//登录信息解析

        Result bean = new Result();

        //{"success":true,"message":"登录成功!","data":{"account":"000001","appendix":"6936632e27f142cd96aa93e80c420714","id":2,"password":"123456","phone":"88989891","userId":"d93795ba34ee433cbe7d3131b332f1eb","userName":"test","userType":"0"},"errorCode":0}

        try {
            JSONObject object = JSONObject.parseObject(infor);
            boolean res = object.getBoolean("success");
            String message = object.getString("message");
            int errorCode = object.getInteger("errorCode");
            bean.setSuccess(res);
            bean.setMessage(message);
            bean.setErrorCode(String.valueOf(errorCode));

            if(res){
                JSONObject deviceObj = object.getJSONObject("data");

                User user = new User();

                user.setId(deviceObj.getInteger("id"));
                user.setUserName(deviceObj.getString("userName"));
                user.setUserType(deviceObj.getString("userType"));
                user.setUserId(deviceObj.getString("userId"));
                user.setPhone(deviceObj.getString("phone"));
                user.setPassword(deviceObj.getString("password"));
                user.setAppendix(deviceObj.getString("appendix"));
                user.setAccount(deviceObj.getString("account"));

                bean.setObj(user);
            }
            return bean;

        } catch (Exception e) {
            // e.printStackTrace();
            bean.setSuccess(false);
            bean.setMessage("数据解析异常!");
        }
        return bean;
    }

   //{"success":true,"message":"查询成功!","data":[{"appendix":"","id":22,"name":"测试","typeId":"c3a52a401a3b4619add8cc2198534dd5"},{"appendix":"","id":21,"name":"新闻","typeId":"262aa8a7ef3345668711637c66bc8e22"},{"appendix":"备注10","id":11,"name":"娱乐","typeId":"8ed8fc23d8654223b6befc2a79cd87ae"},{"appendix":"备注9","id":10,"name":"休闲","typeId":"e49647a2a7b84cc1a90540162ea15d75"},{"appendix":"备注8","id":9,"name":"轻松","typeId":"594ee4a509144ae19a072b4bdeb4e34f"},{"appendix":"备注7","id":8,"name":"搞笑","typeId":"6394344d24f54a31ba3dfff2447cc449"},{"appendix":"备注6","id":7,"name":"公告","typeId":"06aeaccc4a0649bc899593488565906f"}],"errorCode":0}
//
//    public static List<Category> getClassifyList(String infor){//获取分类列表信
//
//        List<Category> classifyBeanList = new ArrayList<Category>();
//        try {
//            JSONObject object = JSONObject.parseObject(infor);;
//            boolean res = object.getBoolean("success");
//
//            if(res) {
//                JSONArray array = object.getJSONArray("data");
//                for (int i = 0; i < array.size(); i++) {
//
//                    JSONObject deviceObj = (JSONObject) array.get(i);
//                    Category bean = new Category();
//
//                    bean.setId(deviceObj.getInteger("id"));
//                    bean.setAppendix(deviceObj.getString("appendix"));
//                    bean.setName(deviceObj.getString("name"));
//                    bean.setTypeId(deviceObj.getString("typeId"));
//
//                    classifyBeanList.add(bean);
//                }
//            }
//            return classifyBeanList;
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return classifyBeanList;
//    }

//    public static List<NewsBean> getNewsList(String infor){//获取信息列表
//
//        List<NewsBean> newsBeanList = new ArrayList<NewsBean>();
//        try {
//            JSONObject object = JSONObject.parseObject(infor);;
//            boolean res = object.getBoolean("success");
//
//            if(res) {
//                JSONArray array = object.getJSONArray("data");
//                for (int i = 0; i < array.size(); i++) {
//
//                    JSONObject deviceObj = (JSONObject) array.get(i);
//                    NewsBean bean = new NewsBean();
//
//                    bean.setId(deviceObj.getInteger("id"));
//                    bean.setAppendix(deviceObj.getString("appendix"));
//                    bean.setNewsId(deviceObj.getString("newsId"));
//                    bean.setNumber(deviceObj.getString("number"));
//                    bean.setPicName(deviceObj.getString("picName"));
//                    bean.setTime(deviceObj.getString("time"));
//                    bean.setTypeId(deviceObj.getString("typeId"));
//                    bean.setTypeName(deviceObj.getString("typeName"));
//
//                    newsBeanList.add(bean);
//                }
//            }
//            return newsBeanList;
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return newsBeanList;
//    }

}
