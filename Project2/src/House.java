/**
 * Jonah Moore
 * CSE 271 - B
 * 10/7/2026
 */

/**
 * This class represents a house, and takes in instance properties like an
 * address, price, square feet, number of beds, number of baths, and whether or
 * not it's sold.
 */
public class House {

    // **** INSTANCE PROPERTIES

    private static int startID = 100;

    private int houseID;
    private String address;
    private double price;
    private int sqFeet;
    private int numBedrooms;
    private double numBathrooms;
    private boolean sold;

    // **** CONSTRUCTORS

    /**
     * Workhorse Constructor
     * 
     * @param address
     * @param price
     * @param sqFeet
     * @param numBedrooms
     * @param numBathrooms
     * @param sold
     */
    public House(String address, double price, int sqFeet, int numBedrooms, double numBathrooms, boolean sold) {

        this.setHouseID(startID);
        startID++;
        this.setAddress(address);
        this.setPrice(price);
        this.setSqFeet(sqFeet);
        this.setNumBedrooms(numBedrooms);
        this.setNumBathrooms(numBathrooms);
        this.setSold(sold);

    }

    /**
     * Partial constructor using a String array
     * 
     * @param parts
     */
    public House(String[] parts) {

        this(parts[0], Double.parseDouble(parts[1]), Integer.parseInt(parts[2]), Integer.parseInt(parts[3]),
                Double.parseDouble(parts[4]), parts[5].equals("SOLD"));
    }

    // **** METHODS

    @Override
    public String toString() {
        String soldStr = "";
        if (this.sold) {
            soldStr = "SOLD";
        } else {
            soldStr = "FOR SALE";
        }

        double payment = mortgageRate();

        return String.format("House #%d $%.2f%n%s%n" + "%d beds, %.1f baths, %d sqft.%nEst. Payment: $%.2f/month%n%s",
                this.getHouseID(), this.getPrice(), this.getAddress(), this.getNumBedrooms(), this.getNumBathrooms(),
                this.getSqFeet(), payment, soldStr);
    }

    @Override
    public boolean equals(Object obj) {

        if (obj instanceof House) {

            House h = (House) obj;

            return this.getAddress().equals(h.getAddress()) && this.getSqFeet() == h.getSqFeet()
                    && this.getNumBedrooms() == h.getNumBedrooms() && this.getNumBathrooms() == h.getNumBathrooms();
        }

        return false;
    }

    /**
     * Calculates the mortgage rate with user given down payment
     * 
     * @param downPayment
     * @return The mortgage rate
     */
    public double mortgageRate(double downPayment) {

        if (downPayment >= this.getPrice()) {
            // There is no mortgage if the house is paid in full.
            return 0;
        } else {

            // Calculating mortgage
            double mortgage = 0;
            double principal = this.getPrice() - downPayment;
            double r = 0.005;
            int n = 360;

            mortgage = principal * ((r * Math.pow(1 + r, n)) / (Math.pow(1 + r, n) - 1));
            return mortgage;
        }

    }

    /**
     * Calculates mortgage rate with a given down payment of 20%
     * 
     * @return The mortgage rate
     */
    public double mortgageRate() {

        return mortgageRate(this.getPrice() * 0.20);

    }

    // **** GETTERS AND SETTERS

    /**
     * Returns houseID
     * 
     * @return the houseID
     */
    public int getHouseID() {
        return houseID;
    }

    /**
     * Sets house ID
     * 
     * @param houseID the houseID to set
     */
    private void setHouseID(int houseID) {
        this.houseID = houseID;
    }

    /**
     * Returns the address
     * 
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the address
     * 
     * @param address the address to set
     */
    public void setAddress(String address) {

        if (address.equals("") || address == null) {
            this.address = "00 Blank St., Plainsville, OH 00000";
        } else {
            this.address = address;
        }

    }

    /**
     * Returns the price
     * 
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets the price
     * 
     * @param price the price to set
     */
    public void setPrice(double price) {

        if (price >= 1000) {
            this.price = price;
        } else {
            this.price = 1000;
        }

    }

    /**
     * Returns the square feet
     * 
     * @return the sqFeet
     */
    public int getSqFeet() {
        return sqFeet;
    }

    /**
     * Sets the square feet
     * 
     * @param sqFeet the sqFeet to set
     */
    public void setSqFeet(int sqFeet) {
        if (sqFeet >= 100) {
            this.sqFeet = sqFeet;
        } else {
            this.sqFeet = 100;
        }

    }

    /**
     * Returns the number of bedrooms
     * 
     * @return the numBedrooms
     */
    public int getNumBedrooms() {
        return numBedrooms;
    }

    /**
     * Sets the number of bedrooms
     * 
     * @param numBedrooms the numBedrooms to set
     */
    public void setNumBedrooms(int numBedrooms) {

        if (numBedrooms >= 0) {
            this.numBedrooms = numBedrooms;
        } else {
            this.numBedrooms = 0;
        }

    }

    /**
     * Returns whether or not the house is sold
     * 
     * @return the sold
     */
    public boolean isSold() {
        return sold;
    }

    /**
     * Sets whether or not the house is sold
     * 
     * @param sold the sold to set
     */
    public void setSold(boolean sold) {
        this.sold = sold;
    }

    /**
     * Returns the number of bathrooms
     * 
     * @return the numBathrooms
     */
    public double getNumBathrooms() {
        return numBathrooms;
    }

    /**
     * Sets the number of bathrooms
     * 
     * @param numBathrooms the numBathrooms to set
     */
    public void setNumBathrooms(double numBathrooms) {

        if (numBathrooms >= 0) {
            this.numBathrooms = numBathrooms;
        } else {
            this.numBathrooms = 0;
        }

    }

}
