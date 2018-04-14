import org.lwjgl.Version
import org.lwjgl.glfw.Callbacks.glfwFreeCallbacks
import org.lwjgl.glfw.GLFW.*
import org.lwjgl.glfw.GLFWErrorCallback
import org.lwjgl.opengl.GL
import org.lwjgl.opengl.GL11.*
import org.lwjgl.system.MemoryStack.stackPush
import org.lwjgl.system.MemoryUtil.NULL



class App {
	
	private var window : Long = NULL
	
	private var WIDTH = 0.0
	private var HEIGHT = 0.0
	private var ASPECT = 0.0
	private var VIEWPORT_WIDTH = 3000.0
	private var VIEWPORT_HEIGHT = 3000.0
	
	private var player = Player()
	
	var x = 0f
	
	fun run() {
		println("Hello LWJGL " + Version.getVersion() + "!")
		
		init()
		loop()
		
		glfwFreeCallbacks(window)
		glfwDestroyWindow(window)
		
		glfwTerminate()
		glfwSetErrorCallback(null)?.free()
	}
	
	private fun init() {
		
		GLFWErrorCallback.createPrint(System.err).set()
		
		if ( !glfwInit() )
			throw IllegalStateException("Unable to initialize GLFW")
		
		glfwDefaultWindowHints()
		glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE)
		glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE)
		
		window = glfwCreateWindow(800, 600, "Hello Kotlin LWJGL !", NULL, NULL)
		
		if ( window == NULL ) {
			throw RuntimeException("Failed to create the GLFW window")
		}
		
		// keybinds
		glfwSetKeyCallback(window) { window, key, _, action, _ ->
			
			if (key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE)
				glfwSetWindowShouldClose(window, true) // We will detect this in the rendering loop

			if (key == GLFW_KEY_UP && action == GLFW_PRESS) {
				glClearColor(0f, 0f, 1f, 0f)
			}
		
		}
		
		glfwMakeContextCurrent(window)
		glfwSwapInterval(1)
		
		glfwShowWindow(window)
		
		centerWindow(window)

	}
	
	
	fun loop() {
		
		GL.createCapabilities()

		glMatrixMode(GL_PROJECTION)
		glLoadIdentity()
		
		
		with(stackPush()) {
			var pw = mallocInt(1)
			var ph = mallocInt(1)

			glfwGetWindowSize(window, pw, ph)
			
			var w = pw.get(0).toDouble()
			var h = ph.get(0).toDouble()
			
			updateViewport(w, h)
		}
		
		
		glfwSetWindowSizeCallback(window, { win, w, h ->
			updateViewport(w.toDouble(), h.toDouble())
		})
		
		// MAINLOOP
		while ( !glfwWindowShouldClose(window) ) {

			glClear(GL_COLOR_BUFFER_BIT or GL_DEPTH_BUFFER_BIT)

			glColor3f(0.5f,0.5f,1.0f)
			
			glPushMatrix()
			glTranslatef(20f, 20f, 0f)
			glRecti(-50, -50, 50, 50)
			glPopMatrix()

			player.draw()
			
			glfwSwapBuffers(window)
			glfwPollEvents()
		}
		
	}
	
	fun updateViewport(w : Double, h : Double) {
		WIDTH = w
		HEIGHT = h
		ASPECT = w/h

		var aspw = 0.5 * VIEWPORT_WIDTH * ASPECT
		var hh = 0.5 * VIEWPORT_HEIGHT
		
		glLoadIdentity()
		glOrtho(-aspw, aspw, -hh, hh, -1.0, 1.0)
		glViewport(0, 0, w.toInt(), h.toInt())
	}
	
	fun centerWindow(w : Long) {
		
		with (stackPush()) {
			
			var pWidth = mallocInt(1) // int *
			var pHeight = mallocInt(1)
			
			glfwGetWindowSize(w, pWidth, pHeight)
			
			var vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor())
			
			glfwSetWindowPos(
					w,
					(vidmode!!.width() - pWidth.get(0)) / 2,
					(vidmode!!.height() - pHeight.get(0)) / 2
			)
			
			// auto pop frame on exit
		}
		
	}
	
}

fun main(args: Array<String>) {
	var app = App()
	app.run()
}
		
		