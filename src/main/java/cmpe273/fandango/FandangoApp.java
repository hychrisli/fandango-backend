package cmpe273.fandango;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class FandangoApp {

  public static void main(String[] args) {

    SpringApplication.run(FandangoApp.class, args);
  }
}
