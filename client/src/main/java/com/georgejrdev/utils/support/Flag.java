package com.georgejrdev.utils.support;


public class Flag {

    private String fileName;
    private String type;
    private String description;
    private int line;

    public Flag(String path, String type, String descrption, int line){
        setFileName(path);
        setType(type);
        setDescription(descrption);
        setLine(line);
    }


    public void setFileName(String path){
        String fileName = path.substring(path.lastIndexOf("/") + 1);
        this.fileName = fileName;
    }
    

    public String getFileName(){
        return this.fileName;
    }


    public void setType(String type){
        this.type = type;
    }


    public String getType(){
        return this.type;
    }


    public void setDescription(String description){
        this.description = description;
    }



    public String getDescription(){
        return this.description;
    }


    public void setLine(int line){
        this.line = line;
    }


    public int getLine(){
        return this.line;
    }
}