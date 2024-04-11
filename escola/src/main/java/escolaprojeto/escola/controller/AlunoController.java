package escolaprojeto.escola.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import escolaprojeto.escola.Model.AlunoModel;
import escolaprojeto.escola.Repository.AlunoRepository;

@Controller
public class AlunoController {

    @Autowired
    private AlunoRepository alunoRepository;

    boolean acessoAluno = false;

    @PostMapping("/cadastro-aluno")
    public String postCadastroAluno(AlunoModel aluno, Model model) {
        // Verifica se algum campo obrigatório está vazio
        if (aluno.getNome() == null || aluno.getNome().isEmpty() ||
            aluno.getMatricula() == null || aluno.getMatricula().isEmpty() ||
            aluno.getDisciplina() == null || aluno.getDisciplina().isEmpty() ||
            aluno.getSenha() == null || aluno.getSenha().isEmpty()) {
            model.addAttribute("mensagem", "Por favor, preencha todos os campos.");
            return "interno/interna-adm";
        }

        // Salva o aluno se todos os campos estiverem preenchidos
        alunoRepository.save(aluno);
        model.addAttribute("mensagem", "Cadastro de aluno realizado com sucesso!");
        return "interno/interna-adm";
    }

    @PostMapping("acesso-aluno")
    public ModelAndView AcessoPageAdm(@RequestParam String matricula, @RequestParam String senha,
            RedirectAttributes attributes) {
    
        // Verifica se o CPF fornecido existe no banco de dados
        AlunoModel aluno = alunoRepository.findByMatricula(matricula);
        if (aluno == null) {
            
            ModelAndView errorMv = new ModelAndView();
            errorMv.setViewName("redirect:/login-aluno");
            return errorMv;
        }
    
        // O CPF existe, continua com a verificação da senha
        boolean acessoSenha = senha.equals(aluno.getSenha());
        ModelAndView mv = new ModelAndView();
    
        if (acessoSenha) {
            String mensagem = "Login Realizado com sucesso";
            System.out.println(mensagem);
            acessoAluno = true;

            mv.setViewName("interno/interna-aluno");
        } else {
            String mensagem = "Senha Incorreta";
            System.out.println(mensagem);
            mv.setViewName("redirect:/login-aluno");
        }
    
        return mv;
    }
}
