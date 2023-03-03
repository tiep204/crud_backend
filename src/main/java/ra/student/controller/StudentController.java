package ra.student.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ra.student.model.entity.Student;
import ra.student.model.service.StudentService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin( "http://localhost:8080")
@RestController
@RequestMapping("api/ojt/student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping
    public List<Student> getAllStudent() {
        return studentService.getAll();
    }

    @GetMapping("test")
    public String test() {
        return "sdsf";
    }
    @GetMapping("/{studentId}")
    public Student getStudentById(@PathVariable("studentId") int studentId){
        return studentService.getById(studentId);
    }
    @PostMapping
    public Student createStudent(@RequestBody Student student){
        return studentService.saveOrUpdate(student);
    }
    @PutMapping("/{studentId}")
    public Student updateStudent(@PathVariable("studentId") int studentId,@RequestBody Student student){
        Student studentUpdate = studentService.getById(studentId);
        studentUpdate.setStudentName(student.getStudentName());
        studentUpdate.setStudentAge(student.getStudentAge());
        studentUpdate.setStudentStatus(student.isStudentStatus());
        return  studentService.saveOrUpdate(studentUpdate);
    }
    @DeleteMapping("/{studentId}")
    public void deleteStudent(@PathVariable("studentId") int studentId){
        studentService.delete(studentId);
    }
    @GetMapping("/search")
    public List<Student> seachByNameOrId(@RequestParam("name") String name){
        return studentService.searchByName(name);
    }

    @GetMapping("/sortByName")
    public ResponseEntity<List<Student>> sortByStudentName(@RequestParam("direction") String direction) {
        List<Student> listStudent = studentService.sortByStudentName(direction);
        return new ResponseEntity<>(listStudent, HttpStatus.OK);
    }
    @GetMapping("/getPagging")
    public ResponseEntity<Map<String,Object>> getPagging(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size
    ){
        Pageable pageable = PageRequest.of(page,size);
        Page<Student> pageStudent = studentService.pagging(pageable);
        Map<String,Object> data = new HashMap<>();
        data.put("student",pageStudent.getContent());
        data.put("total",pageStudent.getSize());
        data.put("totalItems",pageStudent.getTotalElements());
        data.put("totalPages",pageStudent.getTotalPages());
        return  new ResponseEntity<>(data,HttpStatus.OK);
    }


    @GetMapping("/getPaggingAndSortByName")
    public ResponseEntity<Map<String,Object>> getPaggingAndSortByName(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size,
            @RequestParam String direction){
        Sort.Order order;
        if (direction.equals("asc")){
            order=new Sort.Order(Sort.Direction.ASC,"studentName");
        }else{
            order=new Sort.Order(Sort.Direction.DESC,"studentName");
        }
        Pageable pageable = PageRequest.of(page,size,Sort.by(order));
        Page<Student> pageStudent = studentService.pagging(pageable);
        Map<String,Object> data = new HashMap<>();
        data.put("books",pageStudent.getContent());
        data.put("total",pageStudent.getSize());
        data.put("totalItems",pageStudent.getTotalElements());
        data.put("totalPages",pageStudent.getTotalPages());
        return  new ResponseEntity<>(data,HttpStatus.OK);
    }






}
