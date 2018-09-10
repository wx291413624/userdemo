package com.example.userdemo.notes.rtTest.java.langtest.instrumenttest;

import javassist.*;

import java.io.IOException;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;

/**
 * @Author: 夜凉如水般清澈
 * 18-5-8 下午1:47
 * JVM运行的是二进制文件，我们可以通过instrument包的使用，在JVM运行之前修改JVM本要运行的二进制文件。
 * Fear can hold you prisoner, hope can set you free. A strong man can save himself
 */
public class MyClassFileTransformer implements ClassFileTransformer {

    /**
     * 字节码加载到虚拟机前会进入这个方法
     */
    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined,
                            ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
        System.out.println("className = " + className);
        //javassist的包名是用点分割的，需要转换下
        if (className.indexOf("/") != -1) {
            className = className.replaceAll("/", ".");
        }
        try {
            //通过包名获取类文件
            CtClass cc = ClassPool.getDefault().get(className);
            //获得指定方法名的方法
            //在方法执行前插入代码
            CtMethod m = cc.getDeclaredMethod("doSomething");
            m.insertBefore("{System.out.println(\"记录日志\"); }");
            return cc.toBytecode();
        } catch (NotFoundException e) {
        } catch (CannotCompileException e) {
        } catch (IOException e) {
            //忽略异常处理
        }
        return new byte[0];
    }

    public static void premain(String options, Instrumentation ins) {
        //注册我自己的字节码转换器
        ins.addTransformer(new MyClassFileTransformer());
    }
}
