package com.worthy.ueditor.define;

/**
 * Created by ly on 2018/3/14.
 */
public interface State {
    boolean isSuccess();

    void putInfo(String var1, String var2);

    void putInfo(String var1, long var2);

    String toJSONString();
}
