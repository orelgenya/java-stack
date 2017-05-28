package com.github.orelgenya.jcip.ch02;

import javax.servlet.*;
import java.math.BigInteger;
import java.util.Random;

/**
 * @author OrelGenya
 */
public abstract class AbstractFactorizer implements Servlet {
    @Override
    public void init(ServletConfig config) throws ServletException {}

    @Override
    public ServletConfig getServletConfig() { return null; }

    /* stub */
    protected BigInteger extractFromRequest(ServletRequest req) { return new BigInteger(5, new Random()); }

    /* stub */
    protected BigInteger[] factor(BigInteger i) {
        return new BigInteger[]{
                new BigInteger(5, new Random()),
                new BigInteger(5, new Random()),
                new BigInteger(5, new Random())
        };
    }

    /* stub */
    protected void encodeIntoResponse(ServletResponse res, BigInteger[] factors) {}

    @Override
    public String getServletInfo() { return null; }

    @Override
    public void destroy() {}
}
