package automata.envisalink.testing;

import java.text.ParseException;
import java.util.Date;

import com.concept.utils.DateTimeUtil;
import com.github.kmbulebu.dsc.it100.ConfigurationBuilder;
import com.github.kmbulebu.dsc.it100.IT100;
import com.github.kmbulebu.dsc.it100.Labels;
import com.github.kmbulebu.dsc.it100.commands.read.ReadCommand;
import com.github.kmbulebu.dsc.it100.commands.read.ZoneAlarmCommand;
import com.github.kmbulebu.dsc.it100.commands.read.ZoneAlarmRestoreCommand;
import com.github.kmbulebu.dsc.it100.commands.read.ZoneOpenCommand;
import com.github.kmbulebu.dsc.it100.commands.read.ZoneRestoredCommand;
import com.github.kmbulebu.dsc.it100.commands.write.WriteCommand;

import rx.Observable;
import rx.functions.Action1;

public class ExampleApp {

	public static void main(String[] args) {
		
		// Configure for Envisalink (defaults)
		final IT100 it100 = new IT100(new ConfigurationBuilder()
				.withRemoteSocket("envisalink", 4025)
				.withStatusPolling(15)
				.withEnvisalinkPassword("user").build());
		
		try {	
			// Start communicating with IT-100.
			it100.connect();
			
			final Observable<ReadCommand> readObservable = it100.getReadObservable();
			
			// Labels gives us friendly names to our zones.
			final Labels labels = new Labels(readObservable, it100.getWriteObservable());
			
			it100.getWriteObservable().subscribe(new Action1<WriteCommand>() {

				@Override
				public void call(WriteCommand command) {
					System.out.println("Write: " + System.currentTimeMillis() + " " + command.getCommandCode() + " " + command.getData());
				}
				
			});
			
			// Subscribe to all
			readObservable.subscribe(new Action1<ReadCommand>() {

				@Override
				public void call(ReadCommand command) {
					// TODO Auto-generated method stub
					System.out.println("Read: " + System.currentTimeMillis() + " " + command.getCommandCode() + " " + command.toString());
				}
				
			});
			
			// Subscribe to Zone opening events to print zone labels (Only on IT-100)
			readObservable.ofType(ZoneOpenCommand.class).subscribe(new Action1<ZoneOpenCommand>() {

				@Override
				public void call(ZoneOpenCommand t1) {
					// Print time and name of zone that opened.
					try {
						System.out.println("ZoneOpen: "+DateTimeUtil.toString(new Date(), DateTimeUtil.yyyy_MM_dd_HH_mm_ss_SSS) + " " + t1.getZone() + " opened.");
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			});
			
			// Subscribe to Zone closing events to print zone labels (Only on IT-100)
			readObservable.ofType(ZoneRestoredCommand.class).subscribe(new Action1<ZoneRestoredCommand>() {

				@Override
				public void call(ZoneRestoredCommand t1) {
					try {
						System.out.println("ZoneOpen: "+DateTimeUtil.toString(new Date(), DateTimeUtil.yyyy_MM_dd_HH_mm_ss_SSS) + " " + t1.getZone() + " closed.");
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			});
			
			readObservable.ofType(ZoneAlarmCommand.class).subscribe(new Action1<ZoneAlarmCommand>() {

				@Override
				public void call(ZoneAlarmCommand t1) {
					try {
						System.out.println("ZoneAlarm: "+DateTimeUtil.toString(new Date(), DateTimeUtil.yyyy_MM_dd_HH_mm_ss_SSS) + " " + t1.getZone() + " closed.");
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			});
			
			readObservable.ofType(ZoneAlarmRestoreCommand.class).subscribe(new Action1<ZoneAlarmRestoreCommand>() {

				@Override
				public void call(ZoneAlarmRestoreCommand t1) {
					try {
						System.out.println("ZoneAlarm: "+DateTimeUtil.toString(new Date(), DateTimeUtil.yyyy_MM_dd_HH_mm_ss_SSS) + " " + t1.getZone() +" - "+labels.getZoneLabel(t1.getZone()) + " closed.");
						
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			});
			
			//Thread.sleep(60000);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
