package gredal.simon.carsrus.service;

import gredal.simon.carsrus.dto.MemberRequest;
import gredal.simon.carsrus.dto.MemberResponse;
import gredal.simon.carsrus.entity.Member;
import gredal.simon.carsrus.entity.Role;
import gredal.simon.carsrus.exception.EmailInvalidException;
import gredal.simon.carsrus.exception.FunctionalityNotImplementedException;
import gredal.simon.carsrus.exception.MemberNotFoundException;
import gredal.simon.carsrus.repository.MemberRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public List<MemberResponse> getMembers() {
        return MemberResponse.of(memberRepository.findAll());
    }

    public MemberResponse getMember(Long id, Boolean includeAll) {
        if (!memberRepository.existsById(id)) throw new MemberNotFoundException("Invalid id");

        return MemberResponse.of(memberRepository.getById(id));
    }

    public MemberResponse addMember(MemberRequest body) {
        if (memberRepository.existsByEmail(body.getEmail() )) throw new EmailInvalidException();

        Member member = body.toMember();
        member.addRole(Role.USER);

        member = memberRepository.save(member);
        return MemberResponse.of(member);
    }

    public MemberResponse editMember(MemberRequest body, Long id) {
        if (!memberRepository.existsById(id)) throw new MemberNotFoundException();

        Member member = memberRepository.getById(id);
        if (body.getFirstName() != null) member.setFirstName(body.getFirstName());
        if (body.getLastName()  != null) member.setLastName(body.getLastName());
        if (body.getStreet()    != null) member.setStreet(body.getStreet());
        if (body.getCity()      != null) member.setCity(body.getCity());
        if (body.getZip()       != null) member.setZip(body.getZip());

        member = memberRepository.save(member);
        return MemberResponse.of(member);
    }

    public void deleteMember(Long id) {
        memberRepository.deleteById(id);
    }
}
