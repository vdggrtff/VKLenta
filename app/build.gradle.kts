import com.android.build.gradle.tasks.PackagePrivacySandboxSdkBundle

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id ("kotlin-parcelize")
    id("vkid.manifest.placeholders")
}

android {
    namespace = "com.example.vklenta"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.vklenta"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        isCoreLibraryDesugaringEnabled = true
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
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
    implementation(libs.androidx.room.runtime.android)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    implementation("androidx.compose.runtime:runtime-livedata:1.8.3")
    implementation("androidx.navigation:navigation-compose:2.9.1")
    implementation("com.google.code.gson:gson:2.13.1")
    implementation("io.coil-kt:coil-compose:2.7.0")

    /*implementation("com.vk:android-sdk-core:4.1.0")
    implementation("com.vk:android-sdk-api:4.1.0")*/

    val vkSdk = "2.5.0"

    //implementation("com.vk:id-kotlin-sdk:1.0.0")
    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:2.1.5")
    implementation("com.vk.id:vkid:$vkSdk")
    implementation("com.vk.id:onetap-compose:${vkSdk}")
    //implementation("com.vk:androidsdk:2.5.0")
   /* implementation("com.vk.id:vk-sdk-support:2.5.0")
    implementation("com.vk.id:onetap-compose:$vkSdk")
    implementation("com.vk.id:multibranding-compose:${vkSdk}")
    implementation("com.vk.id:onetap-common:${vkSdk}")
    implementation("com.vk.id:group-subscription-compose:${vkSdk}")
    implementation("com.vk.id:multibranding-common:${vkSdk}")
    implementation("com.vk.id:group-subscription-common:${vkSdk}")
    implementation("com.vk.id:multibranding-internal:${vkSdk}")
    implementation("com.vk.id:analytics:${vkSdk}")
    implementation("com.vk.id:network:${vkSdk}")
    implementation("com.vk.id:tracking-tracer:${vkSdk}")
    implementation("com.vk.id:logger:${vkSdk}")
    implementation("om.vk.id:tracking-core:${vkSdk}")
    implementation("com.vk.id:common:${vkSdk}")
    implementation("om.vk.id:tracking-core:2.5.0")*/
}