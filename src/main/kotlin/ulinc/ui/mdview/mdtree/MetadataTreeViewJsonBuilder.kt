package ulinc.ui.mdview.mdtree

import com.google.gson.Gson
import com.intellij.ui.treeStructure.Tree
import ulinc.ui.mdview.command.CommandProvider
import ulinc.ui.mdview.command.CommandProviderImpl
import ulinc.ui.mdview.command.MdCommand
import java.awt.event.MouseListener
import java.nio.charset.StandardCharsets
import java.util.*
import javax.swing.ImageIcon
import javax.swing.tree.DefaultMutableTreeNode
import javax.swing.tree.DefaultTreeModel
import kotlin.collections.HashMap

class MetadataTreeViewJsonBuilder : MetadataTreeViewBuilder{

    override fun build(tree: Tree?){

        if (tree != null) {

            var commandProvider  = CommandProviderImpl()
            var IconCashe = HashMap<String, ImageIcon>()

            tree.model = generateModel(IconCashe,commandProvider)

            var MdTCR: MdTreeCellRenderer = MdTreeCellRenderer()
            MdTCR.setCashe(IconCashe)
            tree.setCellRenderer(MdTCR)

            var Listener:MdTreeMouseListener = MdTreeMouseListener()
            Listener.setCommandProvider(commandProvider)
            tree.addMouseListener(Listener)
        }

    }

    fun generateModel(IconCashe : HashMap<String, ImageIcon>, commandP: CommandProvider):DefaultTreeModel{

        val root = DefaultMutableTreeNode("Metadata")
        val o = getJsonObjectFromResource();

       val  templates = o.templates;
        for (index in templates.indices) {
            val currentTemplate = templates[index]
            val metadata = currentTemplate.metadata

            for (index1 in metadata.indices) {
                val metadataMember = metadata[index1]

                val mdId: String? = metadataMember.id
                val header: String? = metadataMember.header
                val parent: String? = metadataMember.parent
                val item = DefaultMutableTreeNode(mdId)
                if (parent.equals(""))
                    root.add(item)
                else {
                    val fNode:DefaultMutableTreeNode? = find(root, parent)
                    if (fNode != null)
                        fNode.add(item)
                }


                val  actions = metadataMember.actions;
                for (index3 in actions.indices) {
                    val action:MetadataMemberAction = actions[index3]
                    if (mdId != null) {
                        commandP.addCommand(mdId,MdCommand(action.id!!,action.command!!))
                    }
                }

            }
        }
        val treeModel = DefaultTreeModel(root)

        val  imageList = o.classImages;
        for (index in imageList.indices) {
            val currentImage:ClassImage = imageList[index]
            var rsr = javaClass.classLoader.getResource(currentImage.image)
            if(rsr!=null)
                IconCashe.put(currentImage.id.toString(),ImageIcon(rsr))
        }

        return treeModel
    }

    private fun getJsonObjectFromResource() : mdFile{

        val strr = javaClass.classLoader.getResourceAsStream("mdtemplates/metadata.json")
        val contents  = String(strr.readAllBytes(), StandardCharsets.UTF_8)
        var gson = Gson()
        var post = gson.fromJson(contents, mdFile::class.java)

        return post

    }

    private fun find(root: DefaultMutableTreeNode, s: String?): DefaultMutableTreeNode? {
        val e = root.depthFirstEnumeration()
        while (e.hasMoreElements()) {
            val node = e.nextElement() as DefaultMutableTreeNode
            if (node.toString().equals(s, ignoreCase = true)) {
                return node
            }
        }
        return null
    }
}

class mdFile {
    var templates: List<mdTemplates> = LinkedList()
    var classImages: List<ClassImage> = LinkedList()
}

class mdTemplates {
    var metadata: List<MetadataMember> = LinkedList<MetadataMember>()
}

class ClassImage {

    val id: String? = null
    val image: String? = null
}


class MetadataMember {
    var id: String? = null
    var header: String? = null
    var parent: String? = null
    var actions: List<MetadataMemberAction> = LinkedList<MetadataMemberAction>()
}

class MetadataMemberAction {

    val id: String? = null
    val command: String? = null

}