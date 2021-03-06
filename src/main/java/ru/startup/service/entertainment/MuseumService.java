package ru.startup.service.entertainment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.startup.dto.entertainment.MuseumDTO;
import ru.startup.mapper.EntertainmentMapper;
import ru.startup.model.entertainment.EntertainmentType;
import ru.startup.model.entertainment.Museum;
import ru.startup.repository.entertaiment.MuseumRepository;

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

    public MuseumDTO getMuseumById(Long id) {
        return entertainmentMapper.map(museumRepository.getMuseumByIdAndDeletedIsFalse(id));
    }

    public void deleteMuseumById(Long id) {
        Museum museum = museumRepository.getById(id);
        museum.setDeleted(true);
        museumRepository.save(museum);
    }

    public boolean existsById(Long id) {
        return museumRepository.existsById(id);
    }

    public boolean existsByName(String name) {
        return museumRepository.existsByNameAndDeletedIsFalse(name);
    }

    public MuseumDTO createMuseum(MuseumDTO museumDTO, EntertainmentType entertainmentType) {
        Museum museum = entertainmentMapper.map(museumDTO);
        museum.setEntertainmentType(entertainmentType);
        return entertainmentMapper.map(museumRepository.save(museum));
    }
}
