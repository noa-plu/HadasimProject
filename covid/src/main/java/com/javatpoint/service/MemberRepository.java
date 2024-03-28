package com.javatpoint.service;

import com.javatpoint.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;


@Component
public interface MemberRepository extends JpaRepository<Member,Long>{
    Member findById(long id);
    Member findByAddressId(long addressId);
    List<Member> findAllByDateBirthBefore(LocalDate date);
    List<Member> findAllByDateBirthAfter(LocalDate date);
    List<Member> findAllByDateBirth(LocalDate date);
    Member findByPhone(String phone);
    Member findByMobilePhone(String mobilePhone);
    Member findByTz(String tz);


}
