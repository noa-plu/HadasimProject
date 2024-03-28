package com.javatpoint.controller;

import com.javatpoint.dto.MemberDTO;
import com.javatpoint.model.Address;
import com.javatpoint.model.Member;
import com.javatpoint.model.Sick;
import com.javatpoint.model.Vaccination;
import com.javatpoint.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/members")
@CrossOrigin
public class MemberController {

    private MemberRepository memberRepository;
    private SickRepository sickRepository;
    private VaccinationRepository vaccinationRepository;
    private AddressRepository addressRepository;
    private MapStructMapper mapper;
    private static String UPLOAD_DIRECTORY=System.getProperty("user.dir")+"\\image\\";

    @Autowired
    public MemberController(MemberRepository memberRepository,SickRepository sickRepository,VaccinationRepository vaccinationRepository,AddressRepository addressRepository, MapStructMapper mapper) {
        this.memberRepository = memberRepository;
        this.sickRepository=sickRepository;
        this.vaccinationRepository=vaccinationRepository;
        this.addressRepository=addressRepository;
        this.mapper=mapper;
    }

    @GetMapping("/getAllMembers")
    public ResponseEntity<List<Member>> getAllMembers(){
        try{
            List<Member> members=new ArrayList<>();
            memberRepository.findAll().forEach(e->members.add((e)));
            return new ResponseEntity<>(members, HttpStatus.OK);
        }
        catch (Exception e)
        {
            System.out.println(e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getAllMembersNotVaccination")
    public ResponseEntity<Integer> getAllMembersNotVaccination(){
        try{
            List<Member> members=new ArrayList<>();
            memberRepository.findAll().forEach(e->members.add((e)));
            Predicate<Member> notVaccination = member -> member.getSick()==null||(member.getSick().getVaccination()).size()==0;
            List<Member> membersNotVaccination=members.stream().filter(notVaccination).collect(Collectors.toList());
            return new ResponseEntity<>(membersNotVaccination.size(), HttpStatus.OK);
        }
        catch (Exception e)
        {
            System.out.println(e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getMemberByAddressId/{addressId}")
    public ResponseEntity<Member> getMemberByAddressId(@PathVariable long addressId) {
        try {
            Member m = memberRepository.findByAddressId(addressId);
            if (m != null)
                return new ResponseEntity<>(m, HttpStatus.OK);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @GetMapping("/getMembersByDateBirth")
    public ResponseEntity<List<Member>> getMembersByDateBirth(@RequestPart("dateBirth") LocalDate dateBirth) throws IOException{
        try {
            List<Member> m = memberRepository.findAllByDateBirth(dateBirth);
            if (m.size()>0)
                return new ResponseEntity<>(m, HttpStatus.OK);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getMembersByDateBirthBefore")
    public ResponseEntity<List<Member>> getMembersByDateBirthBefore(@RequestPart("dateBirth") LocalDate dateBirth)throws IOException {
        try {
            List<Member> m = memberRepository.findAllByDateBirthBefore(dateBirth);
            if (m.size()>0)
                return new ResponseEntity<>(m, HttpStatus.OK);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getMembersByDateBirthAfter")
    public ResponseEntity<List<Member>> getMembersByDateBirthAfter(@RequestPart("dateBirth") LocalDate dateBirth) throws IOException{
        try {
            List<Member> m = memberRepository.findAllByDateBirthAfter(dateBirth);
            if (m.size()>0)
                return new ResponseEntity<>(m, HttpStatus.OK);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch (Exception e){

            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getMemberById/{id}")
    public ResponseEntity<Member> getMemberById(@PathVariable long id) {
        try {
            Member m = memberRepository.findById(id);
            if(m!=null)
                return new ResponseEntity<>(m, HttpStatus.OK);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getDto/{id}")
    public ResponseEntity <MemberDTO> getDTO(@PathVariable long id) throws IOException {
        try{
            Member m = memberRepository.findById(id);
            if(m!=null)
                return new ResponseEntity<>(mapper.memberToDTO(m), HttpStatus.OK);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch (Exception e)
        {
            System.out.println(e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("getMembersDTO")
    public ResponseEntity<List<MemberDTO>> getMembersDTO(){
        try{
            List<Member> clients=new ArrayList<>();
            memberRepository.findAll().forEach(m->clients.add(m));
            System.out.println(clients);
            return new ResponseEntity(mapper.membersToDTO(clients), HttpStatus.OK);
        }
        catch (Exception e){
            System.out.println(e);
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/createNewMemberWithImage")
    public ResponseEntity<Member> createNewUserWithImage(@RequestPart("image") MultipartFile file,
                                                       @RequestPart("member") Member m) throws IOException {
        Member m1= memberRepository.findByTz(m.getTz());
        if(m1==null){
            try {
                String filePath = UPLOAD_DIRECTORY + file.getOriginalFilename();
                Path filename = Paths.get(filePath);
                Files.write(filename, file.getBytes());
                m.setImage(filePath);
                if(m.getDateBirth().isAfter(LocalDate.now())||m.getTz().length()!=9||m.getPhone().length()<9||m.getPhone().length()>10||m.getMobilePhone().length()!=10)
                    return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
                Member newMember= memberRepository.save(m);
                Address a=m.getAddress();
                a.setMember(newMember);
                addressRepository.save(a);
                if(m.getSick()!=null){
                Sick s = m.getSick();
                s.setMember(newMember);
                sickRepository.save(s);
                for (Vaccination vaccine : s.getVaccination()) {
                    vaccine.setSick(s);
                    vaccinationRepository.save(vaccine);
                }}
                return  new ResponseEntity<>(newMember,HttpStatus.CREATED);
            }
            catch (Exception e){
                System.out.println(e);
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        else
            return new ResponseEntity(HttpStatus.CONFLICT);
    }


}
