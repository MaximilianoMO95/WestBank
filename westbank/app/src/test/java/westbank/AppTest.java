package westbank;

import westbank.utils.Validations;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AppTest {
        @Test void validateRun() {
                // Basic
                assertFalse(Validations.validateRun(""));
                assertFalse(Validations.validateRun(" "));
                assertFalse(Validations.validateRun("12345678912"));
                assertFalse(Validations.validateRun("1234567891"));

                // Must pass
                assertTrue(Validations.validateRun("11123432"));
                assertTrue(Validations.validateRun("1123432"));
                assertTrue(Validations.validateRun("1111111"));

                // Will it break?
                assertFalse(Validations.validateRun("111111111111111111111"));
                assertFalse(Validations.validateRun("       "));
                assertFalse(Validations.validateRun("--------"));
                assertFalse(Validations.validateRun("''''''''"));
        }

        @Test void validateDv() {
                // Basic
                assertFalse(Validations.validateDv(""));
                assertFalse(Validations.validateDv(" "));
                assertFalse(Validations.validateDv("0"));
                assertFalse(Validations.validateDv("-"));

                // Must pass
                assertTrue(Validations.validateDv("1"));
                assertTrue(Validations.validateDv("6"));
                assertTrue(Validations.validateDv("K"));

                // Will it break?
                assertFalse(Validations.validateRun("'"));
                assertFalse(Validations.validateRun("1111111111111111"));
                assertFalse(Validations.validateRun("''"));
                assertFalse(Validations.validateRun("k"));
        }

        @Test void validateTel() {
                // Basic
                assertFalse(Validations.validateTel(""));
                assertFalse(Validations.validateTel(" "));
                assertFalse(Validations.validateTel("1234"));
                assertFalse(Validations.validateTel("abc"));

                // Must pass
                assertTrue(Validations.validateTel("12345678"));
                assertTrue(Validations.validateTel("+5290909023"));
                assertTrue(Validations.validateTel("+569 90909023"));

                // Will it break?
                assertFalse(Validations.validateTel("12345678901234567"));
                assertFalse(Validations.validateTel("+1abc123456"));
                assertFalse(Validations.validateTel("+569 12312321321321321"));
        }
}

