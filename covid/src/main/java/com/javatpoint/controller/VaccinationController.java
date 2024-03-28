package com.javatpoint.controller;


import com.javatpoint.model.Vaccination;
import com.javatpoint.service.VaccinationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/vaccination")
@CrossOrigin
public class VaccinationController {
    private VaccinationRepository vaccinationRepository;
    @Autowired
    public VaccinationController(VaccinationRepository vaccinationRepository) {
        this.vaccinationRepository = vaccinationRepository;
    }

    @GetMapping("/getVaccinationById/{id}")
    public ResponseEntity<Vaccination> getVaccinationById(@PathVariable long id) {
        try {
            Vaccination v = vaccinationRepository.findById(id);
            if(v!=null)
                return new ResponseEntity<>(v, HttpStatus.OK);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getAllVaccination")
    public ResponseEntity<List<Vaccination>> getAllVaccination(){
        try{
            List<Vaccination> vaccinations=new ArrayList<>();
            vaccinationRepository.findAll().forEach(e->vaccinations.add((e)));
            return new ResponseEntity<>(vaccinations, HttpStatus.OK);
        }
        catch (Exception e)
        {
            System.out.println(e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getVaccinationsByGettingBefore")
    public ResponseEntity<List<Vaccination>> getVaccinationsByGettingBefore(@RequestPart("getting") LocalDate getting) throws IOException {
        try {
            List<Vaccination> v = vaccinationRepository.findAllByGettingBefore(getting);
            if(v.size()>0)
                return new ResponseEntity<>(v, HttpStatus.OK);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getVaccinationsByGettingAfter")
    public ResponseEntity<List<Vaccination>> getVaccinationsByGettingAfter(@RequestPart("getting") LocalDate getting) throws IOException {
        try {
            List<Vaccination> v = vaccinationRepository.findAllByGettingAfter(getting);
            if(v.size()>0)
                return new ResponseEntity<>(v, HttpStatus.OK);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getVaccinationsBySickId/{sickid}")
    public ResponseEntity<List<Vaccination>> getVaccinationsBySickId(@PathVariable long sickid) {
        try {
            List<Vaccination> v = vaccinationRepository.findAllBySickId(sickid);
            if(v.size()>0)
                return new ResponseEntity<>(v, HttpStatus.OK);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getVaccinationsByManufacturer/{manufacturer}")
    public ResponseEntity<List<Vaccination>> getVaccinationsByManufacturer(@PathVariable String manufacturer) {
        try {
            List<Vaccination> v = vaccinationRepository.findAllByManufacturer(manufacturer);
            if(v.size()>0)
                return new ResponseEntity<>(v, HttpStatus.OK);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/createNewVaccination")
    public ResponseEntity createNewVaccination(@RequestBody Vaccination v){
//        Vaccination v1=vaccinationRepository.findByGettingAndSickId(v.getGetting(),v.getSick().getId());
//        List<Vaccination> numV=vaccinationRepository.findAllBySickId(v.getSick().getId());
//        if(v1==null && numV.size()<4){
            try{
            Vaccination newVaccination=vaccinationRepository.save(v);
            return new ResponseEntity<>(newVaccination,HttpStatus.CREATED);
            }
            catch (Exception e){
                System.out.println(e);
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            }
//        }
//        else
//            return new ResponseEntity(null,HttpStatus.CONFLICT);
    }
}
