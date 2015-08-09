package com.gwtsystem.domain;


import com.gwtsystem.shared.dto.CustomerDTO;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
 * Entity for persistence
 * Created by alexanderleonovich on 29.07.15.
 */
@Entity
@Table(name = "T_CUSTOMERS")
public class Customer implements Serializable {

    private static final long serialVersionUID = 8592265135238241564L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "F_CUSTOMER_ID")
    private Long customerId;

    @Pattern(regexp = "^[A-Za-z]{1,3}$", message = "Valid only letters of the English alphabet!")
    @Size(min = 2, max = 3)
    @Column(name = "F_TITLE")
    private String title;

    @Pattern(regexp = "^[A-Za-z]+$", message = "Valid only letters of the English alphabet!")
    @Column(name = "F_FIRST_NAME")
    private String firstName;

    @Column(name = "F_FIRST_NAME_METAPHONE")
    private String firstNameMetaphone;

    @Pattern(regexp = "^[A-Za-z]+$", message = "Valid only letters of the English alphabet!")
    @Column(name = "F_LAST_NAME")
    private String lastName;

    @Column(name = "F_LAST_NAME_METAPHONE")
    private String lastNameMetaphone;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "F_MODIFIED_WHEN")
    private Date modifiedWhen;

    @ManyToOne(targetEntity = CustomerType.class, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "F_TYPE")
    private CustomerType customerType;

    public Customer(CustomerDTO customerDTO) {
        customerId = customerDTO.getCustomerId();
        title = customerDTO.getTitle();
        firstName = customerDTO.getFirstName();
        firstNameMetaphone = customerDTO.getFirstNameMetaphone();
        lastName = customerDTO.getLastName();
        lastNameMetaphone = customerDTO.getLastNameMetaphone();
    }


    public Customer() {
    }

    public Customer(Long customerId, String title, String firstName, String lastName, CustomerType customerType) {
        this.customerId = customerId;
        this.title = title;
        this.firstName = firstName;
        this.lastName = lastName;
        this.customerType = customerType;
    }

    public Customer(Long customerId, String title, String firstName, String firstNameMetaphone,
                    String lastName, String lastNameMetaphone, CustomerType customerType) {
        this.customerId = customerId;
        this.title = title;
        this.firstName = firstName;
        this.firstNameMetaphone = firstNameMetaphone;
        this.lastName = lastName;
        this.lastNameMetaphone = lastNameMetaphone;
        this.customerType = customerType;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstNameMetaphone() {
        return firstNameMetaphone;
    }

    public void setFirstNameMetaphone(String firstNameMetaphone) {
        this.firstNameMetaphone = firstNameMetaphone;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastNameMetaphone() {
        return lastNameMetaphone;
    }

    public void setLastNameMetaphone(String lastNameMetaphone) {
        this.lastNameMetaphone = lastNameMetaphone;
    }

    public Date getModifiedWhen() {
        return modifiedWhen;
    }

    public void setModifiedWhen(Date modifiedWhen) {
        this.modifiedWhen = modifiedWhen;
    }

    public CustomerType getCustomerType() {
        return customerType;
    }

    public void setCustomerType(CustomerType customerType) {
        this.customerType = customerType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Customer customer = (Customer) o;

        if (customerId != null ? !customerId.equals(customer.customerId) : customer.customerId != null) return false;
        if (title != null ? !title.equals(customer.title) : customer.title != null) return false;
        if (firstName != null ? !firstName.equals(customer.firstName) : customer.firstName != null) return false;
        if (firstNameMetaphone != null ? !firstNameMetaphone.equals(customer.firstNameMetaphone) : customer.firstNameMetaphone != null)
            return false;
        if (lastName != null ? !lastName.equals(customer.lastName) : customer.lastName != null) return false;
        if (lastNameMetaphone != null ? !lastNameMetaphone.equals(customer.lastNameMetaphone) : customer.lastNameMetaphone != null)
            return false;
        return !(modifiedWhen != null ? !modifiedWhen.equals(customer.modifiedWhen) : customer.modifiedWhen != null);

    }

    @Override
    public int hashCode() {
        int result = customerId != null ? customerId.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (firstNameMetaphone != null ? firstNameMetaphone.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (lastNameMetaphone != null ? lastNameMetaphone.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", title='" + title + '\'' +
                ", firstName='" + firstName + '\'' +
                ", firstNameMetaphone='" + firstNameMetaphone + '\'' +
                ", lastName='" + lastName + '\'' +
                ", lastNameMetaphone='" + lastNameMetaphone + '\'' +
                ", modifiedWhen=" + modifiedWhen +
                '}';
    }
}
