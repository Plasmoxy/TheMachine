import org.lwjgl.Version
import org.lwjgl.glfw.Callbacks.glfwFreeCallbacks
import org.lwjgl.glfw.GLFW.*
import org.lwjgl.glfw.GLFWErrorCallback
import org.lwjgl.opengl.GL
import org.lwjgl.opengl.GL11.*
import org.lwjgl.system.MemoryStack.stackPush
import org.lwjgl.system.MemoryUtil.NULL
import org.lwjgl.opengl.GL11.glOrtho

class Player {
	
	var x = 0f
	var y = 0f
	
	fun draw() {
		glColor3d(1.0, 0.0, 1.0)
		glPushMatrix()
		glTranslatef(x, y, 0f)
		
		glBegin(GL_POLYGON_MODE)
		
		glVertex3f(0f, 0f, 0f)
		glVertex3f(100f, 0f, 0f)
		glVertex3f(100f, 0f, 100f)
		glVertex3f(0f, 0f, 100f)
		
		
		glEnd()
		
		glPopMatrix()
	}
	
}