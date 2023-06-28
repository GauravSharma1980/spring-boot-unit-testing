package com.luv2code.springmvc;


import com.luv2code.springmvc.dao.StudentDao;
import com.luv2code.springmvc.models.CollegeStudent;
import com.luv2code.springmvc.models.StudentGrades;
import com.luv2code.springmvc.service.StudentAndGradeService;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@TestPropertySource("/application.properties")
@SpringBootTest
public class StudentAndGradeServiceTest {

    @Autowired
    StudentDao studentDao;
    @Autowired
    StudentAndGradeService studentService;

    @Autowired
    JdbcTemplate jdbcTemplate;


    Logger logger = LoggerFactory.getLogger(StudentAndGradeServiceTest.class);

    @BeforeEach()
    public void setUpDatabase(){
        //studentService.createStudent("Gaurav","Sharma","gauravrsharmamca@yahoo.com");
        jdbcTemplate.execute("insert into student(id,firstname,lastname,email_address) values (15,'Gaurav','Sharma','gauravrsharmamca@yahoo.com')");
    }

    @Test
    public void createStudentService(){

        CollegeStudent collegeStudent = studentDao.findByEmailAddress("gauravrsharmamca@yahoo.com");

        Assertions.assertEquals("gauravrsharmamca@yahoo.com",collegeStudent.getEmailAddress());
    }

    @Test
    public void isStudentNullCheck(){
        assertTrue(studentService.checkIfStudentExists(1));
        assertFalse(studentService.checkIfStudentExists(99));
    }


    @Test
    public void deleteStudentService(){
        Optional<CollegeStudent> student = studentDao.findById(1);
        assertTrue(student.isPresent(),"must return true");
        studentService.deleteStudent(student.get().getId());
        student = studentDao.findById(student.get().getId());
        assertFalse(student.isPresent(),"must return false");
    }



    @Sql("/insertData.sql")
    @Test
    public void getGradeStudentListService(){
       Iterable<CollegeStudent> studentGrades  = studentService.getGradeStudentList();
       List<CollegeStudent> collegeStudentList = new ArrayList<>();
       studentGrades.forEach(collegeStudent -> collegeStudentList.add(collegeStudent));
       assertEquals(10,collegeStudentList.size());
    }

    @AfterEach
    public void cleanUpDatabase(){
        logger.info("from cleanUpDatabase.....");
        jdbcTemplate.execute("delete from student");
    }

}
