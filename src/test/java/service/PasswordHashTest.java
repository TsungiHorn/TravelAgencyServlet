package service;

import com.kolosovskyi.agency.service.PasswordHasher;
import org.junit.jupiter.api.Test;
import java.security.NoSuchAlgorithmException;


import static org.junit.jupiter.api.Assertions.*;

public class PasswordHashTest {
    private static final String password1 = "ee_&fse42";
    private static final String password2 = "234rGFVGJr58r";

 

   @Test
    void hashTest() throws NoSuchAlgorithmException {

        assertNotEquals(password1, PasswordHasher.toHexString(PasswordHasher.getSHA(password1)));
        assertNotEquals(password2, PasswordHasher.toHexString(PasswordHasher.getSHA(password1)));
    }
}
