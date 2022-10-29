package it.unibo.generics.graph;

import it.unibo.generics.graph.api.Graph;
import it.unibo.generics.graph.impl.GraphImpl;

import java.util.Arrays;
import java.util.Objects;
import java.util.Set;

/**
 *
 */
public final class UseGraph {

    private UseGraph() {
    }

    /**
     * @param args
     *            ignored
     */
    public static void main(final String... args) {
        /*
         * Test your graph implementation(s) by calling testGraph
         */
        GraphImpl<String> grafo = new GraphImpl<>();
        testGraph(grafo);
        /*Test with Integer
        GraphImpl<Integer> grafoInt = new GraphImpl<>();
        testGraphInt(grafoInt);
        */
    }

    private static void testGraph(final Graph<String> graph) {
        graph.addNode("a");
        graph.addNode("b");
        graph.addNode("c");
        graph.addNode("d");
        graph.addNode("e");
        graph.addEdge("a", "b");
        graph.addEdge("b", "c");
        graph.addEdge("c", "d");
        graph.addEdge("d", "e");
        graph.addEdge("c", "a");
        graph.addEdge("e", "a");
        /*
         * Should be ["a","b","c","d","e"], in any order
         */
        assertIsAnyOf(graph.nodeSet(), Set.of(splitOnWhiteSpace("a b c d e")));
        /*
         * ["d","a"], in any order
         */
        assertIsAnyOf(graph.linkedNodes("c"), Set.of(splitOnWhiteSpace("a d")));
        /*
         * Either the path b,c,a or b,c,d,e,a
         */
        assertIsAnyOf(
            graph.getPath("b", "a"),
            Arrays.asList(splitOnWhiteSpace("b c a")),
            Arrays.asList(splitOnWhiteSpace("b c d e a"))
        );
    }

    /*Test with Integer
    private static void testGraphInt(final Graph<Integer> graph) {
        graph.addNode(1);
        graph.addNode(7);
        graph.addNode(3);
        graph.addNode(2);
        graph.addNode(9);
        graph.addEdge(1, 7);
        graph.addEdge(7, 3);
        graph.addEdge(3, 2);
        graph.addEdge(2, 9);
        graph.addEdge(3, 1);
        graph.addEdge(9, 1);
        
        System.out.println("Node set: " + graph.nodeSet());
        
        System.out.println("Linked nodes of 3" + graph.linkedNodes(3));
        
        graph.getPath(7, 1);
    }
    */

    private static void assertIsAnyOf(final Object actual, final Object... valid) {
        for (final var target: Objects.requireNonNull(valid)) {
            if (Objects.equals(target, actual)) {
                System.out.println("OK: " + actual + " matches " + target); // NOPMD
                return;
            }
        }
        throw new AssertionError("None of " + Arrays.asList(valid) + " matches " + actual);
    }

    private static String[] splitOnWhiteSpace(final String target) {
        return target.split("\\s+");
    }
}
