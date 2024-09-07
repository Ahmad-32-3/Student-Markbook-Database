public class Student {

  // 4 private fields
  private String firstName;
  private String lastName;
  private double testOne;
  private double testTwo;
  private double testThree;

  // Constructor with 4 parameters 
  public Student (String firstName, String lastName, double testOne, double testTwo, double testThree) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.testOne = testOne;
    this.testTwo = testTwo;
    this.testThree = testThree;
  }

  // get students last name
  public String getlastName () {
    return lastName;
  }

  // get students first name
  public String getfirstName () {
    return firstName;
  }

  // get students test 1 grade
  public double getTestOne () {
    return testOne;
  }

  // get students test 2 grade
  public double getTestTwo () {
    return testTwo;
  }
  
  // get students test 3 grade
  public double getTestThree () {
    return testThree;
  }

  // set students test one grade
  public void setTestTwo(double testTwo) {
    this.testTwo = testTwo;
  }

  // set students test three grade
  public void setTestThree(double testThree) {
    this.testThree = testThree;
  }

  // set students test three grade
  public void setTestOne(double testOne) {
    this.testOne = testOne;
  }

  // Instance method to calculate average
  public double average() {
    return (this.testOne + this.testTwo + this.testThree) / 3;
  }

  // toString method
  public String toString() {
      return String.format("%-15s %-15s %-15.2f %-15.2f %-15.2f\n",
          this.firstName, this.lastName, this.testOne, this.testTwo, this.testThree);
  }  
}