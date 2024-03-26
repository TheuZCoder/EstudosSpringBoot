package escolaprojeto.escola.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import escolaprojeto.escola.Model.AdministradorModel;
import escolaprojeto.escola.Repository.AdministradorRepository;
import escolaprojeto.escola.Repository.PreCadAdmRepository;


@Controller
public class AdministradorController {

    @Autowired
    private AdministradorRepository ar;
    @Autowired
    private PreCadAdmRepository pcar;

    @PostMapping("cadastro_adm")
    public String postCadastroAdm(AdministradorModel adm) {

        String cpfVerificação = pcar.findByCpf(adm.getCpf()).getCpf();

        if (cpfVerificação.equals(adm.getCpf())){} 
        ar.save(adm);
        //enviar mensagem de cadastro com sucesso
        System.out.println("Cadastro realizado com succeso!");
        return "login/login_adm";
    }
    
}
