package com.github.peacetrue.symbol;

import javax.annotation.Nullable;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * @author xiayx
 */
public class CombinedSymbolResolver<T> implements SymbolResolver<T> {

    private List<SymbolResolver<T>> symbolResolvers;


    public CombinedSymbolResolver(SymbolResolver<T>... symbolResolvers) {
        this.symbolResolvers = Arrays.asList(Objects.requireNonNull(symbolResolvers));
    }

    public CombinedSymbolResolver(List<SymbolResolver<T>> symbolResolvers) {
        this.symbolResolvers = new LinkedList<>(Objects.requireNonNull(symbolResolvers));
    }

    @Nullable
    @Override
    public T resolveSymbol(HttpServletRequest request) {
        for (SymbolResolver<T> symbolResolver : symbolResolvers) {
            T symbol = symbolResolver.resolveSymbol(request);
            if (symbol != null) return symbol;
        }
        return null;
    }
}
