package jetbrains.buildServer.configs.kotlin.v10.projectFeatures

import jetbrains.buildServer.configs.kotlin.v10.*

/**
 * Cloud Integration project feature
 *
 * **Example.**
 * Disables cloud profiles for the whole project, as well as its subprojects. Subprojects cannot override this
 * ```
 * cloudIntegration {
 *   id = "MyProjectId"
 *   enabled = false
 *   subprojectsEnabled = false
 *   allowOverride = false
 * }
 * ```
 *
 *
 * @see cloudIntegration
 */
open class CloudIntegration : ProjectFeature {
    constructor(init: CloudIntegration.() -> Unit = {}, base: CloudIntegration? = null): super(base = base as ProjectFeature?) {
        type = "CloudIntegration"
        init()
    }

    /**
     * Whether to enable cloud integrations in this project.
     */
    var enabled by booleanParameter()

    /**
     * Whether to enable cloud integrations in subprojects.
     */
    var subprojectsEnabled by booleanParameter("SubprojectsEnabled")

    /**
     * Whether to allow override cloud integrations.
     */
    var allowOverride by booleanParameter("AllowOverride")

}


/**
 * Adds a Cloud Integration project feature
 *
 * **Example.**
 * Disables cloud profiles for the whole project, as well as its subprojects. Subprojects cannot override this
 * ```
 * cloudIntegration {
 *   id = "MyProjectId"
 *   enabled = false
 *   subprojectsEnabled = false
 *   allowOverride = false
 * }
 * ```
 *
 *
 * @see CloudIntegration
 */
fun ProjectFeatures.cloudIntegration(base: CloudIntegration? = null, init: CloudIntegration.() -> Unit = {}) {
    feature(CloudIntegration(init, base))
}
