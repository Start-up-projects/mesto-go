package ru.startup.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.startup.dto.PlanetariumDTO;
import ru.startup.mapper.EntertainmentMapper;
import ru.startup.model.entertainment.EntertainmentType;
import ru.startup.model.entertainment.Planetarium;
import ru.startup.repository.PlanetariumRepository;

@Service
public class PlanetariumService {

    private PlanetariumRepository planetariumRepository;

    private EntertainmentMapper entertainmentMapper;

    @Autowired
    public void setEntertainmentMapper(EntertainmentMapper entertainmentMapper) {
        this.entertainmentMapper = entertainmentMapper;
    }

    @Autowired
    public void setPlanetariumRepository(PlanetariumRepository planetariumRepository) {
        this.planetariumRepository = planetariumRepository;
    }

    public PlanetariumDTO getPlanetariumById(Long id){
        return entertainmentMapper.map(planetariumRepository.getById(id), PlanetariumDTO.class);
    }

    public PlanetariumDTO createPlanetarium(PlanetariumDTO planetariumDTO, EntertainmentType entertainmentType){
        Planetarium planetarium = entertainmentMapper.map(planetariumDTO, Planetarium.class);
        planetarium.setEntertainmentType(entertainmentType);
        return entertainmentMapper.map(planetariumRepository.save(planetarium), PlanetariumDTO.class);
    }
}
