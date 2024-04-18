package escolaprojeto.escola.Repository;
import org.springframework.data.repository.CrudRepository;
import escolaprojeto.escola.Model.DisciplinaModel;

public interface DisciplinaRepository extends CrudRepository<DisciplinaModel, Long> {
    Iterable<DisciplinaModel> findAll();
}