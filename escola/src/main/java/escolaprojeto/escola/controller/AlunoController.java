package escolaprojeto.escola.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import escolaprojeto.escola.Model.AlunoModel;
import escolaprojeto.escola.Model.DisciplinaModel;
import escolaprojeto.escola.Repository.AlunoRepository;
import escolaprojeto.escola.Repository.DisciplinaRepository;
import jakarta.servlet.http.HttpSession;

@Controller
public class AlunoController {

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private DisciplinaRepository disciplinaRepository;

    boolean acessoAluno = false;

    // METODO PARA CADASTRAR NOVOS ALUNOS
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
        System.out.println("Disciplinas associadas ao aluno: " + aluno.getDisciplinas()); // Adicione este log
        model.addAttribute("mensagem", "Cadastro de aluno realizado com sucesso!");
        return "interno/interna-adm";
    }

    // LOGIN DO ALUNO
    @PostMapping("acesso-aluno")
    public ModelAndView AcessoPageAluno(@RequestParam String matricula, @RequestParam String senha, HttpSession session,
            RedirectAttributes attributes) {

        AlunoModel aluno = alunoRepository.findByMatricula(matricula);
        boolean acessoSenha = senha.equals(aluno.getSenha());
        ModelAndView mv = new ModelAndView();

        if (acessoSenha) {
            String mensagem = "Login Realizado com sucesso";
            System.out.println(mensagem);
            acessoAluno = true;
            session.setAttribute("aluno", aluno);
            System.out.println("Aluno na sessão: " + session.getAttribute("aluno")); // Adicione este log
            // Adicione o aluno logado na sessão
            session.setAttribute("aluno", aluno);
            System.out.println(session);
            mv.setViewName("interno/Aluno/interna-aluno");
        } else {
            String mensagem = "Senha Incorreta";
            System.out.println(mensagem);
            mv.setViewName("redirect:/login-aluno");
        }

        return mv;
    }

    @GetMapping("/list-aluno")
    public ModelAndView listarDocentes() {
        ModelAndView mv = new ModelAndView("interno/Aluno/interna-aluno");
        mv.addObject("alunos", alunoRepository.findAll());
        
        return mv;
    }

    /*@GetMapping("/aluno-filtrado")
    public ModelAndView filtroAluno() {
        ModelAndView mv = new ModelAndView("fragmentos/aluno-filtrado");
        mv.addObject("alunos", alunoRepository.findAll());
        return mv;
    }

    @RequestMapping(value = "/deletar-aluno/{matricula}", method = RequestMethod.GET)
    public String deletarAluno(@PathVariable("matricula") String matricula) {
        // Buscar o aluno pelo RG
        AlunoModel aluno = alunoRepository.findByMatricula(matricula);
    
        // Verificar se o aluno foi encontrado
        if (aluno != null) {
            // Remover o aluno
            alunoRepository.delete(aluno);
        }
    
        return "redirect:/list-aluno";
    }

    @RequestMapping(value = "/edit-aluno/{matricula}", method = RequestMethod.GET)
    public ModelAndView editarAluno(@PathVariable("matricula") String matricula) {
        ModelAndView mv = new ModelAndView("interna/aluno/edit-aluno");
        mv.addObject("aluno", alunoRepository.findByMatricula(matricula));
        return mv;
    }
    @RequestMapping(value = "/edit-aluno/{matricula}", method = RequestMethod.POST)
    public String atualizarAluno(AlunoModel aluno) {
        alunoRepository.save(aluno);
        return "redirect:/list-aluno";
    }

    /*@GetMapping("/list-boletim")
    public ModelAndView listarBoletim(HttpSession session) {
        ModelAndView mv = new ModelAndView("areaAluno/boletimAluno");
       
        AlunoModel aluno = (AlunoModel) session.getAttribute("aluno");
        if (aluno != null) {
           
        
        mv.addObject("aluno", aluno);
        mv.addObject("boletim", lr.findAll());
            
        } else {
            // Redirecionar para a página de login se o professor não estiver logado
            mv.setViewName("redirect:/login-aluno");
        }
        return mv;
    } */
}
