package com.example.userdemo.wechat.config;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class StringBufferUtil {


    public static String readBuffer(InputStream inputStream) {
        BufferedReader br = null;
        StringBuffer sReturnBuf = new StringBuffer();
        try {
            br = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            String readStr = null;
            readStr = br.readLine();
            while (readStr != null) {
                sReturnBuf.append(readStr);
                readStr = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                    br = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                    inputStream = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return sReturnBuf.toString();
    }
}
