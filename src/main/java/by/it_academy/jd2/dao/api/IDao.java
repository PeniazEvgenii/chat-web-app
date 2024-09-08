package by.it_academy.jd2.dao.api;

import javax.swing.text.html.parser.Entity;
import java.util.List;
import java.util.Optional;

public interface IDao<K,E> {
    E create(E entity);

    boolean delete(K id);

    void update(E entity);  //может СУшность возвращать!

    Optional<Entity> getById(K id);

    List<E> getAll();
}
