package com.example.HomeService.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "service_providers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ServiceProvider {
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-incremented ID
	    private Long id;

	    @OneToOne
	    @JoinColumn(name = "user_id", nullable = false, unique = true)
	    private Users user;  // Link to Users table

	    @Column(name = "company_name", nullable = false)
	    private String companyName;

	    @Column(name = "experience_years")
	    private int experienceYears;

	    @Column(name = "address", nullable = false)
	    private String address;
	    
	    @Column(name = "emailId", nullable = false)
	    private String emailId;

	    @Column(name = "phone_number", nullable = false, unique = true)
	    private String phoneNumber;
	    
	    public String getImageUrl() {
			return imageUrl;
		}

		public void setImageUrl(String imageUrl) {
			this.imageUrl = imageUrl;
		}

		@Column(name = "image_url", nullable = true)
	    private String imageUrl;

	    @Column(name = "joining_date", nullable = false, updatable = false)
	    private LocalDate joiningDate; // Automatically set when the provider is created  
	    
	    public ServiceProvider(Users user, String companyName, int experienceYears, String address,String imageUrl) {
	        this.user = user;
	        this.companyName = companyName;
	        this.experienceYears = experienceYears;
	        this.address = address;
	        this.phoneNumber = user.getPhoneNumber();
	        this.emailId = user.getEmail();
	        this.joiningDate = LocalDate.now();
	        this.imageUrl = imageUrl;
	        
	    }

	    @Override
	    public String toString() {
	        return "ServiceProvider{" +
	                "id=" + id +
	                ", name='" + user.getName() + '\'' +  // Fetch provider's name from Users table
	                ", email='" + user.getEmail() + '\'' + // Fetch email from Users table
	                ", companyName='" + companyName + '\'' +
	                ", experienceYears=" + experienceYears +
	                ", address='" + address + '\'' +
	                ", phoneNumber='" + phoneNumber + '\'' +
	                ", joiningDate=" + joiningDate +
	                '}';
	    }

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public Users getUser() {
			return user;
		}

		public void setUser(Users user) {
			this.user = user;
		}

		public String getCompanyName() {
			return companyName;
		}

		public void setCompanyName(String companyName) {
			this.companyName = companyName;
		}

		public int getExperienceYears() {
			return experienceYears;
		}

		public void setExperienceYears(int experienceYears) {
			this.experienceYears = experienceYears;
		}

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		public String getEmailId() {
			return emailId;
		}

		public void setEmailId(String emailId) {
			this.emailId = emailId;
		}

		public String getPhoneNumber() {
			return phoneNumber;
		}

		public void setPhoneNumber(String phoneNumber) {
			this.phoneNumber = phoneNumber;
		}

		public LocalDate getJoiningDate() {
			return joiningDate;
		}

		public void setJoiningDate(LocalDate joiningDate) {
			this.joiningDate = joiningDate;
		}


}
