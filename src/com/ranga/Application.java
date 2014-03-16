package com.ranga;
import java.util.List;
/**
 * Application.java
 * @author ranga
 * Mar 14, 2014
 */
public class Application {
	public static void main(String[] args) throws Exception {
		StudentDAO studentDAO = new StudentDAOImpl();
		
		Student student1 = new Student(100l, "Ranga", 25);
		Student student2 = new Student(101l, "Raja", 50);
		Student student3 = new Student(102l, "Vasu", 50);
		Student student4 = new Student(103l, "Manoj", 10);
		Student student5 = new Student(104l, "Yasu", 8);
		
		// creating multiple students
		studentDAO.createStudent(student1);
		studentDAO.createStudent(student2);
		studentDAO.createStudent(student3);
		studentDAO.createStudent(student4);
		studentDAO.createStudent(student5);
		// retriving all students		
		List<Student> allStudents = studentDAO.getAllStudents();
		System.out.println("Total Students : "+allStudents.size());
		for (Student stu : allStudents) {
			System.out.println(stu);	
		}
		
		// retriving student record
		Student student = studentDAO.getStudent(102l);
		System.out.println("Student Info : "+student);
		
		// updating record
		student.setAge(50);
		student.setName("Raja Sekhar Reddy");
		
		studentDAO.updateStudent(student);
		student = studentDAO.getStudent(102l);
		System.out.println("Updated Student Info : "+student);
		
		// deleting record		
		studentDAO.deleteStudent(student);
		
		allStudents = studentDAO.getAllStudents();
		System.out.println("Total Students : "+allStudents.size());
		for (Student stu : allStudents) {
			System.out.println(stu);	
		}
	}
}