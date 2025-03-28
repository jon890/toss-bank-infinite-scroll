dependencyResolutionManagement {
    versionCatalogs {
        create("libs") {
            from(files("libs.versions.toml"))
        }
    }
}

rootProject.name = "toss-bank-infinite-scroll"

include(
    "core-banking-server",
    "transfer-server",
    "discovery-server"
)
