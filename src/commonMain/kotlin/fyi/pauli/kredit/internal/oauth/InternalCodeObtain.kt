package fyi.pauli.kredit.internal.oauth

import fyi.pauli.kredit.annotations.InternalKreditApi
import fyi.pauli.kredit.api.util.getEnvironmentOrThrow

/**
 * @author Paul Kindler
 * @since 13/01/2024
 */
@InternalKreditApi
internal interface InternalCodeObtain {

	fun obtain(): String

	object FROM_ENVIRONMENT : InternalCodeObtain {
		override fun obtain(): String {
			return getEnvironmentOrThrow("APPLICATION_AUTH_CODE")
		}
	}

}