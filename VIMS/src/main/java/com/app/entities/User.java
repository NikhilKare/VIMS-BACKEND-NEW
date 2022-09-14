package com.app.entities;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.app.utils.StatusEnum;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_Id")
	private Long userId;
	@Column(name = "First_Name")
	private String firstName;
	@Column(name = "Last_Name")
	private String lastName;
	@Column(name = "User_Name")
	private String userName;
	private String address;
	private String contactNumber;
	@Column(unique = true)
	private String email;
	private String password;
	@Column(name = "Date_Created")
	private LocalDate dateCreated;
	@Enumerated(EnumType.STRING)
	private StatusEnum status;
	private String imagePath;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "User_Roles_Mapping",joinColumns = @JoinColumn(name="User_Id"),
				inverseJoinColumns = @JoinColumn(name="Role_Id"))
	private Set<UserRoles> roles=new HashSet<UserRoles>();
	
	
}
