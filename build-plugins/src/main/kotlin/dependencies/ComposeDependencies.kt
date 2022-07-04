package dependencies

import COMPOSE_CATALOG_NAME
import NeededDependencies
import getCatalog
import org.gradle.api.Project

internal class ComposeDependencies : NeededDependencies {

    override fun addDependencies(project: Project) {
        val composeLibs = project.getCatalog()
        val kotlin = project.getCatalog().getKotlin().get()

        project.dependencies.implementation(kotlin)
        project.dependencies.implementation(composeLibs.findBundle(COMPOSE_CATALOG_NAME).get())
    }
}
