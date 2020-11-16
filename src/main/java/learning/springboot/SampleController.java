package learning.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RestController
@EnableAutoConfiguration
public class SampleController {

    @GetMapping("/")
    @CrossOrigin
    Map home(HttpServletRequest request) {
        String queryStr = request.getQueryString();
        System.out.println(queryStr);
        Map re = new HashMap();
        re.put("result", "Hello World!" + queryStr);
        return re;
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(SampleController.class, args);
    }

}
