package ru.netology.geo;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import ru.netology.entity.Country;
import ru.netology.entity.Location;

import java.util.ArrayList;


public class GeoServicelmplTest {
    GeoServiceImpl sut;

    @BeforeEach
    public void init() {
        System.out.println("Test started");
        sut = new GeoServiceImpl();
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
    @ValueSource(strings = {"172.", "96.", "172.0.32.11", "96.44.183.149", "127.0.0.1", "0."})
    public void byIpTest(String ip) {

        ArrayList<Location> locations = new ArrayList<>();
        locations.add(new Location("Moscow", Country.RUSSIA, null, 0));
        locations.add(new Location("New York", Country.USA, null, 0));
        locations.add(new Location("Moscow", Country.RUSSIA, "Lenina", 15));
        locations.add(new Location("New York", Country.USA, " 10th Avenue", 32));
        locations.add(new Location(null, null, null, 0));
        locations.add(null);

        switch (ip) {
            case "172.":
                assertAll("",
                        () -> assertEquals(locations.get(0).getCity(), sut.byIp(ip).getCity()),
                        () -> assertEquals(locations.get(0).getCountry(), sut.byIp(ip).getCountry()),
                        () -> assertEquals(locations.get(0).getStreet(), sut.byIp(ip).getStreet()),
                        () -> assertEquals(locations.get(0).getBuiling(), sut.byIp(ip).getBuiling())
                );
                break;
            case "96.":
                assertAll("",
                        () -> assertEquals(locations.get(1).getCity(), sut.byIp(ip).getCity()),
                        () -> assertEquals(locations.get(1).getCountry(), sut.byIp(ip).getCountry()),
                        () -> assertEquals(locations.get(1).getStreet(), sut.byIp(ip).getStreet()),
                        () -> assertEquals(locations.get(1).getBuiling(), sut.byIp(ip).getBuiling())
                );
                break;
            case "172.0.32.11":
                assertAll("",
                        () -> assertEquals(locations.get(2).getCity(), sut.byIp(ip).getCity()),
                        () -> assertEquals(locations.get(2).getCountry(), sut.byIp(ip).getCountry()),
                        () -> assertEquals(locations.get(2).getStreet(), sut.byIp(ip).getStreet()),
                        () -> assertEquals(locations.get(2).getBuiling(), sut.byIp(ip).getBuiling())
                );
                break;
            case "96.44.183.149":
                assertAll("",
                        () -> assertEquals(locations.get(3).getCity(), sut.byIp(ip).getCity()),
                        () -> assertEquals(locations.get(3).getCountry(), sut.byIp(ip).getCountry()),
                        () -> assertEquals(locations.get(3).getStreet(), sut.byIp(ip).getStreet()),
                        () -> assertEquals(locations.get(3).getBuiling(), sut.byIp(ip).getBuiling())
                );
                break;
            case "127.0.0.1":
                assertAll("",
                        () -> assertEquals(locations.get(4).getCity(), sut.byIp(ip).getCity()),
                        () -> assertEquals(locations.get(4).getCountry(), sut.byIp(ip).getCountry()),
                        () -> assertEquals(locations.get(4).getStreet(), sut.byIp(ip).getStreet()),
                        () -> assertEquals(locations.get(4).getBuiling(), sut.byIp(ip).getBuiling())
                );
                break;
            default:
                assertEquals(locations.get(5), sut.byIp(ip));
                break;
        }
    }


    @ParameterizedTest
    @CsvSource({"123.443,154.55", "172.0,32.11"})
    public void byCoordinatesTest(double latitude, double longitude) {
        assertThrows(RuntimeException.class, () -> sut.byCoordinates(latitude, longitude));
    }
}



