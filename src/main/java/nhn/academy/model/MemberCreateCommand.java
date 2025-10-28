package nhn.academy.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;


public class MemberCreateCommand {
    private String name;
    private int age;

    @JsonProperty("class")  // @JsonProperty가 있는 필드는 세터 없어도 됨!
    private ClassType clazz;

    @JsonCreator
    public MemberCreateCommand(String name, int age, ClassType clazz) {
        this.name = name;
        this.age = age;
        this.clazz = clazz;
    }

    public MemberCreateCommand() {
        System.out.println("no args constructor");
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(Integer age) {
        this.age = age;
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

    @Override
    public String toString() {
        return "MemberCreateCommand{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", clazz='" + clazz + '\'' +
                '}';
    }
}
