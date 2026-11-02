package lw04.prelab;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    public static void main(String[] args){
        try{
            File file = new File("lw04/prelab/branches.txt");
            Scanner scanner = new Scanner(file);

            if (!scanner.hasNextInt()){
                scanner.close();
                return;
            }
            int n = scanner.nextInt();
            int[] branches = new int[n];
            for (int i = 0; i < n; i++){
                    branches[i] = scanner.nextInt();
                } 

                int m = scanner.nextInt();
                int[] targets = new int[m];
                for (int i = 0; i < m; i++){
                        targets[i] = scanner.nextInt();
                    }

                scanner.close();

                    int totalPasses = 3;
                    for (int i = 0; i < totalPasses && i < n - 1; i++){
                        int maxIdx = i;
                        for (int j = i + 1; j < n; j++){
                            if (branches[j] > branches[maxIdx]){
                                maxIdx = j;
                            }
                        }
                        int temp = branches[maxIdx];
                        branches[maxIdx] = branches[i];
                        branches[i] = temp;
                    }

                    for (int i = 0; i < n; i++){
                        System.out.print(branches[i] + (i == n - 1 ? "" : " "));
                    }
                    System.out.println();

                    for (int i = 0; i < m; i++){
                        int target = targets[i];
                        int foundIndex = -1;

                        for (int j = 0; j < n; j++){
                            if (branches[j] == target){
                                foundIndex = j;
                                break;
                            }
                        }

                        if (foundIndex != -1){
                            System.out.println(target + ": FOUND at index " + foundIndex);
                        } else {
                            System.out.println(target + ": NOT FOUND");
                        }
                    }
            } catch (FileNotFoundException e){
            System.out.println("File branches.txt tidak ditemukan!");
            }
        }
    }
