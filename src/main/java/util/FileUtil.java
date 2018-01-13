package util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {

  /**
   * Write the result into a file
   *
   * @param result : the String to be written
   *               String file : the file location
   *               No exception thrown if file not found
   */
  public static void writeFileResult(String result, String file) {
    PrintWriter writer;
    try {
//			System.out.println("Writing result (into " + file +") : \n");
      writer = new PrintWriter(file, "UTF-8");
      writer.println(result);
      writer.close();

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Read the file containing the list of films
   *
   * @param file : the file location
   * @return the list of films
   * No exception thrown if file not found
   */
  public static List<String> readFromFile(String file) {

    List<String> result = new ArrayList<String>();
    BufferedReader bufferedReader = null;
    //TODO try with resources
    try {
      String sCurrentLine;
      bufferedReader = new BufferedReader(new FileReader(file));
      while ((sCurrentLine = bufferedReader.readLine()) != null) {
        result.add(formatString(sCurrentLine)); // style edition before adding
      }
    } catch (Exception ex) {
      System.out.println(ex.getMessage());
    } finally {
      try {
        bufferedReader.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    //System.out.println(result.toString());
    return result;
  }

  /**
   * Edit the string to fit with the research rules
   */
  private static String formatString(String in) {
    String out = in.trim();
    out = out.replace(" ", "+");

    // If contains "[...]" deletes all (brackets+content). Example : [The movie]
    if (out.contains("[")) {
      out = out.substring(0, out.indexOf("["));
    }

    // If contains "(...)", replaces the brackets with blanks (the content is unchanged).
    // Example: Hangover (The) -> The Hangover
    if (out.contains("(")) {
      out = out.replace("(", "");
      out = out.replace(")", "");
    }
    return out.substring(0, out.length());
  }

}
