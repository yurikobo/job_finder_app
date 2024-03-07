pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Job Searching Client"
include(":app")
include(":core:network")
include(":feature:vacancy:data")
include(":feature:vacancy:domain")
include(":feature:vacancy:ui")
include(":feature:company:data")
include(":feature:company:domain")
include(":feature:company:ui")
include(":core:common")
include(":core:featureapi")
include(":feature:resume:data")
include(":feature:resume:domain")
include(":feature:resume:ui")
include(":feature:infoscreen:data")
include(":feature:infoscreen:domain")
include(":feature:infoscreen:ui")
