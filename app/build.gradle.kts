import org.jetbrains.kotlin.cli.common.incrementalCompilationIsEnabled

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("com.google.dagger.hilt.android")
    id("com.google.devtools.ksp")
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.serialization)
    alias(libs.plugins.safeArgs)
}

android {
    namespace = "com.example.myapplicationtest"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.myapplicationtest"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        multiDexEnabled = true
    }

    buildTypes {
        release {
            isDebuggable = true
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
        viewBinding = true
        buildConfig = true
    }
    composeOptions{
        kotlinCompilerExtensionVersion = "1.5.3"
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    implementation(libs.androidx.navigation.compose)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation("androidx.compose.runtime:runtime")
    api(platform("androidx.compose:compose-bom:2024.12.01"))
    api(platform("org.jetbrains.kotlin:kotlin-bom:2.0.10-RC"))
    implementation("com.google.dagger:hilt-android:2.51.1")
    ksp("com.google.dagger:hilt-android-compiler:2.51.1")
    ksp ("androidx.room:room-compiler:2.5.2")

    api(libs.retrofit)
    api(libs.retrofitGsonConverter)

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.3")
    implementation ("com.jakewharton.timber:timber:5.0.1")
    implementation ("com.google.code.gson:gson:2.10.1")
    implementation("com.squareup.okhttp3:okhttp:4.12.0")

    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.9.0")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.9.0")



    // other dependencies
    // Compose
    implementation("androidx.activity:activity-compose:1.9.3")
    implementation("androidx.compose.material3:material3")
    implementation("com.google.accompanist:accompanist-themeadapter-material3:0.28.0")

    debugImplementation("androidx.compose.ui:ui-tooling")
    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.6.1")
    implementation("io.coil-kt:coil-compose:2.6.0")
    implementation("io.coil-kt:coil-gif:0.9.5")

    implementation("androidx.activity:activity-ktx:1.9.3")
//    implementation("androidx.navigation.safeargs.kotlin:2.8.5")
    implementation ("com.android.support:multidex:2.0.1")
}