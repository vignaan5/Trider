plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.gyanu.trider"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.gyanu.trider"
        minSdk = 25
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

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.compose.material)
    implementation(libs.androidx.navigation.runtime.ktx)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.play.services.location)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    // KTX for the Maps SDK for Android library
    implementation ("com.google.maps.android:maps-ktx:5.0.0")

    // KTX for the Maps SDK for Android Utility Library
    implementation ("com.google.maps.android:maps-utils-ktx:5.0.0")

    //retrofit2
    implementation("com.squareup.retrofit2:retrofit:2.9.0")

    //gson
    implementation ("com.google.code.gson:gson:2.10.1")

    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0")


    implementation ("androidx.core:core-ktx:1.12.0")

    //maps compose
    implementation ("com.google.maps.android:maps-compose:4.3.3")

    implementation ("androidx.lifecycle:lifecycle-viewmodel-compose:2.7.0")

    implementation("androidx.core:core-splashscreen:1.0.1")
    
    implementation ("com.squareup.retrofit2:converter-gson:2.7.0")

    implementation ("androidx.compose.material:material:1.6.3")

    implementation("com.google.dagger:hilt-android:2.48")

    kapt("com.google.dagger:hilt-android-compiler:2.48")

}