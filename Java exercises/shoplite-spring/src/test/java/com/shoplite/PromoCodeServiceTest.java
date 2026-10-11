package com.shoplite;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Promo codes")
class PromoCodeServiceTest {
    private PromoCodeService service;

    @BeforeEach
    void setUp() {
        service = new PromoCodeService();
    }

    @ParameterizedTest(name = "{1} on {0} MAD → {2} MAD")
    @CsvSource(textBlock = """
            # amount, code, expected
            200, SUMMER10,  180
            50,  SUMMER10,  45
            200, WELCOME50, 150
            50,  WELCOME50, 0
            30,  WELCOME50, 0
            """)
    void validCodes(double amount, String code, double expected) {
        assertThat(service.apply(amount, code)).isCloseTo(expected, within(0.0001));
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", "  "})
    void noCode_priceUnchanged(String code) {
        assertThat(service.apply(200, code)).isCloseTo(200.0, within(0.0001));
    }

    @ParameterizedTest
    @ValueSource(strings = {"summer10", "Summer10", " SUMMER10 "})
    void code_ignoresCaseAndSpaces(String code) {
        assertThat(service.apply(200, code)).isCloseTo(180.0, within(0.0001));
    }

    @Test
    void unknownCode_throws() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> service.apply(200, "BLACKFRIDAY"))
                .withMessage("Unknown promo code: BLACKFRIDAY");
    }
}