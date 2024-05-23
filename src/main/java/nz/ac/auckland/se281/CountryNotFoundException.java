package nz.ac.auckland.se281;

public class CountryNotFoundException extends RuntimeException {
  public CountryNotFoundException(String country) {
    super(MessageCli.INVALID_COUNTRY.getMessage(country));
  }
}
