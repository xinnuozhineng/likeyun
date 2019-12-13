package net.likeyun.common.enum_base;

import org.apache.ibatis.type.MappedTypes;

/**
 * @Description:
 * @Author: lfy
 * @Date: 2019/12/5 14:34
 */
@MappedTypes({RoleEnum.class})
public class RoleEnumTypeHandler<E extends Enum<E> & BaseEnum> extends BaseEnumTypeHandler<E> {
    public RoleEnumTypeHandler(Class<E> type) {
        super(type);
    }
}
