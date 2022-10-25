package it.unibo.collections;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Example class using {@link List} and {@link Map}.
 *
 */
public final class UseListsAndMaps {
    private static final int MIN = 1000;
    private static final int MAX = 2000;
    private static final int ELEMS = 100_000;
    private static final long AFRICA_POPULATION = 1_110_635_000L;
    private static final long AMERICAS_POPULATION = 972_005_000L;
    private static final long ANTARCTICA_POPULATION = 0L;
    private static final long ASIA_POPULATION = 4_298_723_000L;
    private static final long EUROPE_POPULATION = 742_452_000L;
    private static final long OCEANIA_POPULATION = 38_304_000L;

    private UseListsAndMaps() {
    }

    /**
     * @param s
     *            unused
     */
    public static void main(final String... s) {
        /*
         * 1) Create a new ArrayList<Integer>, and populate it with the numbers
         * from 1000 (included) to 2000 (excluded).
         */
        final List<Integer> arrList = new ArrayList<>();

        for (int i = MIN; i < MAX; i++){
            arrList.add(i);
        }
        /*
         * 2) Create a new LinkedList<Integer> and, in a single line of code
         * without using any looping construct (for, while), populate it with
         * the same contents of the list of point 1.
         */
        final List<Integer> linkList = new LinkedList<>(arrList);
        /*
         * 3) Using "set" and "get" and "size" methods, swap the first and last
         * element of the first list. You can not use any "magic number".
         * (Suggestion: use a temporary variable)
         */
        int temp = arrList.get(arrList.size() - 1);
        arrList.set(arrList.size() - 1, arrList.get(0));
        arrList.set(0, temp);
        /*
         * 4) Using a single for-each, print the contents of the arraylist.
         */
        for (int elem : arrList) {
            System.out.println(" " + elem);
        }
        /*
         * 5) Measure the performance of inserting new elements in the head of
         * the collection: measure the time required to add 100.000 elements as
         * first element of the collection for both ArrayList and LinkedList,
         * using the previous lists. In order to measure times, use as example
         * TestPerformance.java.
         */
        //ArrayList
        long time = System.nanoTime();
        for (int i = 0; i < ELEMS; i++){
            arrList.add(0, i);
        }
        time = System.nanoTime() - time;
        var millis = TimeUnit.NANOSECONDS.toMillis(time);
        System.out.println(// NOPMD
            "Adding "
                + arrList.size()
                + " ints in a ArrayList took "
                + time
                + "ns ("
                + millis
                + "ms)"
        );

        //Linked List
        time = System.nanoTime();
        for (int i = 0; i < ELEMS; i++){
            linkList.add(0, i);
        }
        time = System.nanoTime() - time;
        millis = TimeUnit.NANOSECONDS.toMillis(time);
        System.out.println(// NOPMD
            "Adding "
                + linkList.size()
                + " ints in a LinkedList took "
                + time
                + "ns ("
                + millis
                + "ms)"
        );
        /*
         * 6) Measure the performance of reading 1000 times an element whose
         * position is in the middle of the collection for both ArrayList and
         * LinkedList, using the collections of point 5. In order to measure
         * times, use as example PerfTest.java.
         */
        time = System.nanoTime();
        for (int i = 0; i < MIN; i++){
            arrList.get(arrList.size()/2);
        }
        time = System.nanoTime() - time;
        millis = TimeUnit.NANOSECONDS.toMillis(time);
        System.out.println(// NOPMD
            "Reading "
                + arrList.size()
                + " ints in a ArrayList took "
                + time
                + "ns ("
                + millis
                + "ms)"
        );

        //Linked List
        time = System.nanoTime();
        for (int i = 0; i < MIN; i++){
            linkList.get(linkList.size()/2);
        }
        time = System.nanoTime() - time;
        millis = TimeUnit.NANOSECONDS.toMillis(time);
        System.out.println(// NOPMD
            "Reading "
                + linkList.size()
                + " ints in a LinkedList took "
                + time
                + "ns ("
                + millis
                + "ms)"
        );
        /*
         * 7) Build a new Map that associates to each continent's name its
         * population:
         *
         * Africa -> 1,110,635,000
         *
         * Americas -> 972,005,000
         *
         * Antarctica -> 0
         *
         * Asia -> 4,298,723,000
         *
         * Europe -> 742,452,000
         *
         * Oceania -> 38,304,000
         */
        final Map<String, Long> world = new HashMap<>();
        world.put("Africa", AFRICA_POPULATION);
        world.put("Americas", AMERICAS_POPULATION);
        world.put("Antarctica", ANTARCTICA_POPULATION);
        world.put("Asia", ASIA_POPULATION);
        world.put("Europe", EUROPE_POPULATION);
        world.put("Oceania", OCEANIA_POPULATION);

        /*
         * 8) Compute the population of the world
         */
        Long worldPopulation = 0L;
        for (long elem : world.values()) {
            worldPopulation += elem;
        }
        System.out.println("World population is: " + worldPopulation);
    }
}
