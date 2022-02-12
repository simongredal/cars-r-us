package gredal.simon.carsrus.api;

import gredal.simon.carsrus.dto.MemberRequest;
import gredal.simon.carsrus.dto.MemberResponse;
import gredal.simon.carsrus.service.MemberService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/members")
@AllArgsConstructor
public class MemberController {
    private final MemberService memberService;

    // Should be accessible by admin
    @GetMapping
    public List<MemberResponse> getMembers() {
        return memberService.getMembers();
    }

    // Should be accessible by admin and user itself
    @GetMapping("/{id}")
    public MemberResponse getMember(@PathVariable Long id) {
        return memberService.getMember(id, false);
    }

    // Should be accessible by anyone only admin?
    @PostMapping
    public MemberResponse addMember(@RequestBody MemberRequest body) {
        return memberService.addMember(body);
    }

    // Should be accessible by admin and user itself
    @PutMapping("/{id}")
    public MemberResponse editMember(@RequestBody MemberRequest body, @PathVariable Long id) {
        return memberService.editMember(body, id);
    }

    // Should be accessible by admin and user itself
    @DeleteMapping("/{id}")
    public void deleteMember(@PathVariable Long id) {
        memberService.deleteMember(id);
    }
}
