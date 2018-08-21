import java.io.File

fun File.rename(name: String) = renameTo(File(absoluteFile.parent, name))

fun list(root: File, level: Int) {

    // captures level through closure
    fun padding() = (0 until level).forEach { print("  ") }

    if (!root.isDirectory) {
        println("This isn't a directory !")
        return
    }

    val all = root.listFiles()
    
    // first directories, recursive
    all.filter { it.isDirectory }.forEach {
        padding()
        println("[${it.name}]")
        list(it, level + 1)
    }
    
    // then files
    all.filter { !it.isDirectory }.forEach {
        padding()
        println(it.name)
    }
}

if (args.size > 0) {
    list(File(args[0]), 0)
} else {
    list(File("folder"), 0)
}
