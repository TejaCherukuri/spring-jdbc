package com.tejacodes.jdbc001.dao;

import java.util.List;

import com.tejacodes.jdbc001.model.Student;

public interface StudentDAO {
	
	void insert(Student student);
	
	void insertBatch(List<Student> students);
	
	void deleteByRollNo(int rollNo);
	
	void updateAddress(int rollNo,String address);
	
	List<Student> getStudentsUsingCustomRowMapper();
	
	Student getStudentByRollNoUsingRowMapper(int rollNo);
	
	List<Student> getStudentsUsingResultSetExtractor();
	
	void cleanUp();

}
