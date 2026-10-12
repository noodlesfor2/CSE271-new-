import java.util.Arrays;

public class Week8Content {
    // Recursive methods
    public static int power(int base, int exp) {
        if (exp == 0) {
            return 1;
        }
        exp--;
        return base * power(base, exp);
    }
    
    public static String reverse (String str) {
        if (str.equals("")) {
            return "";
        }
        char last = str.charAt(str.length() - 1);
        String rest = str.substring(0, str.length() - 1);
        return last + reverse(rest);
    }
    
    public static boolean palindrome (String str) {
        if (str.length() == 1 || str.equals("")) {
            return true;
        }
        else if(str.charAt(0) == str.charAt(str.length() - 1)) {
            return palindrome(str.substring(1, str.length() - 1));
        }
        return false;
    }
    
    public static void main(String[] args) {
        System.out.println(power(2, 3));
        System.out.println(power(4, 4));
        System.out.println(reverse("hello"));
        System.out.println(palindrome("racecar"));
        System.out.println(palindrome("tacocat"));
        System.out.println(palindrome("hi"));
        int[] nums = {2, 4, 6, -3, 8};
        System.out.println(sumPositive(nums));
    }
    
    // Recursive arrays
    public static int sumPositive (int[] arr) {
        // Base case:
        if(arr.length == 0) {
            return 0;
        }
        // Recursive call:
        // Idea: Check one element in the array, if it's > 0 add that value to the call with smaller array
        // Otherwise make the recursive call with the smaller array
        if (arr[0] > 0) {
           return arr[0] + sumPositive(Arrays.copyOfRange(arr, 1, arr.length));
        }else {
            return sumPositive(Arrays.copyOfRange(arr, 1, arr.length));
        }
    }
}
