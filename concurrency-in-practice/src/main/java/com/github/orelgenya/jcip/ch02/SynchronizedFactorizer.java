package com.github.orelgenya.jcip.ch02;

import com.github.orelgenya.jcip.annotations.GuardedBy;
import com.github.orelgenya.jcip.annotations.ThreadSafe;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.math.BigInteger;

/**
 * @author OrelGenya
 */
@ThreadSafe
public class SynchronizedFactorizer extends AbstractFactorizer {
    @GuardedBy("this")
    private BigInteger lastNumber;
    @GuardedBy("this")
    private BigInteger[] lastFactors;

    /** Unacceptably poor io.github.orelgenya.concurrency! */
    @Override
    public synchronized void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        BigInteger i = extractFromRequest(req);
        if (i.equals(lastNumber))
            encodeIntoResponse(res, lastFactors);
        else {
            BigInteger[] factors = factor(i);
            lastNumber = i;
            lastFactors = factors;
            encodeIntoResponse(res, factors);
        }
    }
}
