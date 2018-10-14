package springMVC.helloworld;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

public class HiController {

    @RequestMapping("/say")
    public String say(Model model){
        model.addAttribute("name", "LG32");
        return "say";
    }
}
