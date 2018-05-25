package com.master.concept.hashmap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

/**
 * This class is produce the symptom of infinite loop
 * when use the HashMap in multi-thread situation
 */
public class InfiniteLoop {

    private static MyHashMap hashMap;

    private static void init() {
        /**
         * A -> B -> null
         */
        MyHashMap.Entry<String, String> entryB = new MyHashMap.Entry<String, String>("B", 123, "Value B");
        MyHashMap.Entry<String, String> entryA = new MyHashMap.Entry<String, String>("A", 123, "Value A", entryB);
        hashMap = new MyHashMap(new MyHashMap.Entry[]{entryA});
    }

    public static void main(String[] args) {
        int i = 0;
        do {
            System.out.println("Try to produce infinite loop: " + ++i);
            init();
            produceInfiniteLoop();
        } while (true);
    }

    private static void produceInfiniteLoop() {

        final int THREAD_NUMBERS = 10;

        ExecutorService es = Executors.newFixedThreadPool(THREAD_NUMBERS);
        List<Future<Map<String, MyHashMap.Entry<String, String>[]>>> futureList = new ArrayList<Future<Map<String, MyHashMap.Entry<String, String>[]>>>();
        for (int i = 0; i < THREAD_NUMBERS; i++) {
            Future<Map<String, MyHashMap.Entry<String, String>[]>> future = es.submit(new Callable<Map<String, MyHashMap.Entry<String, String>[]>>() {
                public Map<String, MyHashMap.Entry<String, String>[]> call() throws Exception {
                    MyHashMap.Entry<String, String>[] newTable = new MyHashMap.Entry[1];
                    hashMap.transfer(newTable);

                    Map<String, MyHashMap.Entry<String, String>[]> map = new HashMap<String, MyHashMap.Entry<String, String>[]>();
                    map.put(Thread.currentThread().getName(), newTable);
                    return map;
                }
            });
            futureList.add(future);
        }
        es.shutdown();

        for (Future<Map<String, MyHashMap.Entry<String, String>[]>> future : futureList) {
            try {
                Map<String, MyHashMap.Entry<String, String>[]> map = future.get();
                String threadName = map.keySet().iterator().next();
                MyHashMap.Entry<String, String> entry = map.get(threadName)[0];

                if (entry.next.next != null) {
                    System.out.println("Infinite loop issue happened!");

                    MyHashMap.Entry<String, String> e = entry;
                    System.out.println("Result from thread[" + threadName + "]:");
                    for (int i=0; i<10; i++) {//to avoid too much output, just print 10 nodes
                        if (e == null) {
                            System.out.print("null");
                            break;
                        } else {
                            System.out.print(e.key + " -> ");
                        }
                        e = e.next;
                    }
                    System.out.println("....");
                    System.exit(100);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

    }

}

class MyHashMap {

    transient Entry[] table;

    public MyHashMap(Entry[] table) {
        this.table = table;
    }

    <K, V> void transfer(Entry[] newTable) {
        final long THREAD_WAIT_TIME = 50;
        Entry[] src = table;
        int newCapacity = newTable.length;
        for (int j = 0; j < src.length; j++) {
            Entry<K, V> e = src[j];
            if (e != null) {
                /**
                 * Sleep here to ensure both thread takes the 1st node before it is set to be null
                 */
                sleep(THREAD_WAIT_TIME);
                src[j] = null;
                do {
                    Entry<K, V> next = e.next;
                    int i = indexFor(e.hash, newCapacity);
                    sleep(THREAD_WAIT_TIME);
                    /**
                     * Sleep here to ensure both thread takes the 2nd node
                     */
                    e.next = newTable[i];
                    sleep(THREAD_WAIT_TIME);
                    newTable[i] = e;
                    e = next;
                } while (e != null);
            }
        }
    }

    private void sleep(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Dummy index
     */
    private int indexFor(int hash, int newCapacity) {
        return 0;
    }

    static class Entry<K, V> {
        final K key;
        V value;
        Entry<K, V> next;
        final int hash;

        public Entry(K key, int hash, V value) {
            this(key, hash, value, null);
        }

        public Entry(K key, int hash, V value, Entry<K, V> next) {
            this.key = key;
            this.hash = hash;
            this.value = value;
            this.next = next;
        }

    }

}