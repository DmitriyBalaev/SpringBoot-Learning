package dmbl.demo.controllers;

import dmbl.demo.model.City;
import dmbl.demo.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Controller {
    private final CityRepository cityRepository;

    @Autowired
    public Controller(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @GetMapping("/c")
    public List<City> getAllCities(){
        return cityRepository.findAll();
    }
}
