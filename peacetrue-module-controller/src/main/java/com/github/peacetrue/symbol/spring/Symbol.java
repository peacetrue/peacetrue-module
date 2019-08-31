package com.github.peacetrue.symbol.spring;

import org.springframework.web.bind.annotation.ModelAttribute;

import java.lang.annotation.*;

/**
 * supported abstract object as method argument
 *
 * @author xiayx
 * @see ModelAttribute
 * @see SymbolArgumentResolver
 */
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Symbol {

    String value() default "";

}
