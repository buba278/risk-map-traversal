package nz.ac.auckland.se281;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Graph<T> {
  private Map<T, List<T>> adjacencyMap;

  public Graph() {
    this.adjacencyMap = new HashMap<>();
  }
  
}
