package com.github.peacetrue.symbol;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * getRequiredById symbol from param or path, param first then path
 *
 * @author xiayx
 */
public class ParamSymbolResolver implements SymbolResolver<String> {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private String paramName;

    public ParamSymbolResolver(String paramName) {
        this.paramName = Objects.requireNonNull(paramName);
    }

    public String resolveSymbol(HttpServletRequest request) {
        String symbol = request.getParameter(paramName);
        logger.debug("got symbol '{}' by param '{}'", symbol, paramName);
        return symbol;
    }

}
