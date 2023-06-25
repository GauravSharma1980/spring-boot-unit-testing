package com.luv2code.test;

import com.luv2code.component.MvcTestingExampleApplication;
import com.luv2code.component.models.CollegeStudent;
import com.luv2code.component.models.StudentGrades;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;

@SpringBootTest(classes = MvcTestingExampleApplication.class)
public class ReflectionTestUtilsTest {

    @Autowired
    ApplicationContext applicationContext;

    @Autowired
    CollegeStudent collegeStudent;

    @Autowired
    StudentGrades studentGrades;

    @BeforeEach
    public void setUp(){
        collegeStudent.setFirstname("Gaurav");
        collegeStudent.setLastname("Sharma");
        collegeStudent.setEmailAddress("gauravrsharmamca@yahoo.com");
        collegeStudent.setStudentGrades(studentGrades);
        ReflectionTestUtils.setField(collegeStudent,"id",123);
        ReflectionTestUtils.setField(collegeStudent,"studentGrades",new StudentGrades(List.of(1.2,2.3,3.2)));
    }

    @DisplayName("data from private field")
    @Test
    public void getDataFromPrivateField(){
        Assertions.assertEquals(123,collegeStudent.getId(),"id not Equal....");
        Assertions.assertEquals(List.of(1.2,2.3,3.2),collegeStudent.getStudentGrades().getMathGradeResults());

        Assertions.assertEquals(123,ReflectionTestUtils.getField(collegeStudent,"id"));

    }

}
