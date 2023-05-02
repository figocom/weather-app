package com.figo.weatherapp.type;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.usertype.UserType;

import java.io.Serializable;
import java.sql.*;

public class GenericLongArrayType<T extends Serializable> implements UserType {
    protected static final int[] SQL_TYPES = {Types.ARRAY};
    private Class<T> typeParameterClass;

    @Override
    public int[] sqlTypes() {
        return new int[]{Types.ARRAY};
    }

    @Override
    public Class returnedClass() {
        return typeParameterClass;
    }

    @Override
    public boolean equals(Object o, Object o1) throws HibernateException {
        if (o == null) {
            return o1 == null;
        }
        return o.equals(o1);
    }

    @Override
    public int hashCode(Object o) throws HibernateException {
        return o.hashCode();
    }

    @Override
    public Object nullSafeGet(ResultSet resultSet, String[] strings, SharedSessionContractImplementor sharedSessionContractImplementor, Object o) throws HibernateException, SQLException {
//        if (resultSet.wasNull()) {
//            return null;
//        }
        if (resultSet.getArray(strings[0]) == null) {
            return new Long[0];
        }

        Array array = resultSet.getArray(strings[0]);
        @SuppressWarnings("unchecked")
        T javaArray = (T) array.getArray();
        return javaArray;
    }

    @Override
    public void nullSafeSet(PreparedStatement preparedStatement, Object o, int i, SharedSessionContractImplementor sharedSessionContractImplementor) throws HibernateException, SQLException {
        Connection connection = preparedStatement.getConnection();
        if (o == null) {
            preparedStatement.setNull(i, SQL_TYPES[0]);
        } else {
            @SuppressWarnings("unchecked")
            T castObject = (T) o;
            Array array = connection.createArrayOf("bigint", (Object[]) castObject);
            preparedStatement.setArray(i, array);
        }
    }

    @Override
    public Object deepCopy(Object o) throws HibernateException {
        return o;
    }

    @Override
    public boolean isMutable() {
        return true;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Serializable disassemble(Object o) throws HibernateException {
        return (T) this.deepCopy(o);
    }

    @Override
    public Object assemble(Serializable serializable, Object o) throws HibernateException {
        return o;
    }

    @Override
    public Object replace(Object o, Object o1, Object o2) throws HibernateException {
        return o;
    }
}
