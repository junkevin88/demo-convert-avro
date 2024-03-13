package com.demo.io.repository;


import com.demo.io.entity.TestingJun;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestingJunRepository extends JpaRepository<TestingJun, Long> {

}
