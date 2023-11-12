package com.hanamarket.converter;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.usertype.DynamicParameterizedType;
import org.hibernate.usertype.UserType;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Properties;

public class CustomEnumType implements UserType, DynamicParameterizedType {

    // 타입 명
    public static final String NAME = "com.hanamarket.converter.CustomEnumType";

    private Class<? extends BaseEnum> enumClass;
    private String entityName;
    private String propertyName;

    @Override
    public void setParameterValues(Properties parameters) {
        ParameterType params = (ParameterType) parameters.get(PARAMETER_TYPE);

        enumClass = (Class<? extends BaseEnum>) params.getReturnedClass();
        entityName = (String) parameters.get(ENTITY);
        propertyName = (String) parameters.get(PROPERTY);
    }

    @Override
    public void nullSafeSet(PreparedStatement st, Object value, int index, SharedSessionContractImplementor session) throws HibernateException, SQLException {
        // Enum -> DB
        if (value != null) {
            value = ((BaseEnum) value).getCode();
        }

        st.setObject(index, value);
    }

    @Override
    public int getSqlType() {
        return Types.VARCHAR;
    }

    @Override
    public Class returnedClass() {
        return enumClass;
    }

    @Override
    public boolean equals(Object x, Object y) throws HibernateException {
        return x == y;
    }

    @Override
    public int hashCode(Object x) throws HibernateException {
        return x.hashCode();
    }

    @Override
    public Object nullSafeGet(ResultSet rs, int position, SharedSessionContractImplementor session, Object owner) throws SQLException {
        return null;
    }

    @Override
    public Object deepCopy(Object value) throws HibernateException {
        return value;
    }

    @Override
    public boolean isMutable() {
        return false;
    }

    @Override
    public Serializable disassemble(Object value) throws HibernateException {
        return (Serializable) value;
    }

    @Override
    public Object assemble(Serializable cached, Object owner) throws HibernateException {
        return cached;
    }

    @Override
    public Object replace(Object original, Object target, Object owner) throws HibernateException {
        return original;
    }
}
