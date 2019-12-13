package net.likeyun.common.enum_base;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum RoleEnum implements BaseEnum<RoleEnum, Integer> {
    ADMIN(1, "admin"),
    WORKER(2, "worker"),
    TEACHING(3, "teaching"),
    TEACHER(4, "teacher"),
    STUDENT(5, "student"),
    PARENT(6, "parent"),
    ;

    @Setter
    Integer value;
    @Setter
    String desc;


    static Map<Integer,RoleEnum> enumMap=new HashMap<>();
    static{
        for(RoleEnum e:RoleEnum.values()){
            enumMap.put(e.getValue(), e);
        }
    }

    @Getter
    static List<RoleEnum> enumList = new ArrayList<>();
    static{
        for(RoleEnum e:RoleEnum.values()){
            enumList.add(e);
        }
    }


    RoleEnum(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }


    @Override
    public Integer getValue() {
        return value;
    }

    @Override
    public String getDesc() {
        return desc;
    }

    public static RoleEnum getEnum(Integer value) {
        return enumMap.get(value);
    }
}
