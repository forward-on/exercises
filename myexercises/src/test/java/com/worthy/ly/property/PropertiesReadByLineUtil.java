package com.worthy.ly.property;

import java.io.*;

/**
 * Created by ly on 2018/3/7.
 */
public class PropertiesReadByLineUtil {

    public static void getLines(File file) {

        FileInputStream fis = null;
        InputStreamReader isr = null;
        BufferedReader bufferedReader = null;

        try {
            fis = new FileInputStream(file);
            isr = new InputStreamReader(fis, "UTF-8");
            bufferedReader = new BufferedReader(isr);

            String line;
            while ((line = bufferedReader.readLine())!=null) {
                System.out.println(line);
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
                + File.separator + "test" + File.separator + "resource" + File.separator + "mobile.properties");
        System.out.println(file.getCanonicalPath());
        System.out.println("***************************");
        getLines(file);
    }

}
