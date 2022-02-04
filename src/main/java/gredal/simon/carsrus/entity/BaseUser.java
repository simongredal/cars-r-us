package gredal.simon.carsrus.entity;

import gredal.simon.carsrus.security.UserWithPassword;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
@DiscriminatorValue("USER")
@Table(name = "user")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class BaseUser implements UserWithPassword {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Email
   @Column(nullable = false, unique = true, length = EMAIL_MAX_SIZE)
   private String email;

   @Column(nullable = false)
   private String password;

   boolean enabled;

   @Enumerated(EnumType.STRING)
   @Column(columnDefinition = "ENUM('USER','ADMIN')")
   @ElementCollection(fetch = FetchType.EAGER)
   @CollectionTable(name="user_role")
   List<Role> roles = new ArrayList<>();

   public BaseUser(String email, String password) {
      this.email = email;
      this.password = passwordEncoder.encode(password);
      this.enabled = true;
   }

   @Override
   public void setPassword(String password) {
      this.password = passwordEncoder.encode(password);
   }

   @Override
   public List<Role> getRoles() {
      return roles;
   }
   @Override
   public void addRole(Role role){
      roles.add(role);
   }

}
