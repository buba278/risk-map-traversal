package nz.ac.auckland.se281;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/** This class is the main entry point. */
public class MapEngine {
  private Graph riskMap = new Graph();

  /**
   * Constructor for initialising the map.

   * @return a map engine instance.
   */
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

      riskMap.addCountry(countrySplit[0], countrySplit[1], countrySplit[2]);
    }

    for (String s : adjacencies) {
      String[] adjSplit = s.split(",");
      // Root country then adjs

      Country rootCountry = riskMap.getCountry(adjSplit[0]);
      for (int i = 1; i < adjSplit.length; i++) {
        riskMap.addBorder(rootCountry, riskMap.getCountry(adjSplit[i]));
      }
    }
  }

  /** this method is invoked when the user run the command info-country. */
  public void showInfoCountry() {
    // compartmentalised input function to be reused
    Country country = getCountryInput(MessageCli.INSERT_COUNTRY.getMessage());
    String name = country.getName();
    MessageCli.COUNTRY_INFO.printMessage(
        name, country.getContinent(), String.valueOf(country.getTax()));
  }

  /** this method is invoked when the user run the command route. */
  public void showRoute() {
    // prompting for inputs
    Country sourceCountry = getCountryInput(MessageCli.INSERT_SOURCE.getMessage());
    Country destinationCountry = getCountryInput(MessageCli.INSERT_DESTINATION.getMessage());

    // check if the source and destiantion are ==
    if (sourceCountry.equals(destinationCountry)) {
      MessageCli.NO_CROSSBORDER_TRAVEL.printMessage();
      return;
    }

    // if not then get the line to take
    List<Country> route = riskMap.getRoute(sourceCountry, destinationCountry);

    // print the route
    LinkedHashSet<String> routeStrings = new LinkedHashSet<>();
    for (Country country : route) {
      routeStrings.add(country.getName());
    }
    String routeString = "[" + String.join(", ", routeStrings) + "]";
    MessageCli.ROUTE_INFO.printMessage(routeString);

    // print continents
    Set<String> continentSet = riskMap.getContinents(route);
    String continentString = "[" + String.join(", ", continentSet) + "]";
    MessageCli.CONTINENT_INFO.printMessage(continentString);

    // print tax
    MessageCli.TAX_INFO.printMessage(String.valueOf(riskMap.getTax(route)));
  }

  /**
   * Method for returning a country input from the user.

   * @param promptMessage the message outputed to the user asking for input.
   * @return the Country that was requested by the user.
   */
  public Country getCountryInput(String promptMessage) {
    boolean validInput = false;
    Country country = null;
    String name;

    while (!validInput) {
      try {
        System.out.println(promptMessage);
        name = Utils.capitalizeFirstLetterOfEachWord(Utils.scanner.nextLine());
        country = riskMap.getCountry(name);
        validInput = true;
      } catch (CountryNotFoundException e) {
        System.out.println(e.getMessage());
      }
    }

    return country;
  }
}
