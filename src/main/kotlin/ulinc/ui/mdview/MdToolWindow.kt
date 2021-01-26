package ulinc.ui.mdview

import com.intellij.openapi.wm.ToolWindow
import javax.swing.JPanel
import com.intellij.ui.treeStructure.Tree
import ulinc.ui.mdview.mdtree.MetadataTreeViewBuilder
import java.awt.BorderLayout

class MdToolWindow {

    private val myToolWindowContent: JPanel? = JPanel(BorderLayout())
    private val tree: Tree? = Tree()

    constructor(toolWindow: ToolWindow,treeBuiler : MetadataTreeViewBuilder) {

        treeBuiler.build(tree)
        myToolWindowContent!!.add(tree, BorderLayout.WEST)

     }

    fun getContent(): JPanel? {
        return myToolWindowContent
    }

}