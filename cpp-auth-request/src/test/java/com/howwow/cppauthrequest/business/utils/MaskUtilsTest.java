package com.howwow.cppauthrequest.business.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MaskUtilsTest {
    @Test
    public void testMaskCardNumber_NullInput() {
        String result = MaskUtils.maskCardNumber(null);
        assertEquals("****", result);
    }

    @Test
    public void testMaskCardNumber_ShortLength() {
        String result = MaskUtils.maskCardNumber("1234");
        assertEquals("1234", result);
    }

}