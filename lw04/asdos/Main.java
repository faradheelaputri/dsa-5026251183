package lw04.asdos;

import java.util.*;

public class Main {
    static void insertionSort(int[] arr, int passes){
        for (int i = 1; i <= passes && i < arr.length; i++){
            int key = arr[i];
            int j = i - 1;

            while (j >= 0 && arr[j] > key){
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    } 

    static int linearSearch(int[] arr, int target){
        for (int i = 0; i < arr.length; i++){
            if (arr[i] == target){
                return i; 
            }
        }
        return -1; 
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(Main.class.getResourceAsStream("branches.txt")); 
        int n = sc.nextInt();
        int[] branches = new int[n];
        for (int i = 0; i < n; i++){
            branches[i] = sc.nextInt();
        }
        int m = sc.nextInt();
        int[] targets = new int[m];
        for (int i = 0; i < m; i++){
            targets[i] = sc.nextInt();
        }
        sc.close();
        
        insertionSort(branches, 3);

        for (int i = 0; i < n; i++){
            System.out.print(branches[i]);
            if (i < n - 1){
                System.out.print(" ");
            }
        }
        
        System.out.println();

        for (int i = 0; i < m; i++){
            int target = targets[i];
            int idx = linearSearch(branches, target);
            if (idx != -1){
              System.out.println(target + ": FOUND at index " + idx);
            } else {
              System.out.println(target + ": NOT FOUND");
            }
        }
    }
}