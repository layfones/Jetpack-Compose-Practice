plugins {

    alias(libs.plugins.android.applicaiton) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.parcelize) apply false
    alias(libs.plugins.kotlin.jvm) apply false
    alias(libs.plugins.hilt) apply false

    // https://developer.android.com/build/migrate-to-ksp
    // https://github.com/google/ksp/releases
    // https://kotlinlang.org/docs/ksp-overview.html
    alias(libs.plugins.ksp) apply false

}