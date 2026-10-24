package in.redblack.crudApplicationDemo.service;

import in.redblack.crudApplicationDemo.entity.Students;
import in.redblack.crudApplicationDemo.repositiry.StudentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private  final StudentRepository studentRepositiry;

    public StudentService(StudentRepository studentRepositiry) {
        this.studentRepositiry = studentRepositiry;
    }

    public Students createStudent(Students students){
        students.setDeleted(false);
    // Students studentResp= studentRepositiry.save(students);
    // return  studentResp;
        return studentRepositiry.save(students);
    }

    public Students getById(Long id) {
        Optional<Students> studentresp= studentRepositiry.findByIdAndDeletedIsFalse(id);
        if(studentresp.isPresent()){
            return studentresp.get();
        }
            return null;
        }
    public Students getByRollNo(int rollNo) {
        Optional<Students> studentresp= studentRepositiry.findByRollNoAndDeletedIsFalse(rollNo);
        if(studentresp.isPresent()){
            return studentresp.get();
        }
        return null;
    }


    public List<Students> getAllById() {
        List<Students> studentList = studentRepositiry.findByDeletedIsFalse();
        return studentList;
    }

    public Students updateStudent(Long id,
                                  Students studentreq) {
     Optional<Students> existingStudent = studentRepositiry.findByIdAndDeletedIsFalse(id);
     if(existingStudent.isEmpty()){
         return null;
     }
     Students studentToSave = existingStudent.get();
     studentToSave.setName(studentreq.getName());
     studentToSave.setRollNo(studentreq.getRollNo());
     studentToSave.setAge(studentreq.getAge());
     studentToSave.setEmail(studentreq.getEmail());
     studentToSave.setSubject(studentreq.getSubject());
     studentToSave.setDeleted(false);
      return studentRepositiry.save(studentToSave);
    }

    public boolean deleteStudent(Long id) {
       boolean isExist = studentRepositiry.existsById(id);
       if(isExist){
           studentRepositiry.deleteById(id);
           return  true;
       }
       return  false;
    }
    public Boolean deleteStudentSoftly(Long id) {
        Optional<Students> existingStudent =
                studentRepositiry.findByIdAndDeletedIsFalse(id);

        if(existingStudent.isPresent()) {
            Students studentToSave = existingStudent.get();
            studentToSave.setDeleted(true);
            studentRepositiry.save(studentToSave);
            return true;
        }


        return false;
    }


    public Boolean restoreStudent(Long id) {
        Optional<Students> existingStudent =
                studentRepositiry.findByIdAndDeletedIsTrue(id);
        if(existingStudent.isEmpty()){
            return false;
        }
        Students studentToSave = existingStudent.get();
        studentToSave.setDeleted(false);
        studentRepositiry.save(studentToSave);
         return  true;

    }

    public List<Students> getByName(String name) {
        List<Students> studentresp= studentRepositiry.findByNameAndDeletedIsFalse(name);
        if(studentresp.isEmpty()){
            return null;
        }
        return studentresp;

    }
    public Students updateStudentPartialy(Long id,
                                  Students studentreq) {
        Optional<Students> existingStudent = studentRepositiry.findByIdAndDeletedIsFalse(id);
        if(existingStudent.isEmpty()){
            return null;
        }
        Students studentToSave = existingStudent.get();
        if(studentreq.getName() != null) studentToSave.setName(studentreq.getName());
        if(studentreq.getRollNo() != 0) studentToSave.setRollNo(studentreq.getRollNo());
        if(studentreq.getAge() != 0) studentToSave.setAge(studentreq.getAge());
        if(studentreq.getEmail() != null) studentToSave.setEmail(studentreq.getEmail());
        if(studentreq.getSubject() != null) studentToSave.setSubject(studentreq.getSubject());
        return studentRepositiry.save(studentToSave);
    }
}
