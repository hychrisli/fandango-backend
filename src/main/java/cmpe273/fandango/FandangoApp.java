package cmpe273.fandango;

    import org.springframework.boot.SpringApplication;
    import org.springframework.boot.autoconfigure.SpringBootApplication;
    import org.springframework.cache.annotation.EnableCaching;
    import org.springframework.context.annotation.Bean;
    import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
    import org.springframework.security.crypto.password.PasswordEncoder;
    import org.springframework.transaction.annotation.EnableTransactionManagement;

    import javax.annotation.PostConstruct;
    import java.util.TimeZone;

@SpringBootApplication
@EnableTransactionManagement
@EnableCaching
public class FandangoApp {

  @PostConstruct
  void started() {
    TimeZone.setDefault(TimeZone.getTimeZone("US/Pacific"));
  }

  public static void main(String[] args) {

    SpringApplication.run(FandangoApp.class, args);
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
