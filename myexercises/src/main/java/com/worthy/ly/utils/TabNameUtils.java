package com.worthy.ly.utils;

/**
 * Created by qijing0802 on 2017/7/25.
 */
public class TabNameUtils {

    public static final int TABLE_NUM = 100;

    public static int getTbNameBySchoolId(Integer schoolId){
        return schoolId%TABLE_NUM;
    }

}
