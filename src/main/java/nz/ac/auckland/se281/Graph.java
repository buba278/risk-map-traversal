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
  
  public void addCountry(T country) {
    adjacencyMap.putIfAbsent(country, new LinkedList<>());
  }

  public void addBorder(T country1, T country2) {
    // make sure they are already initiailsed
    // ignored if not anyways
    addCountry(country1);
    addCountry(country2);

    // add adjacency for each of them
    adjacencyMap.get(country1).add(country2);
    adjacencyMap.get(country2).add(country1);
  }
}
