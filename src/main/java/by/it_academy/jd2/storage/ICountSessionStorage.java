package by.it_academy.jd2.storage;

public interface ICountSessionStorage {
    void addActive();

    void remove();

    Long getCountActiveUsers();
}
