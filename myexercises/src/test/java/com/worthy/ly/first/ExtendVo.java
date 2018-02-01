package com.worthy.ly.first;

import java.util.LinkedHashMap;

/**
 * Created by ly on 2018/1/30.
 */
public class ExtendVo {

    private int isMultiple = 0;

    private int isShow = 0;

    private int type = 0;

    private LinkedHashMap<String,Object> choice;

    public int getIsMultiple() {
        return isMultiple;
    }

    public void setIsMultiple(int isMultiple) {
        this.isMultiple = isMultiple;
    }

    public int getIsShow() {
        return isShow;
    }

    public void setIsShow(int isShow) {
        this.isShow = isShow;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public LinkedHashMap<String, Object> getChoice() {
        return choice;
    }

    public void setChoice(LinkedHashMap<String, Object> choice) {
        this.choice = choice;
    }


}
