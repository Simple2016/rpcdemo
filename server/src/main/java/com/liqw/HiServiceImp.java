package com.liqw;

public class HiServiceImp implements HiService {

    @Override
    public String sayHi(String content) {
        return "hi! " +content;
    }
}
