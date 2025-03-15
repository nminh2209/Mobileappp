plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("kotlin-parcelize")
}

android {
    namespace = "com.example.musicrental"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.musicrental"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner" // ✅ Fixed syntax
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {
    // Core dependencies
    implementation(libs.androidx.core.ktx.v1120)
    implementation(libs.androidx.appcompat.v161)
    implementation(libs.material.v1110)
    implementation(libs.androidx.activity.ktx)
    implementation(libs.androidx.constraintlayout.v214)

    // Unit testing
    testImplementation(libs.junit)
    testImplementation(libs.androidx.junit.v115)
    testImplementation(libs.mockito.core.v560)

    // Android UI Testing Dependencies (Espresso, JUnit, and UI Testing)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.runner.v152)
    androidTestImplementation(libs.androidx.rules.v161)
    androidTestImplementation(libs.androidx.espresso.core.v351)
    androidTestImplementation(libs.androidx.espresso.contrib.v351)
    androidTestImplementation(libs.androidx.espresso.intents.v351)
}
