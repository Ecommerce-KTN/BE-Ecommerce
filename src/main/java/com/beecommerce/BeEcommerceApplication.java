package com.beecommerce;

import com.beecommerce.models.*;
import com.beecommerce.models.enums.ProductOption;
import com.beecommerce.repositories.CategoryRepository;
import com.beecommerce.repositories.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.*;
import java.util.stream.Collectors;

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
	public CommandLineRunner commandLineRunner(ProductRepository productRepository, CategoryRepository categoryRepository) {
		return args -> {
			Product product = new Product();
			product.setName("Product 1");
			product.setPrice(1000.0);
			product.setCreatedTime(new Date());

			List<OptionValue> optionValues = Arrays.asList(
					new OptionValue(ProductOption.RAM, "8GB"),
					new OptionValue(ProductOption.RAM, "16GB"),
					new OptionValue(ProductOption.RAM, "32GB"),
					new OptionValue(ProductOption.STORAGE, "256GB"),
					new OptionValue(ProductOption.STORAGE, "512GB"),
					new OptionValue(ProductOption.STORAGE, "1TB"),
					new OptionValue(ProductOption.COLOR, "Black"),
					new OptionValue(ProductOption.COLOR, "White"),
					new OptionValue(ProductOption.COLOR, "Red"),
					new OptionValue(ProductOption.COLOR, "Blue")
			);
			product.setOptionValues(optionValues);

			product.setCategories(Arrays.asList(
					categoryRepository.findById("66f048509a77155bd3392c52")
							.orElseThrow(() -> new NoSuchElementException("Category not found: 66eefee6c9a9bf72735a9eed")),
					categoryRepository.findById("66f194334cc7f65c2c0fa6b9")
							.orElseThrow(() -> new NoSuchElementException("Category not found: 66eafddadbfe700397ba45a5"))
			));

			List<ProductVariant> variants = generateAllVariants(optionValues);
			product.setProductVariants(variants);

			productRepository.save(product);
		};
	}

	private List<ProductVariant> generateAllVariants(List<OptionValue> optionValues) {
		Map<ProductOption, List<String>> optionMap = optionValues.stream()
				.collect(Collectors.groupingBy(
						OptionValue::getOptionName,
						Collectors.mapping(OptionValue::getValues, Collectors.toList())
				));

		List<String> ramOptions = optionMap.get(ProductOption.RAM);
		List<String> storageOptions = optionMap.get(ProductOption.STORAGE);
		List<String> colorOptions = optionMap.get(ProductOption.COLOR);

		List<ProductVariant> variants = new ArrayList<>();

		for (String ram : ramOptions) {
			for (String storage : storageOptions) {
				for (String color : colorOptions) {
					ProductVariant variant = new ProductVariant();
					variant.setOptionValue(Arrays.asList(
							new OptionValue(ProductOption.RAM, ram),
							new OptionValue(ProductOption.STORAGE, storage),
							new OptionValue(ProductOption.COLOR, color)
					));
					variant.setValue(ram + " " + storage + " " + color);
					variant.setBasePrice(1000.0);
					variant.setDiscountPrice(900.0);
					variant.setPrice(900.0);
					variant.setSKU("SKU-" + ram + "-" + storage + "-" + color);
					variant.setQuantity(100);
					variant.setSold(0);
					variant.setView(0);
					variant.setRating(0.0);
					variants.add(variant);
				}
			}
		}

		return variants;
	}
}