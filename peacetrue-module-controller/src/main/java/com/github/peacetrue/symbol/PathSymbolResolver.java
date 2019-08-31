package com.github.peacetrue.symbol;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * path variable represent a symbol
 *
 * @author xiayx
 */
public class PathSymbolResolver implements SymbolResolver<String> {

    private static final String[] EMPTY = new String[0];

    protected Logger logger = LoggerFactory.getLogger(getClass());
    private Integer pathVarIndex;

    public PathSymbolResolver() {
        this(0);
    }

    public PathSymbolResolver(int pathVarIndex) {
        if (pathVarIndex < 0) throw new IllegalArgumentException("the pathVarIndex must >= 0, but actual " + pathVarIndex);
        this.pathVarIndex = pathVarIndex;
    }

    public String resolveSymbol(HttpServletRequest request) {
        String[] pathVars = getPathVariables(request);
        if (pathVarIndex >= pathVars.length) return null;

        String symbol = StringUtils.trimWhitespace(pathVars[pathVarIndex]);
        logger.debug("got symbol '{}' at index {}", symbol, pathVarIndex);
        return symbol;
    }

    protected String[] getPathVariables(HttpServletRequest request) {
        String path = request.getRequestURI();
        logger.debug("got request uri '{}'", path);

        String[] pathVariables = path == null || path.length() <= 1
                ? EMPTY
                : path.substring(1).split("/");//path start with '/', remove it
        logger.debug("got path variables {}", Arrays.toString(pathVariables));

        return pathVariables;
    }


}
