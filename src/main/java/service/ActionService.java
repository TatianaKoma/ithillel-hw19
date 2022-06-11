package service;

import enums.ActionType;
import enums.Message;
import model.Book;

import java.sql.Connection;
import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;

import static java.util.Map.entry;

public class ActionService {
    private final Map<ActionType, Runnable> actions = Map.ofEntries(
            entry(ActionType.MENU, getMenuAction()),
            entry(ActionType.ADD, getAddAction()),
            entry(ActionType.SHOW, getShowAction()),
            entry(ActionType.DELETE, getDeleteAction()),
            entry(ActionType.EXIT, getExitAction()),
            entry(ActionType.DEFAULT, getDefaultAction())
    );

    private final Scanner scanner;
    private final BookService bookService;

//    public ActionService(Scanner scanner, String path) {
//        this.bookService = new BookService(path);
//        this.scanner = scanner;
//    }

    public ActionService(Scanner scanner, Connection connection) {
        this.bookService = new BookService(connection);
        this.scanner = scanner;
    }

    public Runnable getAction(String input) {
        ActionType type = Arrays.stream(ActionType.values())
                .filter(com -> com.getValue().equals(input))
                .findAny()
                .orElse(ActionType.DEFAULT);
        return actions.get(type);
    }

    private Runnable getMenuAction() {
        return () -> Arrays.stream(ActionType.values())
                .limit(5)
                .forEach(System.out::println);
    }

    private Runnable getAddAction() {
        return () -> {
            System.out.println(Message.NAME.getValue());
            var name = scanner.nextLine();
            System.out.println(Message.AUTHOR.getValue());
            var author = scanner.nextLine();
            System.out.println(Message.DESCRIPTION.getValue());
            var description = scanner.nextLine();
            System.out.println(Message.PAGES.getValue());
            var pages = Integer.parseInt(scanner.nextLine());
            bookService.save(new Book(name, author, description, pages));
        };
    }

    private Runnable getShowAction() {
        return () -> {
            if (bookService.getAll().isEmpty()) {
                System.out.println(Message.EMPTY.getValue());
            } else {
                bookService.getAll().forEach(System.out::println);
            }
        };
    }

    private Runnable getDeleteAction() {
        return () -> {
            System.out.println(Message.NAME.getValue());
            bookService.delete(scanner.nextLine());
        };
    }

    private Runnable getExitAction() {
        return () -> System.out.println(Message.BYE.getValue());
    }

    private Runnable getDefaultAction() {
        return () -> System.out.println(Message.WRONG.getValue());
    }
}
