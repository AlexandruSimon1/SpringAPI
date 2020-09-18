package config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Data
@Component
@PropertySource("application-${spring-profile-activate}.properties")
public class DBConstants {
    @Value("${db_driver}")
    private String db_driver;
    @Value("${db_url}")
    private String db_url;
    @Value("${db_username}")
    private String db_username;
    @Value("${db_password}")
    private String db_password;
    @Value("${db_name}")
    private String db_name;
}
