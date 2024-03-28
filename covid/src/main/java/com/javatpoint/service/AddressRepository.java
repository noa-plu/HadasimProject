package com.javatpoint.service;

import com.javatpoint.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface AddressRepository extends JpaRepository<Address,Long> {
    Address findById(long id);
    List<Address> findAllByCity(String city);
    List<Address> findAllByStreet(String street);
    Address findByMemberId(long memberId);

}
