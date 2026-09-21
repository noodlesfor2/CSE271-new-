// Jonah Moore
// CSE 271 - B
// 9/4/2026
// Project 1 running various analyses on various scripts for TV shows.

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ScriptReviewTool {

    // ******** USER INPUT METHODS ********

    /**
     * This method acquires the file name from the user.
     * 
     * @param kb
     * @return the string containing the file name
     */
    public static String getFileName(Scanner kb) {
        File f = null;
        String fileName = null;
        // Asking user for new file name
        while (f == null) {
            try {
                // Method used to get a file name from user input.
                System.out.print("Enter input file name: ");

                // Collecting the input file name
                fileName = kb.nextLine();
                // Checking if file exists
                f = new File(fileName);
                boolean validFile = f.exists();

                if (validFile == false) {
                    throw new IllegalArgumentException();
                }

            } catch (IllegalArgumentException iae) {
                System.out.println("File does not exist. Please try again.");
                f = null;

            }

        }
        return fileName;
    }

    /**
     * Method used to acquire the desired output file from user.
     * 
     * @param kb
     * @return the string of the output file name
     */
    public static String getOutputFileName(Scanner kb) {
        System.out.print("\nEnter output file name: ");

        kb.nextLine();
        String oFileName = kb.nextLine();

        return oFileName;
    }

    /**
     * This method displays a menu of user choices, and take in input.
     * 
     * @param kb
     * @return integer value of the user's choice
     */
    public static int getUserChoice(Scanner kb) {
        // Method used to display a menu of choices the user can make,
        // takes in input only, actual execution of the choice will be done in main
        // method.
        int userChoice = 0;

        // While-loop to keep running until correct choice
        while (userChoice == 0) {
            // Try-catch clause for user error
            try {
                // Printing out a menu of options
                System.out.print("\nOutput options:\n1. Raw word list\n2. Palindrome list\n3. High value word list\n"
                        + "4. Unique cast-member list\nEnter choice: ");

                // Collecting the user's choice
                userChoice = kb.nextInt();

                if (userChoice > 4 || userChoice < 1) {
                    throw new IllegalArgumentException();
                }
            } catch (InputMismatchException ime) {
                System.out.println("User choice must be an integer. Try again.");
                userChoice = 0;
                kb.nextLine();
            } catch (IllegalArgumentException iae) {
                System.out.println("User choice must be between 1-4. Try again.");
                userChoice = 0;
                kb.nextLine();
            }

        }

        return userChoice;
    }

    // ******** USER CHOICE METHODS ********

    /**
     * This method takes in a string, and strips it of all non-letters and white
     * space, and it becomes upper case.
     * 
     * @param word
     * @return the new "cleaned" string
     */
    public static String clean(String word) {
        // Method used to take in input string, and output a new string that has been
        // stripped of
        // all non-letters and white space, and is now in upper case.

        // Setting up an output word
        String oWord = "";

        // Looping through each character of the word, adding to output word if they are
        // a letter.
        for (int i = 0; i < word.length(); i++) {

            if (Character.isLetter(word.charAt(i))) {

                oWord += word.charAt(i);
            }

        }

        // Setting our new word to upper case
        oWord = oWord.toUpperCase();

        return oWord;
    }

    /**
     * This method checks if an input string is a palindrome.
     * 
     * @param word
     * @return true or false depending if the string is a palindrome
     */
    public static boolean isPalindrome(String word) {
        // A method used to check if an input string is a palindrome (meaning that
        // it's a word that reads the same forwards and backwards).

        // Cleaning given word
        word = clean(word);

        // Initializing necessary variables
        String wordCheck = "";
        boolean boolReturn = false;

        // If the word length is 1 from the start, the method will immediately return
        // false.
        if (word.length() <= 1) {

            return boolReturn;
        }
        // Creating a reverse version of our given word
        for (int i = 1; i < word.length() + 1; i++) {

            wordCheck += word.charAt(word.length() - i);
        }

        // Finally, comparing our reverse word with given word, to determine if it is a
        // palindrome

        if (wordCheck.equals(word)) {

            boolReturn = true;
        }
        return boolReturn;
    }

    /**
     * This method acquires the value of a string using its position in the
     * alphabet.
     * 
     * @param word
     * @return the total value of the string
     */
    public static int getValue(String word) {

        // Cleaning our given word
        word = clean(word);

        // Initializing a sum variable
        int totalValue = 0;

        // For loop to gain a total value
        for (int i = 0; i < word.length(); i++) {

            totalValue += (int) word.charAt(i) - 64;

        }

        return totalValue;
    }

    // ******** PARSING METHODS ********
    /**
     * Reads in words from the input file and lists them numerically on the output
     * file.
     * 
     * @param inFile
     * @param outFile
     */
    public static void parse(File inFile, File outFile) {
        Scanner reader = null;
        PrintWriter pw = null;
        // While loop to keep going despite errors

        int count = 0;
        try {
            // Initializing scanner and print writer
            reader = new Scanner(inFile);
            pw = new PrintWriter(outFile);
            // Looping through words in the file
            while (reader.hasNext()) {
                String word = reader.next();
                count++;
                pw.printf("%d. %s%n", count, word);
            }
        } catch (FileNotFoundException fnf) {
            System.out.println("File not found. Try again.");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                reader.close();
            }
            if (pw != null) {
                pw.close();
            }
        }

    }

    /**
     * Reads in words from the input file, only writes the palindromes to the output
     * file.
     * 
     * @param inFile
     * @param outFile
     */
    public static void parsePalindromes(File inFile, File outFile) {
        Scanner reader = null;
        PrintWriter pw = null;
        ArrayList<String> palList = new ArrayList<>();

        // Reading palindromes from file
        try {
            reader = new Scanner(inFile);
            while (reader.hasNext()) {
                String word = reader.next();

                if (isPalindrome(word)) {
                    String cleaned = clean(word);
                    if (!palList.contains(cleaned)) {
                        palList.add(cleaned);
                    }
                }
            }
            // Sorting alphabetically
            Collections.sort(palList);

            // Writing to the output file
            pw = new PrintWriter(outFile);
            for (int i = 0; i < palList.size(); i++) {
                pw.println(palList.get(i));
            }
            pw.println(palList.size());
        } catch (FileNotFoundException fnf) {
            System.out.println("File not found. Try again.");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (pw != null) {
                pw.close();
            }
            if (reader != null) {
                reader.close();
            }

        }

    }

    /**
     * Reads through a file and takes the value of words in that file. Writes words
     * with values greater than or equal to 100.
     * 
     * @param inFile
     * @param outFile
     */
    public static void parseHundredDollarWord(File inFile, File outFile) {
        Scanner reader = null;
        PrintWriter pw = null;
        ArrayList<String> hundredList = new ArrayList<>();
        // Try-catch for exceptions
        try {
            // Scanner and PrintWriter
            reader = new Scanner(inFile);
            pw = new PrintWriter(outFile);

            while (reader.hasNext()) {

                String word = reader.next();
                String cleaned = clean(word);

                // Comparing values to see what output should look like
                if (!hundredList.contains(cleaned) && !hundredList.contains(cleaned + "!")) {
                    if (getValue(word) == 100) {
                        hundredList.add(cleaned + "!");
                    } else if (getValue(word) > 100) {
                        hundredList.add(cleaned);
                    }
                }

            }

            Collections.sort(hundredList);

            for (String x : hundredList) {
                pw.println(x);
            }
        } catch (FileNotFoundException fnf) {
            System.out.println("File not found. Try again.");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                reader.close();
            }
            if (pw != null) {
                pw.close();
            }

        }
    }

    /**
     * Reads a script, and takes out each unique cast member and writes them into a
     * file. Shows the sum of cast members as well.
     * 
     * @param inFile
     * @param outFile
     */
    public static void parseUniqueCast(File inFile, File outFile) {
        Scanner reader = null;
        PrintWriter pw = null;
        String line = null;
        // Creating ArrayList of unique cast members
        ArrayList<String> castList = new ArrayList<>();

        try {
            reader = new Scanner(inFile);
            pw = new PrintWriter(outFile);
            while (reader.hasNextLine()) {

                // Reading one line out
                line = reader.nextLine();

                int serenityByJan = line.indexOf(":");
                String castMember = line.substring(0, serenityByJan);

                if (!castList.contains(castMember)) {
                    castList.add(castMember);
                }

            }

            // Sum of cast members
            int castSum = castList.size();
            // Printing each member
            for (String x : castList) {
                pw.println(x);
            }

            pw.printf("Unique cast members: %d", castSum);
        } catch (FileNotFoundException fnf) {
            System.out.println("File not found. Try again.");
        } catch (IndexOutOfBoundsException ioob) {
            pw.println("Unique cast members: 0");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                reader.close();
            }
            if (pw != null) {
                pw.close();
            }
        }
    }
    // ******** MAIN METHOD ********

    public static void main(String[] args) {

        // Initializing the Scanner for user input
        Scanner kb = new Scanner(System.in);

        // Getting user choice and file name
        String fileName = getFileName(kb);
        int userChoice = getUserChoice(kb);

        // Getting output file name
        String oFileName = getOutputFileName(kb);

        // Creating file objects
        File inputF = new File(fileName);
        File outputF = new File(oFileName);

        // User choice if and else-if
        if (userChoice == 1) {
            parse(inputF, outputF);
        } else if (userChoice == 2) {
            parsePalindromes(inputF, outputF);
        } else if (userChoice == 3) {
            parseHundredDollarWord(inputF, outputF);
        } else if (userChoice == 4) {
            parseUniqueCast(inputF, outputF);
        }

    }
}
