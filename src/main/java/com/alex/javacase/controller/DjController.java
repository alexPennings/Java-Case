package com.alex.javacase.controller;

import com.alex.javacase.entitys.Dj;
import com.alex.javacase.repository.DjRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class DjController {

    @Autowired
    DjRepository djRepository;

    @GetMapping("/dj")
    public ArrayList<Dj> getAllDj(){

        return (ArrayList<Dj>) djRepository.findAll();
    }

    @PostMapping("/dj")
    public ArrayList<Dj> createDj(@RequestBody Dj dj){
        Dj newDj = new Dj(dj.getName());
        djRepository.save(newDj);

        return (ArrayList<Dj>) djRepository.findAll();
    }

    @DeleteMapping("/dj")
    public ArrayList<Dj> deleteDj(@RequestParam int id){
        Optional<Dj> optionalDjToDelete = djRepository.findById(id);

        if(optionalDjToDelete.isPresent() == true){
            Dj djToDelete =  optionalDjToDelete.get();
      djRepository.delete(djToDelete);
        }else{
            System.out.println("Failed to find Dj to delete");

        }
        return (ArrayList<Dj>) djRepository.findAll();
    }

    @PutMapping("/dj")
    public Dj editDj(@RequestBody Dj receivedDj){
        Optional<Dj> optionalDjToEdit = djRepository.findById(receivedDj.getId());

        if(optionalDjToEdit.isPresent() == true){
            Dj djToEdit = optionalDjToEdit.get();
            djToEdit.setName(receivedDj.getName());

            djRepository.save(djToEdit);
        }else {
            System.out.println("couldnt find Dj to edit");
        }
        return receivedDj;
    }
}