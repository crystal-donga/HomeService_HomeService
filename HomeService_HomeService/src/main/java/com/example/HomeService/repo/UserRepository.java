package com.example.HomeService.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.HomeService.model.Users;

public interface UserRepository extends JpaRepository<Users,Long>{

	 Users findByEmail(String email);

	
	

}
