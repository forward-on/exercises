package com.worthy.ly.first;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Unit test for simple App.
 */
public class AppTest {

    @Test
    public void testDate() throws ParseException {
        SimpleDateFormat format =  new SimpleDateFormat("MM-dd HH:mm:ss");

        Long time = new Long(1515751666009L);

        String d = format.format(time);

        System.out.println(d);

        Date date = format.parse(d);

        System.out.println(date);
    }

    @Test
    public void dealRich(){
        String rich = "她在看电视时你就可以理解你就知道自己有个&nbsp;<span id=\"selectionBoundary_1516267360791_7149137350011169\" class=\"rangySelectionBoundary\" style=\"line-height: 0; display: none;\">\uFEFF</span>\n" +
                "\n" +
                "<a href=\"http://filesystem1.bbtree.com/2018/01/18/658ed8b837fec76463f420c77e1e8f4c/ios/1516267372110990.webp\"><img src=\"http://filesystem1.bbtree.com/2018/01/18/658ed8b837fec76463f420c77e1e8f4c/ios/1516267372110990.webp\" alt=\"\" width=\"828\" height=\"1104\" class=\"alignnone size-full wp-image-2910941087\"></a>&nbsp;";
        String content = rich.replaceAll("<(/p|br/?)>", "\n")
                .replaceAll("&nbsp;", " ")
                .replaceAll("<[^<>]+>", "")
                .replaceAll("&lt;", "<")
                .replaceAll("&gt;", ">");
        System.out.println(content);
    }

}
