package kg.zeroone.java.sayHi.Service;

import kg.zeroone.java.sayHi.entity.Student;

import java.util.List;
import java.util.Optional;

public interface StudentService {
    List<Student> findAll();

    Optional<Student> findById(Long id);

    Student save(Student student);
}
