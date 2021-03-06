//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.worthy.ueditor.upload;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import com.worthy.ueditor.PathFormat;
import com.worthy.ueditor.define.BaseState;
import com.worthy.ueditor.define.FileType;
import com.worthy.ueditor.define.State;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class BinaryUploader {
    public BinaryUploader() {
    }

    public static final State save(HttpServletRequest request, Map<String, Object> conf) {
        FileItemStream fileStream = null;
        boolean isAjaxUpload = request.getHeader("X_Requested_With") != null;
        if(!ServletFileUpload.isMultipartContent(request)) {
            return new BaseState(false, 5);
        } else {
            ServletFileUpload upload = new ServletFileUpload(new DiskFileItemFactory());
            if(isAjaxUpload) {
                upload.setHeaderEncoding("UTF-8");
            }

            try {
                for(FileItemIterator iterator = upload.getItemIterator(request); iterator.hasNext(); fileStream = null) {
                    fileStream = iterator.next();
                    if(!fileStream.isFormField()) {
                        break;
                    }
                }

                if(fileStream == null) {
                    return new BaseState(false, 7);
                } else {
                    String savePath = (String)conf.get("savePath");
                    String originFileName = fileStream.getName();
                    String suffix = FileType.getSuffixByFilename(originFileName);
                    originFileName = originFileName.substring(0, originFileName.length() - suffix.length());
                    savePath = savePath + suffix;
                    long maxSize = ((Long)conf.get("maxSize")).longValue();
                    if(!validType(suffix, (String[])((String[])conf.get("allowFiles")))) {
                        return new BaseState(false, 8);
                    } else {
                        savePath = PathFormat.parse(savePath, originFileName);
                        (new StringBuilder()).append((String)conf.get("rootPath")).append(savePath).toString();
                        State storageState = StorageManager.saveFileOSSByInputStream(fileStream.openStream(), fileStream.getContentType(), savePath, maxSize);
                        if(storageState.isSuccess()) {
                            storageState.putInfo("type", suffix);
                            storageState.putInfo("original", originFileName + suffix);
                        }

                        return storageState;
                    }
                }
            } catch (FileUploadException var13) {
                return new BaseState(false, 6);
            } catch (IOException var14) {
                return new BaseState(false, 4);
            }
        }
    }

    private static boolean validType(String type, String[] allowTypes) {
        List<String> list = Arrays.asList(allowTypes);
        return list.contains(type);
    }
}
