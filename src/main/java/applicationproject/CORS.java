package applicationproject;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull; // Add this import
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CORS implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(@NonNull CorsRegistry registry) { // Add @NonNull here
        registry.addMapping("/api/**") // Allow CORS for all endpoints under /api
                .allowedOrigins("http://localhost:4200") // Allow requests from Angular app
                .allowedMethods("GET", "POST", "PUT", "DELETE","OPTIONS") // Allow specific HTTP methods
                .allowedHeaders("*") // Allow all headers
                .allowCredentials(true); // Allow credentials (e.g., cookies)
    }
}