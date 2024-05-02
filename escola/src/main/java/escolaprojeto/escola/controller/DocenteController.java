package escolaprojeto.escola.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import escolaprojeto.escola.Model.DisciplinaModel;
import escolaprojeto.escola.Model.DocenteModel;
import escolaprojeto.escola.Repository.DisciplinaRepository;
import escolaprojeto.escola.Repository.DocenteRepository;

@Controller
public class DocenteController {

    @Autowired
    private DocenteRepository docenteRepository;

    @Autowired
    private DisciplinaRepository disciplinaRepository;

    boolean acessoProfessor = false;

    @PostMapping("/cadastro-docente")
    public String postCadastroDocente(@RequestParam("disciplinas[]") List<String> disciplinas,
            @RequestParam String cpf,
            @RequestParam String nome,
            @RequestParam String senha,
            Model model) {
        // Verifica se algum campo obrigatório está vazio
        if (nome == null || nome.isEmpty() ||
                cpf == null || cpf.isEmpty() ||
                disciplinas == null || disciplinas.isEmpty() ||
                senha == null || senha.isEmpty()) {
            model.addAttribute("mensagem", "Por favor, preencha todos os campos.");
            return "interno/interna-adm";
        }

        // Crie um novo aluno e adicione as disciplinas selecionadas
        DocenteModel docente = new DocenteModel();
        docente.setNome(nome);
        docente.setCpf(cpf);
        docente.setSenha(senha);
        for (String disciplinaId : disciplinas) {
            DisciplinaModel disciplina = disciplinaRepository.findById(Long.parseLong(disciplinaId)).orElse(null);
            if (disciplina != null) {
                docente.addDisciplina(disciplina);
            }
        }

        // Salva o aluno
        docenteRepository.save(docente);
        model.addAttribute("mensagem", "Cadastro de aluno realizado com sucesso!");
        return "interno/interna-adm";
    }

    @PostMapping("acesso-professor")
    public ModelAndView AcessoPageAdm(@RequestParam String cpf, @RequestParam String senha,
            RedirectAttributes attributes) {
    
        // Verifica se o CPF fornecido existe no banco de dados
        DocenteModel professor = docenteRepository.findByCpf(cpf);
        if (professor == null) {
            
            ModelAndView errorMv = new ModelAndView();
            errorMv.setViewName("redirect:/login-professor");
            return errorMv;
        }
    
        // O CPF existe, continua com a verificação da senha
        boolean acessoSenha = senha.equals(professor.getSenha());
        ModelAndView mv = new ModelAndView();
    
        if (acessoSenha) {
            String mensagem = "Login Realizado com sucesso";
            System.out.println(mensagem);
            acessoProfessor = true;

            mv.setViewName("interno/interna-professor");
        } else {
            String mensagem = "Senha Incorreta";
            System.out.println(mensagem);
            mv.setViewName("redirect:/login-professor");
        }
    
        return mv;
    }
}
