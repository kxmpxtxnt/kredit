package fyi.pauli.kredit.api.util

/**
 * @author Paul Kindler
 * @since 11/01/2024
 */
public actual fun getEnvironment(key: String): String? = System.getenv()[key]