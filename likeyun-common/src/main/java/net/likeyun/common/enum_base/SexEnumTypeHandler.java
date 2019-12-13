package net.likeyun.common.enum_base;

import org.apache.ibatis.type.MappedTypes;

/**
 * @Description:
 * @Author: lfy
 * @Date: 2019/12/6 11:30
 */
@MappedTypes({SexEnum.class})
public class SexEnumTypeHandler<E extends Enum<E> & BaseEnum> extends BaseEnumTypeHandler<E>  {
    public SexEnumTypeHandler(Class<E> type) {
        super(type);
    }
}
