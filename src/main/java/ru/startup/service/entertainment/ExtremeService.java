package ru.startup.service.entertainment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.startup.dto.entertainment.ExtremeDTO;
import ru.startup.mapper.EntertainmentMapper;
import ru.startup.model.entertainment.EntertainmentType;
import ru.startup.model.entertainment.Extreme;
import ru.startup.repository.entertaiment.ExtremeRepository;

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
        return entertainmentMapper.map(extremeRepository.getExtremeByIdAndDeletedIsFalse(id));
    }

    public void deleteExtremeById(Long id) {
        Extreme extreme = extremeRepository.getById(id);
        extreme.setDeleted(true);
        extremeRepository.save(extreme);
    }

    public boolean existsById(Long id) {
        return extremeRepository.existsById(id);
    }

    public boolean existsByName(String name) {
        return extremeRepository.existsByNameAndDeletedIsFalse(name);
    }

    public ExtremeDTO createExtreme(ExtremeDTO extremeDTO, EntertainmentType entertainmentType) {
        Extreme extreme = entertainmentMapper.map(extremeDTO);
        extreme.setEntertainmentType(entertainmentType);
        return entertainmentMapper.map(extremeRepository.save(extreme));
    }
}
