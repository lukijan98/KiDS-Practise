package com.company;

import java.io.*;
import java.util.Map;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingDeque;

public class Main {

    public static final int CONSUMER_COUNT = 5;

    public static void main(String[] args) throws IOException {

        Thread[] consumers = new Thread[CONSUMER_COUNT];
        BlockingDeque[] consumersDeque = new BlockingDeque[CONSUMER_COUNT];
        ConcurrentHashMap<ObjectURL,Integer> map = new ConcurrentHashMap<ObjectURL,Integer>();

        for(int i = 0; i < CONSUMER_COUNT;i++)
        {
            consumersDeque[i] = new LinkedBlockingDeque<String>();
            consumers[i] = new Thread(new Consumer(consumersDeque, map, i));
            consumers[i].setName(Integer.toString(i));
            consumers[i].setDaemon(true);
            consumers[i].start();
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while(true)
        {
            String input = reader.readLine();
            if(input.equals("exit"))
                break;
            if(input.equals("print map"))
            {
                for(Map.Entry<ObjectURL,Integer> mapEntry: map.entrySet())
                {
                    System.out.println("URL: "+mapEntry.getKey()+" ; Word count: "+mapEntry.getValue());
                }
            }
            else
            {
                try{
                    BufferedReader br = new BufferedReader(new FileReader(input));
                    String url;
                    int threadNumber = 0;
                    while( (url = br.readLine()) != null){
                        threadNumber=threadNumber%CONSUMER_COUNT;
                        consumersDeque[threadNumber].add(new ObjectURL(url,true));
                        threadNumber++;
                    }
                    br.close();
                }
                catch (FileNotFoundException e)
                {
                    System.out.println("Invalid file location: "+ input);
                }
            }
        }
        reader.close();
        System.out.println("Program has ended");

    }
}
