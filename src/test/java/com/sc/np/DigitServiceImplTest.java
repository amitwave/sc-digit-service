package com.sc.np;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DigitServiceImplTest {

    private DigitService service;

    @Before
    public void setUp(){
        service = new DigitServiceImpl();
    }

    @Test
    public void shouldParseFileWithAllZeros(){
        String content = service.getNumbers("allzeros.txt");
        assertEquals("000000000", content);
    }

    @Test
    public void shouldParseFileWithZerosMultipleLinesAndOneInvalidDigit(){
        String content = service.getNumbers("allzeros2lines.txt");
        assertEquals("000000000\n00000000?ILL", content);
    }

    @Test
    public void shouldIgnoreLineInTheFIleWithInappropriateContentength(){
        String content = service.getNumbers("invalidCOntent.txt");
        assertEquals("0?ILL", content);
    }

    @Test
    public void shouldParseFileWithZerosMultipleLinesAndValidAndInvalidCharacters(){
        String content = service.getNumbers("mixed.txt");
        assertEquals("0?ILL234567?ILL\n01234567?ILL", content);
    }
}
