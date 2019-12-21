object Versions {

    const val compiledSdkVersion = 29
    const val buildToolsVersion = "29.0.2"
    const val applicationId = "com.example.myapplication"
    const val mindSdkVersion = 16
    const val targetSdkVersion = 29
    const val versionCode = 1
    const val versionName = "1.0"
    const val testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

    const val coroutine_android = "1.3.2"
    const val support_desin = "28.0.0"
    const val kotlin = "1.2.71"
    const val androidx_appcompat = "1.1.0"
    const val ktx_core = "1.1.0"
    const val constrainlayout = "1.1.3"
    const val junit = "4.12"
    const val mockito_core = "2.28.2"
    const val core_testing = "1.1.1"
    const val loggin_interceptor = "3.12.1"
    const val retrofit_gson_converter = "2.4.0"
    const val mockk = "1.2"
    const val mockito_inline = "2.24.5"
    const val mockito_kotlin = "2.1.0"
    const val test_runnter = "1.2.0-alpha03"
    const val espresso_core = "3.2.0-alpha03"
    const val retrofit = "2.6.1"
    const val gson = "2.8.5"
    const val couroutines_core = "1.3.1"
    const val viewmodel_ktx = "2.2.0-alpha04"
    const val koin_viewmodel = "2.0.1"
    const val recyclerview = "1.0.0"
    const val koin = "2.0.1"

    const val swipeRefresh = "1.0.0"
    const val viewpager2 = "1.0.0-rc01"

    const val dagger = "2.25.2"
    const val dagger_android = "2.25.2"
}

object Dependencies {

    val junit = "junit:junit:${Versions.junit}"
    val mockito_core = "org.mockito:mockito-core:${Versions.mockito_core}"
    val core_testing = "android.arch.core:core-testing:${Versions.core_testing}"
    val mockk = "io.mockk:mockk:${Versions.mockk}"
    val mockito_inline = "org.mockito:mockito-inline:${Versions.mockito_inline}"
    val mockito_kotlin = "com.nhaarman.mockitokotlin2:mockito-kotlin:${Versions.mockito_kotlin}"
    val test_runnter = "androidx.test:runner:${Versions.test_runnter}"
    val espresso_core = "androidx.test.espresso:espresso-core:${Versions.espresso_core}"

    val kotlinStandardLibrary = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
    val androidAppCompat = "androidx.appcompat:appcompat:${Versions.androidx_appcompat}"
    val ktxCore = "androidx.core:core-ktx:${Versions.ktx_core}"
    val constrainLayout = "androidx.constraintlayout:constraintlayout:${Versions.constrainlayout}"
    val recyclerview = "com.android.support:recyclerview-v7:${Versions.recyclerview}"

    val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    val loggin_interceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.loggin_interceptor}"
    val retrofit_gson_converter = "com.squareup.retrofit2:converter-gson:${Versions.retrofit_gson_converter}"
    val gson = "com.google.code.gson:gson:${Versions.gson}"

    val couroutines_core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.couroutines_core}"
    val kotlinCoroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutine_android}"
    val viewmodel_ktx = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.viewmodel_ktx}"
    val koin_viewmodel = "org.koin:koin-android-viewmodel:${Versions.koin_viewmodel}"

    val koin = "org.koin:koin-android:${Versions.koin}"
    val support_design = "com.android.support:design:${Versions.support_desin}"
    val swipeRefresh = "androidx.swiperefreshlayout:swiperefreshlayout:${Versions.swipeRefresh}"
    val viewpager2 = "androidx.viewpager2:viewpager2:${Versions.viewpager2}"

    val dagger2 = "com.google.dagger:dagger:${Versions.dagger}"
    val dagger2_android = "com.google.dagger:dagger-android:${Versions.dagger_android}"

}
