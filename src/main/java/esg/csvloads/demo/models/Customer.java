package esg.csvloads.demo.models;

import jakarta.persistence.*;


@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long customerRef;
    public String customerName;
    public String address1;
    public String address2;
    public String town;
    public String county;
    public String country;
    public String postcode;
    public Customer(Long customerRef, String customerName, String address1, String address2, String town, String county, String country, String postcode){
        this.customerRef = customerRef;
        this.customerName = customerName;
        this.address1 = address1;
        this.address2 = address2;

        this.town = town;
        this.county = county;
        this.country = country;
        this.postcode = postcode;
    }
    public Customer(){

    }
    @Override
    public String toString(){
        return "customerRef: " + customerRef +
                " customerName: " + customerName +
                " address2: " + address1 +
                " address2: " + address2 +
                " town: " + town +
                " county: " + county +
                " country: " + country +
                " postcode: " + postcode;
    }

    public Long getCustomerRef() {
        return customerRef;
    }

    public void setCustomerRef(Long customerRef) {
        this.customerRef = customerRef;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }
}
