package com.georgejrdev.utils;

import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import com.georgejrdev.utils.interfaces.InterfaceLocalizeAllFlags;
import com.georgejrdev.utils.support.File;
import com.georgejrdev.utils.support.Flag;


public class LocalizeFlags implements InterfaceLocalizeAllFlags{

    private File file;

    public LocalizeFlags(File file){
        this.file = file;
    }


    @Override
    public List<Flag> searchAllFlags(String folderPath) throws Exception{
        List<Flag> allFlags = new ArrayList<Flag>();

        List<String> allFilesPaths = findJavaFiles(folderPath);

        for (String filePath : allFilesPaths){
            List<Flag> flags = this.file.readFile(filePath);

            for (Flag flag : flags){
                allFlags.add(flag);
            }
        }

        return allFlags;
    }


    private List<String> findJavaFiles(String folderPath) {
        List<String> javaFiles = new ArrayList<>();
        Path startPath = Paths.get(folderPath);

        try {
            Files.walkFileTree(startPath, EnumSet.noneOf(FileVisitOption.class), Integer.MAX_VALUE, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    if (file.toString().endsWith(".java")) {
                        javaFiles.add(file.toAbsolutePath().toString());
                    }
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        return javaFiles;
    }
}
