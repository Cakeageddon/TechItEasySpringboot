package com.voorbeeld.TechItEasy.controllers;

import com.voorbeeld.TechItEasy.dtos.TelevisionDto;
import com.voorbeeld.TechItEasy.dtos.TelevisionInputDto;
import com.voorbeeld.TechItEasy.service.TelevisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class TelevisionController {
    @Autowired
    private TelevisionService televisionService;

    public TelevisionController(TelevisionService televisionService) {
        this.televisionService = televisionService;
    }

    @GetMapping("/televisions")
    public ResponseEntity<List<TelevisionDto>> getAllTelevisions() {
        return ResponseEntity.ok(televisionService.getAllTelevisions());
    }

    @GetMapping("/televisions/{id}")
    public ResponseEntity<TelevisionDto> getTelevision(@PathVariable Long id) {
        TelevisionDto televisionDto = televisionService.getOneTelevision(id);
        return ResponseEntity.ok(televisionDto);
    }

    @PostMapping("/televisions")
    public ResponseEntity<Object> createTelevision(@RequestBody TelevisionInputDto television) {
        TelevisionDto televisionSavedLocal = televisionService.createTelevision(television);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(televisionSavedLocal.getId()).toUri();
        return ResponseEntity.created(location).body("Created television");
    }

    @PutMapping("/televisions/{id}")
    public ResponseEntity<Object> updateTelevision(@PathVariable Long id, @RequestBody TelevisionInputDto t) {
        return ResponseEntity.ok(televisionService.updateTelevision(id, t));
    }

    @DeleteMapping("/televisions/{id}")
    public ResponseEntity<Object> deleteTelevision(@PathVariable Long id) {
        televisionService.deleteTelevision(id);
        return ResponseEntity.ok("Object with id: " + id + " is deleted");
    }
}
