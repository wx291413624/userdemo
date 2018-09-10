package com.example.userdemo.rmic;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @Author: 夜凉如水般清澈
 * 18-5-10 下午2:56
 * Fear can hold you prisoner, hope can set you free. A strong man can save himself
 */
public interface SayHello extends Remote {
    public String say() throws RemoteException;
}
