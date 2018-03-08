package com.worthy.ly.property;

import java.io.*;

/**
 * Created by ly on 2018/3/7.
 */
public class PropertiesWriterUtil {

    public static void wirteFile(File targetFile, String text) {

        FileOutputStream fos = null;
        OutputStreamWriter osr = null;
        BufferedWriter bufferedWriter = null;

        try {
            fos = new FileOutputStream(targetFile);
            osr = new OutputStreamWriter(fos, "UTF-8");
            bufferedWriter = new BufferedWriter(osr);

            bufferedWriter.write(text);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferedWriter != null) {
                try {
                    bufferedWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (osr != null) {
                try {
                    osr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public static void main(String[] args) throws IOException {
        //use . to get current directory
        File dir = new File(".");
        File file = new File(dir.getCanonicalPath() + File.separator + "myexercises" + File.separator + "src"
                + File.separator + "test" + File.separator + "resource" + File.separator + "number.properties");
        String text = "a c b  e d s  g r h\nlief  afe \n  aiega 螺蛳粉IE阿分裂  就发泄阿里";
        wirteFile(file, text);
    }

}
