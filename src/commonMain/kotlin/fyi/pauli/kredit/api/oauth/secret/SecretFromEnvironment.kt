package fyi.pauli.kredit.api.oauth.secret

/**
 * @author Paul Kindler
 * @since 07/01/2024
 */

/**
 * Key used to get the secret from the environment.
 * @see String
 */
public const val SECRET_ENV_KEY: String = "KREDIT_APPLICATION_SECRET"

/**
 * Loaded secret from the environment.
 * @see String
 * @throws NotImplementedError when not implemented on current platforms.
 * @throws NullPointerException when environment variable is not set.
 */
public expect val SECRET_FROM_ENVIRONMENT: String