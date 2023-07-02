package ua.ithillel;

import org.junit.jupiter.api.*;

public class BaseTest {

    @BeforeEach
    public void testStartInfo(TestInfo testInfo) {
        System.out.println("Test Start: " + testInfo.getDisplayName());
    }

    @AfterEach
    public void testEndInfo(TestInfo testInfo) {
        System.out.println("Test End: " + testInfo.getDisplayName());
    }

    @AfterAll
    public static void cleanUp(){
        System.out.println("\nAfter All cleanUp() method called");
    }
}
