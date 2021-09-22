package com.zupacademy.propostas.commos.validations.isBase64;


import com.zupacademy.propostas.commos.validations.existsId.ExistIdValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {IsBase64Validator.class})
public @interface IsBase64 {
    String message() default "Campo deve ser binário e estar convertido na Base64";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}
