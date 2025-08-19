// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    id ("com.google.dagger.hilt.android") version "2.57" apply false
    id("com.google.devtools.ksp") version "2.0.21-1.0.27" apply false
    id("vkid.manifest.placeholders") version "1.1.0" apply true
}
// Добавление значений в Manifest Placeholders.
vkidManifestPlaceholders {
    // Добавьте плейсхолдеры сокращенным способом. Например, vkidRedirectHost будет "vk.com", а vkidRedirectScheme будет "vk$clientId".
    init(
        clientId = "53942033",
        clientSecret = "QGCJy4CuCwdCUvN3LCdg",
    )
}