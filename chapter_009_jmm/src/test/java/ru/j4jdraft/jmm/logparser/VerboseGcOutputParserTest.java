package ru.j4jdraft.jmm.logparser;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class VerboseGcOutputParserTest {

    private static final double DELTA = 1e-4;

    @Test
    public void testTimeOfPausesParsing() {
        List<String> lines = List.of(
                "[0,185s][info][gc] GC(2) Pause Young (Concurrent Start) (G1 Evacuation Pause) 3M->2M(8M) 1,142ms",
                "[0,185s][info][gc] GC(3) Concurrent Cycle",
                "[0,187s][info][gc] GC(3) Pause Remark 2M->2M(8M) 0,603ms"
        );
        VerboseGcOutputParser parser = new VerboseGcOutputParser(lines);
        double expectedPauses = 1.745;
        double expectedTotal = 187.0;
        int expectedCount = 3;
        double pauses = parser.timeOfPauses();
        double total = parser.totalTime();
        int count = parser.gcCount();
        assertEquals(expectedPauses, pauses, DELTA);
        assertEquals(expectedTotal, total, DELTA);
        assertEquals(expectedCount, count);
    }
}
