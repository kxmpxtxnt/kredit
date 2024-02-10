package fyi.pauli.kredit.internal.serialization

import kotlinx.serialization.json.Json

/**
 * Serializer used to de-/serialize data from and to the reddit api.
 * @see Json
 * @author Paul Kindler
 * @since 09/01/2024
 */
internal val serializer: Json = Json {
	prettyPrint = false
	encodeDefaults = true
}