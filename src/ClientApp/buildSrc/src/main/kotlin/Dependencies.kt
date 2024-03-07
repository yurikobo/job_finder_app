import org.gradle.api.artifacts.dsl.DependencyHandler

object Version {
    const val coreVersion = "1.12.0"
    const val materialVersion = "1.11.0"
    const val daggerVersion = "2.50"
    const val lifecycleVersion = "2.7.0"
    const val retrofitVersion = "2.9.0"
    const val retrofitSerialization = "1.0.0"
    const val okHttp3Interceptor = "4.11.0"
    const val composeActivity = "1.8.2"
    const val composeNavigation = "2.7.7"
    const val composePlatform = "2023.08.00"
    const val junit = "4.13.2"
    const val junitExtTest = "1.1.5"
    const val espressoCore = "3.5.1"
    const val coroutinesVersion = "1.3.9"
    const val composeTracing = "1.0.0-alpha03"
}

object Deps {
    const val coreKtx = "androidx.core:core-ktx:${Version.coreVersion}"
    const val androidMaterial = "com.google.android.material:material:${Version.materialVersion}"
    const val retrofit = "com.squareup.retrofit2:retrofit:${Version.retrofitVersion}"
    const val retrofitSerialization =
        "com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:${Version.retrofitSerialization}"

    const val loggingInterceptor =
        "com.squareup.okhttp3:logging-interceptor:${Version.okHttp3Interceptor}"
    const val uiCompose = "androidx.compose.ui:ui"
    const val uiGraphicsCompose = "androidx.compose.ui:ui-graphics"
    const val uiToolingPreviewCompose = "androidx.compose.ui:ui-tooling-preview"
    const val materialCompose = "androidx.compose.material3:material3"
    const val uiToolingCompose = "androidx.compose.ui:ui-tooling"
    const val uiTestManifestCompose = "androidx.compose.ui:ui-test-manifest"
    const val composeActivity = "androidx.activity:activity-compose:${Version.composeActivity}"
    const val composeNavigation =
        "androidx.navigation:navigation-compose:${Version.composeNavigation}"
    const val composeBom = "androidx.compose:compose-bom:${Version.composePlatform}"
    const val composeTracing = "androidx.compose.runtime:runtime-tracing:${Version.composeTracing}"

    const val dagger = "com.google.dagger:dagger:${Version.daggerVersion}"
    const val kaptDaggerCompiler = "com.google.dagger:dagger-compiler:${Version.daggerVersion}"
    const val viewModelLifecycleCompose =
        "androidx.lifecycle:lifecycle-viewmodel-compose:${Version.lifecycleVersion}"
    const val lifecycleRuntimeKtx =
        "androidx.lifecycle:lifecycle-runtime-ktx:${Version.lifecycleVersion}"
    const val junit = "junit:junit:${Version.junit}"
    const val junitExtTest = "androidx.test.ext:junit:${Version.junitExtTest}"
    const val espressoCore = "androidx.test.espresso:espresso-core:${Version.espressoCore}"
    const val junitTestUiCompose = "androidx.compose.ui:ui-test-junit4"
    const val coroutines =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Version.coroutinesVersion}"

}

fun DependencyHandler.core() {
    implementation(Deps.androidMaterial)
    implementation(Deps.coreKtx)
    implementation(Deps.lifecycleRuntimeKtx)
}

fun DependencyHandler.dagger() {
    implementation(Deps.dagger)
    kapt(Deps.kaptDaggerCompiler)
}

fun DependencyHandler.retrofit() {
    implementation(Deps.retrofit)
    implementation(Deps.retrofitSerialization)
    implementation(Deps.loggingInterceptor)
}

fun DependencyHandler.compose() {
    implementation(Deps.uiCompose)
    implementation(Deps.uiGraphicsCompose)
    implementation(Deps.uiToolingPreviewCompose)
    implementation(Deps.materialCompose)
    implementation(platform(Deps.composeBom))
    androidTestImplementation(platform(Deps.composeBom))
    androidTestImplementation(Deps.junitTestUiCompose)
    debugImplementation(Deps.uiToolingCompose)
    debugImplementation(Deps.uiTestManifestCompose)
    implementation(Deps.composeActivity)
    implementation(Deps.viewModelLifecycleCompose)
    implementation(Deps.composeTracing)

}

fun DependencyHandler.composeNavigation() {
    implementation(Deps.composeNavigation)
}

fun DependencyHandler.coroutines() {
    implementation(Deps.coroutines)
}

fun DependencyHandler.test() {
    testImplementation(Deps.junit)
    androidTestImplementation(Deps.junitExtTest)
    androidTestImplementation(Deps.espressoCore)
}
