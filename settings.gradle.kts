rootProject.name = "kredit"

pluginManagement {
	repositories {
		google()
		gradlePluginPortal()
		mavenCentral()
	}
}

dependencyResolutionManagement {
	versionCatalogs {
		create("libs") {
			library("uuid", "com.benasher44", "uuid").version("0.8.2")
		}

		create("jetbrains") {
			version("kotlin", "1.9.21")

			plugin("multiplatform", "org.jetbrains.kotlin.multiplatform").versionRef("kotlin")
			plugin("serialization", "org.jetbrains.kotlin.plugin.serialization").versionRef("kotlin")

			library("kotlinx.io", "org.jetbrains.kotlinx", "kotlinx-io-core").version("0.3.0")
			library("kotlinx.datetime", "org.jetbrains.kotlinx", "kotlinx-datetime").version("0.5.0")
			library("kotlinx.coroutines", "org.jetbrains.kotlinx", "kotlinx-coroutines-core").version("1.7.3")
			library("kotlinx.immutable", "org.jetbrains.kotlinx", "kotlinx-collections-immutable").version("0.3.6")
			library("kotlinx.serialization", "org.jetbrains.kotlinx", "kotlinx-serialization-json").version("1.6.2")

			bundle("kotlinx", listOf(
				"kotlinx.io",
				"kotlinx.datetime",
				"kotlinx.coroutines",
				"kotlinx.immutable",
				"kotlinx.serialization",
			))
		}

		create("ktorio") {
			library("ktor.cio", "io.ktor", "ktor-client-cio").version("2.3.7")
			library("ktor.core", "io.ktor", "ktor-client-core").version("2.3.7")
			library("ktor.testing", "io.ktor", "ktor-client-mock").version("2.3.7")
			library("ktor.logging", "io.ktor", "ktor-client-logging").version("2.3.7")
			library("ktor.encoding", "io.ktor", "ktor-client-encoding").version("2.3.7")
			library("ktor.resources", "io.ktor", "ktor-client-resources").version("2.3.7")
			library("ktor.negotiation", "io.ktor", "ktor-client-content-negotiation").version("2.3.7")

			bundle("client", listOf(
				"ktor.cio",
				"ktor.core",
				"ktor.logging",
				"ktor.encoding",
				"ktor.resources",
				"ktor.negotiation",
			))
		}

		create("mobile") {
			plugin("android", "com.android.library").version("7.4.2")
		}

		create("misc") {
			plugin("dokka", "org.jetbrains.dokka").version("1.9.10")
			plugin("detekt", "io.gitlab.arturbosch.detekt").version("1.18.0")

			library("logging", "ch.qos.logback", "logback-classic").version("1.4.14")
			library("klogging", "io.github.oshai", "kotlin-logging").version("6.0.1")
		}
	}
}