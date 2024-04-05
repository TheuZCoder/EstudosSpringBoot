package escolaprojeto.escola.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import escolaprojeto.escola.Model.AdministradorModel;
import escolaprojeto.escola.Model.AlunoModel;
import escolaprojeto.escola.Repository.AdministradorRepository;
import escolaprojeto.escola.Repository.AlunoRepository;
import escolaprojeto.escola.Repository.PreCadAdmRepository;

@Controller
public class AlunoController {

    @Autowired
    private AdministradorRepository ar;

    @Autowired
    private PreCadAdmRepository pcar;

    @Autowired
    private AlunoRepository alr;

    @PostMapping("cadastro-aluno")
    public String postCadastroAdm(AlunoModel aluno) {

       

     
            alr.save(aluno);
            // enviar mensagem de cadastro com sucesso
            System.out.println("Cadastro realizado com succeso!");
        
        return "interno/interna-adm";
    }

}
