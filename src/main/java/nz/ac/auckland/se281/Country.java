package nz.ac.auckland.se281;

/**
 * This interface sets the foundation for bot functionality to play it's turn
 * and select it's strategy.
 */
public class Country {
  private String name;
  private String continent;
  private int tax;

  /**
   * This is the constructor for initialising a country with its attributes.

   * @param name the name of the country.
   * @param continent the continent the country resides in.
   * @param tax the amount of tax it costs to traverse the country.
   */
  public Country(String name, String continent, String tax) {
    this.name = name;
    this.continent = continent;
    this.tax = Integer.parseInt(tax);
  }

  /**
   * A getter function for returning the continent.
   */
  public String getContinent() {
    return this.continent;
  }

  /**
   * A getter function for returning the tax.
   */
  public int getTax() {
    return this.tax;
  }

  /**
   * A getter function for returning the name of the country.
   */
  public String getName() {
    return this.name;
  }
}
