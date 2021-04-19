package se.lexicon.eddie.registeration.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.lexicon.eddie.registeration.entity.Student;
import se.lexicon.eddie.registeration.repository.StudentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/v1/student")
public class StudentController {
    StudentRepository studentRepository;


    @Autowired
    public void setStudentRepository(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    @GetMapping("/")
    public ResponseEntity<List<Student>> finAll(){
        List<Student> studentList = new ArrayList<>();
        studentRepository.findAll().iterator().forEachRemaining(studentList::add);

        return ResponseEntity.ok(studentList);
    }


    @PostMapping("/")
    public ResponseEntity<Student> save(@RequestBody Student student) {
        Student result = studentRepository.save(student);

        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Student> findById(@PathVariable("id") String id){
        System.out.println("ID: " + id);
        Optional<Student> optionalStudent = studentRepository.findById(id);

        if (optionalStudent.isPresent())
            return ResponseEntity.ok(optionalStudent.get()); // 200
        else
            return ResponseEntity.noContent().build(); // 204
    }


}
