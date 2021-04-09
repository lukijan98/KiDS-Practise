package cli.command;

import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicReference;

import app.AppConfig;
import servent.message.MessageUtil;
import servent.message.SumHelpMessage;

public class SumCommand implements CLICommand {

	public static AtomicReference<BigInteger> helpResult = new AtomicReference<BigInteger>(null);
	
	@Override
	public String commandName() {
		return "sum";
	}

	@Override
	public void execute(String args) {
		try {
			BigInteger number = new BigInteger(args);
			
			BigInteger mid = number.divide(new BigInteger("2"));
			
			int myId = AppConfig.myServentInfo.getId();
			
			int myFriendId = (myId % 2 == 0) ? myId + 1 : myId - 1;
			
			MessageUtil.sendMessage(
				new SumHelpMessage(
					AppConfig.myServentInfo, AppConfig.getInfoById(myFriendId), mid));
			
			BigInteger mySum = BigInteger.ZERO;
			
			for (BigInteger val = mid; val.compareTo(number) == -1; val = val.add(BigInteger.ONE)) {
				mySum = mySum.add(val);
			}
			
			while (helpResult.get() == null) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			System.out.println("Sum result: " + mySum.add(helpResult.get()).toString());
			
			helpResult.set(null);
		} catch (NumberFormatException e) {
			System.err.println("Errong in starting sum. Bad argument: " + args);
		}

	}

}
