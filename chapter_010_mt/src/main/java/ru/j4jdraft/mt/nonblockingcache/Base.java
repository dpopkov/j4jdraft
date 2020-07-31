package ru.j4jdraft.mt.nonblockingcache;

import net.jcip.annotations.NotThreadSafe;

@NotThreadSafe
public class Base {
    private final int id;
    /** This value should be incremented when the model is updated. */
    private int version;

    public Base(int id) {
        this.id = id;
    }

    public Base(Base model) {
        this.id = model.id;
        this.version = model.version;
    }

    public void updateVersion() {
        version++;
    }

    public int getId() {
        return id;
    }

    public int getVersion() {
        return version;
    }
}
