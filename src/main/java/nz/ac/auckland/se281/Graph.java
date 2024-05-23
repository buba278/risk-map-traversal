package nz.ac.auckland.se281;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * The mother class for holding the entire Risk map together relating the given countries through
 * borders.
 */
public class Graph {
  private Map<Country, LinkedList<Country>> adjacencyMap;
  private Map<String, Country> countrySet = new HashMap<String, Country>();

  /** 
   * Constructor for first initialising the graph.

   * @return Graph object. 
   */
  public Graph() {
    this.adjacencyMap = new HashMap<Country, LinkedList<Country>>();
  }

  /**
   * Method for adding new countries to the map while accounting for exceptions of invalid
   * countries.

   * @param name the name of the country.
   * @param continent the continent the country resides in.
   * @param tax the amount of tax it costs to traverse the country.
   */
  public void addCountry(String name, String continent, String tax) {
    Country country = new Country(name, continent, tax);
    adjacencyMap.putIfAbsent(country, new LinkedList<Country>());
    // add to the tracker for countries too
    countrySet.putIfAbsent(name, country);
  }

  /**
   * Method for adding a border between two countries.

   * @param country1 the first country.
   * @param country2 the second country.
   */
  public void addBorder(Country country1, Country country2) {
    // assuming all the countries were loaded before

    // add adjacency for each of them
    adjacencyMap.get(country1).add(country2);
  }

  /**
   * A getter method to transition between country name, and country object.

   * @param name name of the country that you want the object to.
   * @return the given country for the given name.
   */
  public Country getCountry(String name) throws RuntimeException {
    if (!countrySet.containsKey(name)) {
      throw new CountryNotFoundException(name);
    }
    return countrySet.get(name);
  }

  /**
   * Method for getting a given route from a starting country and the desired 
   * destination.

   * @param source the starting country.
   * @param destination the end country.
   * @return the list of countries to get from source to destination in order.
   */
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
        // if we found the country we are looking for
        if (adjCountry.equals(destination)) {
          // just track this one too for first iteration
          parentMap.put(adjCountry, current);
          // traverse back till we find source
          while (!adjCountry.equals(source)) {
            route.add(adjCountry);
            adjCountry = parentMap.get(adjCountry);
          }

          // add the final source location
          route.add(source);
          // reverse the list for the correct order
          Collections.reverse(route);
          return route;
        }
        // if I havent visited this yet, put it in queue for exploration
        else if (!visited.contains(adjCountry)) {
          // avoids repetition with visited set
          parentMap.put(adjCountry, current);
          visited.add(adjCountry);
          queue.add(adjCountry);
        }
      }
    }

    return route;
  }

  /**
   * Method for returning the crossed continents on a given route.

   * @param route the given countries traversed.
   * @return a ordered set of the continents crossed.
   */
  public Set<String> getContinents(List<Country> route) {
    // retained order through link
    Set<String> continentSet = new LinkedHashSet<>();
    for (Country country : route) {
      // no duplicate continents cause set
      String continent = country.getContinent();
      continentSet.add(continent);
    }
    return continentSet;
  }

  /**
   * Method for returning the tax paid on a given route.

   * @param route the given countries traversed.
   * @return the tax sum needed to pay.
   */
  public int getTax(List<Country> route) {
    // get the route planned and sum taxes excluding the first
    int sum = 0;
    for (Country country : route) {
      if (country.equals(route.get(0))) {
        continue;
      }
      sum += country.getTax();
    }

    return sum;
  }
}
