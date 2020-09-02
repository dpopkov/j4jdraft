package ru.j4jdraft.djob.store;

import ru.j4jdraft.djob.model.Candidate;
import ru.j4jdraft.djob.model.Post;

import java.util.Map;
import java.util.Scanner;
import java.util.function.Consumer;

public class PsqlMain {
    private static final Scanner scanner = new Scanner(System.in);

    private static final Map<String, Consumer<Store>> ACTIONS = Map.of(
            "listPosts", s -> s.findAllPosts().forEach(System.out::println),
            "listCandidates", s -> s.findAllCandidates().forEach(System.out::println),
            "addPost", s -> s.save(new Post(0, readName())),
            "addCandidate", s -> s.save(new Candidate(0, readName())),
            "updatePost", s -> s.save(new Post(readId(), readName())),
            "updateCandidate", s -> s.save(new Candidate(readId(), readName())),
            "findPost", s -> System.out.println(s.findPostById(readId())),
            "findCandidate", s -> System.out.println(s.findCandidateById(readId()))
    );
    private static final Consumer<Store> INVALID = s -> System.out.println("Invalid command");

    public static void main(String[] args) {
        Store store = PsqlStore.getInstance();
        while (true) {
            System.out.print("Command: ");
            String cmd = scanner.nextLine();
            if ("exit".equals(cmd)) {
                break;
            }
            ACTIONS.getOrDefault(cmd, INVALID).accept(store);
        }
    }

    private static int readId() {
        System.out.print("Id: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        return id;
    }

    private static String readName() {
        System.out.print("Name: ");
        return scanner.nextLine();
    }
}
