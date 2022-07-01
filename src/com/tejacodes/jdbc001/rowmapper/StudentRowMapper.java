package com.tejacodes.jdbc001.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.tejacodes.jdbc001.model.Student;

public class StudentRowMapper implements RowMapper<Student> {
	
	// This rs will contain one row at a time. This mapRow method executes row by row (No. of times executed = No. of rows)
	// This is the major diff between RowMapper and ResultSetExtractor.
	// rs in ResultSetExtractor will fetch all rows at a once and stores in rs
	@Override
	public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Student student = new Student();
		
		student.setRollno(rs.getInt("ROLLNO"));
		student.setName(rs.getString("NAME"));
		student.setAddress(rs.getString("ADDRESS"));
		
		return student;
	}

}
