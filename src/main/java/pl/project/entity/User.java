package pl.project.entity;


import jakarta.persistence.*;

@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private Long id;

    @Column(name="user_name")
    private String name;


    @Column(name = "user_password")
    private String password;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role roleId;


    public User() {
    }

    public User(Long id, String name, String password, Role roleId) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.roleId = roleId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Role getRoleId() {
        return roleId;
    }

    public void setRoleId(Role roleId) {
        this.roleId = roleId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
