package lw03.prelab;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        solveProblem1();
        solveProblem2();
        solveProblem3();
    }

    public static void solveProblem1() {
        System.out.println("===== Problem 1 =====");
        List<String> playlist = new ArrayList<>();

        try {
            File file = new File("lw03/prelab/playlist.txt");
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (line.isEmpty()) continue;

                String[] parts = line.split(" ", 2);
                String command = parts[0];

                if (command.equals("ADD")) {
                    playlist.add(parts[1]);
                } else if (command.equals("INSERT")) {
                    String[] insertParts = parts[1].split(" ", 2);
                    int index = Integer.parseInt(insertParts[0]);
                    playlist.add(index, insertParts[1]);
                } else if (command.equals("REMOVE")) {
                    playlist.remove(parts[1]);
                }
            }
            scanner.close();

            System.out.println("Total songs: " + playlist.size());
            for (int i = 0; i < playlist.size(); i++) {
                System.out.println((i + 1) + ": " + playlist.get(i));
            }

        } catch (FileNotFoundException e) {
            System.out.println("File playlist.txt tidak ditemukan.");
        }
    }

    public static void solveProblem2() {
        System.out.println("Problem 2 =====");
        Set<String> participants = new LinkedHashSet<>();
        int duplicateCount = 0;

        try {
            File file = new File("lw03/prelab/participants.txt");
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String name = scanner.nextLine().trim();
                if (name.isEmpty()) continue;

                if (!participants.add(name)) {
                    duplicateCount++;
                }
            }
            scanner.close();

            System.out.println("Unique participants: " + participants.size());
            int index = 1;
            for (String name : participants) {
                System.out.println(index + ". " + name);
                index++;
            }
            System.out.println("Duplicate registrations: " + duplicateCount);

        } catch (FileNotFoundException e) {
            System.out.println("File participants.txt tidak ditemukan.");
        }
    }

    public static void solveProblem3() {
        System.out.println("===== Problem 3 =====");
        Map<String, Integer> inventory = new LinkedHashMap<>();
        int failedSales = 0;

        try {
            File file = new File("lw03/prelab/inventory.txt");
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (line.isEmpty()) continue;

                String[] parts = line.split(" ");
                String type = parts[0];
                String product = parts[1];
                int quantity = Integer.parseInt(parts[2]);

                if (type.equals("ADD")) {
                    inventory.put(product, inventory.getOrDefault(product, 0) + quantity);
                } else if (type.equals("SELL")) {
                    int currentStock = inventory.getOrDefault(product, 0);
                    if (inventory.containsKey(product) && currentStock >= quantity) {
                        inventory.put(product, currentStock - quantity);
                    } else {
                        failedSales++;
                    }
                }
            }
            scanner.close();

            for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }
            System.out.println("Failed sales: " + failedSales);

        } catch (FileNotFoundException e) {
            System.out.println("File inventory.txt tidak ditemukan.");
        }
    }
}