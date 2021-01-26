package ulinc.ui.mdview.mdtree

import ulinc.ui.mdview.command.CommandProvider
import java.awt.event.MouseEvent
import java.awt.event.MouseListener
import javax.swing.JSeparator

import java.awt.event.ActionEvent

import java.awt.event.ActionListener

import javax.swing.JMenuItem

import javax.swing.JTree

import javax.swing.JPopupMenu
import com.intellij.ui.treeStructure.Tree
import javax.swing.tree.DefaultMutableTreeNode
import javax.swing.tree.TreePath
import com.intellij.openapi.ui.ComboBox

class MdTreeMouseListener: MouseListener {

    private var cp: CommandProvider? = null
    private var treePopup:TreePopup = TreePopup()

    fun setCommandProvider(_cp: CommandProvider){
        cp = _cp
    }

    override fun mouseClicked(e: MouseEvent?) {
    }

    override fun mousePressed(e: MouseEvent?) {
     }

    override fun mouseReleased(e: MouseEvent?) {
        if ((cp != null)&&(e != null)&&(e.button == MouseEvent.BUTTON3)){
            if(e.isPopupTrigger()) {
                var cmp = e.component
                var tree:Tree = cmp as Tree
                var node:TreePath = tree.getPathForLocation(e.getX(), e.getY())
                var id:String  = node.lastPathComponent.toString()
                var commandList =  this.cp!!.getCommandList(id)


                var treePopup:TreePopup = TreePopup()
                val h:HashMap<String, String> = commandList
                for ((key, value) in h) {
                    val mItem = JMenuItem(value)
                    treePopup.add(mItem)
                }
                treePopup!!.show(cmp, e.getX(), e.getY());
            }
        }
     }

    override fun mouseEntered(e: MouseEvent?) {
    }

    override fun mouseExited(e: MouseEvent?) {
     }
}

class TreePopup() : JPopupMenu() {

}