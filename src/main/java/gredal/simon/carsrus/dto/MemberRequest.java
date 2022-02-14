package gredal.simon.carsrus.dto;

import gredal.simon.carsrus.entity.Member;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter @ToString
public class MemberRequest {
    private String email;
    private String password;

    private String firstName;
    private String lastName;

    private String street;
    private String city;
    private String zip;

    public Member toMember() {
        Member member = new Member();
        member.setEmail(email);
        member.setPassword(password);
        member.setFirstName(firstName);
        member.setLastName(lastName);
        member.setStreet(street);
        member.setCity(city);
        member.setZip(zip);
        return member;
    }
}
