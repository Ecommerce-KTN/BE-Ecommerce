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
//	@Bean
//	public CommandLineRunner commandLineRunner(ProductRepository productRepository, CategoryRepository categoryRepository) {
//		return args -> {
//			Product product = new Product();
//			product.setName("Product 1");
//			product.setPrice(1000.0);
//			product.setCreatedTime(new Date());
//			List<OptionValue> optionValues = Arrays.asList(
//					new OptionValue(ProductOption.RAM, "8GB"),
//					new OptionValue(ProductOption.RAM, "16GB"),
//					new OptionValue(ProductOption.RAM, "32GB"),
//					new OptionValue(ProductOption.STORAGE, "256GB"),
//					new OptionValue(ProductOption.STORAGE, "512GB"),
//					new OptionValue(ProductOption.STORAGE, "1TB"),
//					new OptionValue(ProductOption.COLOR, "Black"),
//					new OptionValue(ProductOption.COLOR, "White"),
//					new OptionValue(ProductOption.COLOR, "Red"),
//					new OptionValue(ProductOption.COLOR, "Blue")
//			);
//			product.setOptionValues(optionValues);
//			product.setCategories(Arrays.asList(
//					categoryRepository.findById("66f048509a77155bd3392c52")
//							.orElseThrow(() -> new NoSuchElementException("Category not found: 66eefee6c9a9bf72735a9eed")),
//					categoryRepository.findById("66f194334cc7f65c2c0fa6b9")
//							.orElseThrow(() -> new NoSuchElementException("Category not found: 66eafddadbfe700397ba45a5"))
//			));
//			List<ProductVariant> variants = generateAllVariants(optionValues);
//			product.setProductVariants(variants);
//			productRepository.save(product);
//		};
//	}
//	private List<ProductVariant> generateAllVariants(List<OptionValue> optionValues) {
//		Map<ProductOption, List<String>> optionMap = optionValues.stream()
//				.collect(Collectors.groupingBy(
//						OptionValue::getOptionName,
//						Collectors.mapping(OptionValue::getValues, Collectors.toList())
//				));
//		List<String> ramOptions = optionMap.get(ProductOption.RAM);
//		List<String> storageOptions = optionMap.get(ProductOption.STORAGE);
//		List<String> colorOptions = optionMap.get(ProductOption.COLOR);
//		List<ProductVariant> variants = new ArrayList<>();
//		for (String ram : ramOptions) {
//			for (String storage : storageOptions) {
//				for (String color : colorOptions) {
//					ProductVariant variant = new ProductVariant();
//					variant.setOptionValue(Arrays.asList(
//							new OptionValue(ProductOption.RAM, ram),
//							new OptionValue(ProductOption.STORAGE, storage),
//							new OptionValue(ProductOption.COLOR, color)
//					));
//					variant.setValue(ram + " " + storage + " " + color);
//					variant.setBasePrice(1000.0);
//					variant.setDiscountPrice(900.0);
//					variant.setPrice(900.0);
//					variant.setSKU("SKU-" + ram + "-" + storage + "-" + color);
//					variant.setQuantity(100);
//					variant.setSold(0);
//					variant.setView(0);
//					variant.setRating(0.0);
//					variants.add(variant);
//				}
//			}
//		}
//		return variants;
//	}

//		@Bean
//		public CommandLineRunner commandLineRunner(ProductRepository productRepository, CategoryRepository categoryRepository) {
//			return args -> {
//				List<Category> categories= (Arrays.asList(
//					categoryRepository.findById("66f048509a77155bd3392c52")
//							.orElseThrow(() -> new NoSuchElementException("Category not found: 66eefee6c9a9bf72735a9eed")),
//					categoryRepository.findById("66f194334cc7f65c2c0fa6b9")
//							.orElseThrow(() -> new NoSuchElementException("Category not found: 66eafddadbfe700397ba45a5"))
//			));
//				createProducts(productRepository, categories);
//			};
//		}

//		private List<Category> createCategories(CategoryRepository categoryRepository) {
//			List<Category> categories = Arrays.asList(
//					new Category("Laptops", "High-performance laptops"),
//					new Category("Smartphones", "Latest smartphones"),
//					new Category("Tablets", "Versatile tablets"),
//					new Category("Accessories", "Tech accessories")
//			);
//			return categoryRepository.saveAll(categories);
//		}



//	tu day
//		private void createProducts(ProductRepository productRepository, List<Category> categories) {
//			List<String> productNames = Arrays.asList("UltraBook Pro", "PowerPhone X", "MegaTab 2000", "TechGear Elite");
//			Random random = new Random();
//
//			for (int i = 0; i < productNames.size(); i++) {
//				Product product = new Product();
//				product.setName(productNames.get(i));
//				product.setPrice(500.0 + random.nextDouble() * 1500);
//				product.setCreatedTime(new Date());
//
//				List<OptionValue> optionValues = generateOptionValues(i);
//				product.setOptionValues(optionValues);
//
//				product.setCategories(Arrays.asList(categories.get(i % categories.size())));
//
//				List<ProductVariant> variants = generateAllVariants(optionValues);
//				product.setProductVariants(variants);
//
//				productRepository.save(product);
//			}
//		}
//
//		private List<OptionValue> generateOptionValues(int productIndex) {
//			List<OptionValue> optionValues = new ArrayList<>();
//
//			switch (productIndex) {
//				case 0: // Laptop
//					optionValues.addAll(Arrays.asList(
//							new OptionValue(ProductOption.RAM, "8GB"),
//							new OptionValue(ProductOption.RAM, "16GB"),
//							new OptionValue(ProductOption.RAM, "32GB"),
//							new OptionValue(ProductOption.STORAGE, "256GB SSD"),
//							new OptionValue(ProductOption.STORAGE, "512GB SSD"),
//							new OptionValue(ProductOption.STORAGE, "1TB SSD"),
//							new OptionValue(ProductOption.COLOR, "Silver"),
//							new OptionValue(ProductOption.COLOR, "Space Gray")
//					));
//					break;
//				case 1: // Smartphone
//					optionValues.addAll(Arrays.asList(
//							new OptionValue(ProductOption.RAM, "6GB"),
//							new OptionValue(ProductOption.RAM, "8GB"),
//							new OptionValue(ProductOption.STORAGE, "128GB"),
//							new OptionValue(ProductOption.STORAGE, "256GB"),
//							new OptionValue(ProductOption.COLOR, "Black"),
//							new OptionValue(ProductOption.COLOR, "White"),
//							new OptionValue(ProductOption.COLOR, "Red")
//					));
//					break;
//				case 2: // Tablet
//					optionValues.addAll(Arrays.asList(
//							new OptionValue(ProductOption.STORAGE, "64GB"),
//							new OptionValue(ProductOption.STORAGE, "128GB"),
//							new OptionValue(ProductOption.STORAGE, "256GB"),
//							new OptionValue(ProductOption.COLOR, "Silver"),
//							new OptionValue(ProductOption.COLOR, "Gold"),
//							new OptionValue(ProductOption.COLOR, "Space Gray")
//					));
//					break;
//				case 3: // Accessory
//					optionValues.addAll(Arrays.asList(
//							new OptionValue(ProductOption.COLOR, "Black"),
//							new OptionValue(ProductOption.COLOR, "White"),
//							new OptionValue(ProductOption.COLOR, "Blue"),
//							new OptionValue(ProductOption.COLOR, "Red")
//					));
//					break;
//			}
//
//			return optionValues;
//		}
//
//		private List<ProductVariant> generateAllVariants(List<OptionValue> optionValues) {
//			Map<ProductOption, List<String>> optionMap = optionValues.stream()
//					.collect(Collectors.groupingBy(
//							OptionValue::getOptionName,
//							Collectors.mapping(OptionValue::getValues, Collectors.toList())
//					));
//
//			List<List<OptionValue>> combinations = new ArrayList<>();
//			generateCombinations(new ArrayList<>(optionMap.entrySet()), 0, new ArrayList<>(), combinations);
//
//			Random random = new Random();
//
//			return combinations.stream().map(combo -> {
//				ProductVariant variant = new ProductVariant();
//				variant.setOptionValue(combo);
//				variant.setValue(combo.stream()
//						.map(ov -> ov.getValues())
//						.collect(Collectors.joining(" ")));
//				double basePrice = 500 + random.nextDouble() * 1500;
//				variant.setBasePrice(basePrice);
//				variant.setDiscountPrice(basePrice * 0.9);
//				variant.setPrice(basePrice * 0.9);
//				variant.setSKU("SKU-" + UUID.randomUUID().toString().substring(0, 8));
//				variant.setQuantity(10 + random.nextInt(91));
//				variant.setSold(random.nextInt(50));
//				variant.setView(random.nextInt(1000));
//				variant.setRating(3.5 + random.nextDouble() * 1.5);
//				return variant;
//			}).collect(Collectors.toList());
//		}
//
//		private void generateCombinations(List<Map.Entry<ProductOption, List<String>>> options, int index,
//										  List<OptionValue> current, List<List<OptionValue>> result) {
//			if (index == options.size()) {
//				result.add(new ArrayList<>(current));
//				return;
//			}
//
//			Map.Entry<ProductOption, List<String>> entry = options.get(index);
//			for (String value : entry.getValue()) {
//				current.add(new OptionValue(entry.getKey(), value));
//				generateCombinations(options, index + 1, current, result);
//				current.remove(current.size() - 1);
//			}
//		}

}