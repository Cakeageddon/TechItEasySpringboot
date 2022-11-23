package com.voorbeeld.TechItEasy.controllers;

import com.voorbeeld.TechItEasy.exceptions.RecordNotFoundException;
import com.voorbeeld.TechItEasy.models.Television;
import com.voorbeeld.TechItEasy.repositories.TelevisionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
    public ResponseEntity<Object> getTelevision(@PathVariable Long id) {

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
        Television televisionSavedLocal = televisionRepository.save(television);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(televisionSavedLocal.getId()).toUri();
        return ResponseEntity.created(location).body("Created television");
    }

    @PutMapping("/televisions/{id}")
    public ResponseEntity<Object> updateTelevision(@PathVariable Long id, @RequestBody Television t) {
        Optional <Television> optionalTelevision = televisionRepository.findById(id);

        if (optionalTelevision.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            Television updateTelevision = optionalTelevision.get();
            if (t.getId() != null) {updateTelevision.setId(t.getId());}
            if (t.getType() != null) {updateTelevision.setType(t.getType());}
            if (t.getBrand() != null) {updateTelevision.setBrand(t.getBrand());}
            if (t.getName() != null) {updateTelevision.setName(t.getName());}
            if (t.getPrice() != null) {updateTelevision.setPrice(t.getPrice());}
            if (t.getAvailableSize() != null) {updateTelevision.setAvailableSize(t.getAvailableSize());}
            if (t.getRefreshRate() != null) {updateTelevision.setRefreshRate(t.getRefreshRate());}
            if (t.getScreenType() != null) {updateTelevision.setScreenType(t.getScreenType());}
            if (t.getScreenQuality() != null) {updateTelevision.setScreenQuality(t.getScreenQuality());}
            if (t.getSmartTv() != null) {updateTelevision.setSmartTv(t.getSmartTv());}
            if (t.getWifi() != null) {updateTelevision.setWifi(t.getWifi());}
            if (t.getVoiceControl() != null) {updateTelevision.setVoiceControl(t.getVoiceControl());}
            if (t.getHdr() != null) {updateTelevision.setHdr(t.getHdr());}
            if (t.getBluetooth() != null) {updateTelevision.setBluetooth(t.getBluetooth());}
            if (t.getAmbiLight() != null) {updateTelevision.setAmbiLight(t.getAmbiLight());}
            if (t.getOriginalStock() != null) {updateTelevision.setOriginalStock(t.getOriginalStock());}
            if (t.getSold() != null) {updateTelevision.setSold(t.getSold());}
            televisionRepository.save(updateTelevision);
            return ResponseEntity.ok(updateTelevision);
        }
    }

    @DeleteMapping("/televisions/{id}")
    public ResponseEntity<Object> deleteTelevision(@PathVariable Long id) {
        Optional <Television> optionalTelevision = televisionRepository.findById(id);

        if (optionalTelevision.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            televisionRepository.deleteById(id);
            return new ResponseEntity<>("Object deleted", HttpStatus.OK);
        }
    }
}
