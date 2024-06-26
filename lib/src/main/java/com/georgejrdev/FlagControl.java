package com.georgejrdev;

import com.georgejrdev.utils.Request;
import com.georgejrdev.utils.Interfaces.InterfaceFlagControl;;


public class FlagControl implements InterfaceFlagControl{

    private Request req;

    public FlagControl(){
        this.req = new Request();
    }


    @Override
    public boolean setNewFeatureFlag(String env, String shortDescription) throws Exception{

        String variable = System.getenv(env);

        if (variable == null){
            throw new Exception("Variable not found");
        }

        boolean result = Boolean.parseBoolean(variable.replaceAll("[\\[\\](){}]", "").trim());

        return result;
    }


    @Override
    public boolean setNewFeatureFlag(String url, String method, String shortDescription){

        String response = req.makeRequest(url, method);
        boolean result = Boolean.parseBoolean(response.replaceAll("[\\[\\](){}]", "").trim());

        return result;
    }
}