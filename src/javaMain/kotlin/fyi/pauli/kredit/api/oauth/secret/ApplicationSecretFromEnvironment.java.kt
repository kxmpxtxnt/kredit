package fyi.pauli.kredit.api.oauth.secret

/**
 * @author Paul Kindler
 * @since 07/01/2024
 */
public actual val SECRET_FROM_ENVIRONMENT: String
	get() = System.getenv(SECRET_ENV_KEY)