package util;

import org.apache.commons.cli.*;
import org.apache.http.HttpHost;
import web.WebConnection;


public class ArgumentsUtil {

    public static String sourceFile = "source.txt";
    public static String resultFile = "result.txt";

    /**
     * parse the commandLine arguments
     *
     * @param args
     */
    public static void parseArgs(String[] args) {
        CommandLineParser parser = new DefaultParser();

        Option sourceFile = Option.builder("s")
                .required(false)
                .hasArg()
                .desc("The source file")
                .longOpt("source")
                .build();

        Option resultFile = Option.builder("r")
                .required(false)
                .hasArg()
                .desc("The result file")
                .longOpt("result")
                .build();

        Option proxyHost = Option.builder("h")
                .required(false)
                .hasArg()
                .desc("The proxy host")
                .longOpt("proxyHost")
                .build();

        Option proxyPort = Option.builder("p")
                .required(false)
                .hasArg()
                .desc("The proxy port")
                .longOpt("proxyPort")
                .build();

        Options options = new Options();
        options.addOption(sourceFile);
        options.addOption(resultFile);
        options.addOption(proxyHost);
        options.addOption(proxyPort);

        try {
            CommandLine commandLine = parser.parse(options, args);

            if (commandLine.hasOption("s")) {
                ArgumentsUtil.sourceFile = commandLine.getOptionValue("s");
            }
            if (commandLine.hasOption("r")) {
                ArgumentsUtil.resultFile = commandLine.getOptionValue("r");
            }
            // If proxy set
            if (commandLine.hasOption("h") && commandLine.hasOption("p")) {
                try {
                    WebConnection.proxy = new HttpHost(commandLine.getOptionValue("h"),Integer.parseInt(commandLine.getOptionValue("p")));
                } catch (NumberFormatException e) {
                    System.err.println("Error while parsing proxy port:" + proxyPort);
                }
            }

        } catch (ParseException e) {
            System.err.println("Error while parsing the arguments: " + args + "\n" + e.getMessage());
        }
    }
}
