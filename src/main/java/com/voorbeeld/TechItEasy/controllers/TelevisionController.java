package com.voorbeeld.TechItEasy.controllers;

import com.voorbeeld.TechItEasy.exceptions.RecordNotFoundException;
import com.voorbeeld.TechItEasy.models.Television;
import com.voorbeeld.TechItEasy.repositories.TelevisionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.Optional;

@RestController
public class TelevisionController {

    @Autowired
    TelevisionRepository televisionRepository;

    @GetMapping("/televisions")
    public ResponseEntity<Object> getAllTelevisions(){
       return ResponseEntity.ok(televisionRepository.findAll());
    }

    @GetMapping("/televisions/{id}")
    public ResponseEntity<Object> getTelevision(@PathVariable int id) {

        Optional <Television> optionalTelevision = televisionRepository.findById(id);

        if (optionalTelevision.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            televisionRepository.findById(id);
            return ResponseEntity.ok(televisionRepository.findById(id));
        }
    }

    @PostMapping("/televisions")
    public ResponseEntity<Object> createTelevision(@RequestBody Television television) {
        televisionRepository.save(television);
        URI location = URI.create(String.format("/%s", television.getName()));
        return ResponseEntity.created(location).body("Created television");
    }

    @PutMapping("/televisions/{id}")
    public ResponseEntity<Object> updateTelevision(@PathVariable int id, @RequestBody Television t) {
        Optional <Television> optionalTelevision = televisionRepository.findById(id);

        if (optionalTelevision.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            Television updateTelevision = optionalTelevision.get();
            updateTelevision.setId(t.getId());
            updateTelevision.setType(t.getType());
            updateTelevision.setBrand(t.getBrand());
            updateTelevision.setName(t.getName());
            updateTelevision.setPrice(t.getPrice());
            updateTelevision.setAvailableSize(t.getAvailableSize());
            updateTelevision.setRefreshRate(t.getRefreshRate());
            updateTelevision.setScreenType(t.getScreenType());
            updateTelevision.setScreenQuality(t.getScreenQuality());
            updateTelevision.setSmartTv(t.getSmartTv());
            updateTelevision.setWifi(t.getWifi());
            updateTelevision.setVoiceControl(t.getVoiceControl());
            updateTelevision.setHdr(t.getHdr());
            updateTelevision.setBluetooth(t.getBluetooth());
            updateTelevision.setAmbiLight(t.getAmbiLight());
            updateTelevision.setOriginalStock(t.getOriginalStock());
            updateTelevision.setSold(t.getSold());
            return ResponseEntity.ok(updateTelevision);
        }
    }

    @DeleteMapping("/televisions/{id}")
    public ResponseEntity<Object> deleteTelevision(@PathVariable int id) {
        Optional <Television> optionalTelevision = televisionRepository.findById(id);

        if (optionalTelevision.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            televisionRepository.deleteById(id);
            return new ResponseEntity<>("Object deleted", HttpStatus.OK);
        }
    }
}
