package com.liqw;

public class HelloServiceImp implements HelloService {
    @Override
    public String sayHello(String content) {
        return "hello! "+ content;
    }
}
