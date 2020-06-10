package ru.j4jdraft.howto.commonscli;

import org.apache.commons.cli.*;

public class CliExample {
    public static void main(String[] args) {
        Options options = new Options();
        Option input = new Option("i", "input", true, "input file path");
        input.setRequired(false);
        options.addOption(input);
        Option output = new Option("o", "output", true, "output file path");
        output.setRequired(false);
        options.addOption(output);
        options.addOption(new Option("version", "print version"));
        Option numLines = new Option("n", "numLines", true, "number of lines");
        numLines.setRequired(true);
        options.addOption(numLines);

        CommandLineParser parser = new DefaultParser();
        HelpFormatter helpFormatter = new HelpFormatter();
        CommandLine cmd = null;
        try {
            cmd = parser.parse(options, args);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            helpFormatter.printHelp("CliExample", options, true);
            System.exit(1);
        }

        if (cmd.hasOption("version")) {
            System.out.println("Version 1.0");
        }
        String inputPath = cmd.getOptionValue("input");
        String outputPath = cmd.getOptionValue("output");
        System.out.println("inputPath = " + inputPath);
        System.out.println("outputPath = " + outputPath);
        int num =  Integer.parseInt(cmd.getOptionValue(numLines.getOpt()));
        for (int i = 1; i <= num; i++) {
            System.out.printf("LIne #%d%n", i);
        }
    }
}
