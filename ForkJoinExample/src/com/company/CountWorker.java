package com.company;

import java.io.File;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.RecursiveAction;


public class CountWorker extends RecursiveAction {

    private File[] fileList;
    private ConcurrentHashMap<String,Integer> map;
    private int size;
    private int limit;
    private int start;
    private int end;

    public CountWorker(File[] fileList, ConcurrentHashMap<String, Integer> map, int limit, int start, int end) {
        this.fileList = fileList;
        this.map = map;
        this.limit = limit;
        this.start = start;
        this.end = end;
    }

    @Override
    protected void compute() {
        if(validateSize())
            return;
        else
        {

        }

    }

    private boolean validateSize(){
        int calculated = 0;
        for(int i = start;i< end;i++)
        {

        }
        return true;
    }
}
