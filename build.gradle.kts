/*
 *
 *  * Copyright © 2025 小满1221
 *  * Author: 小满1221
 *  * Date: 2025/3/30
 *
 */

plugins {
    kotlin("jvm") version "2.1.20"
    id("com.gradleup.shadow") version "8.3.6"
    id("org.jetbrains.kotlin.plugin.serialization") version "2.1.20"
    id("application")
}

group = "com.fuck.xixunyun"
version = "1.0"

repositories {
    mavenCentral()
    maven("https://repo.maven.rtast.cn/releases")
    maven("https://repo.maven.rtast.cn/snapshots")}

dependencies {
    testImplementation(kotlin("test"))
    implementation(kotlin("stdlib"))
    implementation("cn.rtast:rtast-util-string:0.0.1")
    implementation("cn.rtast:rtast-util-http:1.0.0")
    implementation("org.bouncycastle:bcpkix-jdk15on:1.70")
}

application {
    mainClass = "com.xixunyun.fuck.MainKt"
}
tasks.build {
    dependsOn(tasks.shadowJar)
}

kotlin {
    jvmToolchain(23)
}