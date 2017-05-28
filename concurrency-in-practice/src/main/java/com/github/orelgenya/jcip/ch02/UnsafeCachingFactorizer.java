package com.github.orelgenya.jcip.ch02;

import com.github.orelgenya.jcip.annotations.NotThreadSafe;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author OrelGenya
 */
@NotThreadSafe
public class UnsafeCachingFactorizer extends AbstractFactorizer {
    private final AtomicReference<BigInteger> lastNumber = new AtomicReference<>();
    private final AtomicReference<BigInteger[]> lastFactors = new AtomicReference<>();

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        BigInteger i = extractFromRequest(req);
        if (i.equals(lastNumber.get()))
            encodeIntoResponse(res, lastFactors.get());
        else {
            BigInteger[] factors = factor(i);
            lastNumber.set(i);                 /** Invariant can be violated by unlucky timing! */
            lastFactors.set(factors);
            encodeIntoResponse(res, factors);
        }
    }
}
