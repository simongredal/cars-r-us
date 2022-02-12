package gredal.simon.carsrus.service;

import gredal.simon.carsrus.dto.MemberRequest;
import gredal.simon.carsrus.dto.MemberResponse;
import gredal.simon.carsrus.error.MemberNotFoundException;
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
        return MemberResponse.of(memberRepository.save(body.toMember()));
    }

    public MemberResponse editMember(MemberRequest body, Long id) {
        return null;
    }

    public void deleteMember(Long id) {
        memberRepository.deleteById(id);
    }
}
