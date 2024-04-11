package escolaprojeto.escola.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import escolaprojeto.escola.Model.AdministradorModel;
import escolaprojeto.escola.Repository.AdministradorRepository;
import escolaprojeto.escola.Repository.PreCadAdmRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AdministradorController {

    @Autowired
    private AdministradorRepository ar;
    @Autowired
    private PreCadAdmRepository pcar;

    boolean acessoAdm = false;


    //METODO PARA FAZER O CADASTRO DO ADMINISTRADOR COM BASE NOS CPF LISTADOS NO SERVIDOR DE CPF AUTORIZADOS
    @PostMapping("cadastro-adm")
    public String postCadastroAdm(AdministradorModel adm) {

        String cpfVerificação = pcar.findByCpf(adm.getCpf()).getCpf();

        if (cpfVerificação.equals(adm.getCpf())) {
            ar.save(adm);
            // enviar mensagem de cadastro com sucesso
            System.out.println("Cadastro realizado com succeso!");
        }
        return "login/login-adm";
    }


    //CODIGO PARA IMPEDIR QUALQUER UM DE ENTRAR DIRETO NA PAGINA DE ADM
    @GetMapping("/interna-adm")
    public String acessoPageInternaAdm() {
        String acesso = "";
        ModelAndView mv = new ModelAndView();
        if (acessoAdm) {
            System.out.println("Acesso Permitido");
            acesso = "interno/interna-adm";

        } else {
            String mensagem = "Acesso não Permitido - faça Login";
            System.out.println(mensagem);
            acesso = "login/login-adm";
            mv.addObject("msg", mensagem);
            mv.addObject("classe", "vermelho");
        }

        return acesso;
    }

    //METODO PARA FAZER LOGIN DO ADMINISTRADOR
    @PostMapping("acesso-adm")
    public ModelAndView AcessoPageAdm(@RequestParam String cpf, @RequestParam String senha,
            RedirectAttributes attributes) {
    
        // Verifica se o CPF fornecido existe no banco de dados
        AdministradorModel usuario = ar.findByCpf(cpf);
        if (usuario == null) {
            // Caso o CPF não exista, exibe uma mensagem de erro
            ModelAndView errorMv = new ModelAndView();
            errorMv.setViewName("redirect:/login-adm");
            return errorMv;
        }
    
        // O CPF existe, continua com a verificação da senha
        boolean acessoSenha = senha.equals(usuario.getSenha());
        ModelAndView mv = new ModelAndView();
    
        if (acessoSenha) {
            String mensagem = "Login Realizado com sucesso";
            System.out.println(mensagem);
            acessoAdm = true;

            mv.setViewName("interno/interna-adm");
        } else {
            String mensagem = "Senha Incorreta";
            System.out.println(mensagem);
            mv.setViewName("redirect:/login-adm");
        }
    
        return mv;
    }
    
}
