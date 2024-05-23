package nz.ac.auckland.se281;

/**
 * An exception for when the country prompted does not exist.
 */
public class CountryNotFoundException extends RuntimeException {
  /**
   * Constructor for the exception passing its message.

   * @param country the given country that couldnt be found.
   */
  public CountryNotFoundException(String country) {
    super(MessageCli.INVALID_COUNTRY.getMessage(country));
  }
}
