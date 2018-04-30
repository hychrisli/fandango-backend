package cmpe273.fandango.lib;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validate {

  public static Boolean isValidZipcode(String zipcode){
    if (zipcode == null) return false;

    zipcode = zipcode.trim();
    String regex = "^[0-9]{5}(?:-[0-9]{4})?$";

    Pattern pattern = Pattern.compile(regex);
    Matcher matcher = pattern.matcher(zipcode);
    return matcher.matches();
  }

  public static String getMainZipcode(String zipcode){
    if (zipcode == null) return null;
    zipcode = zipcode.trim();

    if ( !isValidZipcode(zipcode)) return null;
    else return zipcode.substring(0, 5);
  }


}
