package com.myfoodhouse.sys.domain.valueobjects;

import java.util.Objects;

public abstract class BaseId<T> {
    private final T value; 

    protected BaseId(T value) { 
        this.value = value; 
    }

    public T getValue() {
        return value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value); 
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        BaseId<?> other = (BaseId<?>) obj;
        if (value == null) {
            if (other.value != null)
                return false;
        } else if (!value.equals(other.value))
            return false;
        return true;
    }

    
}
