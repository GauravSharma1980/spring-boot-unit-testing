package com.luv2code.springmvc.controller;


import com.luv2code.springmvc.models.CollegeStudent;
import com.luv2code.springmvc.models.GradebookCollegeStudent;
import com.luv2code.springmvc.service.StudentAndGradeService;
import org.aspectj.lang.annotation.After;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.ModelAndViewAssert;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import java.util.List;

@TestPropertySource("/application.properties")
@AutoConfigureMockMvc
@SpringBootTest
public class GradebookControllerTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private MockMvc mockMvc;
    @Mock
    private StudentAndGradeService studentAndGradeService;


    @BeforeEach
    public void setUp(){
        jdbcTemplate.execute("insert into student(id,firstname,lastname,email_address) values (15,'Gaurav','Sharma','gauravrsharmamca@yahoo.com')");
    }

    @Test
    public void getStudentHttpRequest() throws Exception {
        CollegeStudent studentOne = new GradebookCollegeStudent("Gaurav","Sharma","gaurav@gmail.com");
        CollegeStudent studentTwo = new GradebookCollegeStudent("Saurav","Sharma","saurav@gmail.com");
        List<CollegeStudent> collegeStudentList = List.of(studentOne,studentTwo);
        Mockito.when(studentAndGradeService.getGradeStudentList()).thenReturn(collegeStudentList);
        Assertions.assertIterableEquals(collegeStudentList,studentAndGradeService.getGradeStudentList());

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        ModelAndView mav = mvcResult.getModelAndView();
        //Assertions.assertEquals(mav.getView().toString(),"index");
        ModelAndViewAssert.assertViewName(mav,"index");
    }


    @AfterEach()
    public void cleanUp(){
        jdbcTemplate.execute("delete from student");
    }
}
