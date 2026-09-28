package lw02.unguided;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Main{
    public static void main(String[] args){
        LinkedList<String[]> request = new LinkedList<>();
        LinkedList<String[]> books = new LinkedList<>();
        LinkedList<String[]> members = new LinkedList<>();
        LinkedList<String[]> succesful = new LinkedList<>();

        Queue<String[]> queue = new LinkedList<>();
        Stack<String[]> stack = new Stack<>();

        books.add(new String[]{"Kalkulus", "2"});
        books.add(new String[]{"Fisika", "1"});
        books.add(new String[]{"Statistika", "2"});

        Scanner scanner = new Scanner(Main.class.getResourceAsStream("borrowing.txt"));

        while (scanner.hasNext()){
            String name = scanner.next();
            String title = scanner.next();

            request.add(new String[]{name, title});

            boolean memberExists = false;
            for (String[] member : members){
                if (member[0].equals(name)){
                    memberExists = true;
                    break;
                }
            }
            if (!memberExists){
                members.add(new String[]{name, "0"});
            }
        }
        scanner.close();

        queue.addAll(request);

        final int MAX_BORROW = 2;

        while (!queue.isEmpty()){
            String[] req = queue.poll();
            String name = req[0];
            String title = req[1];

            String[] bookRecord = null;
            for (String[] b : books) {
                if (b[0].equals(title)){
                    bookRecord = b;
                    break;
                }
            }

            String[] memberRecord = null;
            for (String[] m : members){
                if (m[0].equals(name)){
                    memberRecord = m;
                    break;
                }
            }

            int currentStock = Integer.parseInt(bookRecord[1]);
            int currentBorrowed = Integer.parseInt(memberRecord[1]);

            if (currentStock > 0 && currentBorrowed < MAX_BORROW){
                bookRecord[1] = String.valueOf(currentStock - 1);
                memberRecord[1] = String.valueOf(currentBorrowed + 1);
                succesful.add(req);
            } else {
                stack.push(req);
            }
        }

        System.out.println("=== Successfully Processed Requests ===");
        for (String[] s : succesful){
            System.out.println(s[0] + " " + s[1]);
        }
        System.out.println(" ");

        System.out.println("=== Remaining Book Stock ===");
        for (String[] b : books){
            System.out.println(b[0] + ": " + b[1]);
        }
        System.out.println(" ");

        System.out.println("=== Failed Requests ===");
        while (!stack.isEmpty()){
            String[] req = stack.pop();
            System.out.println(req[0] + " " + req[1]);
        }
        System.out.println(" ");
    }
}