package com.example.hibernate;

import org.flywaydb.core.Flyway;

public class Application {

    public static void main(String[] args) {
        // создаем или обновляем таблицу базы данных
        Flyway database = Flyway.configure()
                .dataSource("jdbc:mysql://localhost/interview_hibernate_demo", "root", "1234")
                .load();
        database.migrate();

        // запускаем тесты
        new Tests().start();
        System.out.println("---------- Тест завершен ----------");
    }
}
