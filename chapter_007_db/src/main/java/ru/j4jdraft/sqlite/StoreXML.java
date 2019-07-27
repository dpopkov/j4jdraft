package ru.j4jdraft.sqlite;

import java.nio.file.Path;
import java.util.List;

public class StoreXML {
    private Path target;

    public StoreXML(Path target) {
        this.target = target;
    }

    /**
     * Saves entries to the target file.
     * @param entries list of entries
     */
    public void save(List<Entry> entries) {

    }
}
