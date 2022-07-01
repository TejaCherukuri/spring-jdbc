package com.tejacodes.jdbc001.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.tejacodes.jdbc001.dao.StudentDAO;
import com.tejacodes.jdbc001.model.Student;
import com.tejacodes.jdbc001.resultsetextractor.StudentResultSetExtractor;
import com.tejacodes.jdbc001.rowmapper.StudentRowMapper;

@Repository
public class StudentDAOImpl implements StudentDAO { 

	// The below jdbcTemplate configuration is made on beans.xml so we can just wire the dependency
	/*
	JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
	
	private DataSource getDataSource()
	{
		
		String url ="jdbc:mysql://127.0.0.1:3306/School";
		String userName="root";
		String password="rootroot";
		
		DataSource dataSource = new DriverManagerDataSource(url,userName,password);
		
		return dataSource;
	}
	*/
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public void insert(Student student) {
		
		String sql = "INSERT INTO STUDENT VALUES (?,?,?)";
		
		Object[] arr = {student.getRollno(),student.getName(),student.getAddress()};
		
		int result = jdbcTemplate.update(sql,arr);
		
		System.out.println("No of rows inserted: "+result);
		
	}

	@Override
	public void insertBatch(List<Student> students) {
		
		String sql = "INSERT INTO STUDENT VALUES (?,?,?)";
		
		List<Object[]> objectArrayList = new ArrayList<>();
		
		for(Student student: students)
		{
			Object[] arr = {student.getRollno(),student.getName(),student.getAddress()};
			objectArrayList.add(arr);
			
		}
		
		jdbcTemplate.batchUpdate(sql, objectArrayList);
		
		System.out.println("Batch Update Done");
		
		
	}
	
	@Override
	public void deleteByRollNo(int rollNo) {
		
		String sql = "DELETE FROM STUDENT WHERE ROLLNO=?";
		
		jdbcTemplate.update(sql, rollNo);
		
		System.out.println("Deletion Done - Student deleted with rollno "+rollNo);
	}
	
	@Override
	public void updateAddress(int rollNo, String address) {
		
		String sql = "UPDATE STUDENT SET ADDRESS=? WHERE ROLLNO=?";
		
		Object[] args = {address,rollNo};
		
		jdbcTemplate.update(sql, args);
		
		System.out.println("Update Done");
	}
	
	@Override
	public List<Student> getStudentsUsingCustomRowMapper() {
		
		String sql = "SELECT * FROM STUDENT";
		
		List<Student> students = jdbcTemplate.query(sql, new StudentRowMapper());
		
		return students;
	}
	
	
	//BeanPropertyRowMapper maps column names with your java pojo field names. If they are not same, use field names as alias names
	//while retrieving column names
	// Eg: SELECT STUDENT_NAME as name from STUDENT;
	@Override
	public Student getStudentByRollNoUsingRowMapper(int rollNo) {
		
		String sql = "SELECT * FROM STUDENT WHERE ROLLNO=?";
		
		Student student = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<Student>(Student.class), rollNo);
		
		return student;
	}
	
	@Override
	public List<Student> getStudentsUsingResultSetExtractor() {
		
		String sql = "SELECT * FROM STUDENT";
		
		List<Student> students = jdbcTemplate.query(sql, new StudentResultSetExtractor());
		
		return students;
	}

	@Override
	public void cleanUp() {
		
		String sql = "TRUNCATE TABLE STUDENT";
		
		jdbcTemplate.execute(sql);
		
		System.out.println("Clean Up Done");
	}


}
