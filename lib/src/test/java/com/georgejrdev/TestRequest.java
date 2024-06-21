package com.georgejrdev;

import java.io.File;
import java.io.IOException;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import com.georgejrdev.utils.Request;


public class TestRequest {

    private static Process flaskProcess;
    private Request request;


    @BeforeClass
    public static void setUpClass() throws IOException, InterruptedException {
        String baseDirectory = System.getProperty("user.dir");
        String appDirectory = baseDirectory + "/../api-test/";
        String[] command = {"python3", "app.py"};

        ProcessBuilder pb = new ProcessBuilder(command);
        pb.directory(new File(appDirectory));
        pb.redirectErrorStream(true);

        flaskProcess = pb.start();
        Thread.sleep(5000);
    }


    @AfterClass
    public static void tearDownClass() {
        flaskProcess.destroy();
    }


    @Before
    public void setUp(){
        request = new Request();
    }

    
    @Test
    public void testGetRequest(){
        String result = request.makeRequest("http://127.0.0.1:3030/getTrue", "GET");
        assertEquals("true",result);
    }
}