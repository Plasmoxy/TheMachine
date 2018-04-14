package io.github.plasmoxy.libgdx.helloworld

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.SpriteBatch

class Player(var batch: SpriteBatch) {
	
	var x = 0f
	var y = 0f

	var sprite = Sprite(Texture("badlogic.jpg"))
	
	fun render() {
		batch.begin()
		sprite.setPosition(x, y)
		sprite.draw(batch)
		batch.end()
	}
	
}