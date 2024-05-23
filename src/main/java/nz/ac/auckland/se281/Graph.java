package nz.ac.auckland.se281;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.HashSet;
import java.util.Set;
import java.util.Queue;
import java.util.Collections;

public class Graph {
  private Map<Country, LinkedList<Country>> adjacencyMap;
  private Map<String, Country> countrySet = new HashMap<String, Country>();

  public Graph() {
    this.adjacencyMap = new HashMap<Country, LinkedList<Country>>();
  }
  
  public void addCountry(String name, String continent, String tax) {
    Country country = new Country(name, continent, tax);
    adjacencyMap.putIfAbsent(country, new LinkedList<Country>());
    // add to the tracker for countries too
    countrySet.putIfAbsent(name, country);
  }

  public void addBorder(Country country1, Country country2) {
  // assuming all the countries were loaded before

  // add adjacency for each of them
  adjacencyMap.get(country1).add(country2);
  adjacencyMap.get(country2).add(country1);
  }

  public Country getCountry(String name) throws RuntimeException {
    if (!countrySet.containsKey(name)) {
      throw new CountryNotFoundException(name);
    }
    return countrySet.get(name);
  }

  public List<Country> getRoute(Country source, Country destination) {
    // find the shortest route from source to destination
    Set<Country> visited = new HashSet<Country>();
    Queue<Country> queue = new LinkedList<Country>();
    List<Country> route = new LinkedList<Country>();
    // "parent" country tracker || country, parent
    Map<Country, Country> parentMap = new HashMap<Country, Country>();

    // start traversal from first node
    queue.add(source);
    visited.add(source);

    // iterate through adjs LL, breadth wise
    while (!queue.isEmpty()) {
      Country current = queue.remove();
      for (Country adjCountry : adjacencyMap.get(current)) {
        if (adjCountry.equals(destination)) {
          // if we found the country we are looking for
          // traverse back till we find source
          while (!adjCountry.equals(destination)) {
            route.add(adjCountry);
            adjCountry = parentMap.get(adjCountry);
          }

          // add the final source location
          route.add(destination);
          return route;
        }
        else if (!visited.contains(adjCountry)) {
          // if I havent visited this yet, put it in queue for exploration
          // avoids repetition with visited set
          // and track parent
          visited.add(adjCountry);
          queue.add(adjCountry);
          parentMap.put(adjCountry, current);
        }
      }
    }

    return route;
  }

  public int getTax(List<Country> route) {
    // get the route planned and sum taxes excluding the first
    return 1;
  }
}
