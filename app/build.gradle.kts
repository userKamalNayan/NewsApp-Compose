import java.io.FileInputStream
import java.io.InputStreamReader
import java.util.Properties

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("dagger.hilt.android.plugin")
    id("kotlin-android")
    id("kotlin-parcelize")
    kotlin("kapt")
}

android {
    namespace = "com.kamalnayan.newsapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.kamalnayan.newsapp"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
        buildConfigField("String","API_KEY","\"${getProperties()?.get("news_api_key")}\"")
        buildConfigField("String","BASE_URL","\"https://newsapi.org/\"")
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
        buildConfig = true
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
    implementation(project(Module.utilities))
    implementation(Dependencies.coreKtx)
    implementation(Dependencies.lifecycleKtx)
    implementation(Dependencies.activityCompose)
    implementation(platform("androidx.compose:compose-bom:2023.08.00"))
    implementation(Dependencies.composeUi)
    implementation(Dependencies.composeUiGraphics)
    implementation(Dependencies.composeUiToolingPreview)
    implementation(Dependencies.composeMat3)
    testImplementation(Dependencies.junit)
    androidTestImplementation(Dependencies.junitExt)
    androidTestImplementation(Dependencies.espresso)
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.08.00"))
    androidTestImplementation(Dependencies.composeUiTestJunit)
    debugImplementation(Dependencies.composeUiTooling)
    debugImplementation(Dependencies.composeUiTestManifest)
    implementation(Dependencies.hiltAndroid)
    kapt(Dependencies.hiltCompiler)

    implementation(Dependencies.hiltNavigationCompose)
//        retrofit
    implementation(Dependencies.retrofit)
    implementation(Dependencies.gsonConverter)
    implementation(Dependencies.okhttp)
    implementation(Dependencies.loggingInterceptor)
    implementation(Dependencies.sandwich)

    implementation(Dependencies.coroutineAndroid)
    implementation(Dependencies.coroutineCore)

    implementation(Dependencies.coil)
}

kapt{
    correctErrorTypes=true
}


/*
Returns properties file
 */
fun getProperties(): Properties? {
    var properties:Properties? =  Properties()
    val file = File("local.properties")
    if (file.exists()) {
        InputStreamReader(FileInputStream(file), Charsets.UTF_8).use { reader ->
            properties?.load(reader)
        }
    } else {
        properties = null
    }
    return properties


//    Properties properties = new Properties()
//    def file = rootProject.file(fileName)
//    if (file.exists()) {
//        file.withInputStream { stream -> properties.load(stream) }
//    } else {
//        properties = null
//    }
//    return properties
}