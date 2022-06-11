package enums;

public enum ActionType {
    MENU("menu"),
    ADD("add"),
    SHOW("show"),
    DELETE("delete"),
    EXIT("exit"),
    DEFAULT("default");

    private final String value;

    ActionType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
