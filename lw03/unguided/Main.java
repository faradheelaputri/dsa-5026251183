package lw03.unguided;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        System.out.println("===== Enrollment Checks =====");
        Map<String, Integer> enrollmentMap = new LinkedHashMap<>();
        List<String> checkResult = new ArrayList<>();

        int rejectedOperations = 0;

        Scanner sc = new Scanner(Main.class.getResourceAsStream("enrollment.txt"));

        while (sc.hasNextLine()){
            String line = sc.nextLine();
            if (line.isEmpty()) continue;
            
            String[] parts = line.split("\\s+");

            String operation = parts[0];
            String courseCode = parts[1];

            if (operation.equals("REGISTER")){
                int count = Integer.parseInt(parts[2]);

                if (count <= 0){
                    rejectedOperations++;
                } else {
                    if (enrollmentMap.containsKey(courseCode)){
                        int currentCount = enrollmentMap.get(courseCode);
                        enrollmentMap.put(courseCode, currentCount + count);
                    } else {
                        enrollmentMap.put(courseCode, count);
                    } 
                }
            } else if (operation.equals("WITHDRAW")){
                int count = Integer.parseInt(parts[2]);

                if (count <= 0){
                    rejectedOperations++;
                } else if (enrollmentMap.containsKey(courseCode)){
                   int currentCount = enrollmentMap.get(courseCode);
                     if (currentCount >= count){
                      enrollmentMap.put(courseCode, currentCount - count);
                    } else {
                        rejectedOperations++;
                    }
                } else {
                    rejectedOperations++;
                }

            } else if (operation.equals("CHECK")){
                if (enrollmentMap.containsKey(courseCode)){
                    checkResult.add(courseCode + ": " + enrollmentMap.get(courseCode) + " students");
                } else {
                    checkResult.add(courseCode + ": Not found");
                }
            }
        }

        sc.close();

        for (String result : checkResult){
            System.out.println(result);
        }

        System.out.println();
        System.out.println("===== Final Enrollment =====");

        for (String course : enrollmentMap.keySet()){
            System.out.println(course + ": " + enrollmentMap.get(course) + " students");
        }

        System.out.println();
        System.out.println("Rejected operations: " + rejectedOperations);
    }
}
