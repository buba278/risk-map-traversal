package nz.ac.auckland.se281;

import java.util.List;

/** This class is the main entry point. */
public class MapEngine {
  private Graph<Country> riskMap = new Graph<>();

  public MapEngine() {
    // add other code here if you want
    loadMap(); // keep this mehtod invocation
  }

  /** invoked one time only when constracting the MapEngine class. */
  private void loadMap() {
    List<String> countries = Utils.readCountries();
    List<String> adjacencies = Utils.readAdjacencies();
    
    // split up the information from countries and adjacencies
    for (String s : countries) {
      String[] countrySplit = s.split(",");
      // 1. Country, 2. Continent, 3. Tax


    }

    for (String s : adjacencies) {
      String[] adjSplit = s.split(",");
      // Root country then adjs


    }
  }

  /** this method is invoked when the user run the command info-country. */
  public void showInfoCountry() {
    // add code here
  }

  /** this method is invoked when the user run the command route. */
  public void showRoute() {}
}
