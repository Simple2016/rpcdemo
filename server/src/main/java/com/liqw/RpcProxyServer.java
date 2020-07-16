package com.liqw;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RpcProxyServer {
    private int port;

    private ExecutorService executorService = Executors.newCachedThreadPool();
    private Map<String, Object> services=new LinkedHashMap<>();
    public RpcProxyServer(int port) {
        this.port = port;
    }

    public void start() {
        ServerSocket socket = null;
        try {
            socket = new ServerSocket(port);
            while (true) {
                Socket accept = socket.accept();
                ProccessHandler command = new ProccessHandler(accept,services);
                executorService.execute(command);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void publisher(Class interfaceCLs,Object service) {

        services.put(interfaceCLs.getName(),service);
    }

}
