import org.jetbrains.dokka.gradle.DokkaTask
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.3.72"
    id("org.jetbrains.dokka") version "0.10.1"
    `maven-publish`
    id("com.jfrog.bintray") version "1.8.5"
}

group = "com.hankadler.util"
version = "0.1.0"

repositories {
    jcenter()
}

dependencies {
    implementation(kotlin("stdlib"))
    testImplementation("org.junit.jupiter:junit-jupiter:5.6.2")
}

/*** Testing ***/
tasks.test {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
    }
}

tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions.jvmTarget = "1.8"
}

/*** Documentation ***/
tasks.register("allDokka") {
    group = "documentation"
    dependsOn("userDokka")
    dependsOn("developerDokka")
}

tasks.register<DokkaTask>("userDokka") {
    outputDirectory = "docs/kdoc/user"
}

tasks.register<DokkaTask>("developerDokka") {
    outputDirectory = "docs/kdoc/developer"
    configuration {
        sourceRoot { path = "src" }  // Includes test sources kdoc.
        includeNonPublic = true  // Includes kdoc for non-public members.
    }
}

/*** Deployment ***/
val manifestName = "${project.group.toString().replace(".", "/")}/"
val implementationTitle = "${project.group}.${project.name.capitalize()}"
val implementationVersion = project.version
val implementationVendor = "hank@hankadler.com"

tasks.jar {
    group = "deployment"
    description = "Creates jar from classes."

    destinationDirectory.set(file("build/jar"))
    archiveClassifier.set("")
    manifest {
        attributes["Name"] =  manifestName
        attributes["Implementation-Title"] = implementationTitle
        attributes["Implementation-Version"] = implementationVersion
        attributes["Implementation-Vendor"] = implementationVendor
    }
}

val sourcesJar by tasks.creating(Jar::class) {
    group = "deployment"
    description = "Creates jar from sources."

    from(sourceSets["main"].allSource)

    destinationDirectory.set(file("build/jar"))
    archiveClassifier.set("sources")
    manifest {
        attributes["Name"] =  manifestName
        attributes["Implementation-Title"] = implementationTitle
        attributes["Implementation-Version"] = implementationVersion
        attributes["Implementation-Vendor"] = implementationVendor
    }
}

val dokkaJar by tasks.creating(Jar::class) {
    group = "deployment"
    description = "Creates jar from dokka."

    from(".") { include("docs/kdoc/user/**") }

    destinationDirectory.set(file("build/jar"))
    archiveClassifier.set("kdoc")
}

/*** Publishing ***/
val repoName = "kotlin-util"
val repoDesc = "General utilities."
val pkgDesc = "Functions for runtime call stack inspection."
val githubRepo = "https://github.com/hankadler/$repoName-${project.name}"
val licenseName = "MIT"
val licenseUrl = "https://opensource.org/licenses/mit-license.php"

bintray {
    user = System.getenv("BINTRAY_USER")
    key = System.getenv("BINTRAY_KEY")
    publish = true
    setPublications(repoName)
    pkg.apply {
        repo = repoName
        desc = repoDesc
        name = project.name
        description = pkgDesc
        version.name = project.version.toString()
        setLicenses("MIT")
        githubRepo = githubRepo
        githubReleaseNotesFile = "$githubRepo/CHANGELOG.md"
        issueTrackerUrl = "$githubRepo/issues"
        vcsUrl = "$githubRepo.git"
        //websiteUrl = "www.hankadler.com/coding/kotlin"
    }
}

publishing {
    publications {
        create<MavenPublication>(repoName) {
            from(components["java"])
            groupId = project.group.toString()
            artifactId = project.name
            version = project.version.toString()
            artifact(sourcesJar)
            artifact(dokkaJar)
        }
    }
}
