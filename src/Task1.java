//STUDENT GRADE TRACKER

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

class StudentGradeTracker{

    StudentGradeTracker(){
        Scanner sc = new Scanner(System.in);
        ArrayList<Double> grades = new ArrayList<>();

        int numOfStudents;
        // Loop until a valid integer is entered for the number of students
        while(true) {
            try {
                System.out.println("***----------------------------Student Grade Tracker----------------------------***\n");
                //ask the teacher how many grades you want to enter
                System.out.print("Enter the number of students : ");
                numOfStudents = sc.nextInt();
                if (numOfStudents <= 0) {
                    throw new IllegalArgumentException("number of student must be a positive");
                }
                break; // Exit the loop if input is valid
            }catch (InputMismatchException e) {
                System.out.println("plz enter grades as Integers!");
                sc.next(); // Clear the invalid input
            }catch(IllegalArgumentException e){
            System.out.println(e.getMessage());
            }
        }

        //Input grades from the teacher
        for(int i=0; i<numOfStudents; i++){
            while(true){
                try{
                    System.out.print("Enter grade for student "+ (i+1) + ": ");
                    //grades.add(sc.nextDouble()); //add grade to the  Arraylist
                    double grade = sc.nextDouble();

                    // Validate the grade range
                    if (grade < 0 || grade > 100) {
                        throw new IllegalArgumentException("Grade must be between 0 and 100!");
                    }
                    grades.add(grade);
                    break;  // Exit the loop if input is valid
                }catch (InputMismatchException e){
                    System.out.println("Plz enter grade as a number!");
                    sc.next(); // Clear the invalid input
                }catch (IllegalArgumentException e){
                    System.out.println(e.getMessage());
                }
            }
        }

        // Calculate and display the average, highest, and lowest scores
        System.out.println("\nThe average grade is : "+avg(grades));
        highestLowestScores(grades);
    }

    public double avg(ArrayList<Double> grades){
        double total = 0;
        for(double grade: grades){
            total += grade; // add the grade to the total
        }
        return  total / grades.size(); // returns the average
    }
    public void highestLowestScores(ArrayList<Double> grades){
        if(grades.isEmpty()){
            System.out.println("No grades is available!");
            return;
        }

        //calculate the highest and lowest scores
        double highest = grades.getFirst();
        double lowest = grades.getFirst();

        for(double grade: grades){
            if(grade > highest){
                highest = grade; //update the highest
            }
            if (grade < lowest) {
                lowest = grade; //update the lowest
            }
        }
        //display the highest and lowest grade
        System.out.println("The highest grade is : "+highest);
        System.out.println("The lowest grade is : "+lowest);

        System.out.println("\n\n-----------------------------------------------------------------------------------");
        System.out.println("Thanks for Using\ndevloped by Muhammad Ali");
        System.out.println("-----------------------------------------------------------------------------------");
    }
}

public class Task1 {

    public static void main(String[] args) {
        new StudentGradeTracker();
    }
}