package gredal.simon.carsrus;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@PropertySource("classpath:git.properties")
public class IndexController {

    @Value("${git.build.time:}")
    private String buildTime;

    @Value("${git.commit.id.abbrev:}")
    private String buildCommit;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("buildTime", buildTime);
        model.addAttribute("buildCommit", buildCommit);
        return "index";
    }
}
