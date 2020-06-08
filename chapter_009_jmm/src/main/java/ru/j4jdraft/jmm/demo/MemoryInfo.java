package ru.j4jdraft.jmm.demo;

class MemoryInfo {
    private final int unit;
    private String unitName;

    /**
     * Constructs instance using specified number of bytes in unit.
     *
     * @param bytesInPrintedUnit number of bytes in memory unit when printing memory info
     *                           (1 for output in bytes, 1024 for output in kilobytes and so on).
     */
    public MemoryInfo(int bytesInPrintedUnit) {
        unit = bytesInPrintedUnit;
        switch (bytesInPrintedUnit) {
            case 1:
                unitName = "bytes";
                break;
            case 1024:
                unitName = "KB";
                break;
            case 1024 * 1024:
                unitName = "MB";
                break;
            case 1024 * 1024 * 1024:
                unitName = "GB";
                break;
            default:
                throw new IllegalArgumentException("Not correct unit size");
        }
    }

    public void print() {
        Runtime runtime = Runtime.getRuntime();
        System.out.println("##### Heap utilization statistics [" + unitName + "] #####");
        printMemory("Used Memory :", (runtime.totalMemory() - runtime.freeMemory()), unit);
        printMemory("Free Memory :", runtime.freeMemory(), unit);
        printMemory("Total Memory:", runtime.totalMemory(), unit);
        printMemory("Max Memory  :", runtime.maxMemory(), unit);
    }

    private void printMemory(String title, long amount, long unit) {
        System.out.printf("# %s %10d%n", title, (amount / unit));
    }
}
