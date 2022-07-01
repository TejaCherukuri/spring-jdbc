package com.tejacodes.jdbc001.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.tejacodes.jdbc001.model.Student;

public class Test {

	public static void main(String[] args) {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		
		StudentDAOImpl studentDAOImpl = context.getBean(StudentDAOImpl.class);
		
		((ClassPathXmlApplicationContext) context).close();
		
		Student student = new Student();
		
		student.setRollno(1);
		student.setName("BL");
		student.setAddress("Guntur");
		
		List<Student> students = setUpStudentTable();
		
		//Clean up the table
		studentDAOImpl.cleanUp();
		
		//Insert one record
		studentDAOImpl.insert(student);
		
		//Insert multiple records at once
		studentDAOImpl.insertBatch(students);
		
		//Delete Record by Roll No
		studentDAOImpl.deleteByRollNo(8);
		
		//Update Address of a student by rollNo
		studentDAOImpl.updateAddress(5, "Vja");
		
		//Retrieving all students using Custom RowMapper
		System.out.println("*****Fetching using CustomRowMapper*****");
		List<Student> studentsResult = studentDAOImpl.getStudentsUsingCustomRowMapper();
		print(studentsResult);
		
		//Get Student By RollNo using BeanPropertyRowMapper class (Pre defined spring class)
		System.out.println("*****Fetching using BeanPropertyRowMapper*****");
		Student studentResult = studentDAOImpl.getStudentByRollNoUsingRowMapper(1);
		System.out.println(studentResult);
		
		//Retrieving all students using Custom RowMapper
		System.out.println("*****Fetching using ResultSetExtractor*****");
		List<Student> studentsResultList = studentDAOImpl.getStudentsUsingResultSetExtractor();
		print(studentsResultList);

		
	}

	private static void print(List<Student> students) {
		
		for(Student student : students)
		{
			System.out.println(student);
		}
		
	}

	private static List<Student> setUpStudentTable() {
		
		List<Student> students = new ArrayList<>();
		
		Student student1 = new Student();
		student1.setRollno(2);
		student1.setName("GKK");
		student1.setAddress("Guntur");
		
		Student student2 = new Student();
		student2.setRollno(3);
		student2.setName("Praveen");
		student2.setAddress("Tenali");
		
		Student student3 = new Student();
		student3.setRollno(4);
		student3.setName("Hemanth");
		student3.setAddress("Vja");
		
		Student student4 = new Student();
		student4.setRollno(5);
		student4.setName("Teja");
		student4.setAddress("Vuyyuru");
		
		Student student5 = new Student();
		student5.setRollno(6);
		student5.setName("Harsha");
		student5.setAddress("Vja");
		
		Student student6 = new Student();
		student6.setRollno(7);
		student6.setName("Balu");
		student6.setAddress("Repalle");
		
		Student student7 = new Student();
		student7.setRollno(8);
		student7.setName("Lokesh");
		student7.setAddress("Tenali");
		
		students.add(student1);
		students.add(student2);
		students.add(student3);
		students.add(student4);
		students.add(student5);
		students.add(student6);
		students.add(student7);
		
		return students;
		
	}

}
