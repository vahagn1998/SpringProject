package remote.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller("test")
@RequestMapping("/")
public class TestController {

    @RequestMapping(path = "/test")
    public String test() {
        return "test";
    }
}
