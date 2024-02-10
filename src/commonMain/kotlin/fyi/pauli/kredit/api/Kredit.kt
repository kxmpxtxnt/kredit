package fyi.pauli.kredit.api

import fyi.pauli.kredit.api.oauth.CodeObtainStrategy
import fyi.pauli.kredit.internal.createKredit
import io.github.oshai.kotlinlogging.KLogger
import io.ktor.client.*
import io.ktor.client.engine.cio.*

/**
 * @author Paul Kindler
 * @since 07/01/2024
 */
public interface Kredit {

	/**
	 * Application secret for the reddit client
	 * @see String
	 */
	public val authenticationCode: String

	/**
	 * App id used for reddit user agent.
	 * @see String
	 */
	public var appId: String

	/**
	 * App version used for reddit user agent.
	 * @see String
	 */
	public var appVersion: String

	/**
	 * Application httpClientConfig to create [httpClient].
	 * @see HttpClientConfig
	 */
	public var httpClientConfig: HttpClientConfig<CIOEngineConfig>

	/**
	 * Logger of the application.
	 * @see KLogger
	 */
	public val logger: KLogger

	/**
	 * Application httpClient used to execute requests to the reddit api.
	 * You'll not need to call it because its underlying in the wrapper.
	 * @see HttpClient
	 */
	public val httpClient: HttpClient

	/**
	 * Strategy to obtain authentication code.
	 * @see CodeObtainStrategy
	 */
	public var codeObtainStrategy: CodeObtainStrategy

	/**
	 * Configures [httpClientConfig] used for executing requests.
	 * @see HttpClient
	 */
	public fun withCustomHttpClient(body: HttpClientConfig<CIOEngineConfig>.() -> Unit)

	/**
	 * Add custom configuration to the [httpClientConfig].
	 * Recommended to use because reddit has some rules such as a precise structure of the user agent.
	 * @see HttpClient
	 */
	public fun withDefaultHttpClient()

}

public fun kredit(
	appId: String,
	appVersion: String,
	body: Kredit.() -> Unit
): Kredit = createKredit(appId, appVersion, body)