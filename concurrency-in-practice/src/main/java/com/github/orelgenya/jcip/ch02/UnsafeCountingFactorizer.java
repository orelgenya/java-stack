package com.github.orelgenya.jcip.ch02;

import com.github.orelgenya.jcip.annotations.NotThreadSafe;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.math.BigInteger;

/**
 * @author OrelGenya
 */
@NotThreadSafe
public class UnsafeCountingFactorizer extends AbstractCountingFactorizer {
    private long count = 0;

    public long getCount() { return count; }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        BigInteger i = extractFromRequest(req);
        BigInteger[] factors = factor(i);
        ++count;  /** Not atomic read-modify-write operation! */
        encodeIntoResponse(res, factors);
    }
}
