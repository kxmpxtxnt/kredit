package fyi.pauli.kredit.api.oauth.secret

import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.toKString
import platform.posix.getenv

/**
 * @author Paul Kindler
 * @since 07/01/2024
 */
@OptIn(ExperimentalForeignApi::class)
actual val SECRET_FROM_ENVIRONMENT: String
	get() = getenv(SECRET_ENV_KEY)?.toKString() ?: throw NullPointerException("Secret from $SECRET_ENV_KEY not found.")