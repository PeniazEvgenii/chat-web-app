package by.it_academy.jd2.service.validation;

public class Error {
    private final String name;
    private final String description;
    public String rusDescription;

    public Error(String name, String description, String rusDescription) {
        this.name = name;
        this.description = description;
        this.rusDescription = rusDescription;
    }

    public Error(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getRusDescription() {
        return rusDescription;
    }
}
