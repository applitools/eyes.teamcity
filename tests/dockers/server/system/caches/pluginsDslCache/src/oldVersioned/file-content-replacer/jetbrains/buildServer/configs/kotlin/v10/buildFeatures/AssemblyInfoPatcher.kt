package jetbrains.buildServer.configs.kotlin.v10.buildFeatures

import jetbrains.buildServer.configs.kotlin.v10.*

/**
 * A [build feature](https://www.jetbrains.com/help/teamcity/?AssemblyInfo+Patcher) which updates the AssemblyVersion,
 * AssemblyFileVersion and AssemblyInformationalVersion attributes in AssemblyInfo files under Properties folders.
 * No additional attributes will be added, make sure you have all necessary attributes in the source code.
 * Changed source files are reverted at the end of a build.
 *
 * **Example.**
 * Updates AssemblyVersion, AssemblyFileVersion and AssemblyInformationalVersion attributes version formats. Also updates versions in GlobalAssemblyInfo
 * ```
 * assemblyInfoPatcher {
 *     assemblyFormat = "1.0.0.%system.build.number%-rc"
 *     fileFormat = "1.0.0.%system.build.number%-rc"
 *     infoFormat = "1.0.0 RC"
 *     patchGlobalAssemblyInfo = true
 * }
 * ```
 *
 *
 * @see assemblyInfoPatcher
 */
open class AssemblyInfoPatcher : BuildFeature {
    constructor(init: AssemblyInfoPatcher.() -> Unit = {}, base: AssemblyInfoPatcher? = null): super(base = base as BuildFeature?) {
        type = "JetBrains.AssemblyInfo"
        init()
    }

    /**
     * Assembly version format to update AssemblyVersion attribute.
     */
    var assemblyFormat by stringParameter("assembly-format")

    /**
     * Assembly file version format to update AssemblyFileVersion attribute.
     * Leave blank to use same version as specified in assembly version.
     */
    var fileFormat by stringParameter("file-format")

    /**
     * Assembly informational version format to update AssemblyInformationalVersion attribute.
     * Leave blank to leave attribute unchanged.
     */
    var infoFormat by stringParameter("info-format")

    /**
     * If true, AssemblyInfoPatcher will attempt to patch GlobalAssemblyInfo files.
     */
    var patchGlobalAssemblyInfo by booleanParameter("patch-global-assembly-info")

}


/**
 * Adds a [build feature](https://www.jetbrains.com/help/teamcity/?AssemblyInfo+Patcher) which updates the [AssemblyVersion](https://learn.microsoft.com/en-us/dotnet/api/system.reflection.assemblyversionattribute),
 * [AssemblyFileVersion](https://learn.microsoft.com/en-us/dotnet/api/system.reflection.assemblyfileversionattribute) and [AssemblyInformationalVersion](https://learn.microsoft.com/en-us/dotnet/api/system.reflection.assemblyinformationalversionattribute) attributes in [AssemblyInfo](https://learn.microsoft.com/en-us/dotnet/api/microsoft.visualbasic.applicationservices.assemblyinfo?view=windowsdesktop-7.0) files under Properties folders.
 * No additional attributes will be added, make sure you have all necessary attributes in the source code.
 * Changed source files are reverted at the end of a build.
 *
 * **Example.**
 * Updates AssemblyVersion, AssemblyFileVersion and AssemblyInformationalVersion attributes version formats. Also updates versions in GlobalAssemblyInfo
 * ```
 * assemblyInfoPatcher {
 *     assemblyFormat = "1.0.0.%system.build.number%-rc"
 *     fileFormat = "1.0.0.%system.build.number%-rc"
 *     infoFormat = "1.0.0 RC"
 *     patchGlobalAssemblyInfo = true
 * }
 * ```
 *
 *
 * @see AssemblyInfoPatchers
 */
fun BuildFeatures.assemblyInfoPatcher(base: AssemblyInfoPatcher? = null, init: AssemblyInfoPatcher.() -> Unit = {}) {
    feature(AssemblyInfoPatcher(init, base))
}
