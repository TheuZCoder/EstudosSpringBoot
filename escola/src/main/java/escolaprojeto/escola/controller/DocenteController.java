package escolaprojeto.escola.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import escolaprojeto.escola.Model.DocenteModel;
import escolaprojeto.escola.Repository.AdministradorRepository;
import escolaprojeto.escola.Repository.AlunoRepository;
import escolaprojeto.escola.Repository.DocenteRepository;
import escolaprojeto.escola.Repository.PreCadAdmRepository;

@Controller
public class DocenteController {
    @Autowired
    private AdministradorRepository ar;

    @Autowired
    private PreCadAdmRepository pcar;

    @Autowired
    private AlunoRepository alr;

    @Autowired
    private DocenteRepository dr;

    @PostMapping("cadastro-docente")
    public String postCadastroAdm(DocenteModel docente) {

        dr.save(docente);
        // enviar mensagem de cadastro com sucesso
        System.out.println("Cadastro realizado com succeso!");

        return "interno/interna-adm";
    }
}
