package enums;

public enum Message {
    HELLO("Hello! Welcome to the library!\n" +
            "Please enter 'menu' to view all options:"),
    WRONG("Incorrect command.\n"),
    ADD("Book was added to the library.\n"),
    EMPTY("There aren't any book in the library.\n"),
    NOT_ADD("This book already exists in the library.\n"),
    NOT_EXISTS("There isn't any book with this name in the library.\n"),
    NAME("Name of book: "),
    AUTHOR("Author: "),
    DESCRIPTION("Description: "),
    PAGES("Number of pages: "),
    DELETE("Book was deleted from the library.\n"),
    BYE("Thank you. Bye!\n");

    private final String value;

    Message(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
