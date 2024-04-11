package escolaprojeto.escola.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import escolaprojeto.escola.Model.AlunoModel;
import escolaprojeto.escola.Model.DocenteModel;
import escolaprojeto.escola.Repository.AlunoRepository;
import escolaprojeto.escola.Repository.DocenteRepository;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ListarController {

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private DocenteRepository docenteRepository;

    @GetMapping("cadastro-aluno")
    @ResponseBody
    public List<AlunoModel> listarAlunos() {
        Iterable<AlunoModel> alunosIterable = alunoRepository.findAll();
        List<AlunoModel> alunosList = new ArrayList<>();
        alunosIterable.forEach(alunosList::add);
        return alunosList;
    }

    @GetMapping("cadastro-docente")
    @ResponseBody
    public List<DocenteModel> listarDocentes() {
        Iterable<DocenteModel> docentesIterable = docenteRepository.findAll();
        List<DocenteModel> docentesList = new ArrayList<>();
        docentesIterable.forEach(docentesList::add);
        return docentesList;
    }
}
