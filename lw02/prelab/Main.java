package lw02.prelab;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    public static void main(String[] args){
        LinkedList<String[]> ListTransaksi = new LinkedList<>();
        LinkedList<String[]> ListCustomer = new LinkedList<>();

        try{
            Scanner scanner = new Scanner(new File("lw02/prelab/transactions.txt"));
            while (scanner.hasNextLine()){
                String line = scanner.nextLine().trim();
                if (line.isEmpty()) continue;

                String[] data = line.split(" ");
                ListTransaksi.add(data);

                boolean ada = false;
                for (String[] customer : ListCustomer){
                    if (customer[0].equals(data[0])){
                        ada = true;
                        break;
                    }
                }
                if (!ada){
                    ListCustomer.add(new String[]{data[0],"0"});
                }
            }
            scanner.close();
        } catch (FileNotFoundException e){
            System.out.println("File tidak ditemukan!");
            return;
        }

        Queue<String[]> antreanTransaksi = new LinkedList<>(ListTransaksi);
        Stack<String[]> stackGagal = new Stack<>();

        while (!antreanTransaksi.isEmpty()){
            String[] trx = antreanTransaksi.poll();
            String nama = trx[0];
            String tipe = trx[1];
            int nominal = Integer.parseInt(trx[2]);

            for (String[] customer : ListCustomer){
                if (customer[0].equals(nama)){
                    int saldo = Integer.parseInt(customer[1]);
                    if (tipe.equals("DEPOSIT")){
                        customer[1] = (saldo + nominal) + "";
                    } else if (tipe.equals("WITHDRAW")){
                        if (nominal > saldo){
                            stackGagal.push(trx);
                        } else {
                            customer[1] = (saldo - nominal) + "";
                        }
                    }
                    break;
                }
            }
        }

        System.out.println("=== Final Balances ===");
        for (String[] customer : ListCustomer){
            System.out.println(customer[0] + ": " + customer[1]);
        }

        System.out.println("=== Failed Transactions ===");
        while (!stackGagal.isEmpty()){
            String[] gagal = stackGagal.pop();
            System.out.println(gagal[0] + " " + gagal[1] + " " + gagal[2]);
        }
    }
}
