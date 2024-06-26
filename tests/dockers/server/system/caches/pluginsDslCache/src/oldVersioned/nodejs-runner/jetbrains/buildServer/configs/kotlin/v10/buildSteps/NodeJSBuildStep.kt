package jetbrains.buildServer.configs.kotlin.v10.buildSteps

import jetbrains.buildServer.configs.kotlin.v10.*

/**
 * A Node.js build step
 *
 * **Example.**
 * Adds a Node.js build step with specified script commands,
 * that will be running in [Node.js LTS Docker container](https://www.jetbrains.com/help/teamcity/?nodejs#Prerequisites).
 * ```
 * nodeJS {
 *     name = "Node.js test"
 *     shellScript = """
 *         npm ci
 *         npm run test
 *     """.trimIndent()
 * }
 * ```
 *
 * **Example.**
 * Adds a Node.js build step with specified script commands,
 * that will be running in specified [Node.js Docker container](https://www.jetbrains.com/help/teamcity/?nodejs#Prerequisites),
 * in custom [working directory](https://www.jetbrains.com/help/teamcity/?Build+Working+Directory).
 * This build step will run even if some previous build steps are failed.
 * ```
 * nodeJS {
 *     name = "Node.js test"
 *     executionMode = BuildStep.ExecutionMode.RUN_ON_FAILURE
 *     workingDir = "frontend"
 *     shellScript = """
 *         npm ci
 *         npm run test
 *     """.trimIndent()
 *     dockerImage = "node:18"
 * }
 * ```
 *
 *
 * @see nodeJS
 */
open class NodeJSBuildStep : BuildStep {
    constructor(init: NodeJSBuildStep.() -> Unit = {}, base: NodeJSBuildStep? = null): super(base = base as BuildStep?) {
        type = "nodejs-runner"
        init()
    }

    /**
     * [Build working directory](https://www.jetbrains.com/help/teamcity/?Build+Working+Directory) for commands run,
     * specify it if it is different from the [checkout directory](https://www.jetbrains.com/help/teamcity/?Build+Checkout+Directory).
     */
    var workingDir by stringParameter("teamcity.build.workingDir")

    /**
     * Shell commands for running Node.js projects
     */
    var shellScript by stringParameter()

    /**
     * Specifies which Docker image to use for running this build step. I.e. the build step will be run inside specified docker image, using 'docker run' wrapper.
     */
    var dockerImage by stringParameter("plugin.docker.imageId")

    /**
     * Specifies which Docker image platform will be used to run this build step.
     */
    var dockerImagePlatform by enumParameter<ImagePlatform>("plugin.docker.imagePlatform", mapping = ImagePlatform.mapping)

    /**
     * If enabled, "pull [image][dockerImage]" command will be run before docker run.
     */
    var dockerPull by booleanParameter("plugin.docker.pull.enabled", trueValue = "true", falseValue = "")

    /**
     * Additional docker run command arguments
     */
    var dockerRunParameters by stringParameter("plugin.docker.run.parameters")

    /**
     * Docker image platforms
     */
    enum class ImagePlatform {
        Any,
        Linux,
        Windows;

        companion object {
            val mapping = mapOf<ImagePlatform, String>(Any to "", Linux to "linux", Windows to "windows")
        }

    }
}


/**
 * Adds a Node.js build step
 *
 * **Example.**
 * Adds a Node.js build step with specified script commands,
 * that will be running in [Node.js LTS Docker container](https://www.jetbrains.com/help/teamcity/?nodejs#Prerequisites).
 * ```
 * nodeJS {
 *     name = "Node.js test"
 *     shellScript = """
 *         npm ci
 *         npm run test
 *     """.trimIndent()
 * }
 * ```
 *
 * **Example.**
 * Adds a Node.js build step with specified script commands,
 * that will be running in specified [Node.js Docker container](https://www.jetbrains.com/help/teamcity/?nodejs#Prerequisites),
 * in custom [working directory](https://www.jetbrains.com/help/teamcity/?Build+Working+Directory).
 * This build step will run even if some previous build steps are failed.
 * ```
 * nodeJS {
 *     name = "Node.js test"
 *     executionMode = BuildStep.ExecutionMode.RUN_ON_FAILURE
 *     workingDir = "frontend"
 *     shellScript = """
 *         npm ci
 *         npm run test
 *     """.trimIndent()
 *     dockerImage = "node:18"
 * }
 * ```
 *
 *
 * @see NodeJSBuildStep
 */
fun BuildSteps.nodeJS(base: NodeJSBuildStep? = null, init: NodeJSBuildStep.() -> Unit = {}) {
    step(NodeJSBuildStep(init, base))
}
