package com.liqw;

import java.io.IOException;

/**
 * Hello world!
 */
public class Client {
    public static void main(String[] args) throws IOException {
        RpcProxyClient rpcProxyClient = new RpcProxyClient("localhost",8081);
        HelloService proxy = rpcProxyClient.proxy(HelloService.class);
        System.out.println(proxy.sayHello("li qingwei"));
        HiService proxy1 = rpcProxyClient.proxy(HiService.class);
        System.out.println(proxy1.sayHi("li qingwei"));
    }
}
