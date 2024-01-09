package fyi.pauli.kredit.api

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
	public var applicationSecret: String

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
	 * Automatically load the [applicationSecret] from the environment.
	 * @see String
	 * @throws NotImplementedError when not implemented on current platforms.
	 * @throws NullPointerException when environment variable is not set.
	 */
	public fun withSecretFromEnvironment()

	/**
	 * Configures [httpClientConfig] used for executing requests.
	 * @see HttpClient
	 */
	public fun withCustomHttpClient(body: HttpClientConfig<CIOEngineConfig>.() -> Unit)

	/**
	 * Add custom configuration to the [httpClientConfig].
	 * @see HttpClient
	 */
	public fun withDefaultHttpClient()

}

public fun kredit(
	body: Kredit.() -> Unit
): Kredit = createKredit(body)