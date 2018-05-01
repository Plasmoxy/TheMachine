
class Walker {
  float x, y;
  
  Walker() {
    x = width/2;
    y = height/2;
  }
  
  void draw() {
    stroke(255);
    point(x,y);
  }
  
  void step() {
    float dx = random(-10, 10);
    float dy = random(-10, 10);
    x += dx;
    y += dy;
  }
}

Walker w;

void setup() {
  size(400, 400);
  background(0);
  w = new Walker();
}

void draw() {
  w.draw();
  w.step();
}