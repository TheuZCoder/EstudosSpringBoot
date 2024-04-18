package escolaprojeto.escola.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import escolaprojeto.escola.Model.DisciplinaModel;
import escolaprojeto.escola.Repository.DisciplinaRepository;


@Controller
public class DisciplinaController {

    @Autowired
    private DisciplinaRepository disciplinaRepository;

    @GetMapping("/disciplinas")
    @ResponseBody // Indica que o retorno deve ser serializado em JSON
    public Iterable<DisciplinaModel> getDisciplinas() {
        return disciplinaRepository.findAll();
    }
}
