package com.example.HomeService.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.HomeService.model.ServiceProvider;
import com.example.HomeService.model.Users;
import com.example.HomeService.model.role;
import com.example.HomeService.repo.UserRepository;
import com.example.HomeService.repo.serviceProviderRepository;

import jakarta.transaction.Transactional;

@Service
public class ServiceProviderService {
	@Autowired
	serviceProviderRepository serviceProviderRepository;
	
	@Autowired
	UserRepository usersRepository;
	 // Register a new service provider (only if user role is PROVIDER)
    //@Transactional
    public ServiceProvider registerServiceProvider(Long userId, String companyName, int experienceYears, String address,String imageUrl) {
        if (serviceProviderRepository.existsByUserId(userId)) {
            throw new RuntimeException("User is already registered as a service provider");
        }

        Users user = usersRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));
        
        System.out.println("user role" + " " + user.getRole());

        if (user.getRole() != role.PROVIDER) {
            throw new RuntimeException("User does not have the PROVIDER role");
        }

        ServiceProvider serviceProvider = new ServiceProvider(user, companyName, experienceYears, address, imageUrl );
        return serviceProviderRepository.save(serviceProvider);
    }
}
