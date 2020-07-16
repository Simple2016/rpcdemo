package com.liqw;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class RemoteInvocationHandler implements InvocationHandler {
    String host;
    int port;

    public RemoteInvocationHandler(String host, int port) {
        this.host = host;
        this.port = port;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] params) throws Throwable {
        RpcRequest rpcRequest = new RpcRequest();
        rpcRequest.setClazz(method.getDeclaringClass());
        rpcRequest.setMethodName(method.getName());
        rpcRequest.setParams(params);
        rpcRequest.setTypes(method.getParameterTypes());
        RpcNetTransport transport = new RpcNetTransport(host, port);

        return transport.send(rpcRequest);
    }
}
