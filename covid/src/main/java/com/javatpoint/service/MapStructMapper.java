package com.javatpoint.service;

import com.javatpoint.dto.MemberDTO;
import com.javatpoint.model.Member;
import org.mapstruct.Mapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;

@Mapper(componentModel = "spring")
public interface MapStructMapper {
    List<MemberDTO> membersToDTO(List<Member> members);

    default MemberDTO memberToDTO(Member m) throws IOException {
        MemberDTO memberDTO= new MemberDTO();
        memberDTO.setId(m.getId());
        memberDTO.setTz(m.getTz());
        memberDTO.setFirstName(m.getFirstName());
        memberDTO.setLastName(m.getLastName());
        memberDTO.setDateBirth(m.getDateBirth());
        memberDTO.setPhone(m.getPhone());
        memberDTO.setMobilePhone(m.getMobilePhone());

        Path fileName= Paths.get(m.getImage());
        byte [] byteImage= Files.readAllBytes(fileName);
        memberDTO.setImage(Base64.getEncoder().encodeToString(byteImage));

        memberDTO.setAddress(m.getAddress());
        memberDTO.setSick(m.getSick());

        return memberDTO;
    }

    default Member dtoToMember(MemberDTO m)throws IOException{
        Member member=new Member();
        member.setId(m.getId());
        member.setTz(m.getTz());
        member.setFirstName(m.getFirstName());
        member.setLastName(m.getLastName());
        member.setDateBirth(m.getDateBirth());
        member.setPhone(m.getPhone());
        member.setMobilePhone(m.getMobilePhone());
        member.setImage(m.getImagePath());
        member.setAddress(m.getAddress());
        member.setSick(m.getSick());

        return  member;
    }
}
