package com.georgejrdev.utils.interfaces;

import java.io.IOException;
import java.util.List;

import com.georgejrdev.utils.support.Flag;


public interface InterfaceFile {
    List<Flag> readFile(String path) throws IOException, Exception;    
}