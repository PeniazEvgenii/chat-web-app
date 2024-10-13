package by.it_academy.jd2.dto;

public class StatisticDto {
    private final int countActiveUsers;
    private final int countAllUsers;
    private final int countMessages;
    private final String loginActiveUser;

    private StatisticDto(int countActiveUsers, int countAllUsers, int countMessages, String loginActiveUser) {
        this.countActiveUsers = countActiveUsers;
        this.countAllUsers = countAllUsers;
        this.countMessages = countMessages;
        this.loginActiveUser = loginActiveUser;
    }

    public static StatisticDtoBuilder builder() {
        return new StatisticDtoBuilder();
    }

    public int getCountActiveUsers() {
        return countActiveUsers;
    }

    public int getCountAllUsers() {
        return countAllUsers;
    }

    public int getCountMessage() {
        return countMessages;
    }

    public String getLoginActiveUser() {
        return loginActiveUser;
    }

    public static class StatisticDtoBuilder {
        private int countActiveUsers;
        private int countAllUsers;
        private int countMessages;
        private String loginActiveUser;

        private StatisticDtoBuilder() { }

        public StatisticDtoBuilder setCountActiveUsers(int countActiveUsers) {
            this.countActiveUsers = countActiveUsers;
            return this;
        }

        public StatisticDtoBuilder setCountAllUsers(int countAllUsers) {
            this.countAllUsers = countAllUsers;
            return this;
        }

        public StatisticDtoBuilder setCountMessages(int countMessages) {
            this.countMessages = countMessages;
            return this;
        }

        public StatisticDtoBuilder setLoginActiveUser(String loginActiveUser) {
            this.loginActiveUser = loginActiveUser;
            return this;
        }

        public StatisticDto build() {
            return new StatisticDto(countActiveUsers, countAllUsers, countMessages, loginActiveUser);
        }
    }
}
