package com.amazonaws.mobilehelper.auth.signin;

import com.amazonaws.mobilehelper.auth.IdentityProvider;

/**
 * Base class for exceptions that occur during auth
 */
public class AuthException extends Exception {
    protected final IdentityProvider provider;

    /**
     * Constructor.
     * @param provider the auth provider that was being used.
     * @param ex the exception that occurred.
     */
    public AuthException(final IdentityProvider provider, final Exception ex) {
        super(ex);
        this.provider = provider;
    }

    /**
     * Constructor.
     * @param ex the exception that occurred.
     */
    public AuthException(final Exception ex) {
        this(null, ex);
    }

    /**
     * @return the provider that was used when the failure occurred, or null if no provider
     *         was being used when the auth exception occurred.
     */
    public IdentityProvider getProvider() {
        return provider;
    }
}
