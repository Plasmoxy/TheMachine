import processing.core.PApplet;

public class HScrollbar {
	int swidth, sheight;    // width and height of bar
	float xpos, ypos;       // x and y position of bar
	float spos, newspos;    // x position of slider
	float sposMin, sposMax; // max and min values of slider
	int loose;              // how loose/heavy
	boolean over;           // is the mouse over the slider?
	boolean locked;
	float ratio;
	
	PApplet p;

	HScrollbar(PApplet applet, float xp, float yp, int sw, int sh, int l) {
		p = applet;
		swidth = sw;
		sheight = sh;
		int widthtoheight = sw - sh;
		ratio = (float)sw / (float)widthtoheight;
		xpos = xp;
		ypos = yp-sheight/2;
		spos = xpos + swidth/2 - sheight/2;
		newspos = spos;
		sposMin = xpos;
		sposMax = xpos + swidth - sheight;
		loose = l;
	}

	void update() {
		if (overEvent()) {
			over = true;
		} else {
			over = false;
		}
		if (p.mousePressed && over) {
			locked = true;
		}
		if (!p.mousePressed) {
			locked = false;
		}
		if (locked) {
			newspos = constrain(p.mouseX-sheight/2, sposMin, sposMax);
		}
		if (p.abs(newspos - spos) > 1) {
			spos = spos + (newspos-spos)/loose;
		}
	}

	float constrain(float val, float minv, float maxv) {
		return p.min(p.max(val, minv), maxv);
	}

	boolean overEvent() {
		if (p.mouseX > xpos && p.mouseX < xpos+swidth &&
				p.mouseY > ypos && p.mouseY < ypos+sheight) {
			return true;
		} else {
			return false;
		}
	}

	void display() {
		p.pushStyle();
		p.noStroke();
		p.fill(204);
		p.rect(xpos, ypos, swidth, sheight);
		if (over || locked) {
			p.fill(0, 0, 0);
		} else {
			p.fill(102, 102, 102);
		}
		p.rect(spos, ypos, sheight, sheight);
		p.popStyle();
	}

	float getPos() {
		// Convert spos to be values between
		// 0 and the total width of the scrollbar
		return spos * ratio;
	}
	
	float getVal(float min, float max) {
		return p.map(spos, sposMin, sposMax, min, max);
	}
}