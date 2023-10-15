package com.qsp.springstudent.exception;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.xml.crypto.Data;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.qsp.springstudent.util.ResponseStructure;



@RestControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler
{

	@ExceptionHandler(IdNotFound.class)
	public ResponseEntity<ResponseStructure<String>> idNotFoundExecepEntity(IdNotFound id)
	{
		ResponseStructure<String> structure= new ResponseStructure<>();
		structure.setMessage(id.getMessage());
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData("Student with given id not found");
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(PhoneNotFound.class)
	public ResponseEntity<ResponseStructure<String>> phoneNotFoundExecepEntity(PhoneNotFound phone)
	{
		ResponseStructure<String> structure= new ResponseStructure<>();
		structure.setMessage(phone.getMessage());
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData("Student with given phone not found");
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}

	
	@ExceptionHandler(EmailNotFound.class)
	public ResponseEntity<ResponseStructure<String>> emailNotFoundExecepEntity(EmailNotFound email)
	{
		ResponseStructure<String> structure= new ResponseStructure<>();
		structure.setMessage(email.getMessage());
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData("Student with given email not found");
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(DataNotFound.class)
	public ResponseEntity<ResponseStructure<String>> dataNotFound(DataNotFound data)
	{
		ResponseStructure<String> structure = new ResponseStructure<>();
		structure.setMessage(data.getMessage());
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData("Data Not Avilable");
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> noSuchAMethodException(NoSuchFieldException exception)
	{
		ResponseStructure<String> structure = new ResponseStructure<>();
		structure.setMessage(exception.getMessage());
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData("Data Not Avilable");
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) 
	{
		List<ObjectError> errors=ex.getAllErrors();
		Map<String, String> map=new HashMap<String,String>();
		for(ObjectError objectError:errors)
		{
			FieldError error=(FieldError) objectError;
			String field=error.getField();
			String message=error.getDefaultMessage();
			map.put(field, message);
		}
		return new ResponseEntity<Object>(map,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<Object> constraintViolationException(ConstraintViolationException ex)
	{
		Set<ConstraintViolation<?>> set= ex.getConstraintViolations();
		List<String> list= new ArrayList<>();
		for(ConstraintViolation<?> constraintViolationException: set)
		{
			String name=constraintViolationException.getMessage();
			list.add(name);
		}
		return new ResponseEntity<Object>(list,HttpStatus.BAD_REQUEST);
	}
}


