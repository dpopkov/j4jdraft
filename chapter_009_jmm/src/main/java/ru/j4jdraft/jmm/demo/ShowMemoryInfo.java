package ru.j4jdraft.jmm.demo;

import java.util.Scanner;

public class ShowMemoryInfo {
    public static void main(String[] args) {
        MemoryInfo mi = new MemoryInfo(1024);
        mi.print();
        System.out.println("Press any key to finish...");
        new Scanner(System.in).nextLine();
    }
}
