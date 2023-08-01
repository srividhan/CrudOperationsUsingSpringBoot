package com.example.mapping.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.mapping.model.Mapping;
import com.example.mapping.repository.MappingRepository;

@RestController
@RequestMapping("/pro")
public class MappingController {
    
    @Autowired
    MappingRepository mappingRepository;

    @GetMapping("/show")
    public List<Mapping> getAllDetails(){
        return (List<Mapping>) mappingRepository.findAll();
    }

    @PostMapping("/create")
    public ResponseEntity<Mapping> createDetials(@RequestBody Mapping mapping){
        Mapping _mapping = mappingRepository
        .save(new Mapping(mapping.getName(),mapping.getCity(),mapping.getPassword()));
        return new ResponseEntity<Mapping>(_mapping,HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<HttpStatus> deleteAllDetails(){
        mappingRepository.deleteAll();
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/insert/{id}")
    public ResponseEntity<Mapping> updateDetails(@PathVariable("id") Long id,@RequestBody Mapping mapping){
        Optional <Mapping>__mapping = mappingRepository.findById(id);
        if(__mapping.isPresent()){  
            Mapping _mapping=__mapping.get();
            _mapping.setName(mapping.getName());
            _mapping.setCity(mapping.getCity());
            _mapping.setPassword(mapping.getPassword());
            return new ResponseEntity<>
            (mappingRepository.save(_mapping),HttpStatus.OK);
    }
    else{
        return new ResponseEntity<Mapping> (HttpStatus.NOT_FOUND);
    }
    }

    @GetMapping("/get/{id}")
    public  ResponseEntity<Mapping> getDetailById(@PathVariable Long id){
        Optional<Mapping> _mapping = mappingRepository.findById(id);
        if(_mapping.isPresent()){
            return new ResponseEntity<>(_mapping.get(), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteById(@PathVariable Long id){
        mappingRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

