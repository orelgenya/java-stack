package com.github.orelgenya.jcip.ch03;

import com.github.orelgenya.jcip.annotations.ThreadSafe;
import com.github.orelgenya.jcip.ch02.AbstractFactorizer;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.math.BigInteger;

/**
 * @author OrelGenya
 */
@ThreadSafe
public class VolatileCachedFactorizer extends AbstractFactorizer {
    private volatile OneValueCache cache = new OneValueCache(null, null);

    @Override
    public void service(ServletRequest req, ServletResponse resp) {
        BigInteger i = extractFromRequest(req);
        BigInteger[] factors = cache.getFactors(i);
        if (factors == null) {
            factors = factor(i);
            cache = new OneValueCache(i, factors);
        }
        encodeIntoResponse(resp, factors);
    }
}
