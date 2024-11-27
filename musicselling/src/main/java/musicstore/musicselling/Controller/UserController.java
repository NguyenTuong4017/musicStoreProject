package musicstore.musicselling.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;

import musicstore.musicselling.Entity.UserEntity;
import musicstore.musicselling.Repository.UserRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/login")
    public String login() {
        return "login";
    }

    @GetMapping("sign-up")
    public String getMethodName(Model model) {
        UserEntity user = new UserEntity();
        model.addAttribute("user", user);
        return "sign-up";
    }

    @PostMapping("create-new-user")
    public String postMethodName(@ModelAttribute("user") UserEntity newUser,
            @RequestParam String confirmPassword,
            Model model) {

        String message = null;
        UserEntity usernameCheck = userRepository.findByUsername(newUser.getUsername());

        if (usernameCheck != null) {
            message = "Username was taken, please use another username.";
            model.addAttribute("message", message);
            return "sign-up";
        }

        if (newUser.getPassword().equals(confirmPassword)) {
            newUser.setRole("USER");
            newUser.setPassword(new BCryptPasswordEncoder().encode(newUser.getPassword()));
            userRepository.save(newUser);
            message = "Sign up success";
        }
        model.addAttribute("message", message);
        return "sign-up";
    }

}
