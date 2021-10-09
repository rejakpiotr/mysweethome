package mysweethome.User;

import java.time.LocalDate;


    public class User {
        private String name;
        private String lastName;
        private String pesel;
        private LocalDate dateOfBirth;
       /* private int rentalHistory; */

        public User(String name, String lastName, String pesel, LocalDate dateOfBirth) {
            this.name = name;
            this.lastName = lastName;
            this.pesel = pesel;
            this.dateOfBirth = dateOfBirth;
        }

        public String getName() {
            return name;
        }

        public String getLastName() {
            return lastName;
        }

        public String getPesel() {
            return pesel;
        }

        public boolean isAgeOver(int desiredAge) {
            return LocalDate.now().minusYears(desiredAge).isAfter(dateOfBirth);
        }

        public String getDisplayInformation() {
            return String.format("%s %s. Pesel: %s, birth date: %s. Cars rented so far: %d",
                    name, lastName, pesel, dateOfBirth);
}
