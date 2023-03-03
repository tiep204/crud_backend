package ra.student.model.serviceImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ra.student.model.entity.Student;

import ra.student.model.repository.StudentRepository;
import ra.student.model.service.StudentService;
import org.springframework.data.domain.Pageable;



import java.util.List;

@Service
public class StudentServiceImp implements StudentService {
    @Autowired
    private StudentRepository studentRepository;
    @Override
    public List<Student> getAll() {
        return studentRepository.findAll();
    }

    @Override
    public Student getById(int studentId) {
        return studentRepository.findById(studentId).get();
    }

    @Override
    public Student saveOrUpdate(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public void delete(int studentId) {
        studentRepository.deleteById(studentId);

    }

    @Override
    public List<Student> searchByName(String name) {
        return studentRepository.findByStudentNameContains(name);
    }

    @Override
    public List<Student> sortByStudentName(String direction) {
        if(direction.equals("asc")){
            return studentRepository.findAll(Sort.by("studentName").ascending());
        }else {
            return studentRepository.findAll(Sort.by("studentName").descending());
        }
    }
    @Override
    public Page<Student> pagging(Pageable pageable) {
        return studentRepository.findAll(pageable);
    }
}
