package com.ourlayer.dto.redis;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class Customer implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public enum Gender {
		MALE, FEMAIL
	}
	
	private long id;
	private String firstName;
	private String lastName;
	private Gender gender;
	
	protected Customer() {
	
	}
	
	public Customer(long id, String firstName, String lastName) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
}
