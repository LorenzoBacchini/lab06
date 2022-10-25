package it.unibo.generics.graph.impl;

import java.util.HashMap;
import java.util.HashSet;
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
    public void addNode(N node) {
        if((node != null) && !graph.containsKey(node)){
            graph.put(node, new HashSet<>());
        }
    }

    @Override
    public void addEdge(N source, N target) {
        Set<N> s;
        if(checkEdge(source, target)){
            s = graph.get(source);
            s.add(target);
            s = graph.get(target);
            s.add(source);
        }
        
    }

    @Override
    public Set<N> nodeSet() {
        return graph.keySet();
    }

    @Override
    public Set<N> linkedNodes(N node) {
        Set<N> s;
        if((node != null)){
            if(graph.containsKey(node)){
                s = new HashSet<>(graph.get(node));
                return s;
            }
        }
        return null;
    }

    @Override
    public List<N> getPath(N source, N target) {
        if(checkEdge(source, target)){
            Map<N, Boolean> visited = new HashMap<>();
            for (N key : graph.keySet()) {
                visited.put(key, false);
            }
        }
        return null;
    }



    private boolean checkNode(N node){
        if((node != null) && graph.containsKey(node)){
            return true;
        }
        return false;
    }

    private boolean checkEdge(N source, N target){
        if(checkNode(source) && checkNode(target)){
            return true;
        }
        return false;
    }
    
}
