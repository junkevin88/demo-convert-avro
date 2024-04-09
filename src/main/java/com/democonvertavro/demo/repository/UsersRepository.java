package com.democonvertavro.demo.repository;

import com.democonvertavro.demo.entity.User;
import net.sf.jsqlparser.statement.select.Select;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersRepository extends JpaRepository<User, Long> {
    @Query(value = "select u from User u", nativeQuery = false)
    List<User> findAll();

}
