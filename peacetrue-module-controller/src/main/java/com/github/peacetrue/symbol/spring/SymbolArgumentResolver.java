package com.github.peacetrue.symbol.spring;

import com.github.peacetrue.symbol.SymbolResolver;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.annotation.ModelAttributeMethodProcessor;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * the bean type is determined by request and is subclass of the special type,
 * similar to {@link ModelAttributeMethodProcessor}
 *
 * @author xiayx
 * @see Symbol
 * @see ModelAttributeMethodProcessor
 */
public class SymbolArgumentResolver extends ModelAttributeMethodProcessor {

    private String symbolValue;
    private SymbolResolver symbolResolver;

    public SymbolArgumentResolver(SymbolResolver symbolResolver) {
        this("", symbolResolver);
    }

    public SymbolArgumentResolver(String symbolValue, SymbolResolver symbolResolver) {
        super(true);
        this.symbolValue = Objects.requireNonNull(symbolValue);
        this.symbolResolver = Objects.requireNonNull(symbolResolver);
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        Symbol symbol = parameter.getParameterAnnotation(Symbol.class);
        return symbol != null && symbol.value().equals(symbolValue);
    }

    protected Object createAttribute(String attributeName, MethodParameter methodParam,
                                     WebDataBinderFactory binderFactory, NativeWebRequest request) {
        return symbolResolver.resolveSymbol(request.getNativeRequest(HttpServletRequest.class));
    }

    protected void bindRequestParameters(WebDataBinder binder, NativeWebRequest request) {
        ServletRequest servletRequest = request.getNativeRequest(ServletRequest.class);
        ServletRequestDataBinder servletBinder = (ServletRequestDataBinder) binder;
        servletBinder.bind(servletRequest);
    }


}
