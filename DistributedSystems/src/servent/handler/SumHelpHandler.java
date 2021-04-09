package servent.handler;

import java.math.BigInteger;

import servent.message.Message;
import servent.message.MessageType;
import servent.message.MessageUtil;
import servent.message.SumResultMessage;

public class SumHelpHandler implements MessageHandler {

	private final Message clientMessage;
	
	public SumHelpHandler(Message clientMessage) {
		this.clientMessage = clientMessage;
	}
	
	@Override
	public void run() {
		if (clientMessage.getMessageType() == MessageType.SUM_HELP_MESSAGE) {
			BigInteger mid = new BigInteger(clientMessage.getMessageText());
			BigInteger sum = BigInteger.ZERO;
			
			for (BigInteger val = BigInteger.ONE; val.compareTo(mid) == -1; val = val.add(BigInteger.ONE)) {
				sum = sum.add(val);
			}
			
			MessageUtil.sendMessage(
				new SumResultMessage(
					clientMessage.getReceiverInfo(),
					clientMessage.getOriginalSenderInfo(),
					sum));
		}

	}

}
