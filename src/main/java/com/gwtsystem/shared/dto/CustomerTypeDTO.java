package com.gwtsystem.shared.dto;

import java.io.Serializable;

/**
 * DTO copy of persistence object CustomerType
 * Created by alexanderleonovich on 29.07.15.
 */

public class CustomerTypeDTO implements Serializable{

    private static final long serialVersionUID = 5205842678122354606L;

    private Long customerTypeId;

    private String customerTypeCaption;

    public CustomerTypeDTO() {
    }

    public CustomerTypeDTO(String customerTypeCaption) {


        this.customerTypeCaption = customerTypeCaption;
    }
    public CustomerTypeDTO(Long customerTypeId, String customerTypeCaption) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CustomerTypeDTO that = (CustomerTypeDTO) o;

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
