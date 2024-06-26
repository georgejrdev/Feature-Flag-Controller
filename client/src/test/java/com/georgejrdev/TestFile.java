package com.georgejrdev;

import java.util.List;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import com.georgejrdev.utils.support.Analyzer;
import com.georgejrdev.utils.support.File;
import com.georgejrdev.utils.support.Flag;

import org.junit.Before;


public class TestFile {
    private File file;
    private Analyzer analyzer;

    @Before
    public void setUp(){
        analyzer = new Analyzer();
        file = new File(analyzer);
    }


    @Test
    public void testReadFile() throws Exception{
        List<Flag> flags = file.readFile("src/test/java/com/georgejrdev/resources/testExample.java");
        assertEquals(2,flags.size());
    }
}