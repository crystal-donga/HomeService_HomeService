package com.example.HomeService.dto;

import java.time.LocalDate;

import com.example.HomeService.model.ServiceProvider;

public class ServiceProviderDTO {
	
	    private String name;
	    private String email;
	    private String companyName;
	    private int experienceYears;
	    private String address;
	    private String phoneNumber;
	    private LocalDate joiningDate;
	    private String imageUrl;

	    // Constructor
	    public ServiceProviderDTO(ServiceProvider provider) {
	       
	        this.name = provider.getUser().getName();
	        this.email = provider.getUser().getEmail();
	        this.companyName = provider.getCompanyName();
	        this.experienceYears = provider.getExperienceYears();
	        this.address = provider.getAddress();
	        this.phoneNumber = provider.getPhoneNumber();
	        this.joiningDate = provider.getJoiningDate();
	        this.imageUrl = provider.getImageUrl();
	    }

	    // Getters
	  
	    public String getName() { return name; }
	    public String getEmail() { return email; }
	    public String getCompanyName() { return companyName; }
	    public int getExperienceYears() { return experienceYears; }
	    public String getAddress() { return address; }
	    public String getPhoneNumber() { return phoneNumber; }
	    public LocalDate getJoiningDate() { return joiningDate; }

		public String getImageUrl() {
			return imageUrl;
		}

		
	    
}
