package ru.startup.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.startup.dto.ShootingClubDTO;
import ru.startup.mapper.EntertainmentMapper;
import ru.startup.model.entertainment.EntertainmentType;
import ru.startup.model.entertainment.ShootingClub;
import ru.startup.repository.ShootingClubRepository;

@Service
public class ShootingClubService {

    private ShootingClubRepository shootingClubRepository;

    private EntertainmentMapper entertainmentMapper;

    @Autowired
    public void setEntertainmentMapper(EntertainmentMapper entertainmentMapper) {
        this.entertainmentMapper = entertainmentMapper;
    }

    @Autowired
    public void setShootingClubRepository(ShootingClubRepository shootingClubRepository) {
        this.shootingClubRepository = shootingClubRepository;
    }

    public ShootingClubDTO getShootingClubById(Long id) {
        return entertainmentMapper.map(shootingClubRepository.getById(id), ShootingClubDTO.class);
    }

    public ShootingClubDTO createShootingClub(ShootingClubDTO shootingClubDTO, EntertainmentType entertainmentType) {
        ShootingClub shootingClub = entertainmentMapper.map(shootingClubDTO, ShootingClub.class);
        shootingClub.setEntertainmentType(entertainmentType);
        return entertainmentMapper.map(shootingClubRepository.save(shootingClub), ShootingClubDTO.class);
    }
}
