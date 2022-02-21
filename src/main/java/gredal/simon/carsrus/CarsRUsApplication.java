package gredal.simon.carsrus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

@SpringBootApplication
@RestController
public class CarsRUsApplication {
    public static void main(String[] args) {
        SpringApplication.run(CarsRUsApplication.class, args);
    }

    private String html;

    @GetMapping
    public String index() {
        if (html == null) {
            InputStream resource = ClassLoader.getSystemResourceAsStream("static/index.html");
            assert resource != null;
            BufferedReader reader = new BufferedReader(new InputStreamReader(resource));
            html = String.join("", reader.lines().toList());
        }
        return html;
    }
}
