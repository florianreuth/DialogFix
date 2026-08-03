import de.florianreuth.baseproject.integration.setupFabric
import de.florianreuth.baseproject.setupProject
import de.florianreuth.baseproject.setupPublishing

plugins {
    id("net.fabricmc.fabric-loom")
    id("de.florianreuth.baseproject")
}

setupProject()
setupFabric()
setupPublishing()
