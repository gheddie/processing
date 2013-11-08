package de.gravitex.processing.testing;

import org.apache.log4j.Logger;

import de.gravitex.processing.core.ProcessContainer;
import de.gravitex.processing.core.ProcessItemFactory;
import de.gravitex.processing.core.ProcessItemType;
import de.gravitex.processing.core.exception.ProcessException;
import de.gravitex.processing.core.item.ProcessItem;
import de.gravitex.processing.testing.action.ActionA2;
import de.gravitex.processing.testing.appliance.decision.DecisionInterestHigh;
import de.gravitex.processing.testing.appliance.decision.DecisionInterestNone;
import de.gravitex.processing.testing.appliance.decision.DecisionInterestPotentially;
import de.gravitex.processing.testing.decision.DecisionForA1;
import de.gravitex.processing.testing.decision.DecisionForA2;
import de.gravitex.processing.testing.decision.DecisionForA3;

public class ProcessDefinitionProvider {
	
	private static Logger			logger					= Logger.getLogger(ProcessDefinitionProvider.class);

	public static ProcessContainer getT4() {
		
		ProcessContainer processContainer = new ProcessContainer();
		
		try {
			ProcessItem s1 = ProcessItemFactory.getProcessElement(ProcessItemType.START, "s1", null);
			processContainer.addElement(s1);
			
			ProcessItem a1 = ProcessItemFactory.getProcessElement(ProcessItemType.ACTION, "a1", null);
			processContainer.addElement(a1);
			
			ProcessItem f1 = ProcessItemFactory.getProcessElement(ProcessItemType.FORK, "f1", null);
			processContainer.addElement(f1);
			
			ProcessItem a2 = ProcessItemFactory.getProcessElement(ProcessItemType.ACTION, "a2", null);
			processContainer.addElement(a2);
			
			ProcessItem a3 = ProcessItemFactory.getProcessElement(ProcessItemType.ACTION, "a3", null);
			processContainer.addElement(a3);
			
			ProcessItem e1 = ProcessItemFactory.getProcessElement(ProcessItemType.END, "e1", null);
			processContainer.addElement(e1);
			
			ProcessItem e2 = ProcessItemFactory.getProcessElement(ProcessItemType.END, "e2", null);
			processContainer.addElement(e2);
			
			//parent relations
			processContainer.relateParent("a1", "s1");
			processContainer.relateParent("f1", "a1");
			
			processContainer.relateParent("a2", "f1");
			processContainer.relateParent("a3", "f1");
			
			processContainer.relateParent("e1", "a2");
			processContainer.relateParent("e2", "a3");
			
		} catch (ProcessException e) {
			e.printStackTrace();
		}
		
		return processContainer;
	}

	public static ProcessContainer getT5() {
		
		ProcessContainer processContainer = new ProcessContainer();
		
		try {
			ProcessItem s1 = ProcessItemFactory.getProcessElement(ProcessItemType.START, "s1", null);
			processContainer.addElement(s1);
			
			ProcessItem f1 = ProcessItemFactory.getProcessElement(ProcessItemType.FORK, "f1", null);
			processContainer.addElement(f1);
			
			ProcessItem a1 = ProcessItemFactory.getProcessElement(ProcessItemType.ACTION, "a1", null);
			processContainer.addElement(a1);
			
			ProcessItem a2 = ProcessItemFactory.getProcessElement(ProcessItemType.ACTION, "a2", null);
			processContainer.addElement(a2);
			
			ProcessItem a3 = ProcessItemFactory.getProcessElement(ProcessItemType.ACTION, "a3", null);
			processContainer.addElement(a3);
			
			ProcessItem j1 = ProcessItemFactory.getProcessElement(ProcessItemType.JOIN, "j1", null);
			processContainer.addElement(j1);
			
			ProcessItem e1 = ProcessItemFactory.getProcessElement(ProcessItemType.END, "e1", null);
			processContainer.addElement(e1);
			
			//parent relations
			processContainer.relateParent("f1", "s1");
			
			processContainer.relateParent("a1", "f1");
			processContainer.relateParent("a2", "f1");
			processContainer.relateParent("a3", "f1");
			
			processContainer.relateParent("j1", "a1");
			processContainer.relateParent("j1", "a2");
			processContainer.relateParent("j1", "a3");
			
			processContainer.relateParent("e1", "j1");
			
			//conditions
			processContainer.addCondition("f1", "a1", DecisionForA1.class);
			processContainer.addCondition("f1", "a2", DecisionForA2.class);
			processContainer.addCondition("f1", "a3", DecisionForA3.class);
			
			//action
			processContainer.addAction("a2", ActionA2.class);
			
		} catch (ProcessException e) {
			e.printStackTrace();
		}
		
		return processContainer;
	}

	public static ProcessContainer getT6() {
		
		ProcessContainer processContainer = new ProcessContainer();
		
		try {
			ProcessItem s1 = ProcessItemFactory.getProcessElement(ProcessItemType.START, "s1", null);
			processContainer.addElement(s1);
			
			ProcessItem j1 = ProcessItemFactory.getProcessElement(ProcessItemType.JOIN, "j1", null);
			processContainer.addElement(j1);
			ProcessItem a1 = ProcessItemFactory.getProcessElement(ProcessItemType.ACTION, "a1", null);
			processContainer.addElement(a1);
			ProcessItem f1 = ProcessItemFactory.getProcessElement(ProcessItemType.FORK, "f1", null);
			processContainer.addElement(f1);
			ProcessItem a2 = ProcessItemFactory.getProcessElement(ProcessItemType.ACTION, "a2", null);
			processContainer.addElement(a2);
			ProcessItem f2 = ProcessItemFactory.getProcessElement(ProcessItemType.FORK, "f2", null);
			processContainer.addElement(f2);
			
			ProcessItem e1 = ProcessItemFactory.getProcessElement(ProcessItemType.END, "e1", null);
			processContainer.addElement(e1);
			ProcessItem e2 = ProcessItemFactory.getProcessElement(ProcessItemType.END, "e2", null);
			processContainer.addElement(e2);
			
			//relations
			processContainer.relateParent("j1", "s1");
			processContainer.relateParent("a1", "j1");
			processContainer.relateParent("f1", "a1");
			processContainer.relateParent("f2", "f1");
			processContainer.relateParent("e1", "f2");
			processContainer.relateParent("e2", "f2");
			processContainer.relateParent("a2", "f1");
			processContainer.relateParent("j1", "a2");
			
		} catch (ProcessException e) {
			e.printStackTrace();
		}
		
		return processContainer;
	}

	public static ProcessContainer getT7() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static ProcessContainer getBewerbung() {
		
		ProcessContainer processContainer = new ProcessContainer();
		
		try {
			ProcessItem start1 = ProcessItemFactory.getProcessElement(ProcessItemType.START, "start", null);
			processContainer.addElement(start1);
			
			ProcessItem gatherData = ProcessItemFactory.getProcessElement(ProcessItemType.TASK, "gatherData", null);
			processContainer.addElement(gatherData);
			
			ProcessItem acknowledgeData = ProcessItemFactory.getProcessElement(ProcessItemType.ACTION, "acknowledgeData", null);
			processContainer.addElement(acknowledgeData);
			
			ProcessItem sightData = ProcessItemFactory.getProcessElement(ProcessItemType.TASK, "sightData", null);
			processContainer.addElement(sightData);
			
			ProcessItem fork1 = ProcessItemFactory.getProcessElement(ProcessItemType.FORK, "fork1", null);
			processContainer.addElement(fork1);
			
			ProcessItem join1 = ProcessItemFactory.getProcessElement(ProcessItemType.FORK, "join1", null);
			processContainer.addElement(join1);
			
			ProcessItem deleteData1 = ProcessItemFactory.getProcessElement(ProcessItemType.ACTION, "deleteData1", null);
			processContainer.addElement(deleteData1);
			
			ProcessItem revoke = ProcessItemFactory.getProcessElement(ProcessItemType.ACTION, "revoke", null);
			processContainer.addElement(revoke);
			
			ProcessItem end1 = ProcessItemFactory.getProcessElement(ProcessItemType.END, "end1", null);
			processContainer.addElement(end1);
			
			ProcessItem join2 = ProcessItemFactory.getProcessElement(ProcessItemType.JOIN, "join2", null);
			processContainer.addElement(join2);
			
			ProcessItem appoint = ProcessItemFactory.getProcessElement(ProcessItemType.TASK, "appoint", null);
			processContainer.addElement(appoint);
			
			ProcessItem fork2 = ProcessItemFactory.getProcessElement(ProcessItemType.FORK, "fork2", null);
			processContainer.addElement(fork2);
			
			ProcessItem writeHRDB = ProcessItemFactory.getProcessElement(ProcessItemType.TASK, "writeHRDB", null);
			processContainer.addElement(writeHRDB);
			
			ProcessItem infoMail = ProcessItemFactory.getProcessElement(ProcessItemType.ACTION, "infoMail", null);
			processContainer.addElement(infoMail);
			
			ProcessItem end2 = ProcessItemFactory.getProcessElement(ProcessItemType.END, "end2", null);
			processContainer.addElement(end2);
			
			ProcessItem askStoreData = ProcessItemFactory.getProcessElement(ProcessItemType.ACTION, "askStoreData", null);
			processContainer.addElement(askStoreData);
			
			ProcessItem storeAnswer = ProcessItemFactory.getProcessElement(ProcessItemType.TASK, "storeAnswer", null);
			processContainer.addElement(storeAnswer);
			
			ProcessItem fork3 = ProcessItemFactory.getProcessElement(ProcessItemType.FORK, "fork3", null);
			processContainer.addElement(fork3);
			
			ProcessItem ackNoStorage = ProcessItemFactory.getProcessElement(ProcessItemType.ACTION, "ackNoStorage", null);
			processContainer.addElement(ackNoStorage);
			
			ProcessItem deleteData2 = ProcessItemFactory.getProcessElement(ProcessItemType.ACTION, "deleteData2", null);
			processContainer.addElement(deleteData2);
			
			ProcessItem end3 = ProcessItemFactory.getProcessElement(ProcessItemType.END, "end3", null);
			processContainer.addElement(end3);
			
			ProcessItem fork4 = ProcessItemFactory.getProcessElement(ProcessItemType.FORK, "fork4", null);
			processContainer.addElement(fork4);
			
			ProcessItem wait1 = ProcessItemFactory.getProcessElement(ProcessItemType.WAIT, "wait1", null);
			processContainer.addElement(wait1);
			
			ProcessItem confStoreData = ProcessItemFactory.getProcessElement(ProcessItemType.ACTION, "confStoreData", null);
			processContainer.addElement(confStoreData);
			
			ProcessItem storeDataPot = ProcessItemFactory.getProcessElement(ProcessItemType.TASK, "storeDataPot", null);
			processContainer.addElement(storeDataPot);
			
			ProcessItem end4 = ProcessItemFactory.getProcessElement(ProcessItemType.END, "end4", null);
			processContainer.addElement(end4);
			
			//relations
			processContainer.relateParent("gatherData", "start");
			processContainer.relateParent("acknowledgeData", "gatherData");
			processContainer.relateParent("sightData", "acknowledgeData");
			processContainer.relateParent("fork1", "sightData");
			processContainer.relateParent("join1", "fork1");
			processContainer.relateParent("deleteData1", "join1");
			processContainer.relateParent("revoke", "deleteData1");
			processContainer.relateParent("end1", "revoke");
			
			processContainer.relateParent("join2", "fork1");
			processContainer.relateParent("appoint", "join2");
			processContainer.relateParent("fork2", "appoint");
			
			processContainer.relateParent("writeHRDB", "fork2");
			processContainer.relateParent("infoMail", "writeHRDB");
			processContainer.relateParent("end2", "infoMail");
			
			processContainer.relateParent("askStoreData", "fork1");
			processContainer.relateParent("storeAnswer", "askStoreData");
			processContainer.relateParent("fork3", "storeAnswer");
			processContainer.relateParent("ackNoStorage", "fork3");
			processContainer.relateParent("deleteData2", "ackNoStorage");
			processContainer.relateParent("end3", "deleteData2");
			
			processContainer.relateParent("fork4", "fork2");
			processContainer.relateParent("wait1", "fork4");
			
			processContainer.relateParent("join1", "fork4");
			processContainer.relateParent("join2", "wait1");
			
			processContainer.relateParent("confStoreData", "fork3");
			processContainer.relateParent("storeDataPot", "confStoreData");
			processContainer.relateParent("end4", "storeDataPot");
			
			//conditions
			processContainer.addCondition("fork1", "join1", DecisionInterestNone.class);
			processContainer.addCondition("fork1", "askStoreData", DecisionInterestPotentially.class);
			processContainer.addCondition("fork1", "join2", DecisionInterestHigh.class);
			
			return processContainer;
			
		} catch (ProcessException e) {
			logger.error(e);
			return null;
		}
	}

	public static ProcessContainer getHaftungsSumme() {
		
		ProcessContainer processContainer = new ProcessContainer();
				
		try {
			ProcessItem start1 = ProcessItemFactory.getProcessElement(ProcessItemType.START, "start1", null);
			processContainer.addElement(start1);
			
			ProcessItem acPrepareShare = ProcessItemFactory.getProcessElement(ProcessItemType.ACTION, "acPrepareShare", "Dokumenten-Share vorbereiten");
			processContainer.addElement(acPrepareShare);
			
			ProcessItem taCalculateDebitSum = ProcessItemFactory.getProcessElement(ProcessItemType.TASK, "taCalculateDebitSum", "Dokumenten-Share vorbereiten");
			processContainer.addElement(acPrepareShare);
			
			//relations
			processContainer.relateParent("acPrepareShare", "start1");
			
			return processContainer;
			
		} catch (ProcessException e) {
			logger.error(e);
			return null;
		}			
	}
}
