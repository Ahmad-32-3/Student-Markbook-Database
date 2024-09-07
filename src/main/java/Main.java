/*
Filename: W17-18-19U5A1_MuhammadAhmad_Markbook
Author:  Muhammad Ahmad
Date: Monday, June 06, 2024
Purpose: To create a markbook database program that simulates a teacher digital markbook for one class of students.
*/

// import libraries
import java.util.*;
import java.io.*;

// class main
public class Main {
  public static void main(String[] args) {

    // Create Array List of Arrays
    ArrayList<Student> dataBase = new ArrayList<Student>();

    // Initialize variables
    int optionChoice;
    String firstName;
    String lastName;
    double testOne;
    double testTwo;
    double testThree;
    String userInput;
    Student studentNameFound;

    // Set scanner to keyedInput
    Scanner keyedInput = new Scanner(System.in);

    // Read text file
    BufferedReader br = null;
    try {
        br = new BufferedReader(new FileReader("studentinfo.txt"));
        while ((firstName = br.readLine()) != null) {  
          // fulfill all parameters of a student (firstName, lastName, test grades)
          lastName = br.readLine();
          testOne = Double.parseDouble(br.readLine());
          testTwo = Double.parseDouble(br.readLine());
          testThree = Double.parseDouble(br.readLine());
          // create student
          Student textStudent = new Student(firstName, lastName, testOne, testTwo, testThree);
          dataBase.add(textStudent);
        }
    } catch (IOException e) {
        e.printStackTrace();
    } finally {
        try {
            br.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    // While loop to keep giving user a menu of options
    while (true) {

      // print out intro
      intro();

      // Take option choice
      optionChoice = keyedInput.nextInt();

      // Switch statement to take user to different options
      switch (optionChoice) {
        // Add student
        case 1:
          addStudent(dataBase);
          break;
          
        // Display students
        case 2:
          displayStudent(dataBase);
          break;
          
        // Edit student info
        case 3:
          editStudent(dataBase);
          break;
          
        // Calculate class average
        case 4:
          classAverage(dataBase);
          break;
          
        // Calculate student's average
        case 5:
          studentAverage(dataBase);
          break;
          
        // Delete student
        case 6:
          deleteStudent(dataBase);
          break;

        // sort students by last name
        case 7: 
          // copy of arraylist
          ArrayList<Student> copyofdataBase = new ArrayList<Student>(dataBase);
          // bubble sorting method
          bubbleSort(copyofdataBase);
          // display copy
          displayStudent(copyofdataBase);
          break;
          
        // search algorithm
        case 8:
          
          // copy of arraylist
          ArrayList<Student> copyofdataBase2 = new ArrayList<Student>(dataBase);
          
          // Sort database
          bubbleSort(copyofdataBase2);
          
          // ask for last name
          System.out.println("Write the last name of the person you want to search for: ");
          userInput = keyedInput.nextLine();
          
          // take input again (bug was not letting console take input)
          userInput = keyedInput.nextLine();
          
          // Use binary search algorithm
          studentNameFound = binarySearch(copyofdataBase2, 0, copyofdataBase2.size() - 1, userInput);

          // Check if name is found by seeing if it returned null or not
          if (studentNameFound != null) {
              System.out.println("The following information was found:");
              // Print headers
              System.out.format("%n%-15s %-15s %-15s %-15s %-15s%n", "First Name", "Last Name", "Test 1",
                  "Test 2", "Test 3");
              // print student name found
              System.out.println(studentNameFound.toString());
          } else {
              System.out.println("No student was found with the name: " + userInput);
          }
          break;
        // Exit program
        case 9:
          System.out.println("You have exited the program, have a great day!");
          System.exit(0);
          break;
        // when a number not between 1 and 9 is input, output invalid input message
        default:
          System.out.println("Invalid input. Please enter a number between 1 and 9\n");
      }

    }

  }

  // Intro
  public static void intro() {
    // Give user options
    System.out.print(
        "\nInput the corrosponding number for the following executables (1-7)\n" +
            "1. Add a student to the markbook database\n" +
            "2. Display all markbook database information\n" +
            "3. Edit student test marks\n" +
            "4. Calculate class average\n" +
            "5. Calculate a specific student's test average\n" +
            "6. Delete student from database\n" +
            "7. Sort students by last name\n" +
            "8. Search student information by last name(A-Z)\n" +
            "9. Exit program\n\n" +
            "Enter the number: ");
  }

  // Adding Students
  public static void addStudent(ArrayList<Student> dataBase) {
    // Initialize variables
    String firstName;
    String lastName;
    double testOne;
    double testTwo;
    double testThree;

    // Set scanner to keyedInput
    Scanner keyedInput = new Scanner(System.in);

    // Take inputs for names and test scores
    System.out.println(
        "To add a member to the database, you must enter their, first name, last name and the three test marks");

    System.out.println("\nEnter the first name: ");
    firstName = keyedInput.next();

    System.out.println("\nEnter the last name: ");
    lastName = keyedInput.next();

    // Ask for test marks
    System.out.println("\nEnter the test mark for test 1: ");
    testOne = keyedInput.nextDouble();

    System.out.println("\nEnter the test mark for test 2: ");
    testTwo = keyedInput.nextDouble();

    System.out.println("\nEnter the test mark for test 3: ");
    testThree = keyedInput.nextDouble();

    // Create a student with the above inputs and store it to the database
    Student newStudent = new Student(firstName, lastName, testOne, testTwo, testThree);
    dataBase.add(newStudent);
  }

  // Display Students
  public static void displayStudent(ArrayList<Student> dataBase) {

    // Print headers
    System.out.format("%n%-15s %-15s %-15s %-15s %-15s%n", "First Name", "Last Name", "Test 1",
        "Test 2", "Test 3");

    for (int i = 0; i < dataBase.size(); i++) {
      Student displayStudent = dataBase.get(i);
      // Print
      System.out.println(displayStudent.toString());
    }
  }
  
  // Edit student's test grades
  public static void editStudent(ArrayList<Student> dataBase) {

    // Initialize local variables and scanner
    String lastName;
    double testOne;
    double testTwo;
    double testThree;
    Boolean cFound = false;
    Scanner keyedInput = new Scanner(System.in);

    // Take input for student record
    System.out.println("Please input the last name of the user who's record you want to edit: ");
    lastName = keyedInput.next();

    // Find the array element the user is in
    for (int element = 0; element < dataBase.size(); element++) {
      Student editStudent = dataBase.get(element);
      if (editStudent.getlastName().equals(lastName)) {

        // Set to true to signal that student was found in databse
        cFound = true;

        // Get test scores
        System.out.println("The last name of the student whose test scores you are editing is: " + lastName);

        System.out.println("Adjusted test one score: ");
        testOne = keyedInput.nextDouble();

        System.out.println("Adjusted test two score: ");
        testTwo = keyedInput.nextDouble();

        System.out.println("Adjusted test three score: ");
        testThree = keyedInput.nextDouble();

        // Set new test scores
        editStudent.setTestOne(testOne);
        editStudent.setTestTwo(testTwo);
        editStudent.setTestThree(testThree);
      }
    }

    // If cFound is never changed to true, then that means that no user was found
    if (cFound == false) {
      System.out.println("\nThere is no user with the last name you inputted in the database");
    }
  }

  // Deleting students
  public static void deleteStudent(ArrayList<Student> dataBase) {
    // Initialize variables and scanner
    String lastName;
    Boolean cFound;
    Scanner keyedInput = new Scanner(System.in);

    // Ask user to enter last name 
    System.out.println("To delete a student's record from the database, you must input their last name: ");
    lastName = keyedInput.next();
    
    // Set to false as default
    cFound = false;

    // Iterate through dataBase
    for (int element = 0; element < dataBase.size(); element++) {

        // create student object
        Student deleteStudent = dataBase.get(element);

        // check if last name is in database and then remove it
        if (deleteStudent.getlastName().equals(lastName)) {
          dataBase.remove(element);
          // set to true to signal that the student is found in the database
          cFound = true;
          System.out.println(lastName +" has been removed from the database");
        }
      }
    // If cFound is never changed to true, then that means that no employee was found
    if(cFound==false) {
      System.out.println("\nThere is no student with the last name you inputted in the database");
    }
  }

  // Class average
  public static void classAverage(ArrayList<Student> dataBase) {
    // Initialize variables
    double classAverage = 0;

    for (int element = 0; element < dataBase.size(); element++) {
      // Create student object
      Student classAverageStudent = dataBase.get(element);
      // Add individual averages to class average
      classAverage += classAverageStudent.average();
    }

    // calculate class average
    classAverage = classAverage / dataBase.size();

    // print class average
    System.out.printf("The class average is: %.2f", classAverage);
    System.out.print("%\n");
    
  }

  // Student average
  public static void studentAverage(ArrayList<Student> dataBase) {
    // Initialize local variables and scanner
    String lastName;
    Scanner keyedInput = new Scanner(System.in);
    boolean cFound = false;

    // ask for student's last name
    System.out.println("Last name of student whose average you want to find: ");
    lastName = keyedInput.next();

    // Find the array element the student is in
    for (int element = 0; element < dataBase.size(); element++) {
      Student averageStudent = dataBase.get(element);
      if (averageStudent.getlastName().equals(lastName)) {
        // tell user average of student
        System.out.printf("The average of " + lastName + " is: " + "%.2f", averageStudent.average());
        System.out.print("%\n");
        cFound = true;
      }
    }

    if (cFound == false) {
      System.out.println("There is no student with the last name you inputted in the database");
    }
    
  }

  // bubble sort
  public static void bubbleSort(ArrayList<Student> dataBase) {
    // YT video: https://youtu.be/F13_wsHDIG4?si=a02NUaK2dcN_3jPk
    int i, j;
    Student temp;
    String lastName;
    String lastName2;

    for (i = 0; i < dataBase.size() - 1; i++) {

      // use for loops to check if value is greater than the next value
      for (j = 0; j < dataBase.size() - 1 - i; j++) {

        // get last names from dataBase
        lastName = dataBase.get(j).getlastName();
        lastName2 = dataBase.get(j + 1).getlastName();

        // use compareTo to sort by alphabet (positive value means it should go first)
        if (lastName.compareTo(lastName2) > 0) {
          // set temp to element 
          temp = dataBase.get(j);
          // swap values (use set instead of = sign)
          dataBase.set(j, dataBase.get(j+1));
          dataBase.set(j + 1, temp);
        }
      }
    }
  }

  // Binary search algorithm
  public static Student binarySearch(ArrayList<Student> copyofdataBase2, int left, int right, String userInput) {
      
    int middle;
    String midlastName;
    int compareSearch;
    String midLastName;
    Student midStudent;

      while (left <= right) {
          middle = (left + right) / 2;

          // Get student for comparison
          midStudent = copyofdataBase2.get(middle);
          midLastName = copyofdataBase2.get(middle).getlastName();

          // use compareTo to see if its the same or not (postive means its to the left and negative means its to the right)
          compareSearch = midLastName.compareTo(userInput);

          // if the middle value is the same as the user input, return the middle value
          if (compareSearch == 0) {
              return midStudent;
          } 
            // if compareSearch is positive then search left side
            else if (compareSearch > 0) {
              right = middle - 1;
          } 
            // if compareSearch is negative then search right side
            else if (compareSearch < 0) {
              left = middle + 1;
          }
      }
      return null;
  }
}