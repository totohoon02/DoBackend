import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class SetMain {
    public static void main(String[] args) {
        User user1 = new User(1L, "Kim");
        User user2 = new User(1L, "Kim");

        Set<User> users = new HashSet<>();

        users.add(user1);
        users.add(user2);

        System.out.println("users = " + users.size()); // override 없으면 2 있으면 1


        String a = "FB";
        String b = "Ea";

        System.out.println(a.equals(b));    // false

        System.out.println(a.hashCode());   // 2236
        System.out.println(b.hashCode());   // 2236
    }

    private static class User {

        private long id;
        private String name;

        public User(long id, String name) {
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            User user = (User) o;
            return Objects.equals(id, user.id) && Objects.equals(name, user.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, name);
        }
    }
}
