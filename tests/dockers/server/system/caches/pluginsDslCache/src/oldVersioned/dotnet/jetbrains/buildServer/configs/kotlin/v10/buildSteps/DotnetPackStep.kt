package jetbrains.buildServer.configs.kotlin.v10.buildSteps

import jetbrains.buildServer.configs.kotlin.v10.*

/**
 * A [dotnet pack step](https://github.com/JetBrains/teamcity-dotnet-plugin) to run .NET CLI command
 *
 * **Example.**
 * Runs [`dotnet pack`](https://learn.microsoft.com/en-us/dotnet/core/tools/dotnet-pack) command for the specified solution.
 * Does not try to build the project, assumes this is already done by some previous step.
 * ```
 * dotnetPack {
 *     projects = "MyProject.sln"
 *     configuration = "Debug"
 *     skipBuild = true
 * }
 * ```
 *
 *
 * @see dotnetPack
 */
open class DotnetPackStep : BuildStep {
    constructor(init: DotnetPackStep.() -> Unit = {}, base: DotnetPackStep? = null): super(base = base as BuildStep?) {
        type = "dotnet"
        param("command", "pack")
        init()
    }

    /**
     * Specify paths to projects and solutions. Wildcards are supported.
     */
    var projects by stringParameter("paths")

    /**
     * [Build working directory](https://www.jetbrains.com/help/teamcity/?Build+Working+Directory) for
     * script,
     * specify it if it is different from the [checkout
     * directory](https://www.jetbrains.com/help/teamcity/?Build+Checkout+Directory).
     */
    var workingDir by stringParameter("teamcity.build.workingDir")

    /**
     * Target configuration to pack for.
     */
    var configuration by stringParameter()

    /**
     * Target runtime to pack for.
     */
    var runtime by stringParameter()

    /**
     * The directory where to place outputs.
     */
    var outputDir by stringParameter()

    /**
     * Defines the value for the $(VersionSuffix) property in the project.
     */
    var versionSuffix by stringParameter()

    /**
     * Do not build the project before packing
     */
    var skipBuild by booleanParameter(trueValue = "true", falseValue = "")

    /**
     * Enter additional command line parameters for dotnet pack.
     */
    var args by stringParameter()

    /**
     * Specify logging verbosity
     *
     *
     * @see Verbosity
     */
    var logging by enumParameter<Verbosity>("verbosity")

    /**
     * .NET SDK versions separated by semicolon to be required on agents.
     */
    var sdk by stringParameter("required.sdk")

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
     * Logging verbosity
     */
    enum class Verbosity {
        Quiet,
        Minimal,
        Normal,
        Detailed,
        Diagnostic;

    }
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
 * Adds a [dotnet pack step](https://github.com/JetBrains/teamcity-dotnet-plugin) to run .NET CLI command
 *
 * **Example.**
 * Runs [`dotnet pack`](https://learn.microsoft.com/en-us/dotnet/core/tools/dotnet-pack) command for the specified solution.
 * Does not try to build the project, assumes this is already done by some previous step.
 * ```
 * dotnetPack {
 *     projects = "MyProject.sln"
 *     configuration = "Debug"
 *     skipBuild = true
 * }
 * ```
 *
 *
 * @see DotnetPackStep
 */
fun BuildSteps.dotnetPack(base: DotnetPackStep? = null, init: DotnetPackStep.() -> Unit = {}) {
    step(DotnetPackStep(init, base))
}
