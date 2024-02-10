package fyi.pauli.kredit.api.util

import kotlinx.cinterop.ExperimentalForeignApi
import platform.posix.getenv

/**
 * @author Paul Kindler
 * @since 11/01/2024
 */
@OptIn(ExperimentalForeignApi::class)
public actual fun getEnvironment(key: String): String? = getenv(key)?.toString()