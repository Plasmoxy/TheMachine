import com.github.messenger4j.Messenger
import com.github.messenger4j.exception.MessengerApiException
import com.github.messenger4j.exception.MessengerIOException
import com.github.messenger4j.send.MessagePayload
import com.github.messenger4j.send.MessagingType
import com.github.messenger4j.send.message.TextMessage
import io.javalin.Javalin
import java.util.*


val messenger = Messenger.create("", "", "") // add your own


fun main(args: Array<String>) {

	
	val server = Javalin.create()
			.port(10123)
			.start()
	
	server.get("/") {
		it.result("XD")
	}
	
	server.get("/webhook") {
		if (it.queryParam("hub.verify_token") == "shardverify") {
			it.result(it.queryParam("hub.challenge") ?: "")
		}
	}
	
	server.post("/webhook") {
		echo(it.body())
	}
	
	
}

fun echo(payload: String) {
	
	messenger.onReceiveEvents(payload, Optional.empty()) { event ->
		val senderId = event.senderId()
		if (event.isTextMessageEvent) {
			val text = event.asTextMessageEvent().text()
			
			val textMessage = TextMessage.create(text)
			val messagePayload = MessagePayload.create(senderId,
					MessagingType.RESPONSE, textMessage)
			
			try {
				messenger.send(messagePayload)
			} catch (e: MessengerApiException) {
				// Oops, something went wrong
			} catch (e: MessengerIOException) {
			}
			
		}
	}
}