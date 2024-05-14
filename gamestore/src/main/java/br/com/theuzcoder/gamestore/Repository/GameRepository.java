package br.com.theuzcoder.gamestore.Repository;

import org.springframework.data.repository.CrudRepository;
import br.com.theuzcoder.gamestore.Model.GameModel;


public interface GameRepository extends CrudRepository<GameModel, Integer> {
    
}
