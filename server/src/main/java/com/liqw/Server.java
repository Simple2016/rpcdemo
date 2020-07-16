package com.liqw;

/**
 * Hello world!
 */
public class Server {
    public static void main(String[] args) {
        RpcProxyServer rpcProxyServer = new RpcProxyServer(8081);
        rpcProxyServer.publisher(HelloService.class, new HelloServiceImp());
        rpcProxyServer.publisher(HiService.class, new HiServiceImp());
        rpcProxyServer.start();
    }
}
