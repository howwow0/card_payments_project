package com.howwow.cppcarddecryptionstarter.carddecrypt.annotation;

import java.lang.annotation.*;

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DecryptedCardData {
}
