package org.laxio.ignition;

import org.apache.commons.cli.*;

public class LaxioIgnitionArguments {

    public static String HELP_PARAMETER = "h";

    public static String CLASS_PATH_PARAMETER = "C";
    public static String IGNITABLES_DIRECTORY_PARAMETER = "I";
    public static String DEFAULT_IGNITABLES_DIRECTORY = "ignitables";

    private final Options options;
    private final CommandLine commandLine;

    public LaxioIgnitionArguments(String[] args) throws ParseException {
        options = buildOptions();
        CommandLineParser parser = new DefaultParser();
        commandLine = parser.parse(options, args);
    }

    public void showHelp() {
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("ignition", "", options, "Please report issues to https://laxio.org/issues", true);
    }

    public boolean isHelpRequested() {
        return commandLine.hasOption(HELP_PARAMETER);
    }

    public boolean isClassPathLoaded() {
        return commandLine.hasOption(CLASS_PATH_PARAMETER);
    }

    public String getIgnitablesDirectoryName() {
        return commandLine.getOptionValue(IGNITABLES_DIRECTORY_PARAMETER, DEFAULT_IGNITABLES_DIRECTORY);
    }

    private static Options buildOptions() {
        Options options = new Options();

        options.addOption(
                Option.builder(HELP_PARAMETER)
                        .required(false)
                        .hasArg(false)
                        .longOpt("help")
                        .build()
        );

        options.addOption(
                Option.builder(CLASS_PATH_PARAMETER)
                        .required(false)
                        .hasArg(false)
                        .longOpt("classpath")
                        .desc("Provide this argument if you wish to load ignitables from the classpath")
                        .build()
        );

        options.addOption(
                Option.builder(IGNITABLES_DIRECTORY_PARAMETER)
                        .required(false)
                        .valueSeparator()
                        .hasArgs()
                        .longOpt("ignitables-directory")
                        .desc("The directory that ignitables should be loaded from")
                        .build()
        );

        return options;
    }

}
