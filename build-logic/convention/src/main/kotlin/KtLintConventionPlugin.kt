import com.jerryokafor.networth.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.attributes.Bundling
import org.gradle.api.tasks.JavaExec
import org.gradle.kotlin.dsl.creating
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getValue
import org.gradle.kotlin.dsl.named
import org.gradle.language.base.plugins.LifecycleBasePlugin

class KtLintConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            val version = libs.findVersion("ktlint").get().toString()
            val ktlint by configurations.creating

            dependencies {
                ktlint("com.pinterest:ktlint:$version") {
                    attributes {
                        attribute(Bundling.BUNDLING_ATTRIBUTE, objects.named(Bundling.EXTERNAL))
                    }
                }
                // ktlintConfig(project(":custom-ktlint-ruleset")) // in case of custom ruleset
            }

            val outputDir = "${project.buildDir}/reports/ktlint/"
            val inputFiles = project.fileTree(mapOf("dir" to "src", "include" to "**/*.kt"))

            tasks.register("ktlintCheck", JavaExec::class.java) {
                inputs.files(inputFiles)
                outputs.dir(outputDir)

                group = LifecycleBasePlugin.VERIFICATION_GROUP
                description = "Check Kotlin code style."
                classpath = ktlint
                mainClass.set("com.pinterest.ktlint.Main")
                args(
                    "**/src/**/*.kt",
                    "**.kts",
                    "!**/build/**",
                )
            }

            tasks.register("ktlintFormat", JavaExec::class.java) {
                inputs.files(inputFiles)
                outputs.dir(outputDir)

                group = LifecycleBasePlugin.VERIFICATION_GROUP
                description = "Fix Kotlin code style deviations."
                classpath = ktlint
                mainClass.set("com.pinterest.ktlint.Main")
                jvmArgs("--add-opens=java.base/java.lang=ALL-UNNAMED")
                args(
                    "-F",
                    "**/src/**/*.kt",
                    "**.kts",
                    "!**/build/**",
                )
            }

            tasks.register("ktlintAndroidStudio", JavaExec::class.java) {
                description = "Setup Android Studio to use the same code style as ktlint."
                classpath = ktlint
                mainClass.set("com.pinterest.ktlint.Main")
                args = listOf("--android", "applyToIDEAProject", "-y")
            }
        }
    }
}
