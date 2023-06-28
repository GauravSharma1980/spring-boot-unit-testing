package com.luv2code.springmvc.service;


import com.luv2code.springmvc.dao.StudentDao;
import com.luv2code.springmvc.models.CollegeStudent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class StudentAndGradeService {

    @Autowired
    StudentDao studentDao;
    public void createStudent(String name, String lname, String mail) {
        CollegeStudent collegeStudent = new CollegeStudent(name,lname,mail);
        collegeStudent.setId(11);
        studentDao.save(collegeStudent);

    }

    public boolean checkIfStudentExists(int id) {
       Optional<CollegeStudent> collegeStudent =  studentDao.findById(id);
       return collegeStudent.isPresent();
    }

    public void deleteStudent(int id) {
        studentDao.deleteById(id);
    }

    public Iterable<CollegeStudent>  getGradeStudentList() {
       return studentDao.findAll();
    }
}
