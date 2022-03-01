package ru.startup.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.startup.dto.ExtremeDTO;
import ru.startup.mapper.EntertainmentMapper;
import ru.startup.model.entertainment.EntertainmentType;
import ru.startup.model.entertainment.Extreme;
import ru.startup.repository.ExtremeRepository;

@Service
public class ExtremeService {

    private ExtremeRepository extremeRepository;

    private EntertainmentMapper entertainmentMapper;

    @Autowired
    public void setEntertainmentMapper(EntertainmentMapper entertainmentMapper) {
        this.entertainmentMapper = entertainmentMapper;
    }

    @Autowired
    public void setExtremeRepository(ExtremeRepository extremeRepository) {
        this.extremeRepository = extremeRepository;
    }

    public ExtremeDTO getExtremeById(Long id) {
        return entertainmentMapper.map(extremeRepository.getById(id), ExtremeDTO.class);
    }

    public ExtremeDTO createExtreme(ExtremeDTO extremeDTO, EntertainmentType entertainmentType) {
        Extreme extreme = entertainmentMapper.map(extremeDTO, Extreme.class);
        extreme.setEntertainmentType(entertainmentType);
        return entertainmentMapper.map(extremeRepository.save(extreme), ExtremeDTO.class);
    }
}
