package com.qsp.springstudent.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qsp.springstudent.dto.Student;

public interface studentRepo extends JpaRepository<Student, Integer>{

	Student findStudentByEmail(String email);

	Student findStudentByPhone(long phone);
	
	List<Student> findStudentByMarksGreaterThan(int marks);
	
	List<Student> findStudentByMarksLessThan(int marks);

}
