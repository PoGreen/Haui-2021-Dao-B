package com.haui.demo.models.bos;

import org.springframework.http.HttpMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Pattern;

public class API {
    private Pattern pathPattern;
    private HttpMethod[] methods;

    private API(Pattern pathPattern, HttpMethod[] methods) {
        this.pathPattern = pathPattern;
        this.methods = methods;
    }

    /**
     * @param pathRegex
     * @param methods
     * @return
     */
    public static API with(String pathRegex, HttpMethod... methods) {
        return new API(Pattern.compile(pathRegex), methods);
    }

    /**
     * @param pathPattern
     * @param methods
     * @return
     */
    public static API with(Pattern pathPattern, HttpMethod... methods) {
        return new API(pathPattern, methods);
    }

    /**
     * @param request
     * @return
     */
    public boolean isSkipRequest(HttpServletRequest request) {
        return isSkipPath(request.getRequestURI()) && isSkipMethod(request.getMethod());
    }

    private boolean isSkipPath(String path) {
        return this.pathPattern.matcher(path).matches();
    }

    private boolean isSkipMethod(String method) {
        if (this.methods == null || this.methods.length == 0) {
            return true;
        }
        for (HttpMethod httpmethod : this.methods) {
            if (httpmethod.matches(method)) {
                return true;
            }
        }
        return false;
    }
}
