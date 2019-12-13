package net.likeyun.common.enum_base;

public interface BaseEnum<E extends Enum<?>, T> {
    T getValue();
    String getDesc();

}
