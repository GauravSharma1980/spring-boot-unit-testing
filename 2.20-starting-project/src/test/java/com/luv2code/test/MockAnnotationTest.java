package com.luv2code.test;

import com.luv2code.component.MvcTestingExampleApplication;
import com.luv2code.component.dao.ApplicationDao;
import com.luv2code.component.models.Student;
import com.luv2code.component.models.StudentGrades;
import com.luv2code.component.service.ApplicationService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.util.List;

@SpringBootTest(classes = MvcTestingExampleApplication.class)
public class MockAnnotationTest {

    @Autowired
    ApplicationContext applicationContext;

    @Autowired
    StudentGrades studentGrades;

    @Mock
    private ApplicationDao applicationDao;

    @InjectMocks
    private ApplicationService applicationService;


    @BeforeEach
    public void setUp(){
        studentGrades.setMathGradeResults(List.of(10.1,10.2));
    }

    @Test
    public void testAddGradeResultsForSingleClass(){
        //setup

        //mock
        Mockito.when(applicationDao.addGradeResultsForSingleClass(studentGrades.getMathGradeResults())).thenReturn(20.3);

        //execute
        double total = applicationDao.addGradeResultsForSingleClass(studentGrades.getMathGradeResults());

        //verify
        Mockito.verify(applicationDao,Mockito.times(1)).addGradeResultsForSingleClass(studentGrades.getMathGradeResults());
    }


}
