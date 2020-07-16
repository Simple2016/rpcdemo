package com.liqw;

import java.lang.reflect.Proxy;

public class RpcProxyClient {
    private String host;
    private int port;

    public RpcProxyClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public <T> T proxy(Class<T> interfaceCls) {
        RemoteInvocationHandler processHandler = new RemoteInvocationHandler(host, port);
        return (T) Proxy.newProxyInstance(interfaceCls.getClassLoader(), new Class[]{interfaceCls}, processHandler);
    }
}
