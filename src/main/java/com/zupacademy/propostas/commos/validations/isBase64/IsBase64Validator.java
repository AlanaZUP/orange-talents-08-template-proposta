package com.zupacademy.propostas.commos.validations.isBase64;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class IsBase64Validator implements ConstraintValidator<IsBase64, Object> {
    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        try{
            byte[] valueDecode = Base64.getDecoder().decode(String.valueOf(value).getBytes());
            String valueEncoder = Base64.getEncoder().encodeToString(valueDecode);
            return valueEncoder.compareTo(String.valueOf(value)) == 0;
        }
        catch (Exception e){
            return false;
        }
    }
}
