package mysweethome.User;

import java.time.LocalDate;
import java.util.Scanner;
import java.util.regex.Pattern;

public class UserCreator {
    private UserStorage userStorage;

    public UserCreator(UserStorage userStorage) {
        this.userStorage = userStorage;
    }

    public void createNewUser() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Add details for new user:");
        System.out.print("Enter name: ");
        String name = sc.nextLine();
        if (name.isEmpty()) {
            System.out.println("Invalid name - exiting...");
            return;
        }

        System.out.print("Enter last name: ");
        String lastName = sc.nextLine();
        if (lastName.isEmpty()) {
            System.out.println("Invalid lastName - exiting...");
            return;
        }

        System.out.print("Enter pesel: ");
        String pesel = sc.nextLine();
        if (!Pattern.compile("\\d{11}").matcher(pesel).matches()) {
            System.out.println("Pesel is incorrect, should contain 11 digits - exiting...");
            return;
        }

        if (userStorage.containsUserWithPesel(pesel)) {
            System.out.printf("User with pesel %s exists - exiting...", pesel);
            return;
        }

        LocalDate birthDate = parsePeselIntoBirthDate(pesel);
        if (LocalDate.now().minusYears(18).isBefore(birthDate)) {
            System.out.println("Only people with age over 18 are allowed - exiting...");
            return;
        }

        User newUser = new User(name, lastName, pesel, birthDate);
        userStorage.addUser(newUser);
    }

    private LocalDate parsePeselIntoBirthDate(String pesel) {
        int yearPart = Integer.parseInt(pesel.substring(0, 2));
        int monthPart = Integer.parseInt(pesel.substring(2, 4));
        int day = Integer.parseInt(pesel.substring(4, 6));

        int year = 1900 + yearPart;
        int month = monthPart;
        if (monthPart > 20) {
            year = 2000 + yearPart;
            month = monthPart - 20;
        }

        return LocalDate.of(year, month, day);
    }
}
