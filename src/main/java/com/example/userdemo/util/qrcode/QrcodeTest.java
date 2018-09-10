//package com.example.userdemo.util.qrcode;
//
//import javax.imageio.ImageIO;
//import java.awt.image.BufferedImage;
//import java.io.FileOutputStream;
//import java.io.OutputStream;
//
///**
// * @Author: 夜凉如水般清澈
// * @Date: 18-4-24 上午10:22
// * Fear can hold you prisoner, hope can set you free. A strong man can save himself
// */
//public class QrcodeTest {
//
//
//    public void insertQRcode() {
//        try {
//            String 投诉详情 = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx071b870c043bdaf2&redirect_uri=http%3A%2F%2Fshop.xiongmaoxingchu.com%2Fwechat%2Frepair%2Frepair%2Findex.html&response_type=code&scope=snsapi_userinfo&state=charts&connect_redirect=1#wechat_redirect";
//
//            String 意见反馈 = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx071b870c043bdaf2&redirect_uri=http%3A%2F%2Fshop.xiongmaoxingchu.com%2Fwechat%2Frepair%2Frepair%2Fhtml%2Ffeedback.html&response_type=code&scope=snsapi_userinfo&state=charts&connect_redirect=1#wechat_redirect";
//
//            String 其它问题 = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx071b870c043bdaf2&redirect_uri=http%3A%2F%2Fshop.xiongmaoxingchu.com%2Fwechat%2Frepair%2Frepair%2Fhtml%2FotherProblems.html&response_type=code&scope=snsapi_userinfo&state=charts&connect_redirect=1#wechat_redirect";
//
//            String 投诉 = "http://food.xiongmaoxingchu.com/qrcode/loading/index.html?code=1";
//            String 意见 = "http://food.xiongmaoxingchu.com/qrcode/loading/index.htmll?code=2";
//            String 其它 = "http://food.xiongmaoxingchu.com/qrcode/loading/index.html?code=3";
//            TwoDimensionCode twoDimensionCode = new TwoDimensionCode();
//            BufferedImage png = twoDimensionCode.qRCodeCommon(投诉,
//                    "png", 5);
//            OutputStream out2 = null;
//            out2 = new FileOutputStream("./bai.png");
//            ImageIO.write(png, "png", out2);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static void main(String[] args) {
//        QrcodeTest qrcodeTest = new QrcodeTest();
//        qrcodeTest.insertQRcode();
//    }
//}
