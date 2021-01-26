package ulinc.ui.mdview.mdtree

import java.awt.Component
import javax.swing.ImageIcon
import javax.swing.tree.DefaultTreeCellRenderer
import javax.swing.JTree

class MdTreeCellRenderer: DefaultTreeCellRenderer() {

    private var IconCashe : HashMap<String, ImageIcon>? = null

    override fun getTreeCellRendererComponent(tree: JTree?, value: Any, sel: Boolean, expanded: Boolean, leaf: Boolean,
        row: Int, hasFocus: Boolean): Component? {


        super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus)

        if(IconCashe != null){
            icon = IconCashe!![value.toString()]
            if (icon!=null){
                setIcon(icon)
            }
        }

        return this
    }

    fun setCashe(_IconCashe : HashMap<String, ImageIcon>){
        IconCashe = _IconCashe
    }

}