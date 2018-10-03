package remote.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller("test")
@RequestMapping()
public class TestController {

    @RequestMapping(path = "/testGet", method = RequestMethod.GET)
    public String test() {
        return "test";
    }
}
