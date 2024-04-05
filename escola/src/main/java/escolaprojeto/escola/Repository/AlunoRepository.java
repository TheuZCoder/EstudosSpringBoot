package escolaprojeto.escola.Repository;

import org.springframework.data.repository.CrudRepository;

import escolaprojeto.escola.Model.AlunoModel;

public interface AlunoRepository extends CrudRepository<AlunoModel, String> {
   
    
}
