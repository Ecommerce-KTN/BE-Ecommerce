package com.beecommerce;

import com.beecommerce.models.Category;
import com.beecommerce.models.Collection;
import com.beecommerce.models.enums.ProductOption;
import com.beecommerce.repositories.CategoryRepository;
import com.beecommerce.repositories.CollectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Date;
import java.util.List;

@SpringBootApplication
public class BeEcommerceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BeEcommerceApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("*");
			}
		};
	}

	@Bean
	public CommandLineRunner commandLineRunner(CollectionRepository collectionRepository) {
		return args -> {
			Collection collection = new Collection();
			collection.setName("Autumn Collection");
			collectionRepository.save(collection);
		};
	};
}