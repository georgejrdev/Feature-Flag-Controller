package com.georgejrdev;

import com.georgejrdev.screen.Frame;
import com.georgejrdev.utils.LocalizeFlags;
import com.georgejrdev.utils.support.Analyzer;
import com.georgejrdev.utils.support.File;


public class Main {
    
    public static void main(String[] args) throws Exception {

        Analyzer analyzer = new Analyzer();

        File file = new File(analyzer);
        
        LocalizeFlags localizeFlags = new LocalizeFlags(file);

        new Frame(localizeFlags);
    }
}
