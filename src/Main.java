import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

class Person {
    private String surname;
    private String firstName;
    private String middleName;
    private String birthDate;
    private String phoneNumber;
    private char gender;

    public Person(String[] data) {
        if (data.length != 6) {
            throw new IllegalArgumentException("Неверное количество данных");
        }

        this.surname = data[0];
        this.firstName = data[1];
        this.middleName = data[2];
        this.birthDate = data[3];
        this.phoneNumber = data[4];
        this.gender = data[5].charAt(0);

        // Проверка формата данных
        if (!isValidBirthDate(this.birthDate)) {
            throw new IllegalArgumentException("Неверный формат даты рождения");
        }
        if (!isValidPhoneNumber(this.phoneNumber)) {
            throw new IllegalArgumentException("Неверный формат номера телефона");
        }
        if (this.gender != 'f' && this.gender != 'm') {
            throw new IllegalArgumentException("Неверный формат пола");
        }
    }

    private boolean isValidBirthDate(String date) {
        // Проверка формата даты рождения 
        return date.matches("\\d{2}\\.\\d{2}\\.\\d{4}");
    }

    private boolean isValidPhoneNumber(String number) {
        // Проверка формата номера телефона
        return number.matches("\\d+");
    }

    public String getSurname() {
        return surname;
    }

    public String toString() {
        return surname + " " + firstName + " " + middleName + " " + birthDate + " " + phoneNumber + " " + gender;
    }
}

public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Введите данные: (Фамилия Имя Отчество Дата_рождения Номер_телефона Пол)");

            String input = scanner.nextLine();
            String[] data = input.split("\\s+");

            try {
                Person person = new Person(data);
                saveToFile(person);
                System.out.println("Данные успешно сохранены в файл.");
            } catch (IllegalArgumentException e) {
                System.out.println("Ошибка: " + e.getMessage());
            }
        }
    }

    private static void saveToFile(Person person) {
        try (FileWriter writer = new FileWriter(person.getSurname() + ".txt", true)) {
            writer.write(person.toString() + "\n");
        } catch (IOException e) {
            System.err.println("Ошибка при записи в файл: " + e.getMessage());
        }
    }
}
