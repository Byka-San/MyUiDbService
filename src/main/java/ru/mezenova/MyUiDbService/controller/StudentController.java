package ru.mezenova.MyUiDbService.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.mezenova.MyUiDbService.dao.StudentRepository;
import ru.mezenova.MyUiDbService.entity.Student;

import java.util.Optional;

@Controller
@Slf4j
public class StudentController {

    private final StudentRepository studentRepository;

    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping({"/","/list"})
    public ModelAndView getAllStudent(){
        log.info("/list -> connection");
        ModelAndView nav = new ModelAndView("/list_students");
        nav.addObject("students",studentRepository.findAll());
        return nav;
    }
    @GetMapping("/addStudentForm")
    public ModelAndView addStudentForm(){
        ModelAndView nav = new ModelAndView("/add-student-form");
        Student student = new Student();
        nav.addObject("student", student);
        return nav;
    }

    @PostMapping("/saveStudent")
    public String saveStudent(@ModelAttribute Student student){
        studentRepository.save(student);
        return "redirect:/list";
    }


    @GetMapping("showUpdateForm")
    public ModelAndView showUpdateForm(@RequestParam Integer studentId){
        ModelAndView nav = new ModelAndView("/add-student-form");
        Optional<Student> optionalStudent = studentRepository.findById(studentId);
        Student student = new Student();
        if (optionalStudent.isPresent()){
            student = optionalStudent.get();
        }
        nav.addObject("student",student);
        return nav;
    }
    @GetMapping("/deleteStudent")
    public String deleteStudent(@RequestParam Integer studentId){
        studentRepository.deleteById(studentId);
        return "redirect:/list";
    }
}
