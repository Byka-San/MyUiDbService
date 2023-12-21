package ru.mezenova.MyUiDbService.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mezenova.MyUiDbService.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer>{


}
