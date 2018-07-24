import io.javalin.Javalin;

public class Sever {

	public Sever() {
		Javalin j = Javalin.create().start(80);
		
		j.get("/", ctx -> {
			ctx.html("XD");
		});
		
		j.get("/boi", new MainHandler());
		
	}
	
	public static void main(String[] args) {
		new Sever();
	}
}
