package com.amazonaws.mobilehelper.auth.signin;

import com.amazonaws.mobilehelper.auth.IdentityProvider;

/**
 * Thrown when there is an error obtaining a Cognito identity using an identity provider's token
 * during the start-up authentication flow or when signing in with a provider.
 */
public class CognitoAuthException extends ProviderAuthException {
    /**
     * Constructor.
     * @param provider the provider that was used while attempting to obtain a Cognito identity.
     * @param ex the exception that occurred while attempting to obtain the Cognito identity.
     */
    public CognitoAuthException(final IdentityProvider provider, final Exception ex) {
        super(provider, ex);
    }
}
