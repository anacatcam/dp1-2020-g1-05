package com.springframework.samples.madaja.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

public class Venta {
	
	@Id
	@Column(name="id")
	@NotEmpty
	protected String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	
	

}
