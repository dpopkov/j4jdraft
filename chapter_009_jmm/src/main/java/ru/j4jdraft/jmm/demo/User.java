package ru.j4jdraft.jmm.demo;

/**
 * Class for memory measurements.
 * It should take 24 bytes in memory.
 */
class User {
    private final long id;

    public User(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "User{id='" + id + '\'' + '}';
    }

    @SuppressWarnings("deprecation")
    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("Thread: " + Thread.currentThread().getName()
                + ", finalizing User #" + id);
    }
}
