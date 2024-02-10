package fyi.pauli.kredit.internal

import fyi.pauli.kredit.annotations.InternalKreditApi
import fyi.pauli.kredit.api.Kredit
import fyi.pauli.kredit.api.oauth.CodeObtainStrategy
import fyi.pauli.kredit.internal.serialization.serializer
import io.github.oshai.kotlinlogging.KLogger
import io.github.oshai.kotlinlogging.KotlinLogging
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.auth.*
import io.ktor.client.plugins.auth.providers.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.util.*

/**
 * @author Paul Kindler
 * @since 08/01/2024
 */
@InternalKreditApi
internal class InternalKredit(
	override var appId: String,
	override var appVersion: String,
	override var httpClientConfig: HttpClientConfig<CIOEngineConfig> = HttpClientConfig(),
) : Kredit {

	override val httpClient: HttpClient
		get() = HttpClient(CIO) { httpClientConfig }

	override val logger: KLogger
		get() = KotlinLogging.logger("kredit")

	override var codeObtainStrategy: CodeObtainStrategy = CodeObtainStrategy.FromEnvironment

	override val authenticationCode: String by lazy { codeObtainStrategy.obtain() }

	override fun withCustomHttpClient(body: HttpClientConfig<CIOEngineConfig>.() -> Unit) {
		httpClientConfig.apply(body)
	}

	override fun withDefaultHttpClient() {
		val default: HttpClientConfig<CIOEngineConfig>.() -> Unit = {
			install(Logging) {
				logger = object : Logger {
					override fun log(message: String) {
						logger.log(message)
					}
				}
			}

			install(Auth) {
				bearer {
				}
			}

			install(UserAgent) {
				agent = "${PlatformUtils.platform.name}:$appId:$appVersion"
			}

			install(ContentNegotiation) {
				serializer
			}
		}

		httpClientConfig.apply(default)
	}
}

@OptIn(InternalKreditApi::class)
internal fun createKredit(
	appId: String,
	appVersion: String,
	body: Kredit.() -> Unit
): Kredit = InternalKredit(appId, appVersion).apply(body)