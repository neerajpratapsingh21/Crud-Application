package in.redblack.crudApplicationDemo.controller;
import in.redblack.crudApplicationDemo.entity.Students;
import in.redblack.crudApplicationDemo.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.net.CacheRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/students")
public class StudentController {
   private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/create")
  public ResponseEntity<Students> create(@RequestBody Students students){
       Students createStudent =studentService.createStudent(students);
          return ResponseEntity
               .status(HttpStatus.CREATED)
               .body(createStudent);
  }
  @GetMapping("/getById/{id}")
    public ResponseEntity<Students> getById(@PathVariable Long id){
     Students studentresp = studentService.getById(id);
     if(studentresp ==  null){
         return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
     }
     return ResponseEntity.ok(studentresp);
    }
    @GetMapping("/getByRollNo/{rollNo}")
    public ResponseEntity<Students> getByRollNo(@PathVariable int rollNo){
        Students studentresp = studentService.getByRollNo(rollNo);
        if(studentresp ==  null){
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(studentresp);
    }
    @GetMapping("/getByName/{name}")
    public ResponseEntity<List<Students>> getByName(@PathVariable String name){
        List<Students> studentresp = studentService.getByName(name);
        if(studentresp ==  null){
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(studentresp);
    }
    @GetMapping("/getAll")
    public ResponseEntity<List<Students>> getAllById(){
        List<Students> studentList=studentService.getAllById();
        if(studentList.isEmpty()){
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(studentList);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Students> update(@PathVariable Long id,
                                           @RequestBody Students studentreq){
        Students studentresp = studentService.updateStudent(id,studentreq);
        if(studentresp ==  null){
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    return  ResponseEntity.ok(studentresp);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
       boolean isDeleted =  studentService.deleteStudent(id);
      if(!isDeleted){
          return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("Record Not Found");
      }
      return  ResponseEntity.ok("Record Deleted Successfully");
    }
    @PatchMapping("/delete-soft/{id}")
    public ResponseEntity<String> deleteStudentSoftly(@PathVariable Long id) {
        Boolean isDeleted = studentService.deleteStudentSoftly(id);

        if(!isDeleted) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok("Record deleted");
    }
    @PatchMapping("/restore/{id}")
    public ResponseEntity<String> restoreStudentById(@PathVariable Long id) {
        Boolean isRestored = studentService.restoreStudent(id);

        if(!isRestored) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(" Record restored");
    }
    @PatchMapping("/update/{id}")
    public ResponseEntity<Students> updatePartial(@PathVariable Long id,
                                           @RequestBody Students studentreq){
        Students studentresp = studentService.updateStudentPartialy(id,studentreq);
        if(studentresp ==  null){
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return  ResponseEntity.ok(studentresp);
    }

}
