package com.yido.clubd;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class ClubdCaveApplication {

	public static void main(String[] args) {
		//SpringApplication.run(asset_manageApplication.class, args);
		/* jasper report viewer 출력을 위한 headless 변경*/
		SpringApplicationBuilder builder = new SpringApplicationBuilder(ClubdCaveApplication.class);
		builder.headless(false);
		ConfigurableApplicationContext context = builder.run(args);
	}

}
