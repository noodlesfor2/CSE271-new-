
public class DogShelter {
    
    public static void main(String[] args) {
        // Creating dog objects:
        
        Dog d = new Dog (); // calling our empty constructor
        
        System.out.println(d.getName());
        System.out.println(d.getAge());
        
        // Using the partial constructor:
        Dog d2 = new Dog("Forest", 8 , "beagle");
        
        System.out.println(d2.getName());
        System.out.println(d2.getAge());
        
        // Using the workhorse constructor:
        Dog d3 = new Dog("Phoebe", 8, "female","mutt", true, 2.0);
        
        // Using copy constructor:
        Dog d2Copy = new Dog (d2);
          
    }                                                                 
}
