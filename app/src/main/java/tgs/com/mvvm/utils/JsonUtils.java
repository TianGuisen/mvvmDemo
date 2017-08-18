package tgs.com.mvvm.utils;

import com.alibaba.fastjson.JSON;

import java.util.List;

/**
 * Created by Administrator on 2017/3/6.
 */

public class JsonUtils {

    private JsonUtils() {
    }

    /**
     * @param src :将要被转化的对象
     * @return :转化后的JSON串
     * @MethodName : toJson
     * @Description : 将对象转为JSON串，此方法能够满足大部分需求
     */
    public static String toJson(Object src) {
        if (null == src) {
            return "";
        }
        return JSON.toJSONString(src);
    }

    /**
     * 解析json数据
     *
     * @param json  json字符串
     * @param clazz 对应的对象
     * @param <T>   返回的数据类型
     * @return 指定类型的数据
     */
    public static <T> T fromJson(String json, Class<T> clazz) {
        return JSON.parseObject(json, clazz);
    }

    /**
     * 解析json数据 [列表]
     *
     * @param json  json字符串
     * @param clazz 对应的对象
     * @param <T>   返回的数据类型
     * @return 指定类型的数据
     */
    public static <T> List<T> fromJsonList(String json, Class<T> clazz){
        return JSON.parseArray(json, clazz);
    }
}
