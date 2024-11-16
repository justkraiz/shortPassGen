package com.planner;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PasswordGenerator {

    // Метод для генерации всех возможных комбинаций
    public static List<String> generatePasswords(String chars, int length) {
        List<String> passwords = new ArrayList<>();
        generate("", chars, length, passwords);
        return passwords;
    }

    // Рекурсивный метод для формирования комбинаций
    private static void generate(String prefix, String chars, int length, List<String> passwords) {
        if (prefix.length() == length) {
            passwords.add(prefix);
            return;
        }
        for (int i = 0; i < chars.length(); i++) {
            generate(prefix + chars.charAt(i), chars, length, passwords);
        }
    }

    // Метод для записи списка паролей в файл
    public static void writePasswordsToFile(List<String> passwords, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (String password : passwords) {
                writer.write(password);
                writer.newLine();
            }
            System.out.println("Пароли успешно записаны в файл: " + fileName);
        } catch (IOException e) {
            System.err.println("Ошибка при записи в файл: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        // Задаем набор символов для генерации паролей
        String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        // Указываем длину пароля
        int passwordLength = 4;  // можно изменить длину пароля
        // Путь к файлу, куда будем записывать пароли
        String fileName = "src/main/resources/passwords.txt";

        // Генерируем все возможные комбинации
        List<String> allPasswords = generatePasswords(chars, passwordLength);

        // Записываем сгенерированные пароли в файл
        writePasswordsToFile(allPasswords, fileName);

        // Выводим количество сгенерированных паролей
        System.out.println("Total passwords generated: " + allPasswords.size());
    }
}
