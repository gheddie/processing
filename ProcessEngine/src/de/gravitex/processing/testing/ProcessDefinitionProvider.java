package de.gravitex.processing.testing;

import org.apache.log4j.Logger;

import de.gravitex.processing.core.ProcessEngine;
import de.gravitex.processing.core.ProcessItemFactory;
import de.gravitex.processing.core.ProcessItemType;
import de.gravitex.processing.core.exception.ProcessException;
import de.gravitex.processing.core.item.ProcessItem;
import de.gravitex.processing.testing.action.ActionA2;
import de.gravitex.processing.testing.decision.DecisionForA1;
import de.gravitex.processing.testing.decision.DecisionForA2;
import de.gravitex.processing.testing.decision.DecisionForA3;
import de.gravitex.processing.testing.taskresolver.appliance.GenericFalseResolver;
import de.gravitex.processing.testing.taskresolver.appliance.GenericTrueResolver;
import de.gravitex.processing.testing.taskresolver.linear.ResolveT1;
import de.gravitex.processing.testing.taskresolver.linear.ResolveT2;
import de.gravitex.processing.testing.taskresolver.linear.ResolveT3;
import de.gravitex.processing.testing.taskresolver.linear.ResolveT4;
import de.gravitex.processing.testing.taskresolver.linear.ResolveT5;
import de.gravitex.processing.testing.taskresolver.linear.ResolveT6;
import de.gravitex.processing.testing.taskresolver.linear.ResolveT7;
import de.gravitex.processing.testing.taskresolver.linear.ResolveT8;

public class ProcessDefinitionProvider {
	
	private static Logger			logger					= Logger.getLogger(ProcessDefinitionProvider.class);

	public static ProcessEngine getBewerbung() {
		
		ProcessEngine processContainer = new ProcessEngine();
		try {
			//action
			ProcessItem delData1 = ProcessItemFactory.getProcessElement(ProcessItemType.ACTION, "delData1", null);
			processContainer.addElement(delData1);
			ProcessItem delData2 = ProcessItemFactory.getProcessElement(ProcessItemType.ACTION, "delData2", null);
			processContainer.addElement(delData2);
			ProcessItem revoke = ProcessItemFactory.getProcessElement(ProcessItemType.ACTION, "revoke", null);
			processContainer.addElement(revoke);
			ProcessItem acknowledgeData = ProcessItemFactory.getProcessElement(ProcessItemType.ACTION, "acknowledgeData", null);
			processContainer.addElement(acknowledgeData);
			ProcessItem infoMail = ProcessItemFactory.getProcessElement(ProcessItemType.ACTION, "infoMail", null);
			processContainer.addElement(infoMail);
			ProcessItem confStoreData = ProcessItemFactory.getProcessElement(ProcessItemType.ACTION, "confStoreData", null);
			processContainer.addElement(confStoreData);
			ProcessItem ackNoStorage = ProcessItemFactory.getProcessElement(ProcessItemType.ACTION, "ackNoStorage", null);
			processContainer.addElement(ackNoStorage);
			ProcessItem askStoreData = ProcessItemFactory.getProcessElement(ProcessItemType.ACTION, "askStoreData", null);
			processContainer.addElement(askStoreData);

			//task
			ProcessItem gatherData = ProcessItemFactory.getProcessElement(ProcessItemType.TASK, "gatherData", null);
			processContainer.addElement(gatherData);
			ProcessItem sightData = ProcessItemFactory.getProcessElement(ProcessItemType.TASK, "sightData", null);
			processContainer.addElement(sightData);
			ProcessItem appoint = ProcessItemFactory.getProcessElement(ProcessItemType.TASK, "appoint", null);
			processContainer.addElement(appoint);
			ProcessItem writeHrDB = ProcessItemFactory.getProcessElement(ProcessItemType.TASK, "writeHrDB", null);
			processContainer.addElement(writeHrDB);
			ProcessItem storePotDB = ProcessItemFactory.getProcessElement(ProcessItemType.TASK, "storePotDB", null);
			processContainer.addElement(storePotDB);
			ProcessItem storeAnswer = ProcessItemFactory.getProcessElement(ProcessItemType.TASK, "storeAnswer", null);
			processContainer.addElement(storeAnswer);

			//timer
			ProcessItem timer1 = ProcessItemFactory.getProcessElement(ProcessItemType.WAIT, "timer1", null);
			processContainer.addElement(timer1);

			//join
			ProcessItem join1 = ProcessItemFactory.getProcessElement(ProcessItemType.JOIN, "join1", null);
			processContainer.addElement(join1);
			ProcessItem join2 = ProcessItemFactory.getProcessElement(ProcessItemType.JOIN, "join2", null);
			processContainer.addElement(join2);

			
			//fork
			ProcessItem fork1 = ProcessItemFactory.getProcessElement(ProcessItemType.FORK, "fork1", null);
			processContainer.addElement(fork1);
			ProcessItem fork2 = ProcessItemFactory.getProcessElement(ProcessItemType.FORK, "fork2", null);
			processContainer.addElement(fork2);
			ProcessItem fork3 = ProcessItemFactory.getProcessElement(ProcessItemType.FORK, "fork3", null);
			processContainer.addElement(fork3);
			ProcessItem fork4 = ProcessItemFactory.getProcessElement(ProcessItemType.FORK, "fork4", null);
			processContainer.addElement(fork4);

			//start
			ProcessItem start = ProcessItemFactory.getProcessElement(ProcessItemType.START, "start", null);
			processContainer.addElement(start);

			//end
			ProcessItem end1 = ProcessItemFactory.getProcessElement(ProcessItemType.END, "end1", null);
			processContainer.addElement(end1);
			ProcessItem end2 = ProcessItemFactory.getProcessElement(ProcessItemType.END, "end2", null);
			processContainer.addElement(end2);
			ProcessItem end3 = ProcessItemFactory.getProcessElement(ProcessItemType.END, "end3", null);
			processContainer.addElement(end3);
			ProcessItem end4 = ProcessItemFactory.getProcessElement(ProcessItemType.END, "end4", null);
			processContainer.addElement(end4);
			
			//relations						
			/*01*/processContainer.relateParentFromTo("revoke", "end1");
			/*02*/processContainer.relateParentFromTo("delData1", "revoke");
			/*03*/processContainer.relateParentFromTo("join1", "delData1");
			/*04*/processContainer.relateParentFromTo("fork1", "join1");

			/*06*/processContainer.relateParentFromTo("fork4", "join1");
			/*07*/processContainer.relateParentFromTo("fork2", "fork4");
			/*08*/processContainer.relateParentFromTo("fork2", "writeHrDB");
			/*09*/processContainer.relateParentFromTo("writeHrDB", "infoMail");
			/*10*/processContainer.relateParentFromTo("infoMail", "end2");
			/*11*/processContainer.relateParentFromTo("fork1", "join2");
			/*12*/processContainer.relateParentFromTo("fork1", "askStoreData");
			/*13*/processContainer.relateParentFromTo("sightData", "fork1");
			/*14*/processContainer.relateParentFromTo("acknowledgeData", "sightData");
			/*15*/processContainer.relateParentFromTo("gatherData", "acknowledgeData");
			/*16*/processContainer.relateParentFromTo("start", "gatherData");
			/*17*/processContainer.relateParentFromTo("askStoreData", "storeAnswer");
			/*18*/processContainer.relateParentFromTo("storeAnswer", "fork3");
			/*19*/processContainer.relateParentFromTo("fork3", "ackNoStorage");
			/*20*/processContainer.relateParentFromTo("fork3", "confStoreData");
			/*21*/processContainer.relateParentFromTo("ackNoStorage", "delData2");
			/*22*/processContainer.relateParentFromTo("confStoreData", "storePotDB");
			/*23*/processContainer.relateParentFromTo("delData2", "end3");
			/*24*/processContainer.relateParentFromTo("storePotDB", "end4");
			/*25*/processContainer.relateParentFromTo("timer1", "join2");
			/*26*/processContainer.relateParentFromTo("join2", "appoint");
			/*27*/processContainer.relateParentFromTo("appoint", "fork2");
			
			/*99*/processContainer.relateParentFromTo("fork4", "timer1");
			
			//resolve
			processContainer.addTaskResolver("gatherData", GenericTrueResolver.class);
			processContainer.addTaskResolver("sightData", GenericTrueResolver.class);
			processContainer.addTaskResolver("appoint", GenericTrueResolver.class);
			processContainer.addTaskResolver("writeHrDB", GenericTrueResolver.class);
			processContainer.addTaskResolver("storePotDB", GenericTrueResolver.class);
			processContainer.addTaskResolver("storeAnswer", GenericFalseResolver.class);
			
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
			processContainer.relateParentFromTo("start", "itemA");
			processContainer.relateParentFromTo("itemA", "itemB");
			processContainer.relateParentFromTo("itemB", "fork1");
			
			processContainer.relateParentFromTo("fork1", "itemC");
			processContainer.relateParentFromTo("itemC", "itemD");
			processContainer.relateParentFromTo("fork1", "timer1");
			processContainer.relateParentFromTo("timer1", "itemE");
			processContainer.relateParentFromTo("itemD", "end1");
			
			processContainer.relateParentFromTo("itemE", "fork2");
			
			processContainer.relateParentFromTo("fork2", "itemF");
			processContainer.relateParentFromTo("fork2", "timer2");
			processContainer.relateParentFromTo("timer2", "itemG");
			
			processContainer.relateParentFromTo("itemF", "join1");
			processContainer.relateParentFromTo("itemG", "join1");
			
			processContainer.relateParentFromTo("join1", "end2");
			
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
			processContainer.relateParentFromTo("start", "ac1");
			processContainer.relateParentFromTo("ac1", "t1");
			processContainer.relateParentFromTo("t1", "fork1");
			
			processContainer.relateParentFromTo("fork1", "ac2");
			processContainer.relateParentFromTo("fork1", "t2");
			
			processContainer.relateParentFromTo("ac2", "end1");
			
			processContainer.relateParentFromTo("fork1", "t2");
			processContainer.relateParentFromTo("t2", "ac3");
			
			processContainer.relateParentFromTo("ac3", "end2");
			
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
			/*01*/processContainer.relateParentFromTo("start", 		"ac1");
			/*02*/processContainer.relateParentFromTo("ac1", 		"t1");
			/*03*/processContainer.relateParentFromTo("t1", 	"fork1");
			/*04*/processContainer.relateParentFromTo("fork1", 		"ac2");
			/*05*/processContainer.relateParentFromTo("fork1", 		"t2");
			/*06*/processContainer.relateParentFromTo("ac2", 		"t3");
			/*07*/processContainer.relateParentFromTo("t3", 		"t8");
			/*08*/processContainer.relateParentFromTo("join2", 		"t6");
			/*09*/processContainer.relateParentFromTo("t6", 		"ac4");
			/*10*/processContainer.relateParentFromTo("ac4", 	"end1");
			/*11*/processContainer.relateParentFromTo("t5", 	"join2");
			/*12*/processContainer.relateParentFromTo("fork2", 		"t7");
			/*13*/processContainer.relateParentFromTo("t7", 	"end2");
			/*14*/processContainer.relateParentFromTo("fork2", 		"t4");
			/*15*/processContainer.relateParentFromTo("t4", 	"join1");
			/*16*/processContainer.relateParentFromTo("join1", 		"ac3");
			/*17*/processContainer.relateParentFromTo("t2", 	"join1");
			/*18*/processContainer.relateParentFromTo("ac3", 	"fork2");
			/*19*/processContainer.relateParentFromTo("fork2", 		"t5");
			/*20*/processContainer.relateParentFromTo("t8", 	"join2");
			
			//task checkers
			processContainer.addTaskResolver("t1", ResolveT1.class);
			processContainer.addTaskResolver("t2", ResolveT2.class);
			processContainer.addTaskResolver("t3", ResolveT3.class);
			processContainer.addTaskResolver("t4", ResolveT4.class);
			processContainer.addTaskResolver("t5", ResolveT5.class);
			processContainer.addTaskResolver("t6", ResolveT6.class);
			processContainer.addTaskResolver("t7", ResolveT7.class);
			processContainer.addTaskResolver("t8", ResolveT8.class);
			
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
			processEngine.relateParentFromTo("start", "ac1");
			processEngine.relateParentFromTo("ac1", "ac2");
			processEngine.relateParentFromTo("ac2", "t1");
			processEngine.relateParentFromTo("t1", "ac3");
			processEngine.relateParentFromTo("ac3", "fork1");
			
			//relate left arm
			processEngine.relateParentFromTo("fork1", "ac4");
			processEngine.relateParentFromTo("ac4", "t2");
			processEngine.relateParentFromTo("t2", "ac7");
			processEngine.relateParentFromTo("ac7", "ac8");
			processEngine.relateParentFromTo("ac8", "ac17");
			processEngine.relateParentFromTo("ac17", "ac18");
			processEngine.relateParentFromTo("ac18", "ac19");
			processEngine.relateParentFromTo("ac19", "t6");
			processEngine.relateParentFromTo("t6", "ac21");
			processEngine.relateParentFromTo("ac21", "ac22");
			processEngine.relateParentFromTo("ac22", "ac23");			
			processEngine.relateParentFromTo("ac23", "end1");
			
			//relate right arm
			processEngine.relateParentFromTo("fork1", "ac5");
			processEngine.relateParentFromTo("ac5", "ac6");
			processEngine.relateParentFromTo("ac6", "ac9");
			processEngine.relateParentFromTo("ac9", "t3");
			processEngine.relateParentFromTo("t3", "fork2");
			
			//relate arm_x
			processEngine.relateParentFromTo("fork2", "ac10");
			processEngine.relateParentFromTo("ac10", "ac11");
			processEngine.relateParentFromTo("ac11", "ac12");
			processEngine.relateParentFromTo("ac12", "end2");
			
			//relate arm_y
			processEngine.relateParentFromTo("fork2", "t4");
			processEngine.relateParentFromTo("t4", "ac13");
			processEngine.relateParentFromTo("ac13", "ac14");
			processEngine.relateParentFromTo("ac14", "end3");
			
			//relate arm_z
			processEngine.relateParentFromTo("fork2", "ac15");
			processEngine.relateParentFromTo("ac15", "ac16");
			processEngine.relateParentFromTo("ac16", "t5");
			processEngine.relateParentFromTo("t5", "end4");
			
			//task checkers
			processEngine.addTaskResolver("t1", ResolveT1.class);
			
			return processEngine;
			
		} catch (ProcessException e) {
			logger.error(e);
			return null;
		}
	}

	public static ProcessEngine getTestTimer() {
		
		ProcessEngine processEngine = new ProcessEngine();
		
		try {
			ProcessItem start = ProcessItemFactory.getProcessElement(ProcessItemType.START, "start", null);
			processEngine.addElement(start);
			ProcessItem t1 = ProcessItemFactory.getProcessElement(ProcessItemType.TASK, "t1", null);
			processEngine.addElement(t1);
			ProcessItem timer1 = ProcessItemFactory.getProcessElement(ProcessItemType.WAIT, "timer1", null);
			processEngine.addElement(timer1);
			ProcessItem ac2 = ProcessItemFactory.getProcessElement(ProcessItemType.ACTION, "ac2", null);
			processEngine.addElement(ac2);
			ProcessItem end = ProcessItemFactory.getProcessElement(ProcessItemType.END, "end", null);
			processEngine.addElement(end);
			
			//relate
			processEngine.relateParentFromTo("start", "t1");
			processEngine.relateParentFromTo("t1", "timer1");
			processEngine.relateParentFromTo("timer1", "ac2");
			processEngine.relateParentFromTo("ac2", "end");
			
			//resolve
			processEngine.addTaskResolver("t1", GenericTrueResolver.class);
			
			return processEngine;
			
		} catch (ProcessException e) {
			logger.error(e);
			return null;
		}
	}
}
