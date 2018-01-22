package com.worthy.ly.utils;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.apache.commons.lang3.StringUtils;

import java.util.Calendar;
import java.util.Date;


public class PinyinUtil {
    /**
     * 将字符串中的中文转化为拼音,其他字符不变
     *
     * @param inputString
     * @return
     */
    public static String getPinYin(String inputString) {
        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        format.setCaseType(HanyuPinyinCaseType.LOWERCASE);//小写
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);//不知道这个是什么意思 写完查api看看
        format.setVCharType(HanyuPinyinVCharType.WITH_V);//

        char[] input = inputString.trim().toCharArray();
        String output = "";

        try {
            for (int i = 0; i < input.length; i++) {
                if (Character.toString(input[i]).matches("[\\u4E00-\\u9FA5]+")) {
                    String[] temp = PinyinHelper.toHanyuPinyinStringArray(input[i], format);
                    output += temp[0];
                } else {
                    output += input[i];
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return output;
    }


    public static String getFirstLetter(String chinese){
        String py = null;
        try {
            if (StringUtils.isNotEmpty(chinese)) {
                chinese = chinese.substring(0,1);
            }
             py=getFirstSpell(chinese);
            return py.substring(0,1);
        }catch (Exception e){
            System.out.println(chinese);
        }
        return "";
    }

    /**
     * 获取汉字串拼音首字母，英文字符不变
     *
     * @param chinese 汉字串
     * @return 汉语拼音首字母
     */
    public static String getFirstSpell(String chinese) {
        StringBuffer pybf = new StringBuffer();
        char[] arr = chinese.toCharArray();
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        defaultFormat.setVCharType(HanyuPinyinVCharType.WITH_V);

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > 128) {
                try {
                    String[] temp = PinyinHelper.toHanyuPinyinStringArray(arr[i], defaultFormat);
                    if (temp != null) {
                        pybf.append(temp[0].charAt(0));
                    } else {
                        pybf.append(arr[i]);
                    }
                } catch (BadHanyuPinyinOutputFormatCombination e) {
                    e.printStackTrace();
                }
            } else {
                pybf.append(arr[i]);
            }
        }
        return pybf.toString().replaceAll("\\W", "").trim().toLowerCase();
    }

    /**
     * 获取汉字串拼音，英文字符不变
     *
     * @param chinese 汉字串
     * @return 汉语拼音
     */
    public static String getFullSpell(String chinese) {
        StringBuffer pybf = new StringBuffer();
        char[] arr = chinese.toCharArray();

        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        defaultFormat.setVCharType(HanyuPinyinVCharType.WITH_V);

        try {
            for (int i = 0; i < arr.length; i++) {
                if (arr[i] > 128) {
                    pybf.append(PinyinHelper.toHanyuPinyinStringArray(arr[i], defaultFormat)[0]);
                } else {
                    pybf.append(arr[i]);
                }
            }
        } catch (BadHanyuPinyinOutputFormatCombination e) {
            e.printStackTrace();
        }
        return pybf.toString();
    }

    public static void main(String args[]) {
       /* Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        System.out.println(hour);*/
      /* String resp = "{\"all\":false,\"attr\":true,\"classIds\":\"100191197,100191211,100191213,100191215\",\"contentNotice\":\"测试一下\",\"endDate\":1514736000000,\"group\":false,\"ip\":\"106.121.76.30\",\"noticeId\":1208,\"ownerId\":111790203,\"platform\":\"App\",\"range\":3,\"role\":3,\"schoolId\":1,\"timeType\":2,\"userVoList\":[{\"classId\":100191197,\"type\":1,\"userId\":101336422},{\"classId\":100191197,\"type\":1,\"userId\":106406688},{\"classId\":100191197,\"type\":1,\"userId\":106406727},{\"classId\":100191197,\"type\":1,\"userId\":106815139},{\"classId\":100191197,\"type\":1,\"userId\":106815154},{\"classId\":100191197,\"type\":1,\"userId\":106815155},{\"classId\":100191197,\"type\":1,\"userId\":106815156},{\"classId\":100191197,\"type\":1,\"userId\":109134396},{\"classId\":100191211,\"type\":1,\"userId\":106406666},{\"classId\":100191211,\"type\":1,\"userId\":106406667},{\"classId\":100191211,\"type\":1,\"userId\":106406668},{\"classId\":100191211,\"type\":1,\"userId\":106406669},{\"classId\":100191211,\"type\":1,\"userId\":106406672},{\"classId\":100191211,\"type\":1,\"userId\":106815163},{\"classId\":100191211,\"type\":1,\"userId\":106815164},{\"classId\":100191213,\"type\":1,\"userId\":106406695},{\"classId\":100191213,\"type\":1,\"userId\":106815068},{\"classId\":100191213,\"type\":1,\"userId\":106815114},{\"classId\":100191213,\"type\":1,\"userId\":106815115},{\"classId\":100191215,\"type\":1,\"userId\":122753},{\"classId\":100191215,\"type\":1,\"userId\":106406691},{\"classId\":100191215,\"type\":1,\"userId\":110342682}]}";
        PushNoticeVo pushNoticeVo = JSONObject.parseObject(resp, PushNoticeVo.class);
//        System.out.println(pushNoticeVo.paresClassWithUser());
        HashMap<String, HashMap<Integer, ArrayList<Long>>> classWithUser = pushNoticeVo.paresClassWithUser();
        String[] classIdStrs = StringUtils.split(pushNoticeVo.getClassIds(), ",");
        for (String classIdStr : classIdStrs) {
            System.out.println(classIdStr);
            Map<Integer, ArrayList<Long>> typeWithUsers = classWithUser.get(classIdStr);
            System.out.println(typeWithUsers);
        }*/
        /*Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.MINUTE,20);
        System.out.println((int) (System.currentTimeMillis() - calendar.getTime().getTime()) / (1000 * 60));*/
       /* String extend = "{\"isShow\":1,choice:{\"参加\":12,\"不参加\":2}}";
        String key = "参加";
        Integer val = 0;
        Map<String,Object> extendMap = JSONObject.parseObject(extend, Map.class);
        String choiceStr = extendMap.get("choice").toString();
        Map<String,Object> choiceMap = JSONObject.parseObject(choiceStr, Map.class);
        System.out.println(choiceMap);
        if(choiceMap.get(key)!=null)val =Integer.parseInt(choiceMap.get(key).toString());
        val = val+1;
        choiceMap.put(key,val);
        extendMap.put("choice",choiceMap);
        extend = JSONObject.toJSONString(extendMap);
        System.out.println(extend);*/
        /*long now = System.currentTimeMillis();
        long startTime = now - (1000 * 60 * 1);
        long endTime = now - (1000 * 60 * 1 - 30 * 1000);

        System.out.println(startTime);
        System.out.println(endTime);*/

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_YEAR,-7);
        calendar.set(Calendar.HOUR_OF_DAY,0);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND,0);

        System.out.println(DateUtils.dateToString(calendar.getTime(),"yyyy-MM-dd HH:mm:ss"));

    }
}
