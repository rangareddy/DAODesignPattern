/**
 * 
 */
package com.ranga;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * StudentDAOImpl.java
 * @author ranga
 * Mar 14, 2014
 */
public class StudentDAOImpl implements StudentDAO {
		
	@Override
	public void createStudent(Student student) throws Exception {		
		Connection con = null; // Declare Resources
		PreparedStatement pstmt = null;
		try {
			con = DBUtil.getConnection(); // Open Connection
			String sql = "INSERT INTO STUDENT(id, name, age) VALUES(?, ?, ?)";
			pstmt = con.prepareStatement(sql); // Create Statement
			pstmt.setLong(1, student.getId()); // Set Parameters
	        pstmt.setString(2, student.getName());
	        pstmt.setInt(3, student.getAge());
	        DBUtil.beginTransaction(); // Begin Transaction
	        int result = pstmt.executeUpdate(); // Execute Statement
	        if (result != 0) {
	            DBUtil.commit(); // Commit Transaction
	        } else {
	            DBUtil.rollback(); // Roleback Transaction
	        }
		} finally {
			DBUtil.closeDBUtil(null, pstmt, con);  // Cleanup Resources	
		}            
	}

	
	@Override
	public void updateStudent(Student student) throws Exception {
		// Declare Resources
		Connection con = null; 
		PreparedStatement pstmt = null;
		try {
			con = DBUtil.getConnection(); // Open Connection
			String sql = "UPDATE STUDENT SET name=?, age=? WHERE id =?";	        
			pstmt = con.prepareStatement(sql); // Create Statement 
			pstmt.setString(1, student.getName()); // Set Parameters
		    pstmt.setInt(2, student.getAge());
		    pstmt.setLong(3, student.getId());
	        DBUtil.beginTransaction(); // Begin Transaction
	        int result = pstmt.executeUpdate(); // Execute Statement
	        if (result != 0) {
	            DBUtil.commit(); // Commit Transaction
	        } else {
	            DBUtil.rollback(); // Roleback Transaction
	        }
		} finally {
			DBUtil.closeDBUtil(null, pstmt, con); // Cleanup Resources	
		}            
	}
	
	@Override
	public Student getStudent(long id) throws Exception {	
		String sql = "SELECT * FROM STUDENT WHERE id = " + id;
        Connection con = DBUtil.getConnection();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        DBUtil.beginTransaction();
        Student student = null;
        if (rs.next()) {
            student = new Student();
            student.setId(rs.getLong(1));
            student.setName(rs.getString(2));
            student.setAge(rs.getInt(3));
            DBUtil.commit();
        } else {
            DBUtil.rollback();
        }
        DBUtil.closeDBUtil(rs, stmt, con);
        return student;		
	}

	@Override
	public void deleteStudent(Student student) throws Exception {		
		Connection con = DBUtil.getConnection();
        String sql = "DELETE FROM STUDENT WHERE id = ?";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setLong(1, student.getId());
        DBUtil.beginTransaction();
        int res = pstmt.executeUpdate();
        if (res != 0) {
            DBUtil.commit();
        } else {
            DBUtil.rollback();
        }
        DBUtil.closeDBUtil(null, pstmt, con);
	}

	@Override
	public List<Student> getAllStudents() throws Exception {
		String sql = "SELECT * FROM STUDENT";
        Connection con = DBUtil.getConnection();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        List<Student> students = new ArrayList<Student>();         
        while(rs.next()) {
        	Student student = new Student();
            student.setId(rs.getLong(1));
            student.setName(rs.getString(2));
            student.setAge(rs.getInt(3));
            students.add(student);            
        }         
        DBUtil.closeDBUtil(rs, stmt, con);
        return students;		
	}	
}