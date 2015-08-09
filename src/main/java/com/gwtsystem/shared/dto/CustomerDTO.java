package com.gwtsystem.shared.dto;


import java.io.Serializable;

/**
 * DTO copy of persistence object Customer
 * Created by alexanderleonovich on 29.07.15.
 */
public class CustomerDTO implements Serializable, Comparable<CustomerDTO>{

    private static final long serialVersionUID = 8599065135238272561L;

    private Long customerId;
    private String title;
    private String firstName;
    private String firstNameMetaphone;
    private String lastName;
    private String lastNameMetaphone;
    private String customerType;


    public CustomerDTO() {
    }

    public CustomerDTO(Long customerId, String title, String firstName, String lastName) {
        this.customerId = customerId;
        this.title = title;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public CustomerDTO(String title, String firstName, String lastName, String customerType) {
        this.title = title;
        this.firstName = firstName;
        this.lastName = lastName;
        this.customerType = customerType;
    }

    public CustomerDTO(String title, String firstName, String lastName) {
        this.title = title;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public CustomerDTO(Long customerId, String title, String firstName, String firstNameMetaphone,
                       String lastName, String lastNameMetaphone) {
        this.customerId = customerId;
        this.title = title;
        this.firstName = firstName;
        this.firstNameMetaphone = firstNameMetaphone;
        this.lastName = lastName;
        this.lastNameMetaphone = lastNameMetaphone;
    }

    public CustomerDTO(Long customerId, String title, String firstName, String firstNameMetaphone,
                       String lastName, String lastNameMetaphone, String customerType) {
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


    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    @Override
    public String toString() {
        return "CustomerDTO{" +
                "customerId=" + customerId +
                ", title='" + title + '\'' +
                ", firstName='" + firstName + '\'' +
                ", firstNameMetaphone='" + firstNameMetaphone + '\'' +
                ", lastName='" + lastName + '\'' +
                ", lastNameMetaphone='" + lastNameMetaphone + '\'' +
                ", customerType='" + customerType + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CustomerDTO that = (CustomerDTO) o;

        if (customerId != null ? !customerId.equals(that.customerId) : that.customerId != null) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
        if (firstNameMetaphone != null ? !firstNameMetaphone.equals(that.firstNameMetaphone) : that.firstNameMetaphone != null)
            return false;
        if (lastName != null ? !lastName.equals(that.lastName) : that.lastName != null) return false;
        if (lastNameMetaphone != null ? !lastNameMetaphone.equals(that.lastNameMetaphone) : that.lastNameMetaphone != null)
            return false;
        return !(customerType != null ? !customerType.equals(that.customerType) : that.customerType != null);

    }

    @Override
    public int hashCode() {
        int result = customerId != null ? customerId.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (firstNameMetaphone != null ? firstNameMetaphone.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (lastNameMetaphone != null ? lastNameMetaphone.hashCode() : 0);
        result = 31 * result + (customerType != null ? customerType.hashCode() : 0);
        return result;
    }

    @Override
    public int compareTo(CustomerDTO o) {
        return (o == null || o.firstName == null) ? -1 : -o.firstName.compareTo(firstName);
    }
}
