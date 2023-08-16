@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.android.app)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.kotlin.parcelize)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.hilt)
    alias(libs.plugins.navigation.safeargs)
}

android {
    namespace = "app.vazovsky.weather"
    compileSdk = 33

    defaultConfig {
        applicationId = "app.vazovsky.weather"
        minSdk = 26
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        isCoreLibraryDesugaringEnabled = true
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
    }
}
kapt {
    correctErrorTypes = true
}
dependencies {
    implementation(libs.material)
    implementation(libs.core)
    implementation(libs.androidx.navigation.fragment)
    implementation(libs.androidx.navigation.ui)
    implementation(libs.androidx.splashscreen)
    implementation(libs.androidx.work)

    implementation(libs.kotlin)
    implementation(libs.kotlin.serialization)
    implementation(libs.kotlin.datetime)
    coreLibraryDesugaring(libs.desugaring)

    implementation(libs.hilt)
    kapt(libs.hilt.compiler)
    implementation(libs.hilt.work)
    kapt(libs.hilt.work.compiler)

    implementation(libs.view.binding)
    implementation(libs.timber)

    implementation(libs.retrofit)
    implementation(libs.retrofit.gson)

    debugImplementation(libs.chucker)
    releaseImplementation(libs.chucker.release)

    implementation(libs.lottie)
    implementation(libs.glide)
    annotationProcessor(libs.glide.compiler)
}