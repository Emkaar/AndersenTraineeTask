package GamesCollection.controller;

import GamesCollection.model.users.User;
import GamesCollection.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class SignUpController {

    @Autowired
    UserService userService;

    @GetMapping("/signup")
    public String signUpPage(@ModelAttribute ("userForm") User user){
        return "signUpPage";
    }

    @PostMapping("/signup")
    public String signUpNewUser(@ModelAttribute ("userForm") @Valid User userForm, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()) {
            return "signUpPage";
        }
        if (!userForm.getPassword().equals(userForm.getPasswordConfirm())){
            model.addAttribute("passwordError", "Пароли не совпадают");
            return "signUpPage";
        }
        if (!userService.saveUser(userForm)){
            model.addAttribute("usernameError", "Пользователь с таким именем уже существует");
            return "signUpPage";
        }
        userService.saveUser(userForm);
        return "redirect:/main";
    }

    @GetMapping("/main")
    public String mainPage(@ModelAttribute("userForm") User user){
        return "main";
    }
}
