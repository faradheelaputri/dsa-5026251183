package lw06.unguided;
import java.util.*;

public class Main {
    public static void main(String[] args){
        Scanner read = new Scanner(Main.class.getResourceAsStream("treenumbers.txt"));
        BinarySearchTree bst = new BinarySearchTree();
          
        Node root = null;
        if (read.hasNextLine()) {
            String Line1 = read.nextLine();
            Scanner lineScanner = new Scanner(Line1);
            while (lineScanner.hasNextInt()) {
                int value = lineScanner.nextInt();
                bst.insert(value);
            }
            lineScanner.close();
        }

        int target = 0;
        if (read.hasNextLine()) {
            target = read.nextInt();
        }
        else if (read.hasNextInt()) {
           String Line2 = read.nextLine();
           if (Line2.isEmpty()) {
               target = Integer.parseInt(Line2);
           }
        }
        read.close();

        System.out.print("Preorder  : ");
        bst.preOrder();
        System.out.println();
           
        System.out.print("Inorder   : ");
        bst.inOrder();
        System.out.println();

        System.out.print("Postorder : ");
        bst.postOrder();
        System.out.println();

        boolean found = bst.search(target);
        System.out.println("Search " + target + " : " + found);

        System.out.println("Minimum : " + bst.findMin());
        System.out.println("Maximum : " + bst.findMax());
    }
}
