plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.eshopping"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.eshopping"
        minSdk = 26
        targetSdk = 34
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

    implementation ("com.squareup.retrofit2:retrofit:2.9.0") //Retrofit
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0") //convertor json

    implementation ("com.github.bumptech.glide:glide:4.11.0") //Glide

    implementation("com.squareup.okhttp3:logging-interceptor:4.9.2") //OKHTTP Logging Interceptor

}