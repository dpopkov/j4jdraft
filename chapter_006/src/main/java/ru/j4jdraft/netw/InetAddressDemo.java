package ru.j4jdraft.netw;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Demonstrates InetAddress.
 */
public class InetAddressDemo {
    public static void main(String[] args) throws UnknownHostException {
        InetAddress address = InetAddress.getLocalHost();
        System.out.println(address);
        address = InetAddress.getByName("www.HerbSchildt.com");
        System.out.println(address);
        System.out.println("getHostAddress: " + address.getHostAddress());
        System.out.println("getHostName: " + address.getHostName());
        InetAddress[] addresses = InetAddress.getAllByName("www.nba.com");
        for (InetAddress addr : addresses) {
            System.out.println(addr);
        }
    }
}
