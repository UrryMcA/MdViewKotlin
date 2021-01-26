package ulinc.ui.mdview

import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowFactory
import com.intellij.ui.content.ContentFactory
import ulinc.ui.mdview.mdtree.MetadataTreeViewJsonBuilder

class MdToolWindowFactory : ToolWindowFactory{

    override fun createToolWindowContent(project: Project, toolWindow: ToolWindow) {

        val myToolWindow = MdToolWindow(toolWindow, MetadataTreeViewJsonBuilder())
        val contentFactory = ContentFactory.SERVICE.getInstance()
        val content = contentFactory.createContent(myToolWindow.getContent(), "", false)
        toolWindow.contentManager.addContent(content)


    }
}