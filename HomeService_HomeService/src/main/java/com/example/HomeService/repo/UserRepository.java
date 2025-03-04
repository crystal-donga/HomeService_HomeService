package com.example.HomeService.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.HomeService.model.Users;
import com.example.HomeService.model.role;

public interface UserRepository extends JpaRepository<Users,Long>{

	 Users findByEmail(String email);
	 role findByRole(role role);
	

	
	

}
