package javasamplers.apps.dbdemo.model;


import java.time.LocalDateTime;

/**
 * Created by ocean on 12/13/2017.
 */
public class User {
    private final Long id;
    private final String name;
    private final LocalDateTime created;

    public User(Long id, String name, LocalDateTime created) {
        this.id = id;
        this.name = name;
        this.created = created;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", created=" + created +
                '}';
    }
}
