package it.unibo.generics.graph.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import it.unibo.generics.graph.api.Graph;

public class GraphImpl<N> implements Graph<N> {

    private final Map<N, Set<N>> graph;
    public GraphImpl(){
        this.graph = new HashMap<>();
    }

    @Override
    public void addNode(final N node) {
        if((node != null) && !graph.containsKey(node)){
            graph.putIfAbsent(node, new HashSet<>());
        }
    }

    @Override
    public void addEdge(final N source, final N target) {
        final Set<N> s;
        if(checkEdge(source, target)){
            s = graph.get(source);
            s.add(target);
        }
        
    }

    @Override
    public Set<N> nodeSet() {
        return graph.keySet();
    }

    @Override
    public Set<N> linkedNodes(final N node) {
        final Set<N> s;
        if(checkNode(node)){
            s = new HashSet<>(graph.get(node));
            return s;
        }
        return null;
    }

    @Override
    public List<N> getPath(final N source, final N target) {
        if(checkEdge(source, target)){
            final HashMap<N, Boolean> visited = new HashMap<>();
            for (N key : graph.keySet()) {
                visited.put(key, false);
            }
           
            final LinkedList<N> path = new LinkedList<>();
            
            if(dfs(source, target, visited, path)){
                System.out.println("Node " + target + " is reachable from node " + source);
                System.out.println("Path: " + path);
                return path;
            }
            System.out.println("Not reachable");
        }
        return Collections.emptyList();
    }

    private boolean dfs(final N source, final N target, 
                        final HashMap<N, Boolean> visited, final LinkedList<N> path){
        visited.replace(source, true);
        path.add(source);

        if(source == target){
            return true;
        }

        for ( N el : graph.get(source)) {
            if(!visited.get(el)){
                if(dfs(el, target, visited, path)){
                    return true;
                }
            }
        }
        path.pop();
        return false;
    }



    private boolean checkNode(final N node){
        if((node != null) && graph.containsKey(node)){
            return true;
        }
        return false;
    }

    private boolean checkEdge(final N source, final N target){
        if(checkNode(source) && checkNode(target)){
            return true;
        }
        return false;
    }
    
}
