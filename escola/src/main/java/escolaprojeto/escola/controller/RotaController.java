package escolaprojeto.escola.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class RotaController {

    @GetMapping("/")
    public String home() {
        return "home"; // Este é o nome do seu arquivo home.html (sem extensão)
    }
    @GetMapping("/login-adm")
    public String loginAdm() {
        return "login/login-adm";
    }
    @GetMapping("/login-professor")
    public String loginProfessor() {
        return "login/login-professor";
    }
    @GetMapping("/login-aluno")
    public String loginAluno() {
        return "login/login-aluno";
    }

    @GetMapping("/cad-adm")
    public String cadAdm() {
        return "cadastro/cad-adm";
    }

    
    
    
}