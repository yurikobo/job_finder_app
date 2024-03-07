plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("kotlin-android")
}

android {
    namespace = "com.example.kotlincourse"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.kotlincourse"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {


    implementation(project(":core:network"))
    implementation(project(":core:common"))
    implementation(project(":core:featureapi"))
    implementation(project(":feature:vacancy:data"))
    implementation(project(":feature:vacancy:domain"))
    implementation(project(":feature:vacancy:ui"))
    implementation(project(":feature:company:data"))
    implementation(project(":feature:company:domain"))
    implementation(project(":feature:company:ui"))
    implementation(project(":feature:resume:data"))
    implementation(project(":feature:resume:domain"))
    implementation(project(":feature:resume:ui"))
    implementation(project(":feature:infoscreen:ui"))
    implementation(project(":feature:infoscreen:domain"))


    core()
    compose()
    composeNavigation()
    dagger()
    retrofit()
    test()
}