package com.georgejrdev.utils.support;

import java.util.List;
import java.util.ArrayList;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.georgejrdev.utils.interfaces.InterfaceFile;


public class File implements InterfaceFile{
    private final String FLAG_SYNTAX = "setNewFeatureFlag";
    private Analyzer analyzer;

    public File(Analyzer analyzer){
        this.analyzer = analyzer;
    }

    
    @Override
    public List<Flag> readFile(String path) throws Exception{

        List<Flag> flags = new ArrayList<Flag>();

        List<String> lines = Files.readAllLines(Paths.get(path));

        for (int i=0; i<lines.size(); i++){
            if (lines.get(i).contains(FLAG_SYNTAX)){

                String type = analyzer.getType(lines.get(i));
                String description = analyzer.getDescription(lines.get(i)) ;

                Flag flag = new Flag(path,type,description,i+1);
                flags.add(flag);
            }
        }

        return flags;
    }
}