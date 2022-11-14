package com.voorbeeld.TechItEasy.controllers;

import com.voorbeeld.TechItEasy.exceptions.RecordNotFoundException;
import com.voorbeeld.TechItEasy.model.Television;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;

@RestController
public class TelevisionController {

    private ArrayList<Television> televisions;
    public TelevisionController() {
        televisions = new ArrayList<>();

    }

    @GetMapping("/televisions")
    public ResponseEntity<Object> getTelevisions(){
       return ResponseEntity.ok("television");
    }

    @GetMapping("/televisions/{id}")
    public ResponseEntity<Object> getPerson(@PathVariable int id) {
        if (id < 10) {
            return new ResponseEntity<>("Television " + id, HttpStatus.OK);
        } else {
            throw new RecordNotFoundException("id not found");
        }
    }

    @PostMapping("/televisions")
    public ResponseEntity<Object> createTelevision(@RequestBody Television television) {
        televisions.add(television);
        URI location = URI.create(String.format("/%s", television.getId()));
        return ResponseEntity.created(location).body("Created television");
    }

    @PutMapping("/televisions")
    public ResponseEntity<Object> updateTelevision(@PathVariable int id, @RequestBody Television t) {
        if (id >= 0 && id < televisions.size()) {
            televisions.set(id, t);
            return new ResponseEntity<>(t, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Invalid id", HttpStatus.BAD_REQUEST);
        }
    }
}
