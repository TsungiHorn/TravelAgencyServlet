package service;

import com.kolosovskyi.agency.exception.service.CredentialService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EmailCredentialTest {
    private static final String CORRECT_EMAIL_1 = "andreytest@gmail.com";
    private static final String CORRECT_EMAIL_2 = "andrey_test@gmail.com";
    private static final String INCORRECT_EMAIL_1 = "and rey_test@gmail.com";
    private static final String INCORRECT_EMAIL_2 = "andrey_testgmail.com";
    private static final String INCORRECT_EMAIL_3 = "andrey_test@com";
    private static final String INCORRECT_EMAIL_4 = "#andrey_test@gmail.com";

    private static final String CORRECT_PASSWORD_1 = "12345678";
    private static final String CORRECT_PASSWORD_2 = "qwsdf4e*^t";
    private static final String INCORRECT_PASSWORD_1 = "        ";
    private static final String INCORRECT_PASSWORD_2 = "1234567";
    private final CredentialService service = CredentialService.getInstance();
    @Test
    void validateEmailTest(){
        assertTrue(service.validateEmail(CORRECT_EMAIL_1));
        assertTrue(service.validateEmail(CORRECT_EMAIL_2));
        assertFalse(service.validateEmail(INCORRECT_EMAIL_1));
        assertFalse(service.validateEmail(INCORRECT_EMAIL_2));
        assertFalse(service.validateEmail(INCORRECT_EMAIL_3));
        assertFalse(service.validateEmail(INCORRECT_EMAIL_4));
    }
@Test
    void isCorrectPasswordTest(){
        assertTrue(service.isCorrectPassword(CORRECT_PASSWORD_1));
        assertTrue(service.isCorrectPassword(CORRECT_PASSWORD_2));
        assertFalse(service.isCorrectPassword(INCORRECT_PASSWORD_1));
        assertFalse(service.isCorrectPassword(INCORRECT_PASSWORD_2));
    }
}
