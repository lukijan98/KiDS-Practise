package com.company;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.ConcurrentHashMap;

public class Consumer implements Runnable {

    private BlockingDeque[] deque;
    private ConcurrentHashMap map;
    private int id;

    public Consumer(BlockingDeque[] deque, ConcurrentHashMap map, int id)
    {
        this.deque = deque;
        this.map = map;
        this.id = id;
    }

    @Override
    public void run() {
        while(true)
        {
            ObjectURL url;
            try {
                url = (ObjectURL)(deque[id].takeFirst());
                listLinks(url);
            } catch (InterruptedException | IOException e) {
                e.printStackTrace();
            }

            while(!deque[id].isEmpty()){
                url = (ObjectURL) (deque[id].poll());
                try {
                    listLinks(url);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            for(int i=0; i < Main.CONSUMER_COUNT;i++)
            {
                if(i!=id) {
                    if(deque[i].peekLast()!=null){           // Da li je ovo problem?
                        deque[id].add(deque[i].pollLast());
                        break;
                    }
                }
            }
        }
    }

    private void listLinks(ObjectURL objectURL) throws IOException {
        Document doc = Jsoup.connect(objectURL.getValue()).get();
        System.out.println(objectURL.getValue() + "  Nit "+id);
        if(objectURL.getFlag())
        {
            Elements links = doc.select("a[href]");
            System.out.println(links.size());
            for (Element link : links) {
                deque[id].add(new ObjectURL(link.attr("abs:href"),false));
            }
        }
//        Elements p = doc.getElementsByTag("p");
//        for (Element e: p)
//            System.out.println(e.text());
        map.put(objectURL.getValue(),0);
    }

    //private void wordCount




}

