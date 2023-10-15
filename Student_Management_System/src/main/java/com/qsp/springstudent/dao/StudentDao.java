package com.qsp.springstudent.dao;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qsp.springstudent.dto.Student;
import com.qsp.springstudent.repo.studentRepo;

@Repository
public class StudentDao 
{
	@Autowired
	private studentRepo repo;
	
	public Student saveStudent(Student student)
	{
		return repo.save(student);
	}
	
	public Student getStudent(int id)
	{
		Optional<Student> optional=repo.findById(id);
		if(optional.isPresent())
		{
			Student student=optional.get();
			return student;
		}
		else
		{
			return null;
		}
		
	}
	
	public List<Student> getAllStudent()
	{
		return repo.findAll();
	}
	
	public Student findByEmail(String email)
	{
		return repo.findStudentByEmail(email);
	}
	
	public Student findByPhone(long phone)
	{
//		return repo.findStudentByPhone(phone);
		return repo.findStudentByPhone(phone);
	}
	
	public Student updateStudent(int id,@Valid Student student)
	{
		Optional<Student> optional=repo.findById(id);
		if(optional.isPresent())
		{
			student.setId(id);
			return repo.save(student);
		}
		else
		{
			return null;
		}
	}
	
	public Student deleteStudent(int id)
	{
		Optional<Student> optional=repo.findById(id);
		if(optional.isPresent())
		{
			repo.deleteById(id);
			return optional.get();
		}
		return null;
	}


	public List<Student> findByMarksGreaterThan(int secured_marks)
	{
		return repo.findStudentByMarksGreaterThan(secured_marks);
	}
//
	public List<Student> findBySecureMarksLessThan(int secured_marks)
	{
		return repo.findStudentByMarksLessThan(secured_marks);
	}
}
