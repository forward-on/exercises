//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.worthy.ueditor.upload;

import com.aliyun.oss.model.ObjectMetadata;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import com.worthy.ueditor.conf.OssConf;
import com.worthy.ueditor.define.BaseState;
import com.worthy.ueditor.define.FileType;
import com.worthy.ueditor.define.State;
import com.worthy.ueditor.oss.OssUtil;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StorageManager {
    public static final int BUFFER_SIZE = 8192;
    private static final Logger logger = LoggerFactory.getLogger(StorageManager.class);

    public StorageManager() {
    }

    public static State saveBinaryFile(byte[] data, String path) {
        File file = new File(path);
        State state = valid(file);
        if(!state.isSuccess()) {
            return state;
        } else {
            try {
                BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
                bos.write(data);
                bos.flush();
                bos.close();
            } catch (IOException var5) {
                return new BaseState(false, 4);
            }

            state = new BaseState(true, file.getAbsolutePath());
            state.putInfo("size", (long)data.length);
            state.putInfo("title", file.getName());
            return state;
        }
    }

    public static State saveFileByInputStream(InputStream is, String path, long maxSize) {
        State state = null;
        File tmpFile = getTmpFile();
        byte[] dataBuf = new byte[2048];
        BufferedInputStream bis = new BufferedInputStream(is, 8192);

        try {
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(tmpFile), 8192);
            boolean var9 = false;

            int count;
            while((count = bis.read(dataBuf)) != -1) {
                bos.write(dataBuf, 0, count);
            }

            bos.flush();
            bos.close();
            if(tmpFile.length() > maxSize) {
                tmpFile.delete();
                return new BaseState(false, 1);
            } else {
                state = saveTmpFile(tmpFile, path);
                if(!state.isSuccess()) {
                    tmpFile.delete();
                }

                return state;
            }
        } catch (IOException var10) {
            return new BaseState(false, 4);
        }
    }

    public static State saveFileByInputStream(InputStream is, String path) {
        State state = null;
        File tmpFile = getTmpFile();
        byte[] dataBuf = new byte[2048];
        BufferedInputStream bis = new BufferedInputStream(is, 8192);

        try {
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(tmpFile), 8192);
            boolean var7 = false;

            int count;
            while((count = bis.read(dataBuf)) != -1) {
                bos.write(dataBuf, 0, count);
            }

            bos.flush();
            bos.close();
            state = saveTmpFile(tmpFile, path);
            if(!state.isSuccess()) {
                tmpFile.delete();
            }

            return state;
        } catch (IOException var8) {
            return new BaseState(false, 4);
        }
    }

    private static File getTmpFile() {
        File tmpDir = FileUtils.getTempDirectory();
        String tmpFileName = (Math.random() * 10000.0D + "").replace(".", "");
        return new File(tmpDir, tmpFileName);
    }

    private static File getTmpFile(String suffix) {
        File tmpDir = FileUtils.getTempDirectory();
        String tmpFileName = (Math.random() * 10000.0D + "").replace(".", "");
        return new File(tmpDir, tmpFileName + suffix);
    }

    private static State saveTmpFile(File tmpFile, String path) {
        State state = null;
        File targetFile = new File(path);
        if(targetFile.canWrite()) {
            return new BaseState(false, 2);
        } else {
            try {
                FileUtils.moveFile(tmpFile, targetFile);
            } catch (IOException var5) {
                return new BaseState(false, 4);
            }

            state = new BaseState(true);
            state.putInfo("size", targetFile.length());
            state.putInfo("title", targetFile.getName());
            return state;
        }
    }

    private static State valid(File file) {
        File parentPath = file.getParentFile();
        return !parentPath.exists() && !parentPath.mkdirs()?new BaseState(false, 3):(!parentPath.canWrite()?new BaseState(false, 2):new BaseState(true));
    }

    public static State saveFileOSSByInputStream(InputStream is, String contentType, String savePath, long maxSize) {
        State state = null;
        String suffix = FileType.getSuffixByFilename(savePath);
        File tmpFile = getTmpFile(suffix);

        BaseState var12;
        try {
            byte[] dataBuf = new byte[2048];
            BufferedInputStream bis = new BufferedInputStream(is, 8192);
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(tmpFile), 8192);
            boolean var11 = false;

            int count;
            while((count = bis.read(dataBuf)) != -1) {
                bos.write(dataBuf, 0, count);
            }

            bos.flush();
            bos.close();
            if(tmpFile.length() <= maxSize && tmpFile.length() <= OssConf.getUploadMaxSize()) {
                savePath = savePath.replaceFirst("/", "");
                ObjectMetadata meta = new ObjectMetadata();
                meta.setContentLength(tmpFile.length());
                meta.setContentType(contentType);
                OssUtil.uploadImg(savePath, tmpFile, meta);
                state = new BaseState(true);
                state.putInfo("size", tmpFile.length());
                state.putInfo("url", String.format(OssConf.getDomain(), new Object[]{savePath}));
                if(state.isSuccess()) {
                    state.putInfo("title", tmpFile.getName());
                }

                BaseState var14 = (BaseState) state;
                return var14;
            }

            var12 = new BaseState(false, 1);
        } catch (Exception var25) {
            logger.error("upload file to oss error.", var25);
            return new BaseState(false, 4);
        } finally {
            tmpFile.delete();
            if(is != null) {
                try {
                    is.close();
                } catch (IOException var24) {
                    ;
                }
            }

        }

        return var12;
    }
}
