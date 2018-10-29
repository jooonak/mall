package com.ourlayer;

import com.ourlayer.dto.redis.Customer;
import com.ourlayer.repository.CustomerRepository;
import lombok.extern.java.Log;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@Log
@RunWith(SpringRunner.class)
@SpringBootTest
public class MallApplicationTests {

	@Autowired
	private CustomerRepository customerRepository;

	@Test
	public void saveTest() {
		customerRepository.save(new Customer(1, "Jack", "Smith"));
	}
}
