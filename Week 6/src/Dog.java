
public class Dog {

    // **** INSTANCE PROPERTIES ****
    // Create properties like name, age, gender, breed, isAdopted(boolean),
    // energyLevel

    // modifier type name;
    // Modifier: public or private
    // Instance properties should always be PRIVATE

    // Members of the Dog class, every Dog object will have its copy of these
    // instance variables.

    private String name;
    private int age;
    private String gender;
    private String breed;
    private boolean isAdopted; // true if dog is adopted
    private double energyLevel;

    // **** CONSTRUCTORS ****
    // Constructors should initialize every instance property,
    // even if there are limited/no input parameters

    // Empty constructor: initialize properties to 'default' values
    public Dog() {
        this.name = "";
        this.age = 0;
        this.breed = "";
        this.gender = "";
        this.isAdopted = false;
        this.energyLevel = 1.0;
    }

    // Partial constructor:
    // Only require some of the properties to be passed as input parameters.
    public Dog(String name, int age, String breed) {
        this.name = name;
        this.age = age;
        this.breed = breed;
        this.gender = "";
        this.isAdopted = false;
        this.energyLevel = 1.0;
    }
    // Can set many partial constructors
    
    // Workhorse Constructor: takes in all of the properties and initializes them. NEED
    public Dog(String name, int age, String gender, String breed, boolean isAdopted, double energyLevel) {
        this.name = name;
        this.age = age;
        this.breed = breed;
        this.gender = gender;
        this.isAdopted = isAdopted;
        this.energyLevel = energyLevel;
    }
    
    // Copy constructor:
    // Takes in another object of the same type and makes a deep copy of it
    public Dog(Dog d) {
        this.name = d.name;
        this.age = d.age;
        this.breed = d.breed;    // GOOD PRACTICE IS USING GETTERS , DIDNT MAKE ALL OF THEM
        this.gender = d.gender;
        this.isAdopted = d.isAdopted;
        this.energyLevel =d.energyLevel;
    }
    // **** GETTERS & SETTERS ****
    // Create Actions (Methods)

    // Getter (accessor): retrieves the instance property of that object
    // Setter (mutator): modify/reassign the value of an instance property

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {

        // Only allow age to be set to 1 year greater
        // than the current dog age.
        int currentAge = this.getAge();

        if (age - currentAge > 1) {
            // Exception message if invalid age is attempting to be set.

            throw new IllegalArgumentException("Dog's age can only be set to one year greater than the current age.");
        } else {
            this.age = age;
        }

    }

}
