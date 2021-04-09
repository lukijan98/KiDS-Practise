package com.company;

import org.apache.commons.io.comparator.SizeFileComparator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Comparator;
import java.util.concurrent.ForkJoinPool;


public class Main {

    public static void main(String[] args) throws IOException {
	// write your code here

        File directoryPath = new File("resources");
        File filesList[] = directoryPath.listFiles();
        Arrays.sort(filesList, SizeFileComparator.SIZE_COMPARATOR);
        filesList[0] = null;
        for (File file:
             filesList) {
            if(file!=null)
                System.out.println(file.getName());
        }

//        BufferedReader br = new BufferedReader(new FileReader(filesList[0].getPath()));
//        String line = null;
//        while( (line= br.readLine()) != null){
//           System.out.println(line);
//        }
//        br.close();

        //ForkJoinPool threadPool = new ForkJoinPool();

    }
}
