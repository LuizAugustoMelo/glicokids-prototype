# Security Hardening: Obfuscation Rules

# Keep Hilt generated classes
-keep class * extends android.app.Activity
-keep class * extends android.app.Application
-keep class * extends android.app.Service
-keep class * extends android.content.BroadcastReceiver
-keep class * extends android.content.ContentProvider

# Protect Domain Use Cases (Obfuscate names but keep logic)
-keepclassmembers class com.glicokids.prototype.domain.usecase.** {
    public *;
}

# Room Database Security
-keep class * extends androidx.room.RoomDatabase
-keep class androidx.room.** { *; }

# General Optimization
-dontwarn com.google.dagger.hilt.**
-dontwarn javax.inject.**
