package dev.cat;

// import io.swagger.v3.oas.annotations.OpenAPIDefinition;
// import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


// @OpenAPIDefinition(
//         tags = {
//                 @Tag(name = "Employee Module", description = "Operations with employee data"),
//                 @Tag(name = "Product Module", description = "Operations with product data")
//         }
// )
@SpringBootApplication
public class OpenapidemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(OpenapidemoApplication.class, args);
    }

}





