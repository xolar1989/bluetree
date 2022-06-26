package org.carlos.bluetree.authentication.auth.service;

import org.carlos.bluetree.authentication.auth.dto.CreateLocalUserRequest;
import org.carlos.bluetree.authentication.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;


@DataJpaTest
@ActiveProfiles("integration")
public class AuthServiceIntegrationTest {

    @Autowired
    private TestEntityManager em;

    @Autowired
    private UserRepository userRepository;


    @Test
    void tt(){

        var z = userRepository.findAll();
        CreateLocalUserRequest userRequest = new CreateLocalUserRequest("karol","xolar1989@o2.pl","dsdsdsds");

        var t = 3;


        var k= 11;

        var gf = 45;
//        assertThat(t).isEqualTo(5);
    }

}
