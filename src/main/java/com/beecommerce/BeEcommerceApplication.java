package com.beecommerce;

import com.beecommerce.models.*;
import com.beecommerce.models.enums.ProductOption;
import com.beecommerce.models.enums.SpecificationOption;
import com.beecommerce.repositories.CategoryRepository;
import com.beecommerce.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
				registry.addMapping("/**")
						.allowedOrigins("http://localhost:3000") // Thay đổi theo domain frontend của bạn
						.allowedMethods("GET", "POST", "PUT", "DELETE")
						.allowedHeaders("*");
			}
		};
	}
//	update specifications to category có id = 1
//	    CPUSPEED("CPU Speed"),
//    CPUTYPE("CPU Type"),
//    SIZEMAINDISPLAY("Size (Main Display)"),
//    RESOLUTIONMAINDISPLAY("Resolution (Main Display)"),
//    TECHNOLOGYMAINDISPLAY("Technology (Main Display)"),
//    COLORDEPTHMAINDISPLAY("Color Depth (Main Display)"),
//    REARCAMERARESOLUTION("Rear Camera - Resolution (Multiple)"),
//    REARCAMERAFNUMBER("Rear Camera - F Number (Multiple)"),
//    REARCAMERAAUTOFOCUS("Rear Camera - Auto Focus"),
//    REARCAMERAOIS("Rear Camera - OIS"),
//    REARCAMERAZOOM("Rear Camera - Zoom"),
//    FRONTCAMERARESOLUTION("Front Camera - Resolution"),
//    FRONTCAMERAFNUMBER("Front Camera - F Number"),
//    FRONTCAMERAAUTOFOCUS("Front Camera - Auto Focus"),
//    MAINCAMERAFLASH("Main Camera Flash"),
//    VIDEORECORDINGRESOLUTION("Video Recording Resolution"),
//    SLOWMOTION("Slow Motion"),
//    AVALIABLESTORAGE("Available Memory"),
//    EXTERNALSTORAGE("External Memory Support"),
//    NUMBEROFSIM("Number of SIM"),
//    SIMSIZE("SIM size"),
//    INFRA("Infra"),
//    SIMSLOTTYPE("SIM Slot Type"),
//    OS("OS"),
//    NFC("NFC"),
//    WIFIDIRECT("Wi-Fi Direct"),
//    BLUETOOTHVERSION("Bluetooth Version"),
//    USBVERSION("USB Version"),
//    ANTPLUS("ANT+"),
//    USBINTERFACE("USB Interface"),
//    LOCATIONTECHNOLOGY("Location Technology"),
//    EARJACK("Earjack"),
//    MHL("MHL"),
//    WIFI("Wi-Fi"),
//    PCSYNC("PC Sync"),
//    SENSOR("Sensors"),
//    INTERNETUSAGETIMELTE("Internet Usage Time(LTE)"),
//    INTERNETUSAGETIMEWIFI("Internet Usage Time(Wi-Fi)"),
//    BATERYCAPACITY("Battery Capacity"),
//    REMOVABLE("Removable"),
//    TALKTIMELTE("Talk Time(LTE)"),
//    STEREO("Stereo Support"),
//    VIDEOPLAYBACKFORMAT("Video Playback Format"),
//    VIDEOPLAYBACKRESOLUTION("Video Playback Resolution"),
//    AUDIOPLAYBACKFORMAT("Audio Playback Format"),
//    GEARSUPPORT("Gear Support"),
//    VIDEOPLAYINGFORMAT("Video Playing Format"),
//    AUDIOPLAYINGFORMAT("Audio Playing Format"),
//    VIDEOPLAYINGRESOLUTION("Video Playing Resolution"),
//    SAMSUNGDEXSUPPORT("Samsung DeX Support"),
//    MOBILETV("Mobile TV"),
//    SVOICE("S Voice");
	@Bean
	public CommandLineRunner updateSpecificationsToCategory(ProductRepository productRepository, CategoryRepository categoryRepository) {
		return args -> {
			Category category = categoryRepository.findById("66f194334cc7f65c2c0fa6b9").get();
			List<SpecificationOption> list= new ArrayList<>();
//			add tất cả
			list.add(SpecificationOption.SIZEMAINDISPLAY);
			list.add(SpecificationOption.RESOLUTIONMAINDISPLAY);
			list.add(SpecificationOption.TECHNOLOGYMAINDISPLAY);
			list.add(SpecificationOption.COLORDEPTHMAINDISPLAY);
			list.add(SpecificationOption.REARCAMERARESOLUTION);
			list.add(SpecificationOption.FRONTCAMERARESOLUTION);
			list.add(SpecificationOption.NUMBEROFSIM);
			list.add(SpecificationOption.BATERYCAPACITY);
			list.add(SpecificationOption.CPUTYPE);
			list.add(SpecificationOption.SIMSIZE);
			list.add(SpecificationOption.DIMENSION);
			list.add(SpecificationOption.INFRA);
			list.add(SpecificationOption.SIMSLOTTYPE);
			list.add(SpecificationOption.OS);
			list.add(SpecificationOption.NFC);
			list.add(SpecificationOption.WIFIDIRECT);
			List<ProductOption> options = new ArrayList<>();
			options.add(ProductOption.COLOR);
			options.add(ProductOption.RAM);
			options.add(ProductOption.STORAGE);

			category.setOptions(options);
			category.setSpecifications(list);
			categoryRepository.save(category);
		};
	}
}