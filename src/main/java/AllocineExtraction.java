import java.io.IOException;
import org.apache.commons.cli.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by yann on 13/03/17.
 */
public class AllocineExtraction {

  public static String sourceFile = "source.txt";
  public static String resultFile = "result.txt";

  public static void main(String[] args) {
    parseArgs(args);

    List<String> titles = FileUtil.readFromFile(sourceFile);
    List<Film> films = new ArrayList<Film>();

    for (String title : titles) {
      try {
        films.add(WebConnection.extractMovie(title));
      }
      catch (IOException e) {
        System.err.println("Error encountered for: " + title);
        System.err.println(e.getMessage());
      }
    }
    ResultLayoutEdition.editFilms(films, resultFile); // Edition of the results and writing into file
  }


  /**
   * parse the commandLine arguments
   * @param args
   */
  public static void parseArgs(String[] args) {
    CommandLineParser parser = new DefaultParser();
    Option option_source = Option.builder("s")
            .required(false)
            .hasArg()
            .desc("The source file")
            .longOpt("source")
            .build();

    Option option_result = Option.builder("r")
            .required(false)
            .hasArg()
            .desc("The result file")
            .longOpt("result")
            .build();

    Options options = new Options();
    options.addOption(option_source);
    options.addOption(option_result);
    try {
      CommandLine commandLine = parser.parse(options, args);

      if (commandLine.hasOption("s")) {
        sourceFile = commandLine.getOptionValue("s");
      }
      if (commandLine.hasOption("r")) {
        resultFile = commandLine.getOptionValue("r");
      }

    } catch (ParseException e) {
        System.err.println("Error while parsing the arguments: "+ args + "\n" + e.getMessage());
    }
  }
}
