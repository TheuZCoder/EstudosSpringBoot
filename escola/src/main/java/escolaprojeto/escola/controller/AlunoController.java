package escolaprojeto.escola.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import escolaprojeto.escola.Model.AlunoModel;
import escolaprojeto.escola.Model.DisciplinaModel;
import escolaprojeto.escola.Repository.AlunoRepository;
import escolaprojeto.escola.Repository.DisciplinaRepository;

@Controller
public class AlunoController {

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private DisciplinaRepository disciplinaRepository;

    boolean acessoAluno = false;

    @PostMapping("/cadastro-aluno")
    public String postCadastroAluno(@RequestParam("disciplinas[]") List<String> disciplinas,
            @RequestParam String matricula,
            @RequestParam String nome,
            @RequestParam String senha,
            Model model) {
        // Verifica se algum campo obrigatório está vazio
        if (nome == null || nome.isEmpty() ||
                matricula == null || matricula.isEmpty() ||
                disciplinas == null || disciplinas.isEmpty() ||
                senha == null || senha.isEmpty()) {
            model.addAttribute("mensagem", "Por favor, preencha todos os campos.");
            return "interno/interna-adm";
        }

        // Crie um novo aluno e adicione as disciplinas selecionadas
        AlunoModel aluno = new AlunoModel();
        aluno.setNome(nome);
        aluno.setMatricula(matricula);
        aluno.setSenha(senha);
        for (String disciplinaId : disciplinas) {
            DisciplinaModel disciplina = disciplinaRepository.findById(Long.parseLong(disciplinaId)).orElse(null);
            if (disciplina != null) {
                aluno.addDisciplina(disciplina);
            }
        }

        // Salva o aluno
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
