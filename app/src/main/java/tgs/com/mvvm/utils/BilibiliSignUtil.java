package tgs.com.mvvm.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lwlizhe on 2017/7/17.
 * 邮箱：624456662@qq.com
 */

public class BilibiliSignUtil {

    public static String getSign(HashMap<String, String> data,String appSecret) {

        String sign = "";
        String sort = "";
        List<String> sortList = new ArrayList<>();

        for (Map.Entry<String, String> entry : data.entrySet()) {
            String s = entry.getKey() + "=" + entry.getValue();
            sortList.add(s);
        }

        Collections.sort(sortList);

        for (int i = 0; i < sortList.size(); i++) {
            sort=sort+"&"+sortList.get(i);
        }

        sort=sort.substring(1,sort.length())+appSecret;
        sign = MD5Util.getMd5Value(sort);

        return sign;
    }


}
