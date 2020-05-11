package com.gmail.juyonglee0208;

import org.springframework.beans.factory.annotation.Autowired;

import lombok.Setter;

public class Restaurant {
	@Setter(onMethod_ = @Autowired)
	private Chef chef;
}
