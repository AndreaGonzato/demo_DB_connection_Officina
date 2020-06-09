public class SQLInjectionParser {
  final static String[] forbiddenWordsArray = {"UNION", "SELECT", "DROP", "ALTER", "DELETE", "INSERT", "--", ";"};

  // return true if one or more elements contains a forbidden word
  static public boolean detectSQLInjection(String... args) {
    for (int i = 0; i < args.length; i++) {
      for (int j = 0; j < forbiddenWordsArray.length; j++) {
        if (containsSubString(args[i], forbiddenWordsArray[j])) {
          return true;
        }
      }
    }
    return false;
  }

  // return true if a string contains a given subString
  private static boolean containsSubString(String str, String subString) {
    return str.toUpperCase().contains(subString.toUpperCase());
  }

}
