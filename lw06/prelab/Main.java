package lw06.prelab;
import java.util.*;

public class Main {

    public static Node Insert(Node root, int data) {
        if (root == null) {
            return new Node(data);
        } else {
            if (data < root.data) {
                root.Left = Insert(root.Left, data);
            } else if (data > root.data) {
                root.Right = Insert(root.Right, data);
            }
            return root;
        }
    }

    public static boolean Search(Node root, int target) {
        if (root == null) {
            return false;
        } 
            
        if (root.data == target) {
            return true;
        } else if (target < root.data) {
            return Search(root.Left, target);
        } else {
            return Search(root.Right, target);
        }
    }

    public static void preorder(Node root) {
        if (root != null) {
            System.out.print(root.data + " ");
            preorder(root.Left);
            preorder(root.Right);
        }
    }

    public static void inorder(Node root) {
        if (root != null) {
            inorder(root.Left);
            System.out.print(root.data + " ");
            inorder(root.Right);
        }
    }

    public static void postorder(Node root) {
        if (root != null) {
            postorder(root.Left);
            postorder(root.Right);
            System.out.print(root.data + " ");
        }
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(Main.class.getResourceAsStream("treenumbers.txt"));
          
        Node root = null;
        if (sc.hasNextLine()) {
            String Line1 = sc.nextLine();
            Scanner lineScanner = new Scanner(Line1);
            while (lineScanner.hasNextInt()) {
                int value = lineScanner.nextInt();
                root = Insert(root, value);
            }
            lineScanner.close();
        }

        int target = 0;
        if (sc.hasNextLine()) {
            target = sc.nextInt();
        }
        sc.close();

        System.out.print("Preorder  : ");
        preorder(root);
        System.out.println();
           
        System.out.print("Inorder   : ");
        inorder(root);
        System.out.println();

        System.out.print("Postorder : ");
        postorder(root);
        System.out.println();

        boolean found = Search(root, target);
        System.out.println("Search " + target + " : " + found);
    }
}

        