package ua.ithillel;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ua.ithillel.lesson17.StreamUtils;
import ua.ithillel.lesson17.User;

import java.util.*;
import java.util.stream.Stream;

/*
 * Переробити третє завдання по домашці java8, щоб кожна реалізація (b-f) була в окремому методі.
 * Реалізувати модульні тести до цих методів за допомогою Junit5. На кожен метод повинно бути два позитивних і один негативний тест.
 * Використовувати анотації BeforeAll, BeforeEach,AfterAll, AfterEach
 * один тест повинен бути параметризованим
 * один тест повинен повторюватись 10 разів
 * Запустити тести в два потоки паралельно
 * Створити репорт після тестування за допомогою maven-surefire-report-plugin
 */

public class StreamTest extends BaseTest {

    private static List<User> userList;
    private static List<User> anotherUserList;
    private static List<User> shortUserList;

    @BeforeAll
    public static void initTests() {
        userList = Arrays.asList(
                new User("John", "Doe01", 11),
                new User("Anna", "Smith", 22),
                new User("John", "Doe03", 13),
                new User("Will", "Doe04", 11),
                new User("John", "Array", 16),
                new User("Kate", "Doe05", 22),
                new User("John", "Doe07", 17),
                new User("Gray", "Doe08", 58),
                new User("John", "Doe09", 19),
                new User("Mary", "Sunny", 22));

        anotherUserList = Arrays.asList(
                new User("Gray", "Doe08", 58),
                new User("John", "Doe01", 11),
                new User("John", "Doe09", 19),
                new User("Anna", "Smith", 22),
                new User("John", "Doe07", 17),
                new User("Kate", "Doe05", 22),
                new User("John", "Doe03", 13),
                new User("Will", "Doe04", 11),
                new User("Mary", "Sunny", 22),
                new User("John", "Array", 16));

        shortUserList = Arrays.asList(
                new User("Mary", "Swan", 34),
                new User("Anna", "Smith", 25),
                new User("Anna", "Doe07", 14),
                new User("Donna", "Angry", 67),
                new User("John", "Doe09", 10),
                new User("Mary", "Sunny", 33));
    }

    @ParameterizedTest(name = "Sort By Age Positive Test with Parameter")
    @DisplayName("Sort By Age Positive Test")
    @MethodSource
    public void testSortByAgePositive(List<User> userList){
        List<User> sortedUserList = Arrays.asList(
                new User("John", "Doe01", 11),
                new User("Will", "Doe04", 11),
                new User("John", "Doe03", 13),
                new User("John", "Array", 16),
                new User("John", "Doe07", 17),
                new User("John", "Doe09", 19),
                new User("Anna", "Smith", 22),
                new User("Kate", "Doe05", 22),
                new User("Mary", "Sunny", 22),
                new User("Gray", "Doe08", 58));

        Assertions.assertEquals(sortedUserList, StreamUtils.sortByName(userList));
    }

     private static Stream<List<User>> testSortByAgePositive() {
        return Stream.of(userList, anotherUserList);
    }

    @RepeatedTest(value = 10, name = "Negative Average Age Verification")
    @DisplayName("Sort By Age Negative Test")
    public void testSortByAgeNegative(){
        Assertions.assertNotEquals(userList, StreamUtils.sortByName(userList));
    }

    @ParameterizedTest(name = "Average Age Positive Test with Parameter")
    @DisplayName("Average Age Positive Test")
    @MethodSource
    public void testAverageAgePositive(List<User> userList, double averageAge){
        Assertions.assertEquals(averageAge, StreamUtils.averageAge(userList));
    }

    static Stream<Arguments> testAverageAgePositive() {
        return Stream.of(
                Arguments.arguments(userList, 21.1),
                Arguments.arguments(shortUserList, 30.5)
                );
    }

    @ParameterizedTest(name = "Average Age Negative Test with Parameter")
    @DisplayName("Average Age Negative Test")
    @MethodSource
    public void testAverageAgeNegative(List<User> userList, double averageAge){
        Assertions.assertNotEquals(averageAge, StreamUtils.averageAge(userList));
    }

    static Stream<Arguments> testAverageAgeNegative() {
        return Stream.of(
                Arguments.arguments(userList, 21),
                Arguments.arguments(shortUserList, 30)
        );
    }

    @ParameterizedTest(name = "Sort By Name And Age Positive Test with Parameter")
    @DisplayName("Sort By Name And Age Positive Test")
    @MethodSource
    public void testSortByNameAndAgePositive(List<User> expectedUserList, List<User> userList){
        Assertions.assertEquals(expectedUserList, StreamUtils.sortByNameAndAge(userList));
    }

    private static Stream<Arguments> testSortByNameAndAgePositive() {

        List<User> sortedUserList = Arrays.asList(
                new User("Anna", "Smith", 22),
                new User("Gray", "Doe08", 58),
                new User("John", "Doe01", 11),
                new User("John", "Doe03", 13),
                new User("John", "Array", 16),
                new User("John", "Doe07", 17),
                new User("John", "Doe09", 19),
                new User("Kate", "Doe05", 22),
                new User("Mary", "Sunny", 22),
                new User("Will", "Doe04", 11)
                );

        List<User> sortedShortUserList = Arrays.asList(
                new User("Anna", "Doe07", 14),
                new User("Anna", "Smith", 25),
                new User("Donna", "Angry", 67),
                new User("John", "Doe09", 10),
                new User("Mary", "Sunny", 33),
                new User("Mary", "Swan", 34)
                );

        return Stream.of(
                Arguments.arguments(sortedUserList, userList),
                Arguments.arguments(sortedShortUserList, shortUserList)
        );
    }

    @ParameterizedTest(name = "Sort By Name And Age Negative Test with Parameter")
    @DisplayName("Sort By Name And Age Negative Test")
    @MethodSource
    public void testSortByNameAndAgeNegative(List<User> expectedUserList, List<User> userList){
        Assertions.assertNotEquals(expectedUserList, StreamUtils.sortByNameAndAge(userList));
    }

    static Stream<Arguments> testSortByNameAndAgeNegative() {
        return Stream.of(
                Arguments.arguments(userList, shortUserList),
                Arguments.arguments(shortUserList, userList)
        );
    }

    @ParameterizedTest(name = "Users With First Letter In Second Names Positive Test with Parameter")
    @DisplayName("Users With First Letter In Second Names Positive Test")
    @MethodSource
    public void testUsersWithFirstLetterInSecondNamesPositive(List<User> expectedUserList, List<User> userList,
                                                              Set<Character> firstLettersForSecondName){
        Assertions.assertEquals(expectedUserList,
                StreamUtils.usersWithFirstLetterInSecondNames(userList, firstLettersForSecondName));
    }

    private static Stream<Arguments> testUsersWithFirstLetterInSecondNamesPositive() {

        Set<Character> firstLettersForSecondName = new HashSet<>();
        firstLettersForSecondName.add('s');
        firstLettersForSecondName.add('a');

        List<User> sortedUserList = Arrays.asList(
                new User("Anna", "Smith", 22),
                new User("John", "Array", 16),
                new User("Mary", "Sunny", 22)
        );

        List<User> sortedShortUserList = Arrays.asList(
                new User("Mary", "Swan", 34),
                new User("Anna", "Smith", 25),
                new User("Donna", "Angry", 67),
                new User("Mary", "Sunny", 33)
        );

        return Stream.of(
                Arguments.arguments(sortedUserList, userList, firstLettersForSecondName),
                Arguments.arguments(sortedShortUserList, shortUserList, firstLettersForSecondName)
        );
    }

    @ParameterizedTest(name = "Users With First Letter In Second Names Negative Test with Parameter")
    @DisplayName("Users With First Letter In Second Names Negative Test")
    @MethodSource
    public void testUsersWithFirstLetterInSecondNamesNegative(List<User> expectedUserList, List<User> userList,
                                                              Set<Character> firstLettersForSecondName){
        Assertions.assertNotEquals(expectedUserList,
                StreamUtils.usersWithFirstLetterInSecondNames(userList, firstLettersForSecondName));
    }

    static Stream<Arguments> testUsersWithFirstLetterInSecondNamesNegative() {
        Set<Character> firstLettersForSecondName = new HashSet<>();
        firstLettersForSecondName.add('s');
        firstLettersForSecondName.add('a');

        return Stream.of(
                Arguments.arguments(userList, shortUserList, firstLettersForSecondName),
                Arguments.arguments(shortUserList, userList, firstLettersForSecondName)
        );
    }

    @ParameterizedTest(name = "Find Adults Positive Test with Parameter")
    @DisplayName("Find Adults Positive Test")
    @MethodSource
    public void testFindAdultsPositive(List<User> expectedUserList, List<User> userList){
        Assertions.assertEquals(expectedUserList, StreamUtils.findAdults(userList));
    }

    private static Stream<Arguments> testFindAdultsPositive() {

        List<User> sortedUserList = Arrays.asList(
                new User("Anna", "Smith", 22),
                new User("Kate", "Doe05", 22),
                new User("Gray", "Doe08", 58),
                new User("John", "Doe09", 19),
                new User("Mary", "Sunny", 22)
        );

        List<User> sortedShortUserList = Arrays.asList(
                new User("Mary", "Swan", 34),
                new User("Anna", "Smith", 25),
                new User("Donna", "Angry", 67),
                new User("Mary", "Sunny", 33)
        );

        return Stream.of(
                Arguments.arguments(sortedUserList, userList),
                Arguments.arguments(sortedShortUserList, shortUserList)
        );
    }

    @ParameterizedTest(name = "Find Adults Negative Test with Parameter")
    @DisplayName("Find Adults Negative Test")
    @MethodSource
    public void testFindAdultsNegative(List<User> expectedUserList, List<User> userList){
        Assertions.assertNotEquals(expectedUserList, StreamUtils.findAdults(userList));
    }

    static Stream<Arguments> testFindAdultsNegative() {
        return Stream.of(
                Arguments.arguments(userList, shortUserList),
                Arguments.arguments(shortUserList, userList)
        );
    }

}
