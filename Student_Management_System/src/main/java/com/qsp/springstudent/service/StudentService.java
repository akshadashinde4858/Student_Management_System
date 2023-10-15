package com.qsp.springstudent.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import com.qsp.springstudent.dao.StudentDao;
import com.qsp.springstudent.dto.Student;
import com.qsp.springstudent.exception.DataNotFound;
import com.qsp.springstudent.exception.EmailNotFound;
import com.qsp.springstudent.exception.IdNotFound;
import com.qsp.springstudent.exception.PhoneNotFound;
import com.qsp.springstudent.util.ResponseStructure;

@Service
public class StudentService 
{
	@Autowired
	StudentDao dao;
	
	public ResponseEntity<ResponseStructure<Student>> saveStudent(Student student)
	{
		int secured_marks=student.getMarks();
		int totalmarks=student.getTotal_marks();
		int a=(secured_marks*100);
		double per=a/totalmarks;
		student.setPercentage(per);
		double percentage= student.getPercentage();
		if(percentage<35)
		{
			student.setGrade("fail");
		}
		else if(percentage>=35 && percentage<60)
		{
			student.setGrade("pass");
		}
		else if(percentage<=60 && percentage<75)
		{
			student.setGrade("First class");
		}
		else if(percentage>=75 && percentage<90)
		{
			student.setGrade("first class with distinction");
		}
		else
		{
			student.setGrade("A+");
		}
		
		ResponseStructure<Student> structure = new ResponseStructure<Student>();
		structure.setMessage("Data Saved Successfully");
		structure.setStatus(HttpStatus.CREATED.value());
		structure.setData(dao.saveStudent(student));
		return new ResponseEntity<ResponseStructure<Student>>(structure,HttpStatus.CREATED);
	}
	
	public ResponseEntity<ResponseStructure<Student>> findById(int id)
	{
		Student Student= dao.getStudent(id);
		ResponseStructure<Student> structure =new ResponseStructure<Student>();
		if(Student!=null)
		{
			structure.setMessage("Data Found");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(Student);
			return new ResponseEntity<ResponseStructure<Student>>(structure, HttpStatus.FOUND);
		}
		else
		{
			throw new IdNotFound("Id not found");
		}
	}
	
	public ResponseEntity<ResponseStructure<List<Student>>> getAllStudent()
	{
		List<Student> students=dao.getAllStudent();
		ResponseStructure<List<Student>> structure=new ResponseStructure<List<Student>>();
		if(students.isEmpty())
		{
			throw new DataNotFound("data not found");
		}
		else
		{
			structure.setMessage("Data Found");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(students);
			return new ResponseEntity<ResponseStructure<List<Student>>>(structure, HttpStatus.FOUND);
		}
		
	}
	
	public ResponseEntity<ResponseStructure<Student>> findByEmail(String email) {
		Student student = dao.findByEmail(email);
		ResponseStructure<Student> structure = new ResponseStructure<Student>();
		if (student != null) {
			structure.setMessage("Data Found");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(student);
			return new ResponseEntity<ResponseStructure<Student>>(structure, HttpStatus.FOUND);
		} 
//		else
//		{
//			structure.setMessage("Data Not Found");
//			structure.setStatus(HttpStatus.NOT_FOUND.value());
//			structure.setData(employee);
//			return new ResponseEntity<ResponseStructure<Employee>>(structure, HttpStatus.NOT_FOUND);
//		}
		else
		{
			throw new EmailNotFound("Email Not Found");
			
		}
	}
	
	public ResponseEntity<ResponseStructure<Student>> findByPhone(long phone)
	{
		Student Student= dao.findByPhone(phone);
		ResponseStructure<Student> structure =new ResponseStructure<Student>();
		if(Student!=null)
		{
			structure.setMessage("Data Found");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(Student);
			return new ResponseEntity<ResponseStructure<Student>>(structure, HttpStatus.FOUND);
		}
		else
		{
			throw new PhoneNotFound("phone not found");
		}
	}
	
	public ResponseEntity<ResponseStructure<List<Student>>> findByMarksGreaterThan(int marks)
	{
		List<Student> Students= dao.findByMarksGreaterThan(marks);
		ResponseStructure<List<Student>> structure =new ResponseStructure<List<Student>>();
		if(Students.isEmpty())
		{
			throw new DataNotFound("Data Not Found");
		}
		else
		{
			structure.setMessage("Data Found");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(Students);
			return new ResponseEntity<ResponseStructure<List<Student>>>(structure,HttpStatus.FOUND);
		}
		
	}
	
	public ResponseEntity<ResponseStructure<List<Student>>> findBySecureMarksLessThan(int secured_marks)
	{
		List<Student> Students= dao.findBySecureMarksLessThan(secured_marks);
		ResponseStructure<List<Student>> structure =new ResponseStructure<List<Student>>();
		if(Students.isEmpty())
		{
			throw new DataNotFound("data not found");
		}
		else
		{
			structure.setMessage("Data Found");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(Students);
			return new ResponseEntity<ResponseStructure<List<Student>>>(structure, HttpStatus.FOUND);
		}
	}
	

	public ResponseEntity<ResponseStructure<Student>> updateStudent(int id,Student student) 
	{
		int secured_marks=student.getMarks();
		int totalmarks=student.getTotal_marks();
		int a=(secured_marks*100);
		double per=a/totalmarks;
		student.setPercentage(per);
		double percentage= student.getPercentage();
		if(percentage<35)
		{
			student.setGrade("fail");
		}
		else if(percentage>=35 && percentage<60)
		{
			student.setGrade("pass");
		}
		else if(percentage<=60 && percentage<75)
		{
			student.setGrade("First class");
		}
		else if(percentage>=75 && percentage<90)
		{
			student.setGrade("first class with distinction");
		}
		else
		{
			student.setGrade("A+");
		}
		
		
		Student student2=dao.getStudent(id);
		ResponseStructure<Student> structure = new ResponseStructure<Student>();
		if(student2!=null)
		{
			structure.setMessage("Data Updated Successfully");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(dao.updateStudent(id, student));
			return new ResponseEntity<ResponseStructure<Student>>(structure,HttpStatus.OK);
		}	
		else
		{
			throw new IdNotFound("id not found");
		}
	}
	
	public ResponseEntity<ResponseStructure<Student>> updateEmail(int id, String email) 
	{
		Student students = dao.getStudent(id);
		ResponseStructure< Student> structure = new ResponseStructure<Student>();
		if(students!=null)
		{
			structure.setMessage("Data Updated ");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(students);
			students.setEmail(email);
			dao.updateStudent(id, students);
			return new ResponseEntity<ResponseStructure<Student>>(structure,HttpStatus.OK);
		}
		else
		{
			throw new IdNotFound("id no found");
		}
		
	}

	public ResponseEntity<ResponseStructure<Student>> updatePhone(int id, long phone) 
	{
		Student Student = dao.getStudent(id);
		ResponseStructure< Student> structure = new ResponseStructure<Student>();
		if(Student!=null)
		{
			Student.setPhone(phone);
			structure.setMessage("Phone Number Updated ");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(Student);
			dao.updateStudent(id, Student);
			return new ResponseEntity<ResponseStructure<Student>>(structure,HttpStatus.OK);
		}
		else
		{
			throw new IdNotFound("id not found");
		}
	}
	
	public ResponseEntity<ResponseStructure<Student>> updateName(int id, String name) 
	{
		Student Student = dao.getStudent(id);
		ResponseStructure< Student> structure = new ResponseStructure<Student>();
		if(Student!=null)
		{
			structure.setMessage("Name Updated ");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(Student);
			Student.setName(name);
			dao.updateStudent(id, Student);
			return new ResponseEntity<ResponseStructure<Student>>(structure,HttpStatus.OK);
		}
		else
		{
			throw new IdNotFound("id not found");
		}
		
	}
	
	public ResponseEntity<ResponseStructure<Student>> updateAddress(int id, String address) 
	{
		Student Student = dao.getStudent(id);
		ResponseStructure< Student> structure = new ResponseStructure<Student>();
		if(Student!=null)
		{
			structure.setMessage("Address Updated ");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(Student);
			Student.setAddress(address);
			dao.updateStudent(id, Student);
			return new ResponseEntity<ResponseStructure<Student>>(structure,HttpStatus.OK);
			
		}
		else
		{
			throw new IdNotFound("id not found");
		}

	}
	
	public ResponseEntity<ResponseStructure<Student>> updateMarks(int id, int marks) 
	{
		Student student = dao.getStudent(id);
		ResponseStructure< Student> structure = new ResponseStructure<Student>();
		if(student!=null)
		{
			structure.setMessage("secured marks Updated ");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(student);
			student.setMarks(marks);
			dao.updateStudent(id, student);
			return new ResponseEntity<ResponseStructure<Student>>(structure,HttpStatus.OK);
		}
		else
		{
			throw new IdNotFound("id not found");
		}
		
	}
	
	
	public ResponseEntity<ResponseStructure<Student>> deleteStudent(int id) 
	{
		Student student1 = dao.getStudent(id);
		ResponseStructure<Student> structure = new ResponseStructure<Student>();
		if(student1!=null)
		{
			structure.setMessage("Data deleted Successfully");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(student1);
			return new ResponseEntity<ResponseStructure<Student>>(structure,HttpStatus.OK);
		}	
		else
		{
			throw new IdNotFound("id not found");
		}
	}	
}

