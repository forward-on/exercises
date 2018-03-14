//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.worthy.ueditor.upload;

import java.util.Map;

import com.worthy.ueditor.PathFormat;
import com.worthy.ueditor.define.BaseState;
import com.worthy.ueditor.define.FileType;
import com.worthy.ueditor.define.State;
import org.apache.commons.codec.binary.Base64;

public final class Base64Uploader {
    public Base64Uploader() {
    }

    public static State save(String content, Map<String, Object> conf) {
        byte[] data = decode(content);
        long maxSize = ((Long)conf.get("maxSize")).longValue();
        if(!validSize(data, maxSize)) {
            return new BaseState(false, 1);
        } else {
            String suffix = FileType.getSuffix("JPG");
            String savePath = PathFormat.parse((String)conf.get("savePath"), (String)conf.get("filename"));
            savePath = savePath + suffix;
            String physicalPath = (String)conf.get("rootPath") + savePath;
            State storageState = StorageManager.saveBinaryFile(data, physicalPath);
            if(storageState.isSuccess()) {
                storageState.putInfo("url", PathFormat.format(savePath));
                storageState.putInfo("type", suffix);
                storageState.putInfo("original", "");
            }

            return storageState;
        }
    }

    private static byte[] decode(String content) {
        return Base64.decodeBase64(content);
    }

    private static boolean validSize(byte[] data, long length) {
        return (long)data.length <= length;
    }
}
