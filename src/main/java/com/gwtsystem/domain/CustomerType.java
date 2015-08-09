package com.gwtsystem.domain;

import com.gwtsystem.shared.dto.CustomerTypeDTO;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * Entity for persistence
 * Created by alexanderleonovich on 29.07.15.
 */
@Entity
@Table(name = "T_CUSTOMER_TYPES")
public class CustomerType implements Serializable{

    private static final long serialVersionUID = 5205242678122358606L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "F_CUSTOMER_TYPE_ID")
    private Long customerTypeId;

    @Column(name = "F_CUSTOMER_TYPE_CAPTION")
    private String customerTypeCaption;

    @OneToMany(mappedBy = "customerType", fetch = FetchType.LAZY, targetEntity = Customer.class)
    private Set<Customer> customers;


    public CustomerType(CustomerTypeDTO customerTypeDTO) {
        customerTypeId = customerTypeDTO.getCustomerTypeId();
        customerTypeCaption = customerTypeDTO.getCustomerTypeCaption();
    }

    public CustomerType() {
    }

    public CustomerType(String customerTypeCaption) {
        this.customerTypeCaption = customerTypeCaption;
    }
    public CustomerType(Long customerTypeId, String customerTypeCaption) {
        this.customerTypeId = customerTypeId;
        this.customerTypeCaption = customerTypeCaption;
    }



    public Long getCustomerTypeId() {
        return customerTypeId;
    }

    public void setCustomerTypeId(Long customerTypeId) {
        this.customerTypeId = customerTypeId;
    }

    public String getCustomerTypeCaption() {
        return customerTypeCaption;
    }

    public void setCustomerTypeCaption(String customerTypeCaption) {
        this.customerTypeCaption = customerTypeCaption;
    }

    public Set<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(Set<Customer> customers) {
        this.customers = customers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CustomerType that = (CustomerType) o;

        if (customerTypeId != null ? !customerTypeId.equals(that.customerTypeId) : that.customerTypeId != null)
            return false;
        return !(customerTypeCaption != null ? !customerTypeCaption.equals(that.customerTypeCaption) : that.customerTypeCaption != null);

    }

    @Override
    public int hashCode() {
        int result = customerTypeId != null ? customerTypeId.hashCode() : 0;
        result = 31 * result + (customerTypeCaption != null ? customerTypeCaption.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "CustomerType{" +
                "customerTypeCaption='" + customerTypeCaption + '\'' +
                ", customerTypeId=" + customerTypeId +
                '}';
    }
}
