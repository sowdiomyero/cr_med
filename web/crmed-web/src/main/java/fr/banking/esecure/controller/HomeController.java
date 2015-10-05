package fr.banking.esecure.controller;


import fr.banking.esecure.form.Subscriber;
import fr.esecure.banking.metier.IEsecureMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @Autowired
    IEsecureMetier metier;

    @ModelAttribute("roles")
    public Subscriber.Role[] roles() {
        return Subscriber.Role.values();
    }
  @RequestMapping(value = "/")
    public String homePage(Model model) {
      Subscriber subscriber = new Subscriber();
      subscriber.setRole(Subscriber.Role.ROLE_USER);
      model.addAttribute("subscriber", subscriber);
      model.addAttribute("transactions", metier.findAllTransaction());
      model.addAttribute("nb_transactions", metier.findAllTransaction().size());
      model.addAttribute("nv_transactions", metier.findAllTransactionNotValidated());
      model.addAttribute("nb_nv_transactions", metier.findAllTransactionNotValidated().size());
        return "homepage";
    }

    @RequestMapping(value = "/loginfailed")
    public String loginFailed(ModelMap model) {
        model.addAttribute("error", "Incorrect Username Or Password");
        model.addAttribute("subscriber", new Subscriber());
        return "homepage";
    }

    @RequestMapping(value = "/clientform")
    public String clientForm(Model model) {
      //pour initialiser le formulaire create user
      Subscriber subscriber = new Subscriber();
      subscriber.setRole(Subscriber.Role.ROLE_USER);
      model.addAttribute("subscriber", subscriber);
      return "clientform";
    }

    @RequestMapping(value = "/userslist")
    public String userListForm(Model model) {
        //pour initialiser le formulaire create user
      Subscriber subscriber = new Subscriber();
      subscriber.setRole(Subscriber.Role.ROLE_USER);
      model.addAttribute("subscriber", subscriber);
       //fin itialisation
      
      Authentication auth = SecurityContextHolder.getContext().getAuthentication();
      String login = auth.getName(); //get logged in username
       
      model.addAttribute("users", metier.findAllUsersWithoutUserConnected(login));
      return "userslist";
    }
    

    
  
    
   

}
