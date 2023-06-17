package cn.pqz.emsboot;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.session.SessionRegistry;

@SpringBootTest
class EmsBootApplicationTests {

    @Autowired
    SessionRegistry sessionRegistry;
    @Test
    void test() {
        System.out.println(sessionRegistry.getAllPrincipals());
    }

}
