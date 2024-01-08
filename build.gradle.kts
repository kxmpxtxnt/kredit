import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.KotlinVersion

plugins {
	alias(misc.plugins.dokka)
	alias(misc.plugins.detekt)
	alias(mobile.plugins.android)
	alias(jetbrains.plugins.multiplatform)
	alias(jetbrains.plugins.serialization)
}

group = "fyi.pauli"
version = "1.0"

val kotlinLanguageVersion = KotlinVersion.KOTLIN_1_9

repositories {
	google()
	mavenCentral()
	gradlePluginPortal()
}

kotlin {
	explicitApi()

	@OptIn(ExperimentalKotlinGradlePluginApi::class)
	compilerOptions {
		apiVersion = kotlinLanguageVersion
		languageVersion = kotlinLanguageVersion

		freeCompilerArgs.add("-Xexpect-actual-classes")
	}

	jvm {
		compilations.all {
			// kotlin compiler compatibility options
			kotlinOptions {
				jvmTarget = "1.8"
			}
		}
	}

	androidTarget {
		publishLibraryVariants("release", "debug")
	}

	val linuxTargets = listOf(
		linuxX64(),
		linuxArm64(),
	)

	val darwinTargets = listOf(
		iosX64(),
		tvosX64(),
		macosX64(),
		iosArm64(),
		tvosArm64(),
		macosArm64(),
		watchosX64(),
		watchosArm64(),
		iosSimulatorArm64(),
		tvosSimulatorArm64(),
		watchosSimulatorArm64(),
	)

	sourceSets {
		val commonMain by getting {
			dependencies {
				implementation(ktorio.bundles.client)
			}
		}
		val commonTest by getting {
			dependencies {
				implementation(ktorio.ktor.testing)
				implementation(kotlin("test-common"))
				implementation(kotlin("test-annotations-common"))
			}
		}

		val javaMain by creating {
			dependsOn(commonMain)
			dependencies {

			}
		}

		val jvmMain by getting {
			dependsOn(javaMain)
			dependencies {

			}
		}

		val jvmTest by getting {
			dependsOn(commonTest)
			dependencies {
				implementation(kotlin("test"))
			}
		}

		val androidMain by getting {
			dependsOn(javaMain)
			dependencies {

			}
		}

		val androidUnitTest by getting {
			dependsOn(commonTest)
			dependencies {
				implementation(kotlin("test"))
			}
		}

		val directMain by creating {
			dependsOn(commonMain)
		}

		val nativeMain by creating {
			dependsOn(directMain)
		}

		val nativeTest by creating {
			dependsOn(commonTest)
			dependencies {
				implementation(kotlin("test"))
			}
		}

		val linuxMain by creating {
			dependsOn(nativeMain)
		}

		val darwinMain by creating {
			dependsOn(commonMain)
		}

		linuxTargets.forEach {
			getByName("${it.targetName}Main") {
				dependsOn(linuxMain)
			}
		}

		darwinTargets.forEach {
			getByName("${it.targetName}Main") {
				dependsOn(darwinMain)
			}
		}
	}
}

tasks {
	register<Jar>("dokkaJar") {
		from(dokkaHtml)
		dependsOn(dokkaHtml)
		archiveClassifier = "javadoc"
	}

	wrapper {
		distributionType = Wrapper.DistributionType.ALL
	}

	withType<Jar> {
		copySpec {
			from("${project.rootDir}/LICENSE")
		}
	}

	withType<Test> {
		useJUnitPlatform()
		testLogging {
			showStandardStreams = true
			showExceptions = true
			exceptionFormat = TestExceptionFormat.FULL
		}
	}

	afterEvaluate {
		check {
		}
	}

}

android {
	compileSdk = 31
	sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
	defaultConfig {
		minSdk = 21
	}
	testOptions {
		unitTests.isReturnDefaultValues = true
	}
	namespace = "fyi.pauli"
}