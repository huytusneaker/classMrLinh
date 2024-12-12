package org.example.Controller;

import org.example.Annotation.BOAutowired;
import org.example.Annotation.BOController;
import org.example.Annotation.BOGetMapping;
import org.example.Annotation.BORequestMapping;
import org.example.Service.StudentService;


@BOController
//@BORequestMapping("/greet")
public class StudentController {
    @BOAutowired
    StudentService studentService;
    public void greet() {
        System.out.println("Greetings from Controller!");
    }

    public void greetFromControllerCallService() {
        studentService.greet();
    }

    @BOGetMapping("/students")
    public String getAllStudents() {
        return "List of students: John, Jane, Bob";
    }
}
