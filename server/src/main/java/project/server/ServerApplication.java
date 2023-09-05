package project.server;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import project.server.enumeration.Status;
import project.server.model.Server;
import project.server.repo.ServerRepo;

import java.util.Arrays;

@SpringBootApplication
public class ServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class, args);
	}

	@Bean
	CommandLineRunner run(ServerRepo serverRepo){
		return args -> {
			serverRepo.save(new Server(1, "192.168.1.14", "Ubuntu Linux", "16 GB", "Personal PC", "http://localhost:8080/server/image/server1.png", Status.SERVER_UP));
			serverRepo.save(new Server(2, "192.168.1.45", "Windows 10", "8 GB", "Personal PC", "http://localhost:8080/server/image/server4.png", Status.SERVER_DOWN));
			serverRepo.save(new Server(3, "192.168.1.44", "Windows 10", "8 GB", "Personal PC", "http://localhost:8080/server/image/server2.png", Status.SERVER_UP));
			serverRepo.save(new Server(4, "172.16.2.10", "Ubuntu 20.04", "16 GB", "Personal PC", "http://localhost:8080/server/image/server1.png", Status.SERVER_DOWN));
			serverRepo.save(new Server(5, "10.0.0.3", "macOS Catalina", "32 GB", "Personal PC", "http://localhost:8080/server/image/server3.png", Status.SERVER_DOWN));
			serverRepo.save(new Server(6, "192.168.0.67", "Fedora 34", "4 GB", "Personal PC", "http://localhost:8080/server/image/server4.png", Status.SERVER_UP));
			serverRepo.save(new Server(7, "192.168.1.110", "Nitro5", "64 GB", "Personal PC", "http://localhost:8080/server/image/server3.png", Status.SERVER_DOWN));

		};
	}

	@Bean
	public CorsFilter corsFilter() {
		UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		corsConfiguration.setAllowCredentials(true);
		corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:3000", "http://localhost:4200"));
		corsConfiguration.setAllowedHeaders(Arrays.asList("Origin", "Access-Control-Allow-Origin", "Content-Type",
				"Accept", "Jwt-Token", "Authorization", "Origin", "Accept", "X-Requested-With",
				"Access-Control-Request-Method", "Access-Control-Request-Headers"));
		corsConfiguration.setExposedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Jwt-Token", "Authorization",
				"Access-Control-Allow-Origin", "Access-Control-Allow-Credentials", "Filename"));
		corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
		urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
		return new CorsFilter(urlBasedCorsConfigurationSource);
	}

}
