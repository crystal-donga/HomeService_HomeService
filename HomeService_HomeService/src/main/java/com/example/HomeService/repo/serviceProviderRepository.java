package com.example.HomeService.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.HomeService.model.ServiceProvider;

public interface serviceProviderRepository extends JpaRepository<ServiceProvider, Long>  {

	// Find provider by user ID
    Optional<ServiceProvider> findByUserId(Long userId);

    // Find provider by company name
    Optional<ServiceProvider> findByCompanyName(String companyName);

    // Check if provider exists for a given user
    boolean existsByUserId(Long userId);
	

}
