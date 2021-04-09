package servent.handler;

import java.math.BigInteger;

import cli.command.SumCommand;
import servent.message.Message;

public class SumResultHandler implements MessageHandler {

	private final Message clientMessage;
	
	public SumResultHandler(Message clientMessage) {
		this.clientMessage = clientMessage;
	}
	
	@Override
	public void run() {
		try {
			BigInteger friendResult = new BigInteger(clientMessage.getMessageText());
			
			SumCommand.helpResult.set(friendResult);
		} catch (NumberFormatException e) {
			System.err.println("Bad message text: " + clientMessage.toString());
		}

	}

}
