import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class UserDataApp {
    public static void main(String[] args) {
        try {
            System.out.println("Введите данные (Фамилия Имя Отчество дата рождения номер телефона пол): ");
            Scanner scanner = new Scanner(System.in);

            String lastName = scanner.nextLine();
            String firstName = scanner.nextLine();
            String middleName = scanner.nextLine();
            String birthDate = scanner.nextLine();
            String phoneNumber = scanner.nextLine();
            String gender = scanner.nextLine();

            try {
                isValidName(firstName);
                isValidName(lastName);
                isValidName(middleName);
                isValidDate(birthDate);
                isValidPhoneNumber(phoneNumber);
                isValidGender(gender);
            } catch (UserDataAppException e) {
                System.out.println(e.getMessage());
            }

            String userData = lastName + " " + firstName + " " + middleName + " " + birthDate + " " + phoneNumber + " " + gender;

            writeUserDataToFile(lastName, userData);
            System.out.println("Данные успешно записаны в файл: " + lastName + ".txt");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Произошла ошибка. Пожалуйста, проверьте введенные данные и попробуйте снова.");
        }
    }

    private static void isValidName(String name) throws InvalidNameException {
        if (!name.matches("^[а-яА-Яa-zA-Z]+$")) {
            throw new InvalidNameException("Неравильное ФИО. Оно должно состоять из букв");
        }
    }

    private static void isValidDate(String date) throws InvalidDateException {
        if (!date.matches("\\d{2}\\.\\d{2}\\.\\d{4}")) {
            throw new InvalidDateException("Неправильная дата. Она должна быть в формате дд.мм.ГГГ");
        }
    }

    private static void isValidPhoneNumber(String phoneNumber) throws InvalidPhoneException {
        if (!phoneNumber.matches("\\d+")) {
            throw new InvalidPhoneException("Неправильный номер. Он должен состоять из цифр");
        }
    }

    private static void isValidGender(String gender) throws InvalidGenderException {
        if (!(gender.equals("Женщина") || gender.equals("Мужчина"))) {
            throw new InvalidGenderException("Неправильный пол");
        }
    }

    private static void writeUserDataToFile(String fileName, String userData) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName + ".txt", true))) {
            writer.write(userData);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class UserDataAppException extends Exception {
    public UserDataAppException(String message) {
        super(message);
    }
}


class InvalidNameException extends UserDataAppException {

    public InvalidNameException(String message) {
        super(message);
    }
}

class InvalidDateException extends UserDataAppException {

    public InvalidDateException(String message) {
        super(message);
    }
}

class InvalidPhoneException extends UserDataAppException {

    public InvalidPhoneException(String message) {
        super(message);
    }
}

class InvalidGenderException extends UserDataAppException {

    public InvalidGenderException(String message) {
        super(message);
    }
}
