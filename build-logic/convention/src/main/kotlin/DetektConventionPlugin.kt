import com.jerryokafor.networth.detekt
import com.jerryokafor.networth.libs
import io.gitlab.arturbosch.detekt.Detekt
import io.gitlab.arturbosch.detekt.DetektCreateBaselineTask
import io.gitlab.arturbosch.detekt.extensions.DetektExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.named
import org.gradle.kotlin.dsl.register

class DetektConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            val detektVersion = libs.findVersion("detekt").get().toString()
            pluginManager.apply("io.gitlab.arturbosch.detekt")

            extensions.getByType<DetektExtension>().apply {
                toolVersion = detektVersion
                source = files("$rootDir/")
                parallel = true
                config = files("$rootDir/config/detekt/detekt-config.yml")
                buildUponDefaultConfig = true
                allRules = false
                baseline = file("$rootDir/config/detekt/detekt-baseline.xml")
                dependencies {
                    detekt("io.gitlab.arturbosch.detekt:detekt-formatting:$detektVersion")
                }
            }

            tasks.named<Detekt>("detekt").configure {
                description = "Runs Detekt on the whole project at once."
                reports {
                    html.required.set(true)
                    html.outputLocation.set(
                        file("$rootDir/build/reports/detekt.html"),
                    )
                }
                parallel = true
                setSource(projectDir)
                include("**/*.kt", "**/*.kts")
                exclude("**/resources/**", "**/build/**")

                // exclude other custom generated files
            }

            tasks.register("detektProjectBaseline", DetektCreateBaselineTask::class) {
                description = "Overrides current baseline."
                buildUponDefaultConfig.set(true)
                ignoreFailures.set(true)
                parallel.set(true)
                setSource(files(rootDir))
                config.setFrom(files("$rootDir/config/detekt/detekt.yml"))
                baseline.set(file("$rootDir/config/detekt/baseline.xml"))
                include("**/*.kt")
                include("**/*.kts")
                exclude("**/resources/**")
                exclude("**/build/**")
            }

            dependencies {
                detekt("io.gitlab.arturbosch.detekt:detekt-cli:$detektVersion")
            }
        }
    }
}
