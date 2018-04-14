package io.github.plasmoxy.libgdx.helloworld

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.glutils.ShapeRenderer

class Game : ApplicationAdapter() {
	
	lateinit var batch : SpriteBatch
	lateinit var plr : Player
	lateinit var plr2 : Player

	override fun create() {
		batch = SpriteBatch()
		plr = Player(batch)
	}

	override fun render() {
		Gdx.gl.glClearColor(0f, 0f, 0f, 1f)
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
		
		plr.render()
		plr.x += 0.5f
	}

	override fun dispose() {
		batch.dispose()
	}
}
