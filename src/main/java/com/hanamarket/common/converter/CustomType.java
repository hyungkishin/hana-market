package com.hanamarket.common.converter;

import org.hibernate.annotations.Parameter;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({FIELD, METHOD})
@Retention(RUNTIME)
public @interface CustomType {
    /**
     * The Hibernate type name.  Usually the fully qualified name of an implementation class for
     * {@link org.hibernate.type.Type}, {@link org.hibernate.usertype.UserType} or
     * {@link org.hibernate.usertype.CompositeUserType}.  May also refer to a type definition by name
     */
    String type();

    /**
     * Any configuration parameters for the named type.
     */
    Parameter[] parameters() default {};
}

