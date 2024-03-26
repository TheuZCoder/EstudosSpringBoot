package escolaprojeto.escola.Repository;

import org.springframework.data.repository.CrudRepository;

import escolaprojeto.escola.Model.AdministradorModel;

public interface AdministradorRepository extends CrudRepository<AdministradorModel, String>{

    AdministradorModel findByCpf (String cpf);
} 
