package by.it_academy.jd2.service.dto;

import java.util.List;

public class StatisticDto {
    private final Long countActiveUsers;
    private final Long countAllUsers;
    private final Long countMessages;
    private final List<String> loginActiveUser;

    private StatisticDto(Long countActiveUsers, Long countAllUsers, Long countMessages, List<String> loginActiveUser) {
        this.countActiveUsers = countActiveUsers;
        this.countAllUsers = countAllUsers;
        this.countMessages = countMessages;
        this.loginActiveUser = loginActiveUser;
    }

    public static StatisticDtoBuilder builder() {
        return new StatisticDtoBuilder();
    }

    public Long getCountActiveUsers() {
        return countActiveUsers;
    }

    public Long getCountAllUsers() {
        return countAllUsers;
    }

    public Long getCountMessages() {
        return countMessages;
    }

    public List<String> getLoginActiveUser() {
        return loginActiveUser;
    }

    public static class StatisticDtoBuilder {
        private Long countActiveUsers;
        private Long countAllUsers;
        private Long countMessages;
        private List<String> loginActiveUser;

        private StatisticDtoBuilder() { }

        public StatisticDtoBuilder setCountActiveUsers(Long countActiveUsers) {
            this.countActiveUsers = countActiveUsers;
            return this;
        }

        public StatisticDtoBuilder setCountAllUsers(Long countAllUsers) {
            this.countAllUsers = countAllUsers;
            return this;
        }

        public StatisticDtoBuilder setCountMessages(Long countMessages) {
            this.countMessages = countMessages;
            return this;
        }

        public StatisticDtoBuilder setLoginActiveUser(List<String> loginActiveUser) {
            this.loginActiveUser = loginActiveUser;
            return this;
        }

        public StatisticDto build() {
            return new StatisticDto(countActiveUsers, countAllUsers, countMessages, loginActiveUser);
        }
    }
}
