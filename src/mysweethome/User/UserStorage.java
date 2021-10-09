package mysweethome.User;

import java.util.Scanner;

public class UserStorage {
    private User[] activeUsers = new User[100];
    private int activeUsersCount;
    private User[] inactiveUsers = new User[100];
    private int inactiveUsersCount;

    public boolean containsUserWithPesel(String pesel) {
        for (User activeUser : activeUsers) {
            if (activeUser != null && activeUser.getPesel().equals(pesel)) {
                return true;
            }
        }

        for (User inactiveUser : inactiveUsers) {
            if (inactiveUser != null && inactiveUser.getPesel().equals(pesel)) {
                return true;
            }
        }

        return false;
    }

    public void listActiveUsers() {
        System.out.println("Active users:");
        displayUsers(activeUsers);
    }

    public User selectUser() {
        listActiveUsers();
        System.out.print("Select user: ");
        Scanner sc = new Scanner(System.in);
        int selectedUserIndex = sc.nextInt() - 1;
        if (selectedUserIndex >= 0 && selectedUserIndex < activeUsersCount) {
            return activeUsers[selectedUserIndex];
        }
        return null;
    }

    public void addUser(User toAdd) {
        for (int i = 0; i < inactiveUsers.length; i++) {
            if (inactiveUsers[i] == null) {
                inactiveUsers[i] = toAdd;
                ++inactiveUsersCount;
                break;
            }
        }
    }

    public void activateUser() {
        displayUsers(inactiveUsers);
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter user number to activate: ");
        int userToActivateNumber = sc.nextInt() - 1;
        if (userToActivateNumber < 0 || userToActivateNumber > inactiveUsersCount) {
            System.out.println("Inactive user number...");
            return;
        }
        activeUsers[activeUsersCount++] = inactiveUsers[userToActivateNumber];
        inactiveUsers[userToActivateNumber] = null;
        --inactiveUsersCount;
        ArrayCompactor.compactUserArray(inactiveUsers);
    }

    private void displayUsers(User[] users) {
        for (int i = 0; i < users.length; i++) {
            if (users[i] != null) {
                System.out.println((i + 1) + ": " + users[i].getDisplayInformation());
            }
        }
    }
}
