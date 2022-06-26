package org.carlos.bluetree.authentication.user.entity.provider;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.carlos.bluetree.authentication.user.entity.User;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

@Builder
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"id","user"})
@NoArgsConstructor
@Entity
public class FacebookProvider{

    @Setter(AccessLevel.PRIVATE)
    @Id
    @SequenceGenerator(
            name = "facebook_user_id_sequence",
            sequenceName = "facebook_user_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "facebook_user_id_sequence"
    )
    private Integer id;

    private String idFacebookUser;

    private String firstName;

    private String lastName;

    @OneToOne(mappedBy = "facebookProvider")
    private User user;

}
