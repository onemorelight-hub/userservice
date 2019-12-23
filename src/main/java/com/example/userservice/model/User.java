package com.example.userservice.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

/**
 * This class define the entity model for user_table table 
 * @author anjan
 *
 */

@Entity
@Table (name= "user_table")
public class User {

	@Id
	@Column (name = "user_id")
	private String userId;
	
	@Column (name = "first_name" , nullable = false)
	private String firstName;
	
	@Column (name = "middle_name")
	private String middleName;
	
	@Column (name = "last_name" , nullable = false)
	private String lastName;
	
	@Column (name = "email" , nullable = false)
	private String email;
	
	@Column (name = "area_code")
	private String areaCode;
	
	@Column (name = "user_type" , nullable = false)
	private String userType;
	
	@Column (name = "created_date" , nullable = false , columnDefinition = "DATETIME")
	@Temporal (TemporalType.TIMESTAMP)
	@CreatedDate
	private Date createdDate;
	
	@Column (name = "last_login" , nullable =false , columnDefinition = "DATETIME")
	@Temporal (TemporalType.TIMESTAMP)
	@LastModifiedDate
	private Date lastLogin;
	
	@Column (name = "verified" , nullable = false , columnDefinition = "boolean default false")
	private Boolean verified;
	
	@Column (name = "verification_code" , nullable = false)
	private String verificationCode;
	
	@Column (name = "profile_image")
	private String profileImage;
	
	@Column (name = "password" , nullable = false)
	private String password;
	
	@Column (name = "address")
	private String address;
	
	@Column (name = "dob" , columnDefinition = "DATETIME")
	private String dob;
	
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getVerificationCode() {
		return verificationCode;
	}

	public void setVerificationCode(String verificationCode) {
		this.verificationCode = verificationCode;
	}

	public String getProfileImage() {
		return profileImage;
	}

	public void setProfileImage(String profileImage) {
		this.profileImage = profileImage;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	public Boolean getVerified() {
		return verified;
	}

	public void setVerified(Boolean verified) {
		this.verified = verified;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getVerifiedCode() {
		return verificationCode;
	}

	public void setVerifiedCode(String verificationCode) {
		this.verificationCode = verificationCode;
	}
	
	@Override
	public String toString() {
		return "User Name: "+this.firstName + ", Email: " + this.email + ", password: " + this.password + ", verified: " +this.verified
				+", createdDate: "+ createdDate + ", lastLoginDate: " +lastLogin;
	}
}



