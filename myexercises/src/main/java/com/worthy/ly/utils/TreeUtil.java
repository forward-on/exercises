package com.worthy.ly.utils;

import com.worthy.ly.model.Module;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ly on 2018/1/4.
 */
public class TreeUtil {

    /**
     * 获取map形式的模板树
     * @param modules
     * @return
     */
    public static Map<String, ArrayList<Module>> buildTreeMap(List<Module> modules) {
        Map<String, ArrayList<Module>> treeMap = new HashMap<>();
        for (Module module : modules) {
            ArrayList<Module> treeNodes = treeMap.get(module.getpCode());
            if (null == treeNodes) {
                treeNodes = new ArrayList<>();
            }
            treeNodes.add(module);
            treeMap.put(module.getpCode(), treeNodes);
        }
        return treeMap;
    }

}
