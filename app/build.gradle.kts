plugins {
    alias(libs.plugins.android.applicaiton)
    alias(libs.plugins.kotlin.android)
    kotlin("kapt")
    alias(libs.plugins.hilt)
    alias(libs.plugins.kotlin.parcelize)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.layfones.composewanandroid"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.layfones.composewanandroid"
        minSdk = 26
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
            proguardFiles (getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    // https://developer.android.com/jetpack/androidx/releases/compose-kotlin
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }

    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        compose = true
        buildConfig = true
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}
dependencies {

    implementation(libs.bundles.androidx)

    // Coil 图片加载
    implementation(libs.coil.compose)

    // Webkit
    implementation(libs.webkit)

    // Compose WebView
    // 系统 ui 控制器
    implementation(libs.bundles.accompanist)

    //Hilt inject framework
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
    implementation(libs.hilt.navigation.compose)

    // Paging
    implementation(libs.bundles.paging)

    implementation(libs.navigation.compose)
    implementation(libs.androidx.compose.android)

    implementation(libs.compose.material3)
    implementation(libs.compose.material)

    implementation(libs.kotlinx.serialization.json)

    // OkHttp
    implementation(libs.bundles.okhttp)

    // Retrofit2
    implementation(libs.bundles.retrofit)

    implementation(libs.bundles.room)
    ksp(libs.room.compiler)

    testImplementation(libs.junit4)
    androidTestImplementation(libs.test.ext)
    androidTestImplementation(libs.test.espresso)

    androidTestImplementation(libs.compose.ui.test)
    androidTestImplementation(libs.compose.ui.test.manifest)
    debugImplementation (libs.compose.ui.tooling)

    // For instrumentation tests
    androidTestImplementation (libs.hilt.android.testing)
    androidTestAnnotationProcessor(libs.hilt.compiler)

    // For local unit tests
    testImplementation(libs.hilt.android.testing)
    testAnnotationProcessor(libs.hilt.compiler)
}