import io.javalin.Context;
import io.javalin.Handler;

public class MainHandler implements Handler {
	
	@Override
	public void handle(Context context) throws Exception {
		context.result("BOI");
	}
	
}
