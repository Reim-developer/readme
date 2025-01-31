plugins {
    id("java")
    kotlin("jvm")
}

group = "reim.dev"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.eclipse.platform:org.eclipse.swt.gtk.linux.x86_64:3.128.0")
    implementation(kotlin("stdlib-jdk8"))
}

configurations.all {
    resolutionStrategy {
        dependencySubstitution {
            val os = System.getProperty("os.name").lowercase()
            when {
                os.contains("windows") -> substitute(module("org.eclipse.platform:org.eclipse.swt.\${osgi.platform}"))
                    .using(module("org.eclipse.platform:org.eclipse.swt.win32.win32.x86_64:3.114.0"))
                os.contains("linux") -> substitute(module("org.eclipse.platform:org.eclipse.swt.\${osgi.platform}"))
                    .using(module("org.eclipse.platform:org.eclipse.swt.gtk.linux.x86_64:3.114.0"))
                os.contains("mac") -> substitute(module("org.eclipse.platform:org.eclipse.swt.\${osgi.platform}"))
                    .using(module("org.eclipse.platform:org.eclipse.swt.cocoa.macosx.x86_64:3.114.0"))
            }
        }
    }
}

kotlin {
    jvmToolchain(17)
}