package org.laxio.ignition;

import org.apache.commons.cli.ParseException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;
import static org.laxio.ignition.LaxioIgnitionArguments.*;

class LaxioIgnitionArgumentsTest {

    private static Pattern ARGS_SPLIT_PATTERN = Pattern.compile("(\"[^\"]+\")|([^\\s\\\\]+)");

    @Test
    void classPathOption() throws ParseException {
        LaxioIgnitionArguments arguments = loadArguments(createOption(CLASS_PATH_PARAMETER));
        assertTrue(arguments.isClassPathLoaded());

        arguments = loadArguments("");
        assertFalse(arguments.isClassPathLoaded());
    }

    @Test
    void ignitablesDirectoryOption() throws ParseException {
        String specifiedDirectory = "something-else";
        LaxioIgnitionArguments arguments = loadArguments(createOption(IGNITABLES_DIRECTORY_PARAMETER, specifiedDirectory));
        assertEquals(specifiedDirectory, arguments.getIgnitablesDirectoryName(), "Ignitables directory should change when parameter is provided");

        arguments = loadArguments("");
        assertEquals(DEFAULT_IGNITABLES_DIRECTORY, arguments.getIgnitablesDirectoryName(), "Ignitables directory should resort to default when no parameter is provided");

        specifiedDirectory = "\"something else\"";
        arguments = loadArguments(createOption(IGNITABLES_DIRECTORY_PARAMETER, specifiedDirectory));
        assertEquals(specifiedDirectory.replace("\"", ""), arguments.getIgnitablesDirectoryName(), "Ignitables directory should change when parameter is provided");
    }

    @Test
    void helpOption() throws ParseException {
        LaxioIgnitionArguments arguments = loadArguments(createOption(HELP_PARAMETER));
        assertTrue(arguments.isHelpRequested());

        arguments = loadArguments("");
        assertFalse(arguments.isHelpRequested());
    }

    private String createOption(String opt) {
        return "-" + opt;
    }

    private String createOption(String opt, String args) {
        return createOption(opt) + " " + args;
    }

    private LaxioIgnitionArguments loadArguments(String args) throws ParseException {
        Matcher matcher = ARGS_SPLIT_PATTERN.matcher(args);
        List<String> matches = new ArrayList<>();
        while (matcher.find()) {
            matches.add(matcher.group());
        }

        String[] split = new String[matches.size()];

        int i = 0;
        for (String string : matches) {
            split[i++] = string;
        }

        return new LaxioIgnitionArguments(split);
    }

}
