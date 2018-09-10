package com.example.userdemo.util.zxing;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

/**
 * @Author: 夜凉如水般清澈
 * @Date: 18-4-24 上午11:01
 * Fear can hold you prisoner, hope can set you free. A strong man can save himself
 */
public class ZxingTest {
    public static void main(String[] args) throws IOException, WriterException {

        String 投诉 = "http://food.xiongmaoxingchu.com/qrcode/loading/index.html?code=1";
        String 意见 = "http://food.xiongmaoxingchu.com/qrcode/loading/index.htmll?code=2";
        String 其它 = "http://food.xiongmaoxingchu.com/qrcode/loading/index.html?code=3";

        // 二维码图片宽度
        int width = 500;
        // 二维码图片高度
        int height = 500;
        // 二维码的图片格式
        String format = "jpg";

        Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
        // 内容所使用字符集编码
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        BitMatrix bitMatrix = new MultiFormatWriter().encode(投诉, BarcodeFormat.QR_CODE, width, height, hints);
        // 生成二维码
        File outputFile = new File("./" + File.separator + "new.jpg");
        MatrixToImageWriter.writeToFile(bitMatrix, format, outputFile);
    }
}
