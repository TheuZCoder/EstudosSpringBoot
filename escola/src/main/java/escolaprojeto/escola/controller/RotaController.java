package escolaprojeto.escola.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class RotaController {

    @GetMapping("/")
    public String home() {
        return "home"; // Este é o nome do seu arquivo home.html (sem extensão)
    }
    @GetMapping("/login_adm")
    public String loginAdm() {
        return "login/login_adm";
    }
    @GetMapping("/login_professor")
    public String loginProfessor() {
        return "login/login_professor";
    }
    @GetMapping("/login_aluno")
    public String loginAluno() {
        return "login/login_aluno";
    }

    @GetMapping("/cad_adm")
    public String cadAdm() {
        return "cadastro/cad_adm";
    }
    
    
}