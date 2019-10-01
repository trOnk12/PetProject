object Versions {

    const val compiledSdkVersion = 29
    const val buildToolsVersion = "29.0.2"
    const val applicationId = "com.example.myapplication"
    const val mindSdkVersion = 15
    const val targetSdkVersion = 29
    const val versionCode = 1
    const val versionName = "1.0"
    const val testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

    const val support_design = "28.0.0"
    const val kotlin = "1.2.71"
    const val androidx_appcompat = "1.1.0"
    const val ktx_core = "1.1.0"
    const val constraint = "1.1.3"
    const val junit = "4.12"
    const val mockito_core = "2.28.2"
    const val core_testing = "1.1.1"
    const val logging_interceptor = "3.12.1"
    const val retrofit_gson_converter = "2.4.0"
    const val mock = "1.2"
    const val mockito_inline = "2.24.5"
    const val mockito_kotlin = "2.1.0"
    const val test_runner = "1.2.0-alpha03"
    const val espresso_core = "3.2.0-alpha03"
    const val retrofit = "2.6.1"
    const val gson = "2.8.5"
    const val coroutines_core = "1.3.1"
    const val view_model_ktx = "2.2.0-alpha04"
    const val koin_view_model = "2.0.1"
    const val recycler_view = "1.0.0"
    const val koin = "2.0.1"

}

object Dependencies {

    val junit = "junit:junit:${Versions.junit}"
    val mockito_core = "org.mockito:mockito-core:${Versions.mockito_core}"
    val core_testing = "android.arch.core:core-testing:${Versions.core_testing}"
    val mock = "io.mock:mock:${Versions.mock}"
    val mockito_inline = "org.mockito:mockito-inline:${Versions.mockito_inline}"
    val mockito_kotlin = "com.nhaarman.mockitokotlin2:mockito-kotlin:${Versions.mockito_kotlin}"
    val test_runner = "androidx.test:runner:${Versions.test_runner}"
    val espresso_core = "androidx.test.espresso:espresso-core:${Versions.espresso_core}"

    val kotlinStandardLibrary = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
    val androidAppCompat = "androidx.appcompat:appcompat:${Versions.androidx_appcompat}"
    val ktxCore = "androidx.core:core-ktx:${Versions.ktx_core}"
    val constraint_layout = "androidx.constraintlayout:constraintlayout:${Versions.constraint}"
    val recycler_view = "com.android.support:recycler_view-v7:${Versions.recycler_view}"

    val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    val logging_interceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.logging_interceptor}"
    val retrofit_gson_converter = "com.squareup.retrofit2:converter-gson:${Versions.retrofit_gson_converter}"
    val gson = "com.google.code.gson:gson:${Versions.gson}"

    val coroutines_core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines_core}"
    val view_model_ktx = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.view_model_ktx}"
    val koin_view_model = "org.koin:koin-android-viewmodel:${Versions.koin_view_model}"

    val koin = "org.koin:koin-android:${Versions.koin}"
    val support_design = "com.android.support:design:${Versions.support_design}"

}
