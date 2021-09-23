package com.yenmin.model.entity;

import java.sql.Timestamp;
import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "yenmin_users")
public class User {

	@Id
	private String userId;
	@Size(min = 3)
	private String firstName;
	@Size(max = 15)
	private String lastName;
	@Email
	@NotEmpty(message = "userEmail is required")
	private String userEmail;
	@NotEmpty(message = "userSso is required")
	private String userSso;
	private String userPhone;
	private Date createdDate;
	private Date modifiedDate;
	private String createdBy;
	private String modifiedBy;
	

}
