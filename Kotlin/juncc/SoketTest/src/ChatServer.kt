
class ChatServer(port: Int) : Server(port) {
	
	var clientNames = mutableMapOf<ClientHandler, String>()
	
	init {
		
		onConnect = { client ->
			println("<${client.address}> <connected> -> clients = ${clients}")
		}
		
		onDisconnect = { client ->
			println("<${client.address}> <disconnected> -> clients = ${clients}")
			clientNames.remove(client)
		}
		
		// use anonym fun instead of lambda because of return ability
		onMessage = fun(msg : String, client: ClientHandler) {
			
			val nc = "/nickname "
			var clientInfoMsg = "<${clientNames[client] ?: client.address}> $msg"


			println(clientInfoMsg)
			client.broadcast(clientInfoMsg)
			
			if (msg.startsWith(nc)) msg.substring(nc.length).run {
				
				val nickmsg = "[Server] Changing nickname of ${client.address} to $this"
				
				println(nickmsg)
				broadcast(nickmsg)
				
				clientNames[client] = this
				
			}
			
		}
		
	}
	
}