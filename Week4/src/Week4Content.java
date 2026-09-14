import java.util.ArrayList;

public class Week4Content {
    public static void main(String[] args) {
        
        // For-each loop
        // Simpler for loop, easier syntax, but limitations come with
        
        String[] vals = {"m","t","w","r","f"};
        
        // Simply prints out each item as x
        for (String x: vals) {
            System.out.println(x);
        }
        
        // Example with ArrayList
        ArrayList<Integer> nums = new ArrayList<>();
        nums.add(42);
        nums.add(9);
        nums.add(83);
        
        // Standard for loop
        for (int i = 0; i < nums.size(); i++) {
            System.out.println(nums.get(i));
        }
        
        for (int x: nums) {
            System.out.println(x);
        }
        
        // CANNOT USE FOR-EACH LOOP TO MODIFY VALUES
        
        
        
    }
}
