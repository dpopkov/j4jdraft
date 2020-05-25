package ru.j4jdraft.jmm.demo;

public class MemoryUsage {

    public static void main(String[] args) throws Exception {
        System.out.println("start");

        int numObjects = 64_000;
        if (args.length == 1) {
            numObjects = Integer.parseInt(args[0]);
        }
        for (int i = 0; i < numObjects; i++) {
            new User(i);
            Thread.sleep(1L);
        }

        /*User user = new User(101L);
        System.out.println(user);
        user = null;
        System.gc();*/

        /* MemoryInfo info = new MemoryInfo(1L);
        info.print();
        User userObj = new User("test");
        SoftReference<User> user = new SoftReference<>(userObj);
        System.out.println(user.get().name);
        System.gc();
        info.print();*/

        System.out.println("finish");
    }

    static class MemoryInfo {
        private final long unit;

        public MemoryInfo(long unit) {
            this.unit = unit;
        }

        public void print() {
            Runtime runtime = Runtime.getRuntime();
            System.out.println("##### Heap utilization statistics [MB] #####");
            printMemory("Used Memory :", (runtime.totalMemory() - runtime.freeMemory()), unit);
            printMemory("Free Memory :", runtime.freeMemory(), unit);
            printMemory("Total Memory:", runtime.totalMemory(), unit);
            printMemory("Max Memory  :", runtime.maxMemory(), unit);
        }

        private void printMemory(String title, long amount, long unit) {
            System.out.printf("# %s %10d%n", title, (amount / unit));
        }
    }
}
