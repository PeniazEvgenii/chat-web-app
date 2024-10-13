package by.it_academy.jd2.entity;

import java.util.Arrays;
import java.util.Optional;

public enum ERole {
    ADMIN,
    USER;

    public static Optional<ERole> getRoleByName(String roleName) {
       return Arrays.stream(ERole.values())
                .filter(role -> role.name().equalsIgnoreCase(roleName))
               .findFirst();
    }
}
