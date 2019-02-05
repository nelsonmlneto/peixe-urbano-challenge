package hello;

import hello.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import hello.repositories.UserRepository;

@Controller
public class GreetingController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/greeting")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);

        User n = new User();
        n.setName("nelson");
        n.setEmail("nelson@email.com");
        userRepository.save(n);

        return "greeting";
    }

}
