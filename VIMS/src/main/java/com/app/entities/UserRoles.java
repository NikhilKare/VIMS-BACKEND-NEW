package com.app.entities;

import javax.persistence.*;

import lombok.*;

@Entity
@Table(name="U_Roles")
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class UserRoles {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long roleId;
	@Enumerated(EnumType.STRING)
	@Column(length = 20)
	private Roles roleName;
}
