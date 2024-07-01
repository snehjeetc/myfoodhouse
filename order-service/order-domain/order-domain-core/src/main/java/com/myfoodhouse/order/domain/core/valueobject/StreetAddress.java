package com.myfoodhouse.order.domain.core.valueobject;

import java.util.Objects;
import java.util.UUID;

public class StreetAddress {
    private final UUID id; 
    private final String street; 
    private final String postalCode; 
    private final String city; 

    public StreetAddress(String city, UUID id, String postalCode, String street) {
        this.city = city;
        this.id = id;
        this.postalCode = postalCode;
        this.street = street;
    }

    public UUID getId() {
        return id;
    }

    public String getStreet() {
        return street;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getCity() {
        return city;
    }

    @Override
    public int hashCode() {
        return Objects.hash(street, city, postalCode);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        StreetAddress other = (StreetAddress) obj;
        if (street == null) {
            if (other.street != null)
                return false;
        } else if (!street.equals(other.street))
            return false;
        if (postalCode == null) {
            if (other.postalCode != null)
                return false;
        } else if (!postalCode.equals(other.postalCode))
            return false;
        if (city == null) {
            if (other.city != null)
                return false;
        } else if (!city.equals(other.city))
            return false;
        return true;
    }

    

    
}
