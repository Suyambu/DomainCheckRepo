package com.example.demo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.model.User;

public interface UserRepo extends JpaRepository<User, Integer> {

	@Query("SELECT id FROM User  where username = :name and password = :password")
	Integer getIdByUserName(@Param("name") String name, @Param("password") String password);
	
}
