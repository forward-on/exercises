package com.worthy.ly.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class PicUtil {
    public static List<String> getPicList(String pics) {
        List<String> pslist = new ArrayList<String>();
        if (pics != null && !pics.equals("")) {
            String ps[] = pics.split(",");
            for (String pic : ps) {
                pslist.add(getPicFileDomain(pic.split("\\|")[1]) + pic.split("\\|")[1]);
            }
        }
        return pslist;
    }

    public static String getPicFileDomain(String pic) {
        if (pic.matches("^\\d{4}[\\s\\S]*$")) {
//            if(Constant.isRuntimeEnvTest()){
//                return "http://ad.bbtree.com/";
//            }
            return "http://filesystem1.bbtree.com/";
        }
        if (StringUtils.startsWithIgnoreCase(pic, "a")) {
            return "http://oss.aliyuncs.com/bbtree-filesystem/";

        }
        if (pic.contains("roup")) {
            String group = pic.split("/")[0];
            int fileNumber = Integer.valueOf(group.charAt(group.length() - 1) + "");
            if (fileNumber == 1) {
                return "http://file.bbtree.com/";
            }
            return "http://file" + fileNumber + ".bbtree.com/";
        } else if (pic.startsWith("/public")) {
            if (pic.contains("unimg.bbtree.com")) {
                return "http://sunimg.bbtree.com";
            }
            return "http://filesystem1.bbtree.com/";
        }
        return null;
    }

    public static String getImgUrl(String pic) {
        /*if (StringUtils.startsWithIgnoreCase(pic, "a")) {
            return "http://oss.aliyuncs.com/bbtree-filesystem/" + pic;
        } else {*/
            return "http://filesystem1.bbtree.com/" + pic;
//        }
    }

    public static String getImageDomain(String imgPath, boolean isOld) {
        imgPath = StringUtils.removeEnd(imgPath, "/");
        if ((imgPath.contains("http://") && imgPath.indexOf("http://") == 0) || (imgPath.contains("https://") && imgPath.indexOf("https://") == 0)) {
            if (!imgPath.contains("zhgAppImg:")) {
                return StringUtils.substring(imgPath, 10);
            } else {
                return imgPath;
            }
        }
        String[] imgPathArr = imgPath.split("\\/");
        if (imgPathArr.length > 0 && ("file" == imgPathArr[0] || "head" == imgPathArr[0])) {
            return "http://aibaobei.oss.aliyuncs.com/" + imgPath;
        }
        try {
            int head = Integer.valueOf(imgPathArr[0]);
            if (head > 2015 && imgPath.contains("@")) {
                return "http://filesystem1.bbtree.com/" + imgPath;
            }
            return "http://filesystem1.bbtree.com/" + imgPath;
        } catch (Exception e) {
        }
        if (imgPath.indexOf("roup") == 1) {
            String fileNumber = StringUtils.substring(imgPathArr[0], "group".length());
            int fileNumberInt = Integer.valueOf(fileNumber);
            if (fileNumberInt == 1) {
                return "http://file.bbtree.com/" + imgPath;
            } else {
                return "http://file" + fileNumberInt + ".bbtree.com/" + imgPath;
            }
        } else if (imgPath.indexOf("public") == 1) {
            if (isOld) {
                return "http://sunimg.bbtree.com/" + imgPath;
            } else {
                return "http://filesystem1.bbtree.com/" + imgPath;
            }
        } else {
            if (isOld) {
                return "http://sunimg.bbtree.com/" + imgPath;
            } else {
                return "http://filesystem1.bbtree.com/" + imgPath;
            }
        }
    }

    public static String getPicUrl(String pic) {
        if (StringUtils.isEmpty(pic)) {
            return "";
        }
        if (pic.startsWith("http")) {
            return pic;
        }
        String picDomain = getPicFileDomain(pic);
        if (StringUtils.isEmpty(picDomain)) {
            return "";
        }
        return picDomain + pic;
    }

    public static void main(String[] args) {
        String respCode = "\"11\"";
         respCode = respCode.replaceAll("\"","");
        System.out.println(respCode);

    }
}
