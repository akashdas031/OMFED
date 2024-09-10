package com.omfed.Entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.antlr.v4.runtime.misc.NotNull;
import org.hibernate.validator.constraints.NotBlank;

import com.omfed.EntityListeners.AuditingEntityListener;
import com.omfed.Enums.Role;
import com.omfed.Enums.Status;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Table(name="users")
public class User {
	 @Id
     private String id;
	
	 @NotBlank(message = "username is Mandatory")
	 @Size(min = 3,max = 50,message = "username must contain minimum 3 characters")
	 @Column(nullable = false,unique = true)
     private String username;
	 
	 @NotBlank(message="Password is Mandatory")
	 @Size(min = 8,max = 50,message = "Password must contain minimum 8 characters")
	 @Column(nullable = false)
     private String password;
	 
	 @NotBlank(message="Email is Mandatory")
	 @Size(message = "Enter a Valid Email address ")
	 @Column(nullable = false,unique = true)
     private String email;
	 
	 @NotBlank(message="FirstName Should not be Empty")
	 @Size(min = 3,max = 50,message = "FirstName must contain minimum 3 characters")
	 @Column(nullable = false)
     private String firstName;
	 
	 @NotBlank(message="LastName Should not be Empty")
	 @Size(min = 3,max = 50,message = "Lastname must contain minimum 3 characters")
	 @Column(nullable = false)
     private String lastName;
	 
     private String phoneNumber;
	 
	 @Enumerated(EnumType.STRING)
     private Role role;
	 
	 @Enumerated(EnumType.STRING)
     private Status status;
	 
	 @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	 private List<MilkCollection> milkCollection=new ArrayList<>();
	 
	 @Column(updatable = false)
     private LocalDateTime createdAt;
     private LocalDateTime lastLogin;
     private LocalDateTime updatedAt;
     private String profilePicture;
     private String address;
     
     @Past(message = "Date of Birth Should not exceed today date")
     private LocalDate dateOfBirth;
     @Column(columnDefinition = "TEXT")
     private String preferences;
     
     @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
     private List<ProductRequest> request;
     
     
}
