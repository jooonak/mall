package com.worksout;

import com.worksout.mapper.TestMapper;
import lombok.extern.java.Log;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Log
@RunWith(SpringRunner.class)
@SpringBootTest
public class MallApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Autowired
	TestMapper testMapper;

	@Test
	public void dbTest() {
		log.info(testMapper.now());
	}

}
