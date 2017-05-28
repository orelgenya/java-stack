package com.github.orelgenya.jcip.ch02;

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
public class StatelessFactorizer extends AbstractFactorizer {
    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        BigInteger i = extractFromRequest(req);
        BigInteger[] factors = factor(i);        /** ThreadSafe, but evaluates factors each request without caching. */
        encodeIntoResponse(res, factors);
    }
}
