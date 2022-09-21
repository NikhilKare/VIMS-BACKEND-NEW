package com.app.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentDTO {
	@JsonProperty("id")
	private long transactionId;
	@NotEmpty(message = "Name must be supplied")
	private String name;
	private double amount;
	private LocalDateTime dateAndTime;
	
}
