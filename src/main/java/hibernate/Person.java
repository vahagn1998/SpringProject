package hibernate;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "person")
@NamedQuery(name = "findAllWithDetail", query = "select distinct p from Person p " +
        "left join fetch p.contacts c")
public class Person {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String lastName;

    @Version
    @Column(name = "version")
    private int version;

    @Temporal(TemporalType.DATE)
    private Date birthday;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "person")
    private Set<Contact> contacts;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Set<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(Set<Contact> contacts) {
        this.contacts = contacts;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
