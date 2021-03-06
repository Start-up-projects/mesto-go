package ru.startup.controller.entertainment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.startup.dto.entertainment.MassageDTO;
import ru.startup.model.entertainment.EntertainmentType;
import ru.startup.service.entertainment.MassageService;

@RestController
public class MassageController {

    private MassageService massageService;

    @Autowired
    public void setMassageService(MassageService massageService) {
        this.massageService = massageService;
    }

    @GetMapping("/api/massage/{id}")
    public ResponseEntity<MassageDTO> getMassageById(@PathVariable Long id) {
        if (!massageService.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(massageService.getMassageById(id), HttpStatus.OK);
    }

    @DeleteMapping("/api/massage/{id}")
    public ResponseEntity<Void> deleteMassageById(@PathVariable Long id) {
        if (!massageService.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        massageService.deleteMassageById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/api/massage")
    public ResponseEntity<MassageDTO> createMassage(@RequestBody MassageDTO massageDTO, @RequestParam EntertainmentType entertainmentType) {
        if (massageService.existsByName(massageDTO.getName())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(massageService.createMassage(massageDTO, entertainmentType), HttpStatus.CREATED);
    }
}
