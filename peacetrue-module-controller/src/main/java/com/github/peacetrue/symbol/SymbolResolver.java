package com.github.peacetrue.symbol;

import org.springframework.web.context.request.NativeWebRequest;

import javax.annotation.Nullable;
import javax.servlet.http.HttpServletRequest;

/**
 * symbol can be anything that you want got from request
 */
public interface SymbolResolver<T> {

    /**
     * resolve a symbol from {@link NativeWebRequest}
     *
     * @param request must not be null
     * @return maybe null
     */
    @Nullable
    T resolveSymbol(HttpServletRequest request);


}
