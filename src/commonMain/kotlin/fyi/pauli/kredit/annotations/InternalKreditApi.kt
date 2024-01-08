package fyi.pauli.kredit.annotations

/**
 * @author Paul Kindler
 * @since 08/01/2024
 */
@MustBeDocumented
@Retention(AnnotationRetention.BINARY)
@RequiresOptIn(
	level = RequiresOptIn.Level.ERROR,
	message = "This is an internal API and its use requires care. " +
			"It is subject to change or removal and is not intended for use outside the library." +
			"Make sure you fully read and understand documentation of the declaration that " +
			"is marked as an internal API."
)
public annotation class InternalKreditApi {
}