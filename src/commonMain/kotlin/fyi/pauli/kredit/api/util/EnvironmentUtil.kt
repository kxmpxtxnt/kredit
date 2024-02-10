package fyi.pauli.kredit.api.util

/**
 * @author Paul Kindler
 * @since 11/01/2024
 */
public expect fun getEnvironment(key: String): String?

public fun getEnvironmentOrDefault(key: String, default: String): String = getEnvironment(key) ?: default

public fun getEnvironmentOrThrow(key: String): String = getEnvironment(key) ?: throw IllegalArgumentException("Environment variable $key not found.")