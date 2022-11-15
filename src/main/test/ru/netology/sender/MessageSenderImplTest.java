package ru.netology.sender;

import org.junit.jupiter.api.*;
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
    MessageSenderImpl sut;
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


    @Test
    public void sendTest() {

        LocalizationService localizationService = Mockito.mock(LocalizationServiceImpl.class);
        Mockito.when(localizationService.locale(Country.RUSSIA)).thenReturn("Добро пожаловать");

        GeoService geoService = Mockito.mock(GeoServiceImpl.class);
        Mockito.when(geoService.byIp("172.")).thenReturn(new Location("Moscow", Country.RUSSIA, null, 0));

        MessageSenderImpl messageSender = new MessageSenderImpl(geoService, localizationService);

        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "172.");

        String locations = messageSender.send(headers);

        String expected = "Добро пожаловать";
        assertEquals(expected, locations);

    }
}
