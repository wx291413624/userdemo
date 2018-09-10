package com.example.userdemo.notes.charsetsTest;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.util.SortedMap;

/**
 * charsets.jar  来处理字节序列和字符序列之间的转换关系，该类包含了用于创建解码器和编码器的方法，还提供了Charset所支持的字符集的方法，Charset类是不可变的。
 *    Big5   大五码 是通行于台湾、香港地区的一个繁体字编码方案 是我国早期中文电脑的业界标准，也是中文社群最常用的电子汉字字集标准
 *    Big5_HKSCS  这是香港用的编码
 *    Big5_HKSCS_2001
 *    Big5_Solaris
 */
public class test {
    public static void main(String[] args) throws CharacterCodingException {

        SortedMap<String,Charset> sm= Charset.availableCharsets();
        for(String str:sm.keySet())
        {
            System.out.println(sm.get(str));
        }//获取所有支持的编码集合


        //获取编码工具
        Charset cs=Charset.forName("GBK");

        //char to byte  OR   byte to char
        CharsetDecoder cd=cs.newDecoder();
        CharsetEncoder ce=cs.newEncoder();
        CharBuffer cb=CharBuffer.allocate(6);
        cb.put("张");
        cb.put("译");
        cb.put("成");
        cb.flip();
        ByteBuffer bb=ce.encode(cb);

        for(int i=0;i<bb.capacity();i++)
        {
            System.out.println(bb.get(i));
        }
        System.out.println(cd.decode(bb));

    }
}
