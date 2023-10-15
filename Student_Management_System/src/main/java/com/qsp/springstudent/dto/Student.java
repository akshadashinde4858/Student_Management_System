package com.qsp.springstudent.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.aspectj.weaver.tools.Trace;
import org.springframework.stereotype.Component;

@Entity
public class Student 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotBlank(message = "Name Can't be blank")
	@NotNull(message = "Name Can't be Null")
	@Pattern(regexp = "[a-zA-Z_$]+",message="name should contains only alphabets ")
	@Size(min=1, max=100,message = "no of chracter should be less than 25")
	private String name;
	
	@NotBlank(message = "Address Can't be blank")
	@NotNull(message = "Address Can't be Null")
	private String address;
	
	@Column(unique = true)
	@Min(value = 6000000000l)
	@Max(value = 9999999999l)
//	@Pattern(regexp = "[6-9][0-9]{9}")  --> regular expression is only use for String type of data
	private long phone;
	
	@Column(unique = true)
	@NotBlank(message = "Email Can't be blank")
	@NotNull(message = "Email Can't be Null")
	@Email(regexp = "[a-z0-9._%$+-]+@[a-z0-9]+\\.[a-z]{2,3}",message = "Invalid Email")
	private String email;
	
	@Min(value = 0)
	private int marks;
	
	private int total_marks;
	
	private double percentage;
	
	private String grade;
	
	public int getMarks() {
		return marks;
	}
	
	public void setMarks(int marks) {
		this.marks = marks;
	}
	public double getPercentage() {
		return percentage;
	}
	public void setPercentage(double percentage) {
		this.percentage = percentage;
	}
	
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public long getPhone() {
		return phone;
	}
	public void setPhone(long phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getTotal_marks() {
		return total_marks;
	}
	public void setTotal_marks(int total_marks) {
		this.total_marks = total_marks;
	}
	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", address=" + address + ", phone=" + phone + ", email=" + email
				+ ", marks=" + marks + ", total_marks=" + total_marks + ", percentage=" + percentage + ", grade="
				+ grade + "]";
	}
	
	
}
