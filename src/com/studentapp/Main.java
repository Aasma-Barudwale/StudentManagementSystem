package com.studentapp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Main {

	private static List<Student> studentsList;
	private static Scanner scanner;
	private static Student student;

	public static void main(String[] args) {

		System.out.println("**************** Student Management ****************");
		studentsList = new ArrayList<Student>();
		scanner = new Scanner(System.in);
		while (true) {
			System.out.println("------------ Choose from one of the folowing options : ------------");
			System.out.println("1.Register a Student");
			System.out.println("2.Find a student with Student Id");
			System.out.println("3.List all student information");
			System.out.println("4.List all students in sorted order");
			System.out.println("5.Exit");

			switch (scanner.next()) {
			case "1":
				registerStudents();
				break;
			case "2":
				findStudentwithId(scanner);
				break;
			case "3":
				printAllStudentInfo();
				break;
			case "4":
				printStudentInfoinSortedOrder();
				break;
			case "5":
				exitRegistration();
				break;
			default:
				System.err.println("Invalid option selected, Please choose from 1 to 5");
				break;
			}
		}

	}

	private static void exitRegistration() {
		System.out.println("------Bye-----");
		System.exit(0);

	}

	private static void printStudentInfoinSortedOrder() {
		Comparator<Student> studentComparator = (o1,o2)-> o1.getName().compareTo(o2.getName());
		Collections.sort(studentsList, studentComparator);
		printAllStudentInfo();
	}

	private static void printAllStudentInfo() {
		if (!studentsList.isEmpty()) {
			studentsList.forEach(student -> student.printStudentInformation());
		} else {
			System.err.println("No students found!! Add students to view information");
		}
	}

	private static void findStudentwithId(Scanner scanner2) {
		String studentId = scanner2.next();
		try {
			Student student = studentsList.stream()
					.filter(student2 -> student2.getStudentId().equalsIgnoreCase(studentId)).findFirst()
					.orElseThrow(() -> new RuntimeException("Student not Found!!!"));
			student.printStudentInformation();
		} catch (RuntimeException e) {
			System.err.println("Student not found, Please enter correct studentID!!!");
		}
	}

	private static void registerStudents() {

		System.out.println("Enter Student Name....");
		String name = scanner.next();// If you want to read the input in String format use next()

		System.out.println("Enter Student Age....");
		int age = scanner.nextInt();

		System.out.println("Enter Student Id....");
		String studentId = scanner.next();

		student = new Student(name, age, studentId);
		while (true) {
			System.out.println("Enter courses to be enrolled.... Type Done to exit");
			String courseName = scanner.next();
			if (courseName.equalsIgnoreCase("done")) {
				break;
			}
			student.enrollCourse(courseName);
					}
		studentsList.add(student);
		student.printStudentInformation();

	}

}
