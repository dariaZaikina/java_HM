package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "addressbook")
public class ContactData {
    @Expose
    @Column(name = "firstname")
    private String firstname;

    @Expose
    @Column(name = "lastname")
    private String lastname;

    @Expose
    @Column(name = "mobile")
    @Type(type = "text")
    private String mobile;

    @Expose
    @Column(name = "email")
    @Type(type = "text")
    private String email;

    @Expose
    @Column(name = "address")
    @Type(type = "text")
    private String address;

    @Id
    @Column(name = "id")
    private int id = Integer.MAX_VALUE;

//    @Transient
//    private String group;

    @Column(name = "home")
    @Type(type = "text")
    private String home;

    @Column(name = "work")
    @Type(type = "text")
    private String work;

    @Transient
    private String allPhones;

    @Transient
    private String allEmails;

    @Transient
    private String email2;

    @Transient
    private String email3;

    @Expose
    @Column(name = "photo")
    @Type(type = "text")
    private String photo;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "address_in_groups",
            joinColumns = @JoinColumn(name = "id"), inverseJoinColumns = @JoinColumn(name = "group_id"))
    private Set<GroupData> groups = new HashSet<GroupData>();

    public ContactData withId(int id) {
        this.id = id;
        return this;
    }

    public ContactData withFirstName(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public ContactData withLastName(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public ContactData withMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public ContactData withEmail(String email) {
        this.email = email;
        return this;
    }

    public ContactData withEmail2(String email2) {
        this.email2 = email2;
        return this;
    }

    public ContactData withEmail3(String email3) {
        this.email3 = email3;
        return this;
    }

    public ContactData withAdress(String address) {
        this.address = address;
        return this;
    }

    public ContactData withHomePhone(String home) {
        this.home = home;
        return this;
    }


    public ContactData withWorkPhone(String work) {
        this.work = work;
        return this;
    }


//    public ContactData withGroup(String group) {
//        this.group = group;
//        return this;
//    }

    public ContactData withAllPhones(String allPhones) {
        this.allPhones = allPhones;
        return this;
    }
    public ContactData withAllEmails(String allEmails) {
        this.allEmails = allEmails;
        return this;
    }

    public ContactData withPhoto(File photo) {
        this.photo = photo.getPath();
        return this;
    }


    public int getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getMobile() {
        return mobile;
    }

    public String getHome() {
        return home;
    }

    public String getWork() {
        return work;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public Groups getGroups() {
        return new Groups(groups);
    }

    @Override
    public String toString() {
        return "ContactData{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactData that = (ContactData) o;
        return id == that.id &&
                Objects.equals(firstname, that.firstname) &&
                Objects.equals(lastname, that.lastname) &&
                Objects.equals(mobile, that.mobile) &&
                Objects.equals(email, that.email) &&
                Objects.equals(address, that.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstname, lastname, mobile, email, address, id);
    }

//    public String getGroup() { return group; }

    public String getAllPhones() {
        return allPhones;
    }

    public String getAllEmails() {
        return allEmails;
    }

    public String getEmail2() {
        return email2;
    }

    public String getEmail3() {
        return email3;
    }

    public File getPhoto() {
        if (photo != null) {
            return new File(photo);
        } else {
            return null;
        }
    }
}
