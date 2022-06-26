package org.carlos.bluetree.authentication.user.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"id","user"})
@Entity
@Table(name = "profiles")
public class Profile {

    @Setter(AccessLevel.PRIVATE)
    @Id
    @SequenceGenerator(
            name = "profile_user_id_sequence",
            sequenceName = "profile_user_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "profile_user_id_sequence"
    )
    private Integer id;

    private String displayName;

    private LocalDateTime birthday;

    @OneToOne(mappedBy = "profile")
    private User user;


}
