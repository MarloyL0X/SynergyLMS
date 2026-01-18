package com.synergy.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class Localization {
    private static boolean isRussian = true;
    private static final Map<String, String> ru = new HashMap<>();
    private static final Map<String, String> en = new HashMap<>();
    private static Consumer<Void> onLanguageChange;

    static {
        // --- English ---
        en.put("app.title", "SYNERGY UNIVERSITY");
        en.put("menu.courses", "Courses");
        en.put("menu.schedule", "Schedule");
        en.put("menu.payments", "Payments");
        en.put("menu.profile", "Profile");
        
        en.put("stats.points", "POINTS");
        en.put("stats.debts", "DEBTS");
        en.put("stats.progress", "PROGRESS");
        en.put("semester", "Semester");
        
        en.put("schedule.title", "My Schedule");
        en.put("schedule.sort", "Sort by: ");
        en.put("schedule.today", "Today");
        en.put("schedule.tomorrow", "Tomorrow");
        en.put("schedule.week", "This Week");
        en.put("schedule.room", "Room");
        
        // Missing keys added
        en.put("schedule.type.lecture", "Lecture");
        en.put("schedule.type.practice", "Practice");
        en.put("schedule.type.exam", "Exam");
        en.put("schedule.type.seminar", "Seminar");
        
        en.put("payments.title", "Financial Overview");
        en.put("payments.paid", "Total Paid");
        en.put("payments.due", "Next Due");
        en.put("payments.outstanding", "Outstanding");
        en.put("payments.history", "Payment History & Upcoming Invoices");
        en.put("payments.make_payment", "Make Payment");
        en.put("payments.pending", "Pending");
        en.put("payments.status_paid", "Paid");
        en.put("payments.overdue", "Overdue");
        en.put("payments.dialog_message", "Payment Gateway Integration Pending.\n(Mock: Payment Processed Successfully!)");
        
        en.put("pay.spring2026", "Spring 2026 Tuition");
        en.put("pay.due_feb20", "Due Feb 20, 2026");
        en.put("pay.fall2025", "Fall 2025 Tuition");
        en.put("pay.paid_sep10", "Paid Sep 10, 2025");
        en.put("pay.books2025", "Fall 2025 Books");
        en.put("pay.paid_sep05", "Paid Sep 05, 2025");
        en.put("pay.spring2025", "Spring 2025 Tuition");
        en.put("pay.paid_feb15", "Paid Feb 15, 2025");
        en.put("pay.winter2024", "Winter 2024 Lab Fee");
        en.put("pay.overdue_jan", "Overdue since Jan 2025");
        
        en.put("table.course", "Course Name");
        en.put("table.instructor", "Instructor");
        en.put("table.credits", "Credits");
        en.put("table.grade", "Grade");
        en.put("table.status", "Status");
        en.put("table.action", "Action");
        en.put("table.view", "View");
        en.put("table.active", "Active");
        en.put("table.pending", "Pending");
        en.put("table.in_progress", "In Progress");
        
        en.put("details.progress", "Progress");
        en.put("details.completed", "Completed");
        en.put("details.topics", "Course Topics & Assignments");
        en.put("details.topic_prefix", "Topic");
        en.put("details.topic_title", "Introduction to concepts");
        en.put("details.close", "Close Details");
        en.put("badge.course", "Course");
        en.put("details.teacher", "Teacher");
        en.put("details.homework", "Homework");

        en.put("docs.title", "Academic Record (Gradebook)");
        en.put("docs.subject", "Subject");
        en.put("docs.sem", "Semester");
        en.put("docs.type", "Type");
        en.put("docs.grade", "Grade");
        en.put("docs.date", "Date");
        en.put("docs.student", "STUDENT");
        en.put("docs.group", "GROUP");
        en.put("docs.faculty", "FACULTY");
        en.put("docs.status", "STATUS");
        en.put("docs.status_val", "Active Student");
        en.put("docs.faculty_val", "Information Systems & Technology");
        
        en.put("login.title", "Login");
        en.put("login.username", "Username");
        en.put("login.password", "Password");
        en.put("login.btn", "Sign In");
        en.put("login.no_account", "No account? Get access code");

        // --- Russian ---
        ru.put("app.title", "УНИВЕРСИТЕТ СИНЕРГИЯ");
        ru.put("menu.courses", "Курсы");
        ru.put("menu.schedule", "Расписание");
        ru.put("menu.payments", "Оплата");
        ru.put("menu.profile", "Зачетка");
        
        ru.put("stats.points", "БАЛЛЫ");
        ru.put("stats.debts", "ДОЛГИ");
        ru.put("stats.progress", "ПРОГРЕСС");
        ru.put("semester", "Семестр");
        
        ru.put("schedule.title", "Мое расписание");
        ru.put("schedule.sort", "Сортировать: ");
        ru.put("schedule.today", "Сегодня");
        ru.put("schedule.tomorrow", "Завтра");
        ru.put("schedule.week", "Эта неделя");
        ru.put("schedule.room", "Ауд.");
        ru.put("schedule.type.lecture", "Лекция");
        ru.put("schedule.type.practice", "Практика");
        ru.put("schedule.type.exam", "Экзамен");
        ru.put("schedule.type.seminar", "Семинар");
        
        ru.put("payments.title", "Финансовый обзор");
        ru.put("payments.paid", "Всего оплачено");
        ru.put("payments.due", "Следующий платеж");
        ru.put("payments.outstanding", "Задолженность");
        ru.put("payments.history", "История платежей и счета");
        ru.put("payments.make_payment", "Оплатить");
        ru.put("payments.pending", "Ожидание");
        ru.put("payments.status_paid", "Оплачено");
        ru.put("payments.overdue", "Просрочено");
        ru.put("payments.dialog_message", "Интеграция платежного шлюза в ожидании.\n(Макет: Платеж прошел успешно!)");
        
        ru.put("pay.spring2026", "Обучение Весна 2026");
        ru.put("pay.due_feb20", "Срок до 20 Фев 2026");
        ru.put("pay.fall2025", "Обучение Осень 2025");
        ru.put("pay.paid_sep10", "Оплачено 10 Сен 2025");
        ru.put("pay.books2025", "Учебники Осень 2025");
        ru.put("pay.paid_sep05", "Оплачено 05 Сен 2025");
        ru.put("pay.spring2025", "Обучение Весна 2025");
        ru.put("pay.paid_feb15", "Оплачено 15 Фев 2025");
        ru.put("pay.winter2024", "Лаб. сбор Зима 2024");
        ru.put("pay.overdue_jan", "Просрочено с Янв 2025");
        
        ru.put("table.course", "Название курса");
        ru.put("table.instructor", "Преподаватель");
        ru.put("table.credits", "Кредиты");
        ru.put("table.grade", "Оценка");
        ru.put("table.status", "Статус");
        ru.put("table.action", "Действие");
        ru.put("table.view", "Просмотр");
        ru.put("table.active", "Активен");
        ru.put("table.pending", "Ожидание");
        ru.put("table.in_progress", "В процессе");
        
        ru.put("details.progress", "Прогресс");
        ru.put("details.completed", "Завершено");
        ru.put("details.topics", "Темы курса и задания");
        ru.put("details.topic_prefix", "Тема");
        ru.put("details.topic_title", "Введение в концепции");
        ru.put("details.close", "Закрыть");
        ru.put("badge.course", "Курс");
        ru.put("details.teacher", "Преподаватель");
        ru.put("details.homework", "Домашнее задание");
        
        ru.put("docs.title", "Академическая успеваемость (Зачетная книжка)");
        ru.put("docs.subject", "Предмет");
        ru.put("docs.sem", "Семестр");
        ru.put("docs.type", "Тип");
        ru.put("docs.grade", "Оценка");
        ru.put("docs.date", "Дата");
        ru.put("docs.student", "СТУДЕНТ");
        ru.put("docs.group", "ГРУППА");
        ru.put("docs.faculty", "ФАКУЛЬТЕТ");
        ru.put("docs.status", "СТАТУС");
        ru.put("docs.status_val", "Активный студент");
        ru.put("docs.faculty_val", "Информационные Системы и Технологии");
        
        ru.put("login.title", "Вход");
        ru.put("login.username", "Имя пользователя");
        ru.put("login.password", "Пароль");
        ru.put("login.btn", "Войти");
        ru.put("login.no_account", "Нет аккаунта? Получить код доступа");
    }

    public static String get(String key) {
        Map<String, String> map = isRussian ? ru : en;
        return map.getOrDefault(key, key);
    }
    
    public static void toggleLanguage() {
        isRussian = !isRussian;
        if (onLanguageChange != null) {
            onLanguageChange.accept(null);
        }
    }
    
    public static boolean isRussian() {
        return isRussian;
    }
    
    public static void setLanguageListener(Consumer<Void> listener) {
        onLanguageChange = listener;
    }
}
