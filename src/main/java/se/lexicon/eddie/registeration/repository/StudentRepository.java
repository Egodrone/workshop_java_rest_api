package se.lexicon.eddie.registeration.repository;


import org.springframework.data.repository.CrudRepository;
import se.lexicon.eddie.registeration.entity.Student;

import java.util.List;
import java.util.Optional;


public interface StudentRepository extends CrudRepository<Student, String> {
    Optional<Student> findByEmailIgnoreCase(String email);

    List<Student> findByFirstNameIgnoreCaseAndLastNameIgnoreCase(String firstName, String lastName);

    List<Student> findByLastNameIgnoreCase(String lastName);

    List<Student> findByFirstNameIgnoreCase(String nameName);
}
