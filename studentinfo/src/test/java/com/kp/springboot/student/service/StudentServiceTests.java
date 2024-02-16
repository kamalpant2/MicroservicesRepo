package com.kp.springboot.student.service;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;
import com.kp.springboot.student.dto.Company;
import com.kp.springboot.student.dto.StudentData;
import com.kp.springboot.student.model.Student;
import com.kp.springboot.student.repo.StudentRepository;
import com.kp.springboot.student.service.StudentService;


@SpringBootTest
public class StudentServiceTests {
	
	@Mock
	private StudentRepository studentrepo;
	
	@Spy
	@InjectMocks
    private StudentService studentservice;
	
	@Test
	public void testStudentAndComany() {
		
	   String studentname="pk";
	   Student student=new Student(101,"xyz","delhi university","delhi","TCS");
       Mockito.when(studentrepo.findByName(studentname)).thenReturn(student);
	   Mockito.doReturn(new Company("TCS","Mumbai")).when(studentservice).companyData(student.getCompanyname());
       StudentData studentdata=studentservice.getStudentAndComany(studentname);
	   Assertions.assertNotNull(studentdata);
	}
	
	@Test 
	public void testsaveStudent() 
	{
		Student student=new Student(1001,"Testuser","test university","test location","test company");
		Mockito.when(this.studentrepo.save(student)).thenReturn(student);
		Student savedstudent=studentservice.saveStudent(student);
		Assertions.assertEquals(student, savedstudent);
	}
	
	@Test
	public void testdeleteStudent() {
		int id=105;
		Mockito.doNothing().when(studentrepo).deleteById(id);
		studentservice.deleteStudent(id);
		Mockito.verify(studentrepo).deleteById(id);
		
	}
	
	@Test
	public void testfindStudents() {
	List<Student> students=Arrays.asList(new Student(10, "testuser1","test university1", "test location1","test company1"),new Student(20, "testuser2","test university2","test location2", "test company2"));
	Mockito.when(this.studentrepo.findAll()).thenReturn(students);
	List<Student> foundstudents=studentservice.findStudents();
	Assertions.assertEquals(students,foundstudents);
	}
	
}

