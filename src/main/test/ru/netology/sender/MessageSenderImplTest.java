package ru.netology.sender;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.i18n.LocalizationService;
import ru.netology.i18n.LocalizationServiceImpl;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class MessageSenderImplTest {
    MessageSenderImpl messageSender;
    Map<String, String> headers = new HashMap<>();

    @BeforeEach
    public void init() {
        System.out.println("Test started");
    }

    @BeforeAll
    public static void started() {
        System.out.println("Tests started");
    }

    @AfterEach
    public void finished() {
        System.out.println("Test completed");
    }


    @AfterAll
    public static void finishedAll() {
        System.out.println("Tests completed");
    }


    @ParameterizedTest
    @ValueSource(strings = {"172.", "96."})
    public void sendTest(String ip) {
        String locations;
        String expected;
        LocalizationService localizationService = Mockito.mock(LocalizationServiceImpl.class);
        GeoService geoService = Mockito.mock(GeoServiceImpl.class);

        switch (ip) {
            case "172.":
                Mockito.when(localizationService.locale(Country.RUSSIA)).thenReturn("Добро пожаловать");
                Mockito.when(geoService.byIp("172.")).thenReturn(new Location("Moscow", Country.RUSSIA, null, 0));
                messageSender = new MessageSenderImpl(geoService, localizationService);
                headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "172.");
                locations = messageSender.send(headers);
                expected = "Добро пожаловать";
                assertEquals(expected, locations);
                break;

            case "96.":
                Mockito.when(localizationService.locale(Country.USA)).thenReturn("Welcome");
                Mockito.when(geoService.byIp("96.")).thenReturn(new Location("New York", Country.USA, null, 0));
                messageSender = new MessageSenderImpl(geoService, localizationService);
                headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "96.");
                locations = messageSender.send(headers);
                expected = "Welcome";
                assertEquals(expected, locations);
                break;
        }
    }
}
