package com.georgejrdev;

import java.io.File;
import java.io.IOException;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;


public class TestFlagControl {
    
    private static Process flaskProcess;
    private FlagControl flagControl;

    
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
    public void setUp() {
        flagControl = new FlagControl();
    }


    @Test
    public void testFalseEnvFlag() throws Exception {
        boolean result = flagControl.setNewFeatureFlag("TEST_FALSE_FLAG_CONTROL");
        assertEquals(false, result);
    }


    @Test
    public void testTrueEnvFlag() throws Exception {
        boolean result = flagControl.setNewFeatureFlag("TEST_TRUE_FLAG_CONTROL");
        assertEquals(true, result);
    }


    @Test
    public void testNullEnvFlag() {
        assertThrows(Exception.class, () -> {
            flagControl.setNewFeatureFlag("TEST_NULL_FLAG_CONTROL");
        });
    }


    @Test
    public void testTrueUrlFlag(){
        boolean result = flagControl.setNewFeatureFlag("http://127.0.0.1:3030/getTrue", "GET");
        assertEquals(true, result);      
    }


    @Test
    public void testFalseUrlFlag(){
        boolean result = flagControl.setNewFeatureFlag("http://127.0.0.1:3030/getFalse", "GET");
        assertEquals(false, result);      
    }
}