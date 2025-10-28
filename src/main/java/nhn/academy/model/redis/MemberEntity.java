package nhn.academy.model.redis;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import nhn.academy.model.ClassType;

public class MemberEntity {
    private String id;
    private String name;
    private Integer age;
    @JsonSerialize(using = ToStringSerializer.class)
    @JsonProperty("class")
    private ClassType clazz;
    private Role role;
    private String password;


    public MemberEntity(MemberCreateCommand memberCreateCommand) {
        this.id = memberCreateCommand.getId();
        this.name = memberCreateCommand.getName();
        this.age = memberCreateCommand.getAge();
        this.clazz = memberCreateCommand.getClazz();
        this.role = memberCreateCommand.getRole();
        this.password = memberCreateCommand.getPassword();
    }

    public MemberEntity() {
    }



    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public ClassType getClazz() {
        return clazz;
    }

    public Role getRole() {
        return role;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "MemberEntity{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", clazz=" + clazz +
                ", role=" + role +
                ", password='" + password + '\'' +
                '}';
    }
}