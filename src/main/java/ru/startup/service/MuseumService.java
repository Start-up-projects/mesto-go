package ru.startup.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.startup.dto.MuseumDTO;
import ru.startup.mapper.EntertainmentMapper;
import ru.startup.repository.MuseumRepository;

@Service
public class MuseumService {

    private MuseumRepository museumRepository;

    private EntertainmentMapper entertainmentMapper;

    @Autowired
    public void setEntertainmentMapper(EntertainmentMapper entertainmentMapper) {
        this.entertainmentMapper = entertainmentMapper;
    }

    @Autowired
    public void setMuseumRepository(MuseumRepository museumRepository) {
        this.museumRepository = museumRepository;
    }

    public MuseumDTO getMuseumById(Long id){
        return entertainmentMapper.map(museumRepository.getById(id), MuseumDTO.class);
    }
}