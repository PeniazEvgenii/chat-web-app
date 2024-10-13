package by.it_academy.jd2.storage;

public class CountSessionStorageFactory {
    private static final ICountSessionStorage countSessionStorage = new CountSessionStorage();

    private CountSessionStorageFactory() {}

    public static ICountSessionStorage getCountSessionStorage() {
        return countSessionStorage;
    }
}
