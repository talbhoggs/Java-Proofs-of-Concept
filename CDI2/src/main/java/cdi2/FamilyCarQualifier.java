package cdi2;

import java.lang.annotation.*;
import javax.inject.*;

@Target({ElementType.TYPE, ElementType.METHOD, ElementType.PARAMETER, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Qualifier
public @interface FamilyCarQualifier {

}
