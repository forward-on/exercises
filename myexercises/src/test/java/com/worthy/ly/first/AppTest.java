package com.worthy.ly.first;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Unit test for simple App.
 */
public class AppTest {

    @Test
    public void testDate() throws ParseException {
        SimpleDateFormat format =  new SimpleDateFormat("MM-dd HH:mm:ss");

        Long time = new Long(1515751666009L);

        String d = format.format(time);

        System.out.println(d);

        Date date = format.parse(d);

        System.out.println(date);
    }

    @Test
    public void dealRich(){
        String rich = "她在看电视时你就可以理解你就知道自己有个&nbsp;<span id=\"selectionBoundary_1516267360791_7149137350011169\" class=\"rangySelectionBoundary\" style=\"line-height: 0; display: none;\">\uFEFF</span>\n" +
                "\n" +
                "<a href=\"http://filesystem1.bbtree.com/2018/01/18/658ed8b837fec76463f420c77e1e8f4c/ios/1516267372110990.webp\"><img src=\"http://filesystem1.bbtree.com/2018/01/18/658ed8b837fec76463f420c77e1e8f4c/ios/1516267372110990.webp\" alt=\"\" width=\"828\" height=\"1104\" class=\"alignnone size-full wp-image-2910941087\"></a>&nbsp;";
        String content = rich.replaceAll("<(/p|br/?)>", "\n")
                .replaceAll("&nbsp;", " ")
                .replaceAll("<[^<>]+>", "")
                .replaceAll("&lt;", "<")
                .replaceAll("&gt;", ">");
        System.out.println(content);
    }

    @Test
    public void testJson(){

//        String extend = "{\"isMultiple\":0,\"type\":1,\"isShow\":0}";
//        Map<String, Object> map = JSON.parseObject(extend.trim());
//        Integer type = (Integer) map.get("type");
//        if (type==1 && map.get("choice") == null) {
//            map.put("参加", 0);
//            map.put("不参加", 0);
//            String string = JSON.toJSONString(map);
//            System.out.println(string);
//        }

        /*String choiceStr = "{\"1\":0,\"2\":0,\"3\":0,\"11\":0,\"12\":0,\"13\":0,\"14\":0,\"15\":0,\"16\":0,\"17\":0,\"4\":0,\"5\":0,\"6\":0,\"7\":0,\"8\":0,\"9\":0,\"10\":0}";
        LinkedHashMap<String,Object> choiceMap = JSONObject.parseObject(choiceStr, LinkedHashMap.class);
        for (Map.Entry<String, Object> entry : choiceMap.entrySet()) {
            String key = entry.getKey();
            Integer value = (Integer) entry.getValue();
            System.out.println(key + "*********" + value);
        }*/

        String extend = "{\"isMultiple\":0,\"type\":1,\"isShow\":0\",choice\":{\"参加\":0,\"不参加\":0}}";
        String ext = extend.trim().substring(0, extend.length()-1);
        ext = ext + ",\"choice\":{\"参加\":0,\"不参加\":0}}";
        System.out.println(ext);

    }

    @Test
    public void json(){
        String response = "{\n" +
                "\"errcode\":0,\n" +
                "\"errmsg\":\"ok\",\n" +
                "\"ticket\":\"bxLdikRXVbTPdHSM05e5u5sUoXNKd8-41ZO3MhKoyN5OfkWITDGgnr2fwJ0m9E8NYzWKVZvdVtaUgWvsdshFKA\",\n" +
                "\"expires_in\":7200\n" +
                "}";
        JSONObject res = JSONObject.parseObject(response);
        Integer expires_in = (Integer) res.get("expires_in");
        System.out.println(expires_in.longValue());
    }


    @Test
    public void extend2map(){
        String extend = "{\"choice\":{\"1. 测试\":0,\"2. 测试\":0,\"3. 测试\":0,\"4. 测试\":0,\"5. 测试\":0,\"6. 测试\":0,\"7. 测试\":0,\"8. 测试\":0,\"9. 测试\":0,\"10. 测试\":0,\"11. 测试\":0,\"12. 测试\":0},\"isMultiple\":0,\"isShow\":1,\"type\":2}";
//        LinkedHashMap<String, Object> extendMap = JSONObject.parseObject(extend, LinkedHashMap.class);
        Map<String, Object> map = JSON.parseObject(extend);
        String choiceStr = map.get("choice").toString();
        LinkedHashMap<String,Object> choiceMap = JSONObject.parseObject(choiceStr, LinkedHashMap.class);
        System.out.println(choiceMap);
    }
    @Test

    public void extend2maps(){
        String extend = "{\"choice\":{\"1. 测试\":0,\"2. 测试\":0,\"3. 测试\":0,\"4. 测试\":0,\"5. 测试\":0,\"6. 测试\":0,\"7. 测试\":0,\"8. 测试\":0,\"9. 测试\":0,\"10. 测试\":0,\"11. 测试\":0,\"12. 测试\":0},\"isMultiple\":0,\"isShow\":1,\"type\":2}";
        ExtendVo extendVo = JSONObject.parseObject(extend, ExtendVo.class);
        LinkedHashMap<String, Object> choice = extendVo.getChoice();
        System.out.println(choice);
    }

}
