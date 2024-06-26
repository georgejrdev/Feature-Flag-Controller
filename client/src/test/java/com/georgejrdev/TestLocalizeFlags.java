package com.georgejrdev;

import java.util.List;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import com.georgejrdev.utils.LocalizeFlags;
import com.georgejrdev.utils.support.Analyzer;
import com.georgejrdev.utils.support.File;
import com.georgejrdev.utils.support.Flag;

import org.junit.Before;


public class TestLocalizeFlags {
    private LocalizeFlags localizeAllFlags;
    private File file;
    private Analyzer analyzer;

    @Before
    public void setUp(){
        analyzer = new Analyzer();
        file = new File(analyzer);
        localizeAllFlags = new LocalizeFlags(file);
    }


    @Test
    public void testLocalizeAllFlags() throws Exception{
        String fold = "src/test/java/com/georgejrdev/resources";

        List<Flag> allFlags = localizeAllFlags.searchAllFlags(fold);

        for (Flag flag : allFlags){
            @SuppressWarnings("unused")
            String path = flag.getFileName();
            @SuppressWarnings("unused")
            String type = flag.getType();
            @SuppressWarnings("unused")
            String description = flag.getDescription();
            @SuppressWarnings("unused")
            int line = flag.getLine();
        }

        assertEquals(6, allFlags.size());
    }
}