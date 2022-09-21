package com.app.dto;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DebitCardDetailsDTO {
	@JsonProperty("id")
	private long cardNumber;
	@NotEmpty(message = "First name must be supplied")
	@Length(min = 2,max=10,message = "Invalid Card Holder name length")
    private String cardHolder;
    private double amount;
    private int mdate;
    private int myear;
    @NotEmpty(message = "CVV must be supplied")
    private int cvv;
}
