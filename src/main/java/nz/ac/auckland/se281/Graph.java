package nz.ac.auckland.se281;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Graph {
  private Map<Country, LinkedList<Country>> adjacencyMap;
  private Map<String, Country> countrySet = new HashMap<String, Country>();

  public Graph() {
    this.adjacencyMap = new HashMap<Country, LinkedList<Country>>();
  }
  
  public void addCountry(String name, String continent, String tax) {
    Country country = new Country(name, continent, tax);
    adjacencyMap.putIfAbsent(country, new LinkedList<Country>());
  }

  public void addBorder(Country country1, Country country2) {
  // assuming all the countries were loaded before

  // add adjacency for each of them
  adjacencyMap.get(country1).add(country2);
  adjacencyMap.get(country2).add(country1);
  }

  public Country getCountry(String name) {
    return countrySet.get(name);
  }
}
