package nhn.academy.model;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum ClassType {
    A, B, C;

    @JsonCreator
    public static ClassType fromString(String str){
        for (ClassType value : ClassType.values()) {
            if (value.name().equalsIgnoreCase(str)) {
                return value;
            }
        }
        //default
        return A;
    }

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
