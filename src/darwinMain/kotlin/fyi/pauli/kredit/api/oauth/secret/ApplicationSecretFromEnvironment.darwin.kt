package fyi.pauli.kredit.api.oauth.secret

/**
 * @author Paul Kindler
 * @since 07/01/2024
 */
actual val SECRET_FROM_ENVIRONMENT: String
	get() = throw NotImplementedError("Darwin environment current not supported.")