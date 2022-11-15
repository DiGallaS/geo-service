package ru.netology.I18n;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.junit.jupiter.api.Assertions.*;

import ru.netology.entity.Country;
import ru.netology.i18n.LocalizationServiceImpl;

public class LocalizationServiceImplTest {
    LocalizationServiceImpl sut;

    @BeforeEach
    public void init() {
        System.out.println("Test started");
        sut = new LocalizationServiceImpl();
    }

    @BeforeAll
    public static void started() {
        System.out.println("Tests started");
    }

    @AfterEach
    public void finished() {
        System.out.println("Test completed");
        sut = null;
    }


    @AfterAll
    public static void finishedAll() {
        System.out.println("Tests completed");
    }

    @ParameterizedTest
    @EnumSource(Country.class)
    public void locale(Country country) {
        String expected = "Добро пожаловать";

        if (country == Country.valueOf("RUSSIA")) {
            assertEquals(expected, sut.locale(country));
        } else {
            assertNotEquals(expected, sut.locale(country));
        }
    }
}
