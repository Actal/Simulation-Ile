package fr.formation.api;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonView;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import fr.formation.dao.IBatimentDao;
import fr.formation.dao.IBiomeDao;
import fr.formation.dao.ISimEtatDao;
import fr.formation.model.Batiment;
import fr.formation.model.Biome;
import fr.formation.model.SimulationEtat;

// @Controller
// @ResponseBody
@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class ApiController {
    @Autowired
    private IBatimentDao daoBatiment;
    @Autowired
    private IBiomeDao daoBiome;
    @Autowired
    private ISimEtatDao daoSimEtat;

    @JsonView(Views.Batiments.class)
    @GetMapping("/batiments")
    public List<Batiment> findBatiments(){
        return this.daoBatiment.findAll();
    }

    @JsonView(Views.Biomes.class)
    @GetMapping("/biomes")
    public List<Biome> findBiomes(){
        return this.daoBiome.findAll();
    }

    @JsonView(Views.SimulationEtat.class)
    @GetMapping("/dateTimeSimu")
    public SimulationEtat findDateTimeSimu(){
        return daoSimEtat.findById(1).get();
    }
}
