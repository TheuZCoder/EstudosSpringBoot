package webapp.newsletterjdbc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import webapp.newsletterjdbc.connection.CadastroDAO;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class CadastroController {

    @RequestMapping(value="/cadastro", method=RequestMethod.POST)
    public ModelAndView enviarEmailBanco(@RequestParam("email") String email, @RequestParam("senha") String senha) {
        ModelAndView mv = new ModelAndView("index");
        new CadastroDAO().cadastrar(email,senha);
        return mv;
    }
    

}
