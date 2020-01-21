package kg.zeroone.java.sayHi.Repository;

import kg.zeroone.java.sayHi.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
