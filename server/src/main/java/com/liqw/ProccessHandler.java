package com.liqw;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;
import java.util.Map;

public class ProccessHandler implements Runnable {
    private Socket socket;
    private Map<String, Object> services;

    public ProccessHandler(Socket socket, Map<String, Object> services) {
        this.socket = socket;
        this.services = services;
    }

    @Override
    public void run() {
        ObjectInputStream objectInputStream = null;
        ObjectOutputStream objectOutputStream = null;
        Object result = null;
        try {
            objectInputStream = new ObjectInputStream(socket.getInputStream());
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            RpcRequest rpcRequest = (RpcRequest) objectInputStream.readObject();
            result = invoke(rpcRequest);
            objectOutputStream.writeObject(result);
            objectOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (objectInputStream != null) {
                    objectInputStream.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                if (objectOutputStream != null) {
                    objectOutputStream.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }


    private Object invoke(RpcRequest rpcRequest) {
        Object result = null;
        try {
            Class<?> aClass = Class.forName(rpcRequest.getClazz().getName());
            Method method = aClass.getMethod(rpcRequest.getMethodName(), rpcRequest.getTypes());
            result = method.invoke(services.get(rpcRequest.getClazz().getName()), rpcRequest.getParams());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return result;
    }
}
