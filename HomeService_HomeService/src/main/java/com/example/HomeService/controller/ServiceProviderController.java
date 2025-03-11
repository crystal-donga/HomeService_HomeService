package com.example.HomeService.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.HomeService.dto.ServiceProviderDTO;
import com.example.HomeService.model.ServiceProvider;
import com.example.HomeService.model.Users;
import com.example.HomeService.model.role;
import com.example.HomeService.repo.UserRepository;
import com.example.HomeService.repo.serviceProviderRepository;
import com.example.HomeService.service.ServiceProviderService;


@RestController
@RequestMapping("/api")
public class ServiceProviderController {
	
	@Autowired
	ServiceProviderService  serviceProviderService;
	
	@PostMapping("/service-providers/register")
    public ResponseEntity<?> registerServiceProvider(@RequestBody Map<String, Object> requestData) {
        try {
        	
        	// Check if userId is present
            if (!requestData.containsKey("userId") || requestData.get("userId") == null) {
                throw new RuntimeException("Missing userId in request data");
            }
        	
            Long userId = Long.valueOf(requestData.get("userId").toString());
            System.out.println("userId" + userId);
        	
        	 
            
            String companyName = requestData.get("companyName").toString();
            System.out.println("companyName" + companyName);
            
            int experienceYears = Integer.parseInt(requestData.get("experienceYears").toString());
            System.out.println("experienceYears" + experienceYears);
            
            String address = requestData.get("address").toString();
            System.out.println("address" + address);
            
            String imageUrl = requestData.containsKey("imageUrl")? requestData.get("imageUrl").toString() : null;
            
           System.out.println("image url" + " " +imageUrl );
          
              
            ServiceProvider registeredProvider = serviceProviderService.registerServiceProvider(
                    userId, companyName, experienceYears, address,imageUrl
            );
            
            System.out.println("Registered Provider: " + registeredProvider  );
            
         // Convert to DTO and return a clean response
            ServiceProviderDTO providerDTO = new ServiceProviderDTO(registeredProvider);
            
            return ResponseEntity.ok(Map.of(
                "message", "Service Provider registered successfully!",
                "provider", providerDTO
            ));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", ex.getMessage()));
        }
    }
}
