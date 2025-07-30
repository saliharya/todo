plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.ksp)

}

android {
    namespace = "com.todo.todo"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.todo.todo"
        minSdk = 27
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
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation(libs.retrofit)
    implementation(libs.retrofit.gson)
    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    implementation(libs.koin.android)
    implementation(libs.retrofit)

    implementation(libs.koin.android)

    implementation(libs.kotlinx.coroutines.android)

    ksp(libs.room.compiler)

    implementation(libs.playcore)
    implementation(libs.androidx.multidex)

    implementation(project(":core"))
    implementation(project(":common"))
    implementation(project(":presentation"))
}