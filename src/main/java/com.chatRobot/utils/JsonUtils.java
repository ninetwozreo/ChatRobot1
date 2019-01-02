//package com.chatRobot.utils;
//
//public class JsonUtils {
//
//    private static Gson gson = new Gson();
//
//
//    /**
//     * @param src :将要被转化的对象
//     * @return :转化后的JSON串
//     * @MethodName : toJson
//     * @Description : 将对象转为JSON串，此方法能够满足大部分需求
//     */
//    public static String toJson(Object src) {
//        if (src == null) {
//            return gson.toJson(JsonNull.INSTANCE);
//        }
//        return gson.toJson(src);
//    }
//}