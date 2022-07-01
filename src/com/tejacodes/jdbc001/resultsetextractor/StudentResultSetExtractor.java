package com.tejacodes.jdbc001.resultsetextractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.tejacodes.jdbc001.model.Student;

public class StudentResultSetExtractor implements ResultSetExtractor<List<Student>> {

	//This rs object has all the rows fetched at once
	
	@Override
	public List<Student> extractData(ResultSet rs) throws SQLException, DataAccessException {
		
		List<Student> students = new ArrayList<>();
		
		while(rs.next())
		{
			Student student = new Student();
			
			student.setRollno(rs.getInt("ROLLNO"));
			student.setName(rs.getString("NAME"));
			student.setAddress(rs.getString("ADDRESS"));
			
			students.add(student);
		}
			
		return students;
	}

}
