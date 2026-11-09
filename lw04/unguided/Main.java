package lw04.unguided;
import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(Main.class.getResourceAsStream("cards.txt"));
            
        if (!sc.hasNextInt()){
            sc.close();
            return;
        }

        int n = sc.nextInt();
        int[] cards = new int[n];
        for (int i = 0; i < n; i++){
                cards[i] = sc.nextInt();
        }

        int m = sc.nextInt();
        int[] queries = new int[m];
        for (int i = 0; i < m; i++){
            queries[i] = sc.nextInt();
        }

        sc.close();

        sortCards(cards, 2);

        for (int i = 0; i < n; i++){
            System.out.print(cards[i] + (i == n - 1 ? "" : " "));
        }
        System.out.println();

        for (int i = 0; i < m; i++){
        int query = queries[i];
        int index = findCards(cards, query);
        if (index != -1){
            System.out.println(query + ": FOUND at index " + index);
            } else {
                System.out.println(query + ": NOT FOUND");
            }
        }
    }

    public static void sortCards(int[] arr, int passes){
        int n = arr.length;
        for (int i = 0; i < passes && i < n - 1; i++){
            int idx = i;
            for (int j = i + 1; j < n; j++){
                if (arr[j] < arr[idx]){
                    idx = j;
                }
            }
            int temp = arr[i];
            arr[i] = arr[idx];
            arr[idx] = temp;
        }
    }

    public static int findCards(int[] arr, int target){
        for (int i = 0; i < arr.length; i++){
            if (arr[i] == target){
                return i;
            }
        }
        return -1;
    }
}
