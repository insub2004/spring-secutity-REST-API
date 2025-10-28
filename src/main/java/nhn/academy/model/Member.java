package nhn.academy.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

public class Member {

    private String name;

    @JsonSerialize(using = ToStringSerializer.class)
    private int age;

    @JsonProperty("class")
    @JsonSerialize(using = ToStringSerializer.class)
    private ClassType clazz;

    public Member(String name, Integer age, ClassType clazz) {
        this.name = name;
        this.age = age;
        this.clazz = clazz;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public ClassType getClazz() {
        return clazz;
    }

}
