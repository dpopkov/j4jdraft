package ru.j4jdraft.io.demos5;

import java.io.*;

/**
 * Demonstration of serialization and deserialization.
 */
public class SerializationDemo {
    public static void main(String[] args) {
        try (ObjectOutputStream out = new ObjectOutputStream(
                new BufferedOutputStream(new FileOutputStream("txt/serial")))) {
            Goods object1 = new Goods("Notebook", 3, 100_000.0);
            System.out.println("serialized object: " + object1);
            out.writeObject(object1);
        } catch (IOException e) {
            System.out.println("Error during serialization: " + e);
            return;
        }
        try (ObjectInputStream in = new ObjectInputStream(
                new BufferedInputStream(new FileInputStream("txt/serial"))
        )) {
            Goods object2 = (Goods) in.readObject();
            System.out.println("deserialized object: " + object2);
        } catch (Exception e) {
            System.out.println("Error during deserialization: " + e);
        }
    }
}
