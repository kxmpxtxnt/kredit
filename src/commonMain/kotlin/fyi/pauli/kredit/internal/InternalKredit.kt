package fyi.pauli.kredit.internal

import fyi.pauli.kredit.annotations.InternalKreditApi
import fyi.pauli.kredit.api.Kredit
import fyi.pauli.kredit.api.oauth.secret.SECRET_FROM_ENVIRONMENT
import io.ktor.client.*
import io.ktor.client.engine.cio.*

/**
 * @author Paul Kindler
 * @since 08/01/2024
 */
@InternalKreditApi
internal class InternalKredit(
	override var httpClientConfig: HttpClientConfig<CIOEngineConfig> = HttpClientConfig<CIOEngineConfig>(),
	override var applicationSecret: String = "",
) : Kredit {

	override val httpClient: HttpClient
		get() = HttpClient(CIO) { httpClientConfig }

	override fun withSecretFromEnvironment() {
		applicationSecret = SECRET_FROM_ENVIRONMENT
	}

	override fun withCustomHttpClient(body: HttpClientConfig<CIOEngineConfig>.() -> Unit) {
		httpClientConfig.apply(body)
	}

	override fun withDefaultHttpClient() {
		httpClient.config {

		}
	}
}

@OptIn(InternalKreditApi::class)
internal fun createKredit(
	body: Kredit.() -> Unit
): Kredit = InternalKredit().apply(body)