package lw03.asdos;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("===== Problem 1 =====");

        List<String> playlist = new ArrayList<>();

        Scanner sc1 = new Scanner(Main.class.getResourceAsStream("playlist.txt"));

        while (sc1.hasNextLine()) {
            String line = sc1.nextLine().trim();
            if (line.isEmpty()) continue;

            String[] parts = line.split(" ", 2);

            String Operation = parts[0];
            String Song = parts[1];

            if (Operation.equals("ADD")) {
                playlist.add(Song);

            } else if (Operation.equals("INSERT")) {
                String[] insertData = Song.split(" ", 2);
                int index = Integer.parseInt(insertData[0]);
                String songName = insertData[1];
                playlist.add(index, songName);

            } else if (Operation.equals("REMOVE")) {
                playlist.remove(Song);
            }
        }

        sc1.close();

        System.out.println("Total songs: " + playlist.size());
        for (int i = 0; i < playlist.size(); i++) {
            System.out.println((i + 1) + ": " + playlist.get(i));
        }

        System.out.println();
        System.out.println("===== Problem 2 =====");

        Set<String> participants = new LinkedHashSet<>();

        int duplicateRegistrations = 0;

        Scanner sc2 = new Scanner(Main.class.getResourceAsStream("participants.txt"));

        while (sc2.hasNextLine()) {
            String participant = sc2.nextLine().trim();
            if (participant.isEmpty()) continue;

            if (!participants.add(participant)) {
                duplicateRegistrations++;   
            }
        }
        sc2.close();

        System.out.println("Unique participants: " + participants.size());
        
        int number = 1;

        for (String name : participants) {
            System.out.println(number + ". " + name);
            number++;
        }

        System.out.println("Duplicate registrations: " + duplicateRegistrations);

        System.out.println();
        System.out.println("===== Problem 3 =====");

        Map<String, Integer> inventory = new LinkedHashMap<>();

        int failedSales = 0;

        Scanner sc3 = new Scanner(Main.class.getResourceAsStream("inventory.txt"));

        while (sc3.hasNextLine()) {
            String line = sc3.nextLine().trim();
            if (line.isEmpty()) continue;

            String[] parts = line.split("\\s+");

            String type = parts[0];
            String products = parts[1];
            int quantity = Integer.parseInt(parts[2]);

            if (type.equals("ADD")) {
                if (inventory.containsKey(products)) {
                    int currentStack = inventory.get(products);
                    inventory.put(products, currentStack + quantity);
                } else {
                    inventory.put(products, quantity);               
                }

            } else if (type.equals("SELL")) {
                if (inventory.containsKey(products)) {
                    int currentStack = inventory.get(products);
                    if (currentStack >= quantity) {
                        inventory.put(products, currentStack - quantity);
                    } else {
                        failedSales++;
                    }
                } else {
                    failedSales++;
                }
            }
        }

        sc3.close();

        for (String product : inventory.keySet()) {
            System.out.println(product + ": " + inventory.get(product));
        }
        System.out.println("Failed sales: " + failedSales);
    }
}