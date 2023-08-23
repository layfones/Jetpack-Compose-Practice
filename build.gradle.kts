
plugins {
    id("com.android.application") version "8.1.0" apply false
    id("com.android.library") version "8.1.0" apply false
    id("org.jetbrains.kotlin.android") version "1.9.0" apply false
    id("com.google.dagger.hilt.android") version "2.47" apply false
    id("org.jetbrains.kotlin.jvm") version "1.9.0" apply false

    /**
     * https://developer.android.com/build/migrate-to-ksp
     * https://github.com/google/ksp/releases
     * https://kotlinlang.org/docs/ksp-overview.html
     */
    id("com.google.devtools.ksp") version "1.9.0-1.0.11" apply false
}