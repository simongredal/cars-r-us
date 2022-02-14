package gredal.simon.carsrus.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import gredal.simon.carsrus.entity.Member;
import gredal.simon.carsrus.entity.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter @Setter @ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MemberResponse {
    private Long id;
    private String email;
    private String password;
    private boolean enabled;
    private List<Role> roles = new ArrayList<>();

    private String firstName;
    private String lastName;

    private String street;
    private String city;
    private String zip;

    private boolean approved;
    private Integer ranking;

    private LocalDateTime created;
    private LocalDateTime lastEdited;

    public MemberResponse(Member member, Boolean includeAll) {
        if (member == null) return;
        this.id = member.getId();
        this.email = member.getEmail();
        this.firstName = member.getFirstName();
        this.lastName = member.getLastName();
        this.street = member.getStreet();
        this.city = member.getCity();
        this.zip = member.getZip();

        if (!includeAll) return;
        this.password = member.getPassword();
        this.enabled = member.isEnabled();
        this.roles = member.getRoles();
        this.approved = member.isApproved();
        this.ranking = member.getRanking();
        this.created = member.getCreated();
        this.lastEdited = member.getLastEdited();
    }

    public static MemberResponse of(Member entity) {
        return new MemberResponse(entity, true);
    }

    public static List<MemberResponse> of(List<Member> entities) {
        return entities.stream().map(MemberResponse::of).toList();
    }
}
