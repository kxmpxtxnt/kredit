package fyi.pauli.kredit.api.oauth

import fyi.pauli.kredit.annotations.InternalKreditApi
import fyi.pauli.kredit.internal.oauth.InternalCodeObtain

/**
 * @author Paul Kindler
 * @since 13/01/2024
 */
public interface CodeObtainStrategy {

	public fun obtain(): String

	public object FromEnvironment : CodeObtainStrategy {

		@OptIn(InternalKreditApi::class)
		override fun obtain(): String = InternalCodeObtain.FROM_ENVIRONMENT.obtain()

	}
}

