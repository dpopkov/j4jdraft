package ru.j4jdraft.jmm.logparser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * Parser of java option -verbose:gc output.
 */
public class VerboseGcOutputParser {
    private final List<String> lines;
    private double timeOfPauses;
    private double totalTime;
    private int gcCount;

    public VerboseGcOutputParser(List<String> lines) {
        this.lines = lines;
        parse();
    }

    public double totalTime() {
        return totalTime;
    }

    public double timeOfPauses() {
        return timeOfPauses;
    }

    public int gcCount() {
        return gcCount;
    }

    private void parse() {
        parsePauses();
        parseTotal();
    }

    private void parsePauses() {
        double pauses = 0.0;
        for (String line : lines) {
            double time = parseLine(line);
            pauses += time;
        }
        timeOfPauses = pauses;
    }

    private void parseTotal() {
        String last = lines.get(lines.size() - 1);
        int bracketIdx = last.indexOf("]");
        String s = last.substring(1, bracketIdx);
        boolean inSeconds = false;
        if (s.endsWith("s")) {
            s = s.substring(0, s.length() - 1);
            inSeconds = true;
        } else if (s.endsWith("ms")) {
            s = s.substring(0, s.length() - 2);
        }
        totalTime = Double.parseDouble(s.replace(",", "."));
        totalTime = inSeconds ? totalTime * 1000 : totalTime;
        int gcCountStart = last.indexOf("GC(");
        int gcCountEnd = last.indexOf(")", gcCountStart + 1);
        String gcCountStr = last.substring(gcCountStart + 3, gcCountEnd);
        int lastGcIdx = Integer.parseInt(gcCountStr);
        gcCount = lastGcIdx + 1;
    }

    private double parseLine(String line) {
        int spaceIdx = line.indexOf(" ");
        String tail = line.substring(spaceIdx + 1);
        if (tail.startsWith("GC(")) {
            spaceIdx = tail.indexOf(" ");
            tail = tail.substring(spaceIdx + 1);
        }
        if (tail.startsWith("Pause")) {
            spaceIdx = tail.lastIndexOf(" ");
            tail = tail.substring(spaceIdx + 1);
            if (!tail.endsWith("ms")) {
                throw new IllegalStateException("Line should end with 'ms'");
            }
            String time = tail.substring(0, tail.length() - 2).replace(",", ".");
            return Double.parseDouble(time);
        }
        return 0.0;
    }

    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            System.err.println("Usage: java " + VerboseGcOutputParser.class.getName() + " path-to-log-file");
            System.exit(1);
        }
        List<String> lines = Files.readAllLines(Path.of(args[0]));
        VerboseGcOutputParser parser = new VerboseGcOutputParser(lines);
        double pausesTime = parser.timeOfPauses();
        double totalTime = parser.totalTime();
        double appTime = totalTime - pausesTime;
        double pausesRate = pausesTime / totalTime;
        System.out.printf("GC count            = %9d%n", parser.gcCount());
        System.out.printf("Time of pauses      = %9.2f ms%n", pausesTime);
        System.out.printf("Time total          = %9.2f ms%n", totalTime);
        System.out.printf("Time of application = %9.2f ms%n", appTime);
        System.out.printf("Time spent in garbage collection was %.1f%% of total time%n", (pausesRate * 100));
    }
}
