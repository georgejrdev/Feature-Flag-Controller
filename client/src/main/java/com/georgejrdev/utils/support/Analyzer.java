package com.georgejrdev.utils.support;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.georgejrdev.utils.interfaces.InterfaceAnalyzer;


public class Analyzer implements InterfaceAnalyzer{
    
    @Override
    public String getType(String phrase) throws Exception{

        List<String> parameter = getParams(phrase);
        String type;

        switch (parameter.size()) {
            case 2:
                type = "env";
                break;
        
            case 3:
                type = "api";
                break;

            default:
                type = "undefined or method declaration";
        }

        return type;
    }


    @Override
    public String getDescription(String phrase){
        List<String> parameter = getParams(phrase);

        if (parameter.isEmpty()){
            return "undefined or method declaration";
        }
        
        return parameter.get(parameter.size()-1);
    }

    private List<String> getParams(String phrase){
        List<String> parameters = new ArrayList<String>();

        Pattern pattern = Pattern.compile("setNewFeatureFlag\\((.*?)\\)");
        Matcher matcher = pattern.matcher(phrase);

        if (matcher.find()) {
            String params = matcher.group(1);
            for (String param : params.split(",\\s*")) {
                parameters.add(param.trim());
            }
        }
        return parameters;
    }
}
