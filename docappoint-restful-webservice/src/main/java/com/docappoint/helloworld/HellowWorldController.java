package com.docappoint.helloworld;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.docappoint.dao.TestDataDao;
import com.docappoint.repository.CommonRepository;



@CrossOrigin(origins="http://localhost:4200")
@RestController
public class HellowWorldController {
	
	@Autowired
	CommonRepository commonRepo;
	
	@GetMapping(path="hello-world-bean")
	public HelloWorldBean helloWorldBean() {
		//return new HelloWorldBean("Hello World - changed");
		throw new RuntimeException("Some Error Happenned,please contact support");
	}
	
	@GetMapping(path="hello-world/path-variable/{name}")
	public HelloWorldBean helloWorldBeanWithPathVariable(@PathVariable String name) {
		return new HelloWorldBean("Hello World :"+name);
	}
	
	@GetMapping(path="dbtest")
	public ArrayList<TestDataDao> dbTest() {
		return commonRepo.testMethod();
	}
}
