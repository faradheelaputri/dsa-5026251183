package lw01.unguided;
import java.io.InputStream;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        InputStream is = Main.class.getResourceAsStream("rentals.txt");
        if (is == null) {
            is = Main.class.getResourceAsStream("/rentals.txt");
        }
        if (is == null) {
            System.out.println("File rentals.txt tidak ditemukan!");
            return;
        }
        try (Scanner scanner = new Scanner(is)) {
            if (!scanner.hasNextInt()) {
                return;
            }
            int n = scanner.nextInt();
            Rental[] rentals = new Rental[n];

            for (int i = 0; i < n; i++) {
                String type = scanner.next();
                String id = scanner.next();
                int days = scanner.nextInt();
                int units = scanner.nextInt();

                Rental temp = null;
                if (type.equalsIgnoreCase("LAPTOP")) {
                    temp = new LaptopRental(id, days);
                } else if (type.equalsIgnoreCase("PROJECTOR")) {
                    temp = new ProjectorRental(id, days);
                }

                if (temp != null) {
                    final int totalCharge = temp.calculateCharge(units);
                    final String itemLabel = temp.label();
                    rentals[i] = new Rental(id, days) {
                        @Override
                        public int calculateCharge() {
                            return totalCharge;
                        }
                        @Override
                        public String label() {
                            return itemLabel;
                        }
                    };
                }
            }

            for (Rental r : rentals) {
                if (r != null) {
                    System.out.println(r.summary());
                }
            }
        }
    }
}