package com.javatpoint.controller;

import com.javatpoint.model.Address;
import com.javatpoint.service.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/Address")
@CrossOrigin
public class AddressController {
    private AddressRepository addressRepository;
    @Autowired
    public AddressController(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

//    @GetMapping("/getAllAddress")
//    public ResponseEntity<List<Address>> getAllAddress(){
//        try{
//            List<Address> addresses=new ArrayList<>();
//            addressRepository.findAll().forEach(e->addresses.add((e)));
//            return new ResponseEntity<>(addresses, HttpStatus.OK);
//        }
//        catch (Exception e)
//        {
//            System.out.println(e);
//            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    @GetMapping("/getAddressByAddressId/{addressId}")
//    public ResponseEntity<Address> getAddressByAddressId(@PathVariable long addressId) {
//        try {
//            Address a = addressRepository.findById(addressId);
//            if(a!=null)
//                return new ResponseEntity<>(a, HttpStatus.OK);
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        catch (Exception e){
//            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    @GetMapping("/getAddressesByCity/{city}")
//    public ResponseEntity<List<Address>> getVaccinationsByCity(@PathVariable String city) {
//        try {
//            List<Address> a = addressRepository.findAllByCity(city);
//            if(a.size()>0)
//                return new ResponseEntity<>(a, HttpStatus.OK);
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        catch (Exception e){
//            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    @GetMapping("/getAddressesByStreet/{street}")
//    public ResponseEntity<List<Address>> getVaccinationsByStreet(@PathVariable String street) {
//        try {
//            List<Address> a = addressRepository.findAllByStreet(street);
//            if(a.size()>0)
//                return new ResponseEntity<>(a, HttpStatus.OK);
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        catch (Exception e){
//
//            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//
//    @GetMapping("/getAddressByMemberId/{memberId}")
//    public ResponseEntity<Address> getAddressByMemberId(@PathVariable long memberId) {
//        try {
//            Address a = addressRepository.findByMemberId(memberId);
//            if(a!=null)
//                return new ResponseEntity<>(a, HttpStatus.OK);
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        catch (Exception e){
//
//            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

//    @PostMapping("/createNewAddress")
//    public ResponseEntity createNewAddress(@RequestBody Address a){
//        Address a1=addressRepository.findByMemberId(a.getMember().getId());
//        if(a1==null){
//            try{
//                Address newAddress=addressRepository.save(a);
//                return new ResponseEntity<>(newAddress, HttpStatus.CREATED);
//            }
//            catch (Exception e){
//                System.out.println(e);
//                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//            }
//        }
//        else
//            return new ResponseEntity(null,HttpStatus.CONFLICT);
//    }

}
