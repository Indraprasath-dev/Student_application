import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * <p>
 * Main application class for the CMS application.
 * </p>
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.i2i.cms")
@EnableJpaRepositories(basePackages = "com.i2i.cms.Repository")
@EntityScan(basePackages = "com.i2i.cms.model")
public class CmsApplication {
    public static void main(String[] args) {
	    SpringApplication.run(CmsApplication.class, args);
	}
}
