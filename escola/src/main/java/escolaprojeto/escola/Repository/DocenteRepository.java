package escolaprojeto.escola.Repository;
import org.springframework.data.repository.CrudRepository;

import escolaprojeto.escola.Model.DocenteModel;

public interface DocenteRepository extends CrudRepository<DocenteModel, Long>{
    
    DocenteModel findByDisciplina(String disciplina);
}
