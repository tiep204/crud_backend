package ra.student.model.service;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ra.student.model.entity.Student;


import java.util.List;

public interface StudentService {
    List<Student> getAll();
    Student getById(int studentId);
    Student saveOrUpdate(Student student);
    void delete(int studentId);
    List<Student> searchByName(String name);

    List<Student> sortByStudentName(String direction);
    Page<Student> pagging(Pageable pageable);

}
