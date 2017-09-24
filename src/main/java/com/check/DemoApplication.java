package com.check;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller//使用RestController会返回字符串而不是页面
@SpringBootApplication
public class DemoApplication {
	
	
	@RequestMapping("/")
	public String index() {
		return "index";
		}

	
	
	
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}