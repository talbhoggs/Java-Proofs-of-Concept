package com.daniel;

import java.lang.annotation.*;
import java.lang.annotation.ElementType.*;
import java.lang.annotation.RetentionPolicy.*;

import javax.inject.*;

@Target({ElementType.TYPE, ElementType.METHOD, ElementType.PARAMETER, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Qualifier
public @interface MaxPassengers {

}
