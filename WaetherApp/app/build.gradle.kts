import java.util.*

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
    id("kotlin-android")
}

android {
    compileSdk= (Versions.COMPILE_SDK)

    defaultConfig {

        applicationId= "com.example.waetherapp"
        minSdk =Versions.MIN_SDK
        targetSdk= Versions.TARGET_SDK
        versionCode= 1
        versionName ="1.0"
        testInstrumentationRunner ="androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            // Shrink apk size
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    buildTypes.forEach {
        // Loading secret API keys from local.properties to BuildConfig
        val properties = Properties().apply {
            load(project.rootProject.file("local.properties").inputStream())
        }
        val apiKeyOpenWeatherMap: String = properties.getProperty("api_key_open_weather_map", "")
        val apiKeyOpenCage: String = properties.getProperty("api_key_open_cage", "")
        it.buildConfigField("String", "API_KEY_OPEN_WEATHER_MAP", apiKeyOpenWeatherMap)
        it.buildConfigField("String", "API_KEY_OPEN_CAGE", apiKeyOpenCage)
    }


    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(Libs.KOTLIN)
    implementation(Libs.COROUTINES)

    // UI
    implementation(Libs.MATERIAL_DIALOGS)
    implementation(Libs.MATERIAL)
    implementation(Libs.ANDROID_SUPPORT)
    implementation(Libs.RECYCLERVIEW)
    implementation(Libs.APPCOMPAT)
    implementation(Libs.SWIPEREFRESH_LAYOUT)
    implementation(Libs.CONSTRAINT_LAYOUT)
    implementation(Libs.INTERPOLATORS)
    implementation("androidx.appcompat:appcompat:1.3.1")
    implementation("com.google.android.material:material:1.4.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.1")

    // Tests
    testImplementation(Libs.JUNIT)
    androidTestImplementation(Libs.ARCH_TESTING)
    androidTestImplementation(Libs.JUNIT)
    androidTestImplementation(Libs.TEST_RUNNER)
    androidTestImplementation(Libs.TEST_RULES)
    androidTestImplementation(Libs.ESPRESSO)

    // Architecture Components
    implementation(Libs.KTX_CORE)
    implementation(Libs.KTX_FRAGMENT)
    implementation(Libs.KTX_PREFERENCE)
    implementation(Libs.KTX_LIFECYCLE_VIEWMODEL)
    implementation(Libs.KTX_LIFECYCLE_LIVEDATA)
    implementation(Libs.KTX_LIFECYCLE_EXTENSIONS)

    // Network
    implementation(Libs.GSON)
    implementation(Libs.RETROFIT2)
    implementation(Libs.RETROFIT2_GSON_CONVERTER)

    // Room - Database
    implementation(Libs.ROOM_RUNTIME)
    implementation(Libs.KTX_ROOM)
    annotationProcessor(Libs.ROOM_COMPILER)
    kapt(Libs.ROOM_COMPILER)

    // Koin - Dependency Injection
    implementation(Libs.KOIN)
    implementation(Libs.KOIN_SCOPE)
    implementation(Libs.KOIN_VIEWMODEL)

    implementation(Libs.INTERCEPTOR)
    implementation(Libs.LOTTIE)
}