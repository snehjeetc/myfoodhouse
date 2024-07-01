package com.myfoodhouse.sys.domain.valueobjects;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public final class Money {
    private final BigDecimal amount; 

    public static final Money ZERO = new Money(BigDecimal.ZERO); 

    public Money(BigDecimal amount) { 
        this.amount = amount; 
    }

    public BigDecimal getAmount() {
        return amount;
    }


    public boolean isGreaterThanZero() { 
        return this.amount != null && this.amount.compareTo(BigDecimal.ZERO) > 0; 
    }

    public boolean isGreaterThan(Money money){ 
        return this.amount != null && this.amount.compareTo(money.getAmount()) > 0; 
    }

    public Money addMoney(Money money){ 
        return new Money(setScale(this.amount.add(money.getAmount())));  
    }

    public Money subtractMoney(Money money){ 
        return new Money(setScale(this.amount.subtract(money.getAmount()))); 
    }

    public Money multiply(int num) { 
        return multiply(new BigDecimal(num)); 
    }

    public Money multiply(BigDecimal bd) { 
        return new Money(setScale(this.amount.multiply(bd))); 
        
    }

    private BigDecimal setScale(BigDecimal input){ 
        return input.setScale(2, RoundingMode.HALF_EVEN); 
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount); 
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Money other = (Money) obj;
        if (amount == null) {
            if (other.amount != null)
                return false;
        } else if (!amount.equals(other.amount))
            return false;
        return true;
    }

    
}
