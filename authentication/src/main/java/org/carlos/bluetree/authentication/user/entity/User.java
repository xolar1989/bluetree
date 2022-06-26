package org.carlos.bluetree.authentication.user.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.carlos.bluetree.authentication.user.entity.provider.FacebookProvider;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Data
@Builder
@Getter
@Setter
@Entity
@EqualsAndHashCode(exclude = {"id"})
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {

    @Setter(AccessLevel.PRIVATE)
    @Id
    @SequenceGenerator(
            name = "user_id_sequence",
            sequenceName = "user_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_id_sequence"
    )
    private Integer id;

    @Column(nullable = false, unique = true)
    @Email
    private String email;

    @Column(nullable = false, unique = true)
    @Size(max = 25)
    private String username;

    @Column(columnDefinition = "boolean default true")
    private boolean active;

    @JsonIgnore
    private String password;

    @Embedded
    private Audit audit;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "profile_id", referencedColumnName = "id")
    private Profile profile;

    @Enumerated(EnumType.STRING)
    private UserType type;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "facebook_provider_id", referencedColumnName = "id")
    private FacebookProvider facebookProvider;

}
