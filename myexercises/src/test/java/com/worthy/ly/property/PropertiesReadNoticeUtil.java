package com.worthy.ly.property;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ly on 2018/3/7.
 */
public class PropertiesReadNoticeUtil {

    public static void getLines(File file) {

        FileInputStream fis = null;
        InputStreamReader isr = null;
        BufferedReader bufferedReader = null;

        try {
            fis = new FileInputStream(file);
            isr = new InputStreamReader(fis, "UTF-8");
            bufferedReader = new BufferedReader(isr);

            List<String> common3TypeList = new ArrayList<>();
            List<String> par1TypeList = new ArrayList<>();
            List<String> vote2TypeList = new ArrayList<>();

            String line;
            while ((line = bufferedReader.readLine())!=null) {
                System.out.println(line);
                String[] split = line.split("|");
                StringBuilder builder = new StringBuilder();
                boolean flag = false;
                String type = "";
                for (int i=0; i<split.length; i++) {
                    if (flag) {
                        type = split[split.length-1];
                        break;
                    }
                    if (!"|".equals(split[i])) {
                        builder.append(split[i]);
                    } else {
                        flag = true;
                        continue;
                    }
                }

                if ("3".equals(type)) {
                    common3TypeList.add(builder.toString());
                } else if ("2".equals(type)) {
                    vote2TypeList.add(builder.toString());
                } else if ("1".equals(type)) {
                    par1TypeList.add(builder.toString());
                }
            }
            System.out.println("type=1:");
            for (String i: par1TypeList){
                System.out.println(i);
            }
            System.out.println("type=2:");
            for (String i: vote2TypeList){
                System.out.println(i);
            }
            System.out.println("type=3:");
            for (String i: common3TypeList){
                System.out.println(i);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (isr != null) {
                try {
                    isr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public static void main(String[] args) throws IOException {
        //use . to get current directory
        File dir = new File(".");
        System.out.println(dir.getCanonicalPath());
        System.out.println(File.separator);
        File file = new File(dir.getCanonicalPath() + File.separator + "myexercises" + File.separator + "src"
                + File.separator + "test" + File.separator + "resource" + File.separator + "notice.properties");
        System.out.println(file.getCanonicalPath());
        System.out.println("***************************");
        getLines(file);
    }

}
