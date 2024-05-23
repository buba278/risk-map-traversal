package nz.ac.auckland.se281;

public class Country {
  private String name;
  private String continent;
  private int tax;

  public Country(String name, String continent, String tax) {
    this.name = name;
    this.continent = continent;
    this.tax = Integer.parseInt(tax);
  }
}
