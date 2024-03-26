package escolaprojeto.escola.Repository;
import org.springframework.data.repository.CrudRepository;
import escolaprojeto.escola.Model.PreCadAdm;


public interface PreCadAdmRepository extends CrudRepository<PreCadAdm, String>{
    
    PreCadAdm findByCpf(String cpf);
}
