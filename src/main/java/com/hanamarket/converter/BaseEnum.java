package com.hanamarket.converter;

public interface BaseEnum {
    Object getCode();

    static <S extends BaseEnum> S getEnum(Class<S> cls, Object code) {
        BaseEnum[] var2 = (BaseEnum[])cls.getEnumConstants();
        int var3 = var2.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            BaseEnum e = var2[var4];
            if (code.equals(e.getCode())) {
                return (S) e;
            }
        }

        return null;
    }
}
