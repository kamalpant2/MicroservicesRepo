package com.kp.springboot.student.service;
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
	void testsaveStudent() 
	{
		Student student=new Student(1001,"Testuser","test university","test location","test company");
		Mockito.when(this.studentrepo.save(student)).thenReturn(student);
		Student savedstudent=studentservice.saveStudent(student);
		Assertions.assertEquals(student, savedstudent);
	}
	
	@Test
	void testdeleteStudent() {
		int id=105;
		Mockito.doNothing().when(studentrepo).deleteById(id);
		studentservice.deleteStudent(id);
		Mockito.verify(studentrepo).deleteById(id);
		
	}
	
}

