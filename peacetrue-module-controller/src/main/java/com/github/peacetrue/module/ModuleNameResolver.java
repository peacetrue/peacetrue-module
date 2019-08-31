package com.github.peacetrue.module;

import com.github.peacetrue.symbol.SymbolResolver;
import com.google.common.base.CaseFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

import static com.google.common.base.CaseFormat.UPPER_CAMEL;

/**
 * resolver entity name from request
 *
 * @author xiayx
 */
public class ModuleNameResolver implements SymbolResolver<String> {

    private Logger logger = LoggerFactory.getLogger(getClass());
    private SymbolResolver<String> symbolResolver;

    public ModuleNameResolver(SymbolResolver<String> symbolResolver) {
        this.symbolResolver = Objects.requireNonNull(symbolResolver);
    }

    @Override
    public String resolveSymbol(HttpServletRequest request) {
        String symbol = symbolResolver.resolveSymbol(request);
        return symbol == null ? null : this.formatSymbol(symbol);
    }

    protected String formatSymbol(String symbol) {
        //snake to pascal e.g. how_to_ask->HowToAsk
        String moduleName = symbol;
        if (moduleName.endsWith("s")) {
            moduleName = moduleName.substring(0, moduleName.length() - 1);
        }
        if (symbol.contains("-")) {
            moduleName = CaseFormat.LOWER_HYPHEN.to(UPPER_CAMEL, symbol);
        } else if (symbol.contains("_")) {
            moduleName = CaseFormat.LOWER_UNDERSCORE.to(UPPER_CAMEL, symbol);
        }
        moduleName = StringUtils.capitalize(moduleName);
        logger.debug("got module name '{}' from symbol '{}'", moduleName, symbol);
        return moduleName;
    }

}
