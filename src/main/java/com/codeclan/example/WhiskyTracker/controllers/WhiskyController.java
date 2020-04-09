package com.codeclan.example.WhiskyTracker.controllers;

import com.codeclan.example.WhiskyTracker.models.Whisky;
import com.codeclan.example.WhiskyTracker.repositories.WhiskyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/whiskies")
public class WhiskyController {

    @Autowired
    WhiskyRepository whiskyRepository;

    @GetMapping
    public ResponseEntity<List<Whisky>> getWhiskyFromYear(
            @RequestParam(name="year", required=false) Integer year,
            @RequestParam(name="distillery", required = false) String name,
            @RequestParam(name="age", required = false) Integer age){
        if (year != null){
            return new ResponseEntity<>(whiskyRepository.findByYear(year),HttpStatus.OK);
        }
        if (name != null && age != null){
            return new ResponseEntity<>(whiskyRepository.findWhiskiesByDistilleryNameAndAge(name, age), HttpStatus.OK);
        }
        return new ResponseEntity<>(whiskyRepository.findAll(), HttpStatus.OK);
    }

//    @GetMapping
//    public ResponseEntity<List<Whisky>> getWhiskiesByYear(@RequestParam(name="year", required = false) Integer year) {
//        if (year != null) {
//            return new ResponseEntity<>(whiskyRepository.findByYear(year), HttpStatus.OK);
//        }
//        return new ResponseEntity<>(whiskyRepository.findAll(), HttpStatus.OK);
//    }

//    @GetMapping(value = "/whiskies/distillery/age")
//    public ResponseEntity<List<Whisky>> getWhiskiesByDistilleryAndAge(@RequestParam(name="distillery") String distillery, @RequestParam(name="age") Integer age) {
//        return new ResponseEntity<>(whiskyRepository.findWhiskiesByDistilleryNameAndAge(distillery, age), HttpStatus.OK);
//    }

}
