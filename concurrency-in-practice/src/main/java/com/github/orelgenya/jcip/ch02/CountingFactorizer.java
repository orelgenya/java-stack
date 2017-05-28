package com.github.orelgenya.jcip.ch02;

import com.github.orelgenya.jcip.annotations.ThreadSafe;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author OrelGenya
 */
@ThreadSafe
public class CountingFactorizer extends AbstractCountingFactorizer {
    private final AtomicLong count = new AtomicLong(0);

    public long getCount() { return count.get(); }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        BigInteger i = extractFromRequest(req);
        BigInteger[] factors = factor(i);
        count.incrementAndGet();
        encodeIntoResponse(res, factors);
    }
}
