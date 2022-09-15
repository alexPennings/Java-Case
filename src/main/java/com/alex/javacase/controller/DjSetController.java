package com.alex.javacase.controller;

import com.alex.javacase.entitys.Dj;
import com.alex.javacase.entitys.DjSet;
import com.alex.javacase.repository.DjRepository;
import com.alex.javacase.repository.DjSetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class DjSetController {

    @Autowired
    DjSetRepository djSetRepository;
    @Autowired
    DjRepository djRepository;





    @GetMapping("/djset")
    public ArrayList<DjSet> getAllDjSet(){

        return (ArrayList<DjSet>) djSetRepository.findAll();
    }
    @GetMapping("/djset/specificset")
    public DjSet getSpecificSetById(@RequestParam int id){
        Optional<DjSet> optionalDjSet = djSetRepository.findById(id);
// used depricated method in ternary, should change
       return optionalDjSet.isPresent() ? optionalDjSet.get() : new DjSet("",new Date(0,0,0),0);
    }

    @PostMapping("/djset")
    public ResponseEntity<DjSet> createDjset(@Valid @RequestBody DjSet receivedDjSet, @RequestParam int djId){
        Optional<Dj> optionalDj = djRepository.findById(djId);
        if(!optionalDj.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
            Dj dj = optionalDj.get();
            DjSet djSet = new DjSet(receivedDjSet.getName(),receivedDjSet.getStartDate(),receivedDjSet.getDuration());
            djSetRepository.save(djSet);
            dj.addDjSet(djSet);
            djRepository.save(dj);
            return new ResponseEntity<>(djSet,HttpStatus.OK);
    }

    @DeleteMapping("/djset")
    public ArrayList<DjSet> deleteDjSet(@RequestParam int id){
        Optional<DjSet> optionalDjSetToDelete = djSetRepository.findById(id);

        if(optionalDjSetToDelete.isPresent() == true){
            DjSet djSetToDelete = optionalDjSetToDelete.get();
            djSetRepository.delete(djSetToDelete);
        }else{
            System.out.println("Couldnt find DjSet to delete");
        }
        return (ArrayList<DjSet>) djSetRepository.findAll();
    }

    @PutMapping("/djset")
    public DjSet editDjSet(@Valid @RequestBody DjSet receivedDjSet){
        Optional<DjSet> optionalDjToEditSet = djSetRepository.findById(receivedDjSet.getId());

        if(optionalDjToEditSet.isPresent() == true){
            DjSet djSetToEdit = optionalDjToEditSet.get();

            djSetToEdit.setName(receivedDjSet.getName());
            djSetToEdit.setDuration(receivedDjSet.getDuration());
            djSetToEdit.setStartDate(receivedDjSet.getStartDate());

            djSetRepository.save(djSetToEdit);
        }else {
            System.out.println("coulnt find Djset to edit");
        }
        return receivedDjSet;
    }
}
