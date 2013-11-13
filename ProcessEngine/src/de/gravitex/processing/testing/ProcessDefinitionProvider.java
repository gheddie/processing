package de.gravitex.processing.testing;

import org.apache.log4j.Logger;

import de.gravitex.processing.core.ProcessEngine;
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
import de.gravitex.processing.testing.taskresolver.ResolveT1;
import de.gravitex.processing.testing.taskresolver.ResolveT2;
import de.gravitex.processing.testing.taskresolver.ResolveT3;

public class ProcessDefinitionProvider {
	
	private static Logger			logger					= Logger.getLogger(ProcessDefinitionProvider.class);

	public static ProcessEngine getT4() {
		
		ProcessEngine processContainer = new ProcessEngine();
		
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

	public static ProcessEngine getT5() {
		
		ProcessEngine processContainer = new ProcessEngine();
		
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

	public static ProcessEngine getT6() {
		
		ProcessEngine processContainer = new ProcessEngine();
		
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

	public static ProcessEngine getT7() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static ProcessEngine getBewerbung() {
		
		ProcessEngine processContainer = new ProcessEngine();
		
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

	public static ProcessEngine getHaftungsSumme() {
		
		ProcessEngine processContainer = new ProcessEngine();
				
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
	
	public static ProcessEngine getReferenceProcess() {
		
		ProcessEngine processContainer = new ProcessEngine();
		
		try {
			ProcessItem start = ProcessItemFactory.getProcessElement(ProcessItemType.START, "start", null);
			processContainer.addElement(start);
			ProcessItem itemA = ProcessItemFactory.getProcessElement(ProcessItemType.ACTION, "itemA", null);
			processContainer.addElement(itemA);
			ProcessItem itemB = ProcessItemFactory.getProcessElement(ProcessItemType.TASK, "itemB", null);
			processContainer.addElement(itemB);
			ProcessItem fork1 = ProcessItemFactory.getProcessElement(ProcessItemType.FORK, "fork1", null);
			processContainer.addElement(fork1);
			ProcessItem timer1 = ProcessItemFactory.getProcessElement(ProcessItemType.WAIT, "timer1", null);
			processContainer.addElement(timer1);
			ProcessItem itemC = ProcessItemFactory.getProcessElement(ProcessItemType.ACTION, "itemC", null);
			processContainer.addElement(itemC);
			ProcessItem itemD = ProcessItemFactory.getProcessElement(ProcessItemType.TASK, "itemD", null);
			processContainer.addElement(itemD);
			ProcessItem itemE = ProcessItemFactory.getProcessElement(ProcessItemType.TASK, "itemE", null);
			processContainer.addElement(itemE);
			ProcessItem fork2 = ProcessItemFactory.getProcessElement(ProcessItemType.START, "fork2", null);
			processContainer.addElement(fork2);
			ProcessItem timer2 = ProcessItemFactory.getProcessElement(ProcessItemType.START, "timer2", null);
			processContainer.addElement(timer2);
			ProcessItem itemF = ProcessItemFactory.getProcessElement(ProcessItemType.START, "itemF", null);
			processContainer.addElement(itemF);
			ProcessItem itemG = ProcessItemFactory.getProcessElement(ProcessItemType.START, "itemG", null);
			processContainer.addElement(itemG);
			ProcessItem join1 = ProcessItemFactory.getProcessElement(ProcessItemType.START, "join1", null);
			processContainer.addElement(join1);
			ProcessItem end1 = ProcessItemFactory.getProcessElement(ProcessItemType.START, "end1", null);
			processContainer.addElement(end1);
			ProcessItem end2 = ProcessItemFactory.getProcessElement(ProcessItemType.START, "end2", null);
			processContainer.addElement(end2);
			
			//relations
			processContainer.relateParent("itemA", "start");
			processContainer.relateParent("itemB", "itemA");
			processContainer.relateParent("fork1", "itemB");
			
			processContainer.relateParent("itemC", "fork1");
			processContainer.relateParent("itemD", "itemC");
			processContainer.relateParent("timer1", "fork1");
			processContainer.relateParent("itemE", "timer1");
			processContainer.relateParent("end1", "itemD");
			
			processContainer.relateParent("fork2", "itemE");
			
			processContainer.relateParent("itemF", "fork2");
			processContainer.relateParent("timer2", "fork2");
			processContainer.relateParent("itemG", "timer2");
			
			processContainer.relateParent("join1", "itemF");
			processContainer.relateParent("join1", "itemG");
			
			processContainer.relateParent("end2", "join1");
			
			return processContainer;
			
		} catch (ProcessException e) {
			logger.error(e);
			return null;
		}
	}

	public static ProcessEngine getReferenceProcessFromWork() {
		
		ProcessEngine processContainer = new ProcessEngine();
		
		try {
			ProcessItem start = ProcessItemFactory.getProcessElement(ProcessItemType.START, "start", null);
			processContainer.addElement(start);
			ProcessItem ac1 = ProcessItemFactory.getProcessElement(ProcessItemType.ACTION, "ac1", null);
			processContainer.addElement(ac1);
			ProcessItem t1 = ProcessItemFactory.getProcessElement(ProcessItemType.TASK, "t1", null);
			processContainer.addElement(t1);
			ProcessItem fork1 = ProcessItemFactory.getProcessElement(ProcessItemType.ACTION, "fork1", null);
			processContainer.addElement(fork1);
			ProcessItem ac2 = ProcessItemFactory.getProcessElement(ProcessItemType.ACTION, "ac2", null);
			processContainer.addElement(ac2);
			ProcessItem t2 = ProcessItemFactory.getProcessElement(ProcessItemType.TASK, "t2", null);
			processContainer.addElement(t2);
			ProcessItem ac3 = ProcessItemFactory.getProcessElement(ProcessItemType.ACTION, "ac3", null);
			processContainer.addElement(ac3);
			ProcessItem end1 = ProcessItemFactory.getProcessElement(ProcessItemType.END, "end1", null);
			processContainer.addElement(end1);
			ProcessItem end2 = ProcessItemFactory.getProcessElement(ProcessItemType.END, "end2", null);
			processContainer.addElement(end2);
			
			//relations
			processContainer.relateParent("ac1", "start");
			processContainer.relateParent("t1", "ac1");
			processContainer.relateParent("fork1", "t1");
			
			processContainer.relateParent("ac2", "fork1");
			processContainer.relateParent("t2", "fork1");
			
			processContainer.relateParent("end1", "ac2");
			
			processContainer.relateParent("t2", "fork1");
			processContainer.relateParent("ac3", "t2");
			
			processContainer.relateParent("end2", "ac3");
			
			return processContainer;
			
		} catch (ProcessException e) {
			logger.error(e);
			return null;
		}
	}
	
	public static ProcessEngine getLinearHomeProcess() {
		
		ProcessEngine processContainer = new ProcessEngine();
		
		try {
			//start
			ProcessItem start = ProcessItemFactory.getProcessElement(ProcessItemType.START, "start", null);
			processContainer.addElement(start);
			
			//tasks
			ProcessItem t1 = ProcessItemFactory.getProcessElement(ProcessItemType.TASK, "t1", null);
			processContainer.addElement(t1);
			ProcessItem t2 = ProcessItemFactory.getProcessElement(ProcessItemType.TASK, "t2", null);
			processContainer.addElement(t2);
			ProcessItem t3 = ProcessItemFactory.getProcessElement(ProcessItemType.TASK, "t3", null);
			processContainer.addElement(t3);
			ProcessItem t4 = ProcessItemFactory.getProcessElement(ProcessItemType.TASK, "t4", null);
			processContainer.addElement(t4);
			ProcessItem t5 = ProcessItemFactory.getProcessElement(ProcessItemType.TASK, "t5", null);
			processContainer.addElement(t5);
			ProcessItem t6 = ProcessItemFactory.getProcessElement(ProcessItemType.TASK, "t6", null);
			processContainer.addElement(t6);
			ProcessItem t7 = ProcessItemFactory.getProcessElement(ProcessItemType.TASK, "t7", null);
			processContainer.addElement(t7);
			ProcessItem t8 = ProcessItemFactory.getProcessElement(ProcessItemType.TASK, "t8", null);
			processContainer.addElement(t8);
			
			//actions
			ProcessItem ac1 = ProcessItemFactory.getProcessElement(ProcessItemType.ACTION, "ac1", null);
			processContainer.addElement(ac1);
			ProcessItem ac2 = ProcessItemFactory.getProcessElement(ProcessItemType.ACTION, "ac2", null);
			processContainer.addElement(ac2);
			ProcessItem ac3 = ProcessItemFactory.getProcessElement(ProcessItemType.ACTION, "ac3", null);
			processContainer.addElement(ac3);
			ProcessItem ac4 = ProcessItemFactory.getProcessElement(ProcessItemType.ACTION, "ac4", null);
			processContainer.addElement(ac4);
			
			//forks
			ProcessItem fork1 = ProcessItemFactory.getProcessElement(ProcessItemType.FORK, "fork1", null);
			processContainer.addElement(fork1);
			ProcessItem fork2 = ProcessItemFactory.getProcessElement(ProcessItemType.FORK, "fork2", null);
			processContainer.addElement(fork2);
			
			//joins
			ProcessItem join1 = ProcessItemFactory.getProcessElement(ProcessItemType.JOIN, "join1", null);
			processContainer.addElement(join1);
			ProcessItem join2 = ProcessItemFactory.getProcessElement(ProcessItemType.JOIN, "join2", null);
			processContainer.addElement(join2);
			
			//ends
			ProcessItem end1 = ProcessItemFactory.getProcessElement(ProcessItemType.END, "end1", null);
			processContainer.addElement(end1);
			ProcessItem end2 = ProcessItemFactory.getProcessElement(ProcessItemType.END, "end2", null);
			processContainer.addElement(end2);
			
			//relations
			/*01*/processContainer.relateParent("ac1", 		"start");
			/*02*/processContainer.relateParent("t1", 		"ac1");
			/*03*/processContainer.relateParent("fork1", 	"t1");
			/*04*/processContainer.relateParent("ac2", 		"fork1");
			/*05*/processContainer.relateParent("t2", 		"fork1");
			/*06*/processContainer.relateParent("t3", 		"ac2");
			/*07*/processContainer.relateParent("t8", 		"t3");
			/*08*/processContainer.relateParent("t6", 		"join2");
			/*09*/processContainer.relateParent("ac4", 		"t6");
			/*10*/processContainer.relateParent("end1", 	"ac4");
			/*11*/processContainer.relateParent("join2", 	"t5");
			/*12*/processContainer.relateParent("t7", 		"fork2");
			/*13*/processContainer.relateParent("end2", 	"t7");
			/*14*/processContainer.relateParent("t4", 		"fork2");
			/*15*/processContainer.relateParent("join1", 	"t4");
			/*16*/processContainer.relateParent("ac3", 		"join1");
			/*17*/processContainer.relateParent("join1", 	"t2");
			/*18*/processContainer.relateParent("fork2", 	"ac3");
			/*19*/processContainer.relateParent("t5", 		"fork2");
			/*20*/processContainer.relateParent("join2", 	"t8");
			
			//task checkers
			processContainer.addTaskResolver("t1", ResolveT1.class);
			processContainer.addTaskResolver("t2", ResolveT2.class);
			processContainer.addTaskResolver("t3", ResolveT3.class);
			
			return processContainer;
			
		} catch (ProcessException e) {
			logger.error(e);
			return null;
		}
	}
	
	public static ProcessEngine getLinearProcess() {
		
		ProcessEngine processEngine = new ProcessEngine();
		
		try {
			ProcessItem start = ProcessItemFactory.getProcessElement(ProcessItemType.START, "start", null);
			processEngine.addElement(start);
			
			ProcessItem ac1 = ProcessItemFactory.getProcessElement(ProcessItemType.ACTION, "ac1", null);
			processEngine.addElement(ac1);
			ProcessItem ac2 = ProcessItemFactory.getProcessElement(ProcessItemType.ACTION, "ac2", null);
			processEngine.addElement(ac2);
			ProcessItem ac3 = ProcessItemFactory.getProcessElement(ProcessItemType.ACTION, "ac3", null);
			processEngine.addElement(ac3);
			ProcessItem ac4 = ProcessItemFactory.getProcessElement(ProcessItemType.ACTION, "ac4", null);
			processEngine.addElement(ac4);
			ProcessItem ac5 = ProcessItemFactory.getProcessElement(ProcessItemType.ACTION, "ac5", null);
			processEngine.addElement(ac5);
			ProcessItem ac6 = ProcessItemFactory.getProcessElement(ProcessItemType.ACTION, "ac6", null);
			processEngine.addElement(ac6);
			ProcessItem ac7 = ProcessItemFactory.getProcessElement(ProcessItemType.ACTION, "ac7", null);
			processEngine.addElement(ac7);
			ProcessItem ac8 = ProcessItemFactory.getProcessElement(ProcessItemType.ACTION, "ac8", null);
			processEngine.addElement(ac8);
			ProcessItem ac9 = ProcessItemFactory.getProcessElement(ProcessItemType.ACTION, "ac9", null);
			processEngine.addElement(ac9);
			ProcessItem ac10 = ProcessItemFactory.getProcessElement(ProcessItemType.ACTION, "ac10", null);
			processEngine.addElement(ac10);
			ProcessItem ac11 = ProcessItemFactory.getProcessElement(ProcessItemType.ACTION, "ac11", null);
			processEngine.addElement(ac11);
			ProcessItem ac12 = ProcessItemFactory.getProcessElement(ProcessItemType.ACTION, "ac12", null);
			processEngine.addElement(ac12);
			ProcessItem ac13 = ProcessItemFactory.getProcessElement(ProcessItemType.ACTION, "ac13", null);
			processEngine.addElement(ac13);
			ProcessItem ac14 = ProcessItemFactory.getProcessElement(ProcessItemType.ACTION, "ac14", null);
			processEngine.addElement(ac14);
			ProcessItem ac15 = ProcessItemFactory.getProcessElement(ProcessItemType.ACTION, "ac15", null);
			processEngine.addElement(ac15);
			ProcessItem ac16 = ProcessItemFactory.getProcessElement(ProcessItemType.ACTION, "ac16", null);
			processEngine.addElement(ac16);			
			ProcessItem ac17 = ProcessItemFactory.getProcessElement(ProcessItemType.ACTION, "ac17", null);
			processEngine.addElement(ac17);
			ProcessItem ac18 = ProcessItemFactory.getProcessElement(ProcessItemType.ACTION, "ac18", null);
			processEngine.addElement(ac18);
			ProcessItem ac19 = ProcessItemFactory.getProcessElement(ProcessItemType.ACTION, "ac19", null);
			processEngine.addElement(ac19);
			ProcessItem ac20 = ProcessItemFactory.getProcessElement(ProcessItemType.ACTION, "ac20", null);
			processEngine.addElement(ac20);
			ProcessItem ac21 = ProcessItemFactory.getProcessElement(ProcessItemType.ACTION, "ac21", null);
			processEngine.addElement(ac21);
			ProcessItem ac22 = ProcessItemFactory.getProcessElement(ProcessItemType.ACTION, "ac22", null);
			processEngine.addElement(ac22);
			ProcessItem ac23 = ProcessItemFactory.getProcessElement(ProcessItemType.ACTION, "ac23", null);
			processEngine.addElement(ac23);
			
			ProcessItem t1 = ProcessItemFactory.getProcessElement(ProcessItemType.TASK, "t1", null);
			processEngine.addElement(t1);
			ProcessItem t2 = ProcessItemFactory.getProcessElement(ProcessItemType.TASK, "t2", null);
			processEngine.addElement(t2);
			ProcessItem t3 = ProcessItemFactory.getProcessElement(ProcessItemType.TASK, "t3", null);
			processEngine.addElement(t3);
			ProcessItem t4 = ProcessItemFactory.getProcessElement(ProcessItemType.TASK, "t4", null);
			processEngine.addElement(t4);
			ProcessItem t5 = ProcessItemFactory.getProcessElement(ProcessItemType.TASK, "t5", null);
			processEngine.addElement(t5);
			ProcessItem t6 = ProcessItemFactory.getProcessElement(ProcessItemType.TASK, "t6", null);
			processEngine.addElement(t6);
			
			ProcessItem fork1 = ProcessItemFactory.getProcessElement(ProcessItemType.FORK, "fork1", null);
			processEngine.addElement(fork1);
			ProcessItem fork2 = ProcessItemFactory.getProcessElement(ProcessItemType.FORK, "fork2", null);
			processEngine.addElement(fork2);
			
			ProcessItem end1 = ProcessItemFactory.getProcessElement(ProcessItemType.END, "end1", null);
			processEngine.addElement(end1);
			ProcessItem end2 = ProcessItemFactory.getProcessElement(ProcessItemType.END, "end2", null);
			processEngine.addElement(end2);
			ProcessItem end3 = ProcessItemFactory.getProcessElement(ProcessItemType.END, "end3", null);
			processEngine.addElement(end3);
			ProcessItem end4 = ProcessItemFactory.getProcessElement(ProcessItemType.END, "end4", null);
			processEngine.addElement(end4);
			
			//relate
			processEngine.relateParent("ac1", "start");
			processEngine.relateParent("ac2", "ac1");
			processEngine.relateParent("t1", "ac2");
			processEngine.relateParent("ac3", "t1");
			processEngine.relateParent("fork1", "ac3");
			
			//relate left arm
			processEngine.relateParent("ac4", "fork1");
			processEngine.relateParent("t2", "ac4");
			processEngine.relateParent("ac7", "t2");
			processEngine.relateParent("ac8", "ac7");
			processEngine.relateParent("ac17", "ac8");
			processEngine.relateParent("ac18", "ac17");
			processEngine.relateParent("ac19", "ac18");
			processEngine.relateParent("t6", "ac19");
			processEngine.relateParent("ac21", "t6");
			processEngine.relateParent("ac22", "ac21");
			processEngine.relateParent("ac23", "ac22");			
			processEngine.relateParent("end1", "ac23");
			
			//relate right arm
			processEngine.relateParent("ac5", "fork1");
			processEngine.relateParent("ac6", "ac5");
			processEngine.relateParent("ac9", "ac6");
			processEngine.relateParent("t3", "ac9");
			processEngine.relateParent("fork2", "t3");
			
			//relate arm_x
			processEngine.relateParent("ac10", "fork2");
			processEngine.relateParent("ac11", "ac10");
			processEngine.relateParent("ac12", "ac11");
			processEngine.relateParent("end2", "ac12");
			
			//relate arm_y
			processEngine.relateParent("t4", "fork2");
			processEngine.relateParent("ac13", "t4");
			processEngine.relateParent("ac14", "ac13");
			processEngine.relateParent("end3", "ac14");
			
			//relate arm_z
			processEngine.relateParent("ac15", "fork2");
			processEngine.relateParent("ac16", "ac15");
			processEngine.relateParent("t5", "ac16");
			processEngine.relateParent("end4", "t5");
			
			//task checkers
			processEngine.addTaskResolver("t1", ResolveT1.class);
			
			return processEngine;
			
		} catch (ProcessException e) {
			logger.error(e);
			return null;
		}
	}
}
