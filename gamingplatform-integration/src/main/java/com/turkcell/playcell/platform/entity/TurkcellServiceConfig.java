package com.turkcell.playcell.platform.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "turkcell_service_config")
public class TurkcellServiceConfig {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	long id;
	
    @NotNull
    @NotEmpty
	@Column(name = "key_name", unique = true)
	private String key_name;
	
    @NotNull
    @NotEmpty
	@Column(name = "key_value", unique = true)
	private String key_value;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getKeyValue() {
		return key_value;
	}

	public void setKeyValue(String key_value) {
		this.key_value = key_value;
	}
	
	public String getKeyName() {
		return key_name;
	}

	public void setKeyName(String key_name) {
		this.key_name = key_name;
	}
}
