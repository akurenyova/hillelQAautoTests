package ua.ithillel.lesson17;

/*
3. Створити клас User з полями: firstName, secondName, age. Виконати наступні операції:
        a. Створити список з 10 обєктів типу User
        b. відсортувати за віком та записати в нову колекцію
        c. Підрахувати ссередній вік юзерів
        d. Сортувати по декількам властивостям: firstName і age
        e. Перевірити чи є юзери у юких прізвище починаєтся з літери "S' або "А"
        f. Перевірити чи всі юзери старше 18 років
*/

import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class StreamUtils {

    public static List<User> sortByName(List<User> userList) {
        return userList.stream()
                .sorted(Comparator.comparingInt(User::getAge))
                .toList();
    }

    public static double averageAge(List<User> userList) {
        return userList.stream()
                .mapToInt(User::getAge)
                .average()
                .orElse(0);
    }

    public static List<User> sortByNameAndAge(List<User> userList) {
        return userList.stream()
                .sorted(Comparator.comparing(User::getFirstName).thenComparing(User::getAge))
                .toList();
    }

    public static List<User> usersWithFirstLetterInSecondNames(List<User> userList, Set<Character> letters) {
        return userList.stream()
                .filter(e -> e.getSecondName().matches(regexForFirstLetter(letters))) //"(s|S|a|A){1}.+"
                .toList();
    }

    public static List<User> findAdults(List<User> userList) {
        return userList.stream()
                .filter(e -> e.getAge() > 18)
                .toList();
    }

    public static void printList(List<User> userList) {
        userList.forEach(System.out::println);
    }

    private static String regexForFirstLetter(Set<Character> letters) {
        // "(s|S|a|A){1}.+"
        String regexForFirstLetter = "(";
        int counter = 0;
        for (Character letter: letters) {
            regexForFirstLetter += Character.toLowerCase(letter) + "|"
                    + Character.toUpperCase(letter);
            counter++;
            if (counter < letters.size()) {
                regexForFirstLetter += "|";
            }
        }
        return regexForFirstLetter + "){1}.+";
    }
}
