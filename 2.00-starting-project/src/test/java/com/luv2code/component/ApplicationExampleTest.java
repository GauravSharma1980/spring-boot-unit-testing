package com.luv2code.component;


import com.luv2code.component.models.StudentGrades;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest(classes = MvcTestingExampleApplication.class)
public class ApplicationExampleTest {


    @Value("${info.school.name}")
    String schoolName;

    @Value("${info.app.name}")
    String appName;

    @Value("${info.app.description}")
    String appDescription;

    @Value("${info.app.version}")
    String appVersion;

    @Autowired
    StudentGrades studentGrades;

    @BeforeEach
    void  setUp(){
        System.out.println(schoolName+"\n"+appName+"\n"+appDescription+"\n"+appVersion);
        List<Double> gradeList = List.of(10.9,11.9,12.9);
        studentGrades.setMathGradeResults(gradeList);
    }

    @DisplayName("Add grade results for student grades ")
    @Test
    void addGradeResultsForSingleClassTest(){
        //setup data

        //execute
        double finalGrade =  studentGrades.addGradeResultsForSingleClass(studentGrades.getMathGradeResults());

        //assert
        assertEquals(35.7,finalGrade,"the finalGrade value is not accurate....");
    }


    @DisplayName("Add grade results for student grades not equal")
    @Test
    void addGradeResultsForSingleClassNotEqualTest(){
        //setup data

        //execute
        double finalGrade =  studentGrades.addGradeResultsForSingleClass(studentGrades.getMathGradeResults());

        //assert
        assertNotEquals(33.7,finalGrade,"the finalGrade value are equal....");
    }

    @DisplayName("Comparing Grades whether greater then or less then")
    @Test
    void isGradeGreaterTrueTest(){
        //setup data

        //execute
        boolean gradeCompareResult = studentGrades.isGradeGreater(studentGrades.getMathGradeResults().get(0)+10,studentGrades.getMathGradeResults().get(1));

        //assert
        assertTrue(gradeCompareResult);
    }

    @DisplayName("Comparing Grades whether greater then or less then False")
    @Test
    void isGradeGreaterFalseTest(){
        //setup data

        //execute
        boolean gradeCompareResult = studentGrades.isGradeGreater(studentGrades.getMathGradeResults().get(0),studentGrades.getMathGradeResults().get(1));

        //assert
        assertFalse(gradeCompareResult);
    }

    @DisplayName("Check grade is null")
    @Test
    void checkNullNotNullTest(){
        //setup data

        //execute
        StudentGrades isStudentGradeOjectNull = (StudentGrades) studentGrades.checkNull(studentGrades);

        //assert
        assertNotNull(isStudentGradeOjectNull);
    }

    @DisplayName("Check grade is null or not")
    @Test
    void checkNull_NullTest(){
        //setup data

        //execute
        StudentGrades isStudentGradeOjectNull = (StudentGrades) studentGrades.checkNull(null);

        //assert
        assertNull(isStudentGradeOjectNull);
    }
}
