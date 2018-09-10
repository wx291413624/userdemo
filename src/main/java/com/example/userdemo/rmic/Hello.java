package com.example.userdemo.rmic;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * @Author: 夜凉如水般清澈
 * 18-5-10 下午2:56
 * Fear can hold you prisoner, hope can set you free. A strong man can save himself
 */
public class Hello extends UnicastRemoteObject implements SayHello {
    public Hello() throws RemoteException {
        super();
    }

    @Override
    public String say() throws RemoteException {
        return null;
    }
}
