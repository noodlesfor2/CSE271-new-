import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Week4Content {
    public static void main(String[] args) {

        // ***** For-each loop *****
        // Simpler for loop, easier syntax, but limitations come with

        String[] vals = { "m", "t", "w", "r", "f" };

        // Simply prints out each item as x
        for (String x : vals) {
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

        for (int x : nums) {
            System.out.println(x);
        }

        // CANNOT USE FOR-EACH LOOP TO MODIFY VALUES

        // ***** Exception Handling w/ User Input *****
        System.out.println("");

        Scanner kb = new Scanner(System.in);
        int age = 0;

        // GOAL: Keep prompting user for age until
        // they give a valid response
        while (age == 0) {
            try {
                System.out.print("Enter age here: ");
                age = kb.nextInt();

                // Filter out negative values:
                if (age <= 0) {
                    throw new IllegalArgumentException(); // will cause catch block to execute
                }
                // What code could cause an exception?
            } catch (InputMismatchException ime) {
                // What do you want to do if this exception occurs?
                System.out.println("Invalid input, must enter an integer.");

                // Skip over the bad input... nextInt() cannot read a non-int
                kb.nextLine();
            } catch (IllegalArgumentException iae) {
                System.out.println("Invalid input, age must be > 0.");
                // Reset age to 0
                age = 0;
                // Again, skip over bad input
                kb.nextLine();

            } finally {
                // Finally will print NO MATTER WHAT
                System.out.println("This code runs NO MATTER WHAT!");
            }
        }

        kb.close();
        System.out.println("");
        
        // ***** Exception Handling w/ File I/O *****

        ArrayList<String> parkData = new ArrayList<>();

        Scanner reader = null;

        try {
            // This line could cause FileNotFoundException
            reader = new Scanner(new File("parks.txt"));
            
            // Loop through each line in file and add data to ArrayList
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                parkData.add(line);
            }

        } catch (FileNotFoundException fnf) {

            fnf.printStackTrace(); // Provide details about exception
        } catch (Exception e) {
            // To handle any other exception that may occur that I can't anticipate
            e.printStackTrace();
        } finally {
            // Always going to close the Scanner...
            // It's possible that reader is still null
            // if exception occurred
            if (reader != null) {
                reader.close();
            }
        }
        System.out.println(parkData);
        
        // Safely WRITING to a file:
        PrintWriter pw = null;
        
        // BOTH Scanners and PrintWriters need to handle FileNotFoundException
        
        try {
            pw = new PrintWriter("parksOut.txt");
            
            // Loop through ArrayList and print data to file:
            for (String park: parkData) {
                pw.println(park);
            }
        } catch(FileNotFoundException fnf) {
            fnf.printStackTrace();
        } catch(Exception e) { // In case anything else goes wrong.
            e.printStackTrace();
        } finally {
            if (pw != null) {
                pw.close();
            }
        }
    }
}
