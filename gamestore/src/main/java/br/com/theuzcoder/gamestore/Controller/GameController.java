package br.com.theuzcoder.gamestore.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.theuzcoder.gamestore.Model.GameModel;
import br.com.theuzcoder.gamestore.Repository.GameRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@Controller
public class GameController {
    
    @Autowired
    GameRepository gamerepository;
    //metodos

    @GetMapping("/list")
    public ModelAndView getListGame() {
        ModelAndView mv = new ModelAndView("game-list");
        mv.addObject("games", gamerepository.findAll());

        return mv;
    }

    @GetMapping("/addgame")
    public String getAddGame() {
        return "game-add";
    }
    
    @PostMapping("/addgame")
    public String postAddGame(GameModel game) {

        gamerepository.save(game);
        return "redirect:list";
    }
    

}
