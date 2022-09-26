package com.app.entities;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
public class Payment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="transaction_id")
	private long transactionId;
	private String name;
	private double amount;
	private LocalDateTime dateAndTime=LocalDateTime.now();
	
	@ManyToOne
	@JoinColumn(name="chasis_no")
	private VehicleDetails vehicle;
}
