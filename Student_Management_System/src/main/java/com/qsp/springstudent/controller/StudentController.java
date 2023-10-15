package com.qsp.springstudent.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.qsp.springstudent.dto.Student;
import com.qsp.springstudent.service.StudentService;
import com.qsp.springstudent.util.ResponseStructure;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
//@RequestMapping("/cart")
public class StudentController 
{
	
	@Autowired
	private StudentService service;
	
	@PostMapping("/save")
	@ApiOperation(value ="Save Employee",notes ="This API is use to save the student data into database")
	@ApiResponses(value={@ApiResponse(code=201,message="Data Saved Successfully")})
	public ResponseEntity<ResponseStructure<Student>> saveStudent(@Valid @RequestBody Student student)
	{
		return service.saveStudent(student);
	}
	
	@PutMapping("/update")
	public ResponseEntity<ResponseStructure<Student>> updateStudent(@RequestParam int id,@Valid @RequestBody Student student) 
	{
		return service.updateStudent(id, student);
	}

	
	@PatchMapping("/updatePhone")
	public ResponseEntity<ResponseStructure<Student>> updatePhone(@RequestParam int id,@Valid @RequestParam long phone) 
	{
		return service.updatePhone(id, phone);
	}
	
	@PatchMapping("/updateName")
	public ResponseEntity<ResponseStructure<Student>> updateName(@RequestParam int id,@Valid @RequestParam String name) 
	{
		return service.updateName(id, name);
	}
	
	@PatchMapping("/updateAddress")
	public ResponseEntity<ResponseStructure<Student>> updateAddress(@RequestParam int id, @Valid @RequestParam String address) 
	{
		return service.updateAddress(id, address);
	}
	
	@PatchMapping("/updateSecuredMarks")
	public ResponseEntity<ResponseStructure<Student>> updatesecured_marks(@RequestParam int id,@Valid @RequestParam int marks) 
	{
		return service.updateMarks(id,marks);
	}
	
	@GetMapping("/getbyid")
	public ResponseEntity<ResponseStructure<Student>> findById(@RequestParam int id)
	{
		return service.findById(id);
	}
	
	@GetMapping("/fetchAll")
	public ResponseEntity<ResponseStructure<List<Student>>> getAllStudent()
	{
		return service.getAllStudent();	
	}
	
	@GetMapping("/getbyemail")
	public ResponseEntity<ResponseStructure<Student>> findByEmail(@Valid @RequestParam String email)
	{
		return service.findByEmail(email);
	}
	
	@GetMapping("/getbyphonenumber")
	public ResponseEntity<ResponseStructure<Student>> findByPhone(@Valid @RequestParam long phone)
	{
		return service.findByPhone(phone);
	}
	
	@GetMapping("/findbySecuredmarksGreaterThan")
	public ResponseEntity<ResponseStructure<List<Student>>> findBysecuredmarksGreaterThan(@RequestParam int securedmarks)
	{
		return service.findByMarksGreaterThan(securedmarks);
	}
	
	@GetMapping("/findbySecuredmarksLessThan")
	public ResponseEntity<ResponseStructure<List<Student>>> findBySecureMarksLessThan(@RequestParam int securedmarks)
	{
		return service.findBySecureMarksLessThan(securedmarks);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<ResponseStructure<Student>> deleteStudent(@PathVariable int id) 
	{
		return service.deleteStudent(id);
	}
}
