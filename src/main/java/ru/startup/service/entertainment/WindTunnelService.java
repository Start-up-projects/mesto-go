package ru.startup.service.entertainment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.startup.dto.entertainment.WindTunnelDTO;
import ru.startup.mapper.EntertainmentMapper;
import ru.startup.model.entertainment.EntertainmentType;
import ru.startup.model.entertainment.WindTunnel;
import ru.startup.repository.entertaiment.WindTunnelRepository;

@Service
public class WindTunnelService {

    private WindTunnelRepository windTunnelRepository;

    private EntertainmentMapper entertainmentMapper;

    @Autowired
    public void setEntertainmentMapper(EntertainmentMapper entertainmentMapper) {
        this.entertainmentMapper = entertainmentMapper;
    }

    @Autowired
    public void setWindTunnelRepository(WindTunnelRepository windTunnelRepository) {
        this.windTunnelRepository = windTunnelRepository;
    }

    public WindTunnelDTO getWindTunnelById(Long id) {
        return entertainmentMapper.map(windTunnelRepository.getWindTunnelByIdAndDeletedIsFalse(id));
    }

    public void deleteWindTunnelById(Long id) {
        WindTunnel windTunnel = windTunnelRepository.getById(id);
        windTunnel.setDeleted(true);
        windTunnelRepository.save(windTunnel);
    }

    public boolean existsById(Long id) {
        return windTunnelRepository.existsById(id);
    }

    public boolean existsByName(String name) {
        return windTunnelRepository.existsByNameAndDeletedIsFalse(name);
    }

    public WindTunnelDTO createWindTunnel(WindTunnelDTO windTunnelDTO, EntertainmentType entertainmentType) {
        WindTunnel windTunnel = entertainmentMapper.map(windTunnelDTO);
        windTunnel.setEntertainmentType(entertainmentType);
        return entertainmentMapper.map(windTunnelRepository.save(windTunnel));
    }
}
