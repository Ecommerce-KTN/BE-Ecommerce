package com.beecommerce;

import com.beecommerce.models.Product;
import com.beecommerce.repositories.ProductRepository;
import jakarta.servlet.Filter;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

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

//	@Bean
//    public CommandLineRunner commandLineRunner(ProductRepository productRepository) {
//		return args -> {
//			if (productRepository.findById("1").isEmpty()) {
//				Product product = new Product("name");
//				productRepository.save(product);
//			}
//		};
//	}


//	// Check security filer chain
//	@Bean
//	public CommandLineRunner printFilterChain(ApplicationContext ctx) {
//		return args -> {
//			FilterChainProxy filterChainProxy = ctx.getBean(FilterChainProxy.class);
//			List<Filter> filters = filterChainProxy.getFilters("/");
//
//			System.out.println("Filters for root path:");
//			for (Filter filter : filters) {
//				System.out.println(filter.getClass().getName());
//			}
//		};
//	}
}

