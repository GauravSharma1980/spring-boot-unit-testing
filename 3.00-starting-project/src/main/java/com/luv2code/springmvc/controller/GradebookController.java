package com.luv2code.springmvc.controller;

import com.luv2code.springmvc.models.*;
import com.luv2code.springmvc.service.StudentAndGradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class GradebookController {

	@Autowired
	private Gradebook gradebook;

	@Autowired
	private StudentAndGradeService studentAndGradeService;
	

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String getStudents(Model m) {
		Iterable<CollegeStudent> gradeStudents = studentAndGradeService.getGradeStudentList();
		m.addAttribute("students",gradeStudents);
		return "index";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String setStudents(CollegeStudent student, BindingResult result, Model model ) {
		System.out.println(student);
		//model.addAttribute("student", student);
		return "register_success";
	}


	@GetMapping("/studentInformation/{id}")
		public String studentInformation(@PathVariable int id, Model m) {
		return "studentInformation";
		}

}
