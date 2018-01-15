package com.worthy.ly;

import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Hello world!
 *
 */
public class App {

    @Test
    public void test(){
        System.out.println("********");
    }

    /**
     * 去除富文本内容的html标签
     *
     * @param content
     * @return
     */
    public static String stripHtml(String content) {
        // <p>段落替换为换行
        content = content.replaceAll("<p .*?>", "\r\n");
        // <br><br/>替换为换行
        content = content.replaceAll("<br\\s*/?>","\r\n");
        // 去掉其它的<>之间的东西
        content = content.replaceAll("\\<.*?>", "");

        content.replaceAll("<a>\\s*|\t|\r|\n</a>", "");

        //content.replaceAll("</?[^>]+>", "");

        return content;
    }


    //  <.+?>
    public static void main(String[] args) throws UnsupportedEncodingException {
        String urlContent = "/next.jsp?param1=hendhs89&furej&param2=sss";
        String encode = URLEncoder.encode(urlContent, "UTF-8");
        System.out.println(encode);
    }
}
