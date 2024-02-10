rootProject.name = "kredit"

pluginManagement {
	repositories {
		google()
		gradlePluginPortal()
		mavenCentral()
	}
}

include(
	":api",
	":core"
)

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
			version("ktor", "2.3.7")
			
			library("server-cio", "io.ktor", "ktor-server-cio").versionRef("ktor")
			library("server-core", "io.ktor", "ktor-server-core").versionRef("ktor")
			library("server-negotiation", "io.ktor", "ktor-server-content-negotiation").versionRef("ktor")

			bundle("server", listOf(
				"server-cio",
				"server-core",
				"server-negotiation"
			))
			
			library("client-cio", "io.ktor", "ktor-client-cio").versionRef("ktor")
			library("client-core", "io.ktor", "ktor-client-core").versionRef("ktor")
			library("client-auth", "io.ktor", "ktor-client-auth").versionRef("ktor")
			library("client-testing", "io.ktor", "ktor-client-mock").versionRef("ktor")
			library("client-logging", "io.ktor", "ktor-client-logging").versionRef("ktor")
			library("client-encoding", "io.ktor", "ktor-client-encoding").versionRef("ktor")
			library("client-resources", "io.ktor", "ktor-client-resources").versionRef("ktor")
			library("client-negotiation", "io.ktor", "ktor-client-content-negotiation").versionRef("ktor")

			bundle("client", listOf(
				"client-cio",
				"client-core",
				"client-auth",
				"client-logging",
				"client-encoding",
				"client-resources",
				"client-negotiation",
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