package com.javatpoint.controller;

import com.javatpoint.model.Sick;
import com.javatpoint.model.Vaccination;
import com.javatpoint.service.MemberRepository;
import com.javatpoint.service.SickRepository;
import com.javatpoint.service.VaccinationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/sick")
@CrossOrigin
public class SickController {
    private SickRepository sickRepository;
    private VaccinationRepository vaccinationRepository;
    private MemberRepository memberRepository;
    @Autowired
    public SickController(SickRepository sickRepository,VaccinationRepository vaccinationRepository,MemberRepository memberRepository) {
        this.sickRepository = sickRepository;
        this.vaccinationRepository=vaccinationRepository;
        this.memberRepository=memberRepository;
    }

//    @GetMapping("/getAllSick")
//    public ResponseEntity<List<Sick>> getAllSick(){
//        try{
//            List<Sick> sicks=new ArrayList<>();
//            sickRepository.findAll().forEach(e->sicks.add((e)));
//            return new ResponseEntity<>(sicks, HttpStatus.OK);
//        }
//        catch (Exception e)
//        {
//            System.out.println(e);
//            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }


//לא עובד
    @GetMapping("/getAllSickLastMonth")
    public ResponseEntity<List<Integer>> getAllSickLastMonth(){
        try{
            List<Integer> allDaySicks=new ArrayList<>();
            LocalDate negativeBefore = LocalDate.now();
            if(negativeBefore.getDayOfMonth()+1<31)
                negativeBefore.plusDays(1);
            else {
                negativeBefore.plusMonths(1);
                negativeBefore.minusDays(30);
            }
            negativeBefore.plusDays(1);
            LocalDate positiveAfter = LocalDate.now();
            if(positiveAfter.getDayOfMonth()-1>0)
                positiveAfter.minusDays(1);
            else {
                positiveAfter.minusMonths(1);
                positiveAfter.plusDays(30);
            }
            List<Sick> sicks=new ArrayList<>();

            for (int i = 0; i <30 ; i++) {
                if(positiveAfter.getDayOfMonth()-1>0)
                    positiveAfter.minusDays(1);
                else {
                    positiveAfter.minusMonths(1);
                    positiveAfter.plusDays(30);
                }
                if(negativeBefore.getDayOfMonth()-1>0)
                    negativeBefore.minusDays(1);
                else {
                    negativeBefore.minusMonths(1);
                    negativeBefore.plusDays(30);
                }
                sicks = (List<Sick>) sickRepository.findAllByPositiveAfterAndNegativeBefore(positiveAfter, negativeBefore);
                allDaySicks.add(sicks.size());
            }
            return new ResponseEntity<>(allDaySicks, HttpStatus.OK);
        }
        catch (Exception e)
        {
            System.out.println(e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    @GetMapping("/getVaccinationsByNegativeBefore")
//    public ResponseEntity<List<Sick>> getVaccinationsByNegativeBefore(@RequestPart("negative") LocalDate negative) throws IOException {
//        try {
//            List<Sick> s = sickRepository.findAllByNegativeBefore(negative);
//            if(s.size()>0)
//                return new ResponseEntity<>(s, HttpStatus.OK);
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        catch (Exception e){
//            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    @GetMapping("/getVaccinationsByNegativeAfter")
//    public ResponseEntity<List<Sick>> getVaccinationsByNegativeAfter(@RequestPart("negative") LocalDate negative) throws IOException {
//        try {
//            List<Sick> s = sickRepository.findAllByNegativeAfter(negative);
//            if(s.size()>0)
//                return new ResponseEntity<>(s, HttpStatus.OK);
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        catch (Exception e){
//            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//
//    @GetMapping("/getVaccinationsByNPositiveBefore")
//    public ResponseEntity<List<Sick>> getVaccinationsByPositiveBefore(@RequestPart("positive") LocalDate positive) throws IOException{
//        try {
//            List<Sick> s = sickRepository.findAllByPositiveBefore(positive);
//            if(s.size()>0)
//                return new ResponseEntity<>(s, HttpStatus.OK);
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        catch (Exception e){
//            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    @GetMapping("/getVaccinationsByPositiveAfter")
//    public ResponseEntity<List<Sick>> getVaccinationsByPositiveAfter(@RequestPart("positive") LocalDate positive) throws IOException {
//        try {
//            List<Sick> s = sickRepository.findAllByPositiveAfter(positive);
//            if(s.size()>0)
//                return new ResponseEntity<>(s, HttpStatus.OK);
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        catch (Exception e){
//            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    @GetMapping("/getSickByMemberId/{memberId}")
//    public ResponseEntity<Sick> getSickByMemberId(@PathVariable long memberId) {
//        try {
//            Sick s = sickRepository.findByMemberId(memberId);
//            if(s!=null)
//                return new ResponseEntity<>(s, HttpStatus.OK);
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        catch (Exception e){
//            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
    @PostMapping("/createNewSick")
    public ResponseEntity createNewSick(@RequestBody Sick s){
            try{
//                if(memberRepository.findById(s.getMember().getId())!=null){
                    Sick newSick=sickRepository.save(s);
                if(s.getVaccination()!=null){
                    for (Vaccination vaccine : s.getVaccination()) {
                        vaccine.setSick(s);
                        vaccinationRepository.save(vaccine);
                    }
                }
                return new ResponseEntity<>(newSick,HttpStatus.CREATED);
//            }
//                else
//                    return new ResponseEntity<>(null, HttpStatus.CONFLICT);
            }
            catch (Exception e){
                System.out.println(e);
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            }

    }


}
