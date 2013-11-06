package de.gravitex.processing.testing;

import de.gravitex.processing.core.ProcessContainer;
import de.gravitex.processing.core.ProcessItemFactory;
import de.gravitex.processing.core.ProcessItemType;
import de.gravitex.processing.core.exception.ProcessException;
import de.gravitex.processing.core.item.ProcessItem;
import de.gravitex.processing.testing.decision.DecisionForA1;
import de.gravitex.processing.testing.decision.DecisionForA2;
import de.gravitex.processing.testing.decision.DecisionForA3;

public class ProcessDefinitionProvider {

	public static ProcessContainer getT4() {
		
		ProcessContainer processContainer = new ProcessContainer();
		
		try {
			ProcessItem s1 = ProcessItemFactory.getProcessElement(ProcessItemType.START, "s1");
			processContainer.addElement(s1);
			
			ProcessItem a1 = ProcessItemFactory.getProcessElement(ProcessItemType.ACTION, "a1");
			processContainer.addElement(a1);
			
			ProcessItem f1 = ProcessItemFactory.getProcessElement(ProcessItemType.FORK, "f1");
			processContainer.addElement(f1);
			
			ProcessItem a2 = ProcessItemFactory.getProcessElement(ProcessItemType.ACTION, "a2");
			processContainer.addElement(a2);
			
			ProcessItem a3 = ProcessItemFactory.getProcessElement(ProcessItemType.ACTION, "a3");
			processContainer.addElement(a3);
			
			ProcessItem e1 = ProcessItemFactory.getProcessElement(ProcessItemType.END, "e1");
			processContainer.addElement(e1);
			
			ProcessItem e2 = ProcessItemFactory.getProcessElement(ProcessItemType.END, "e2");
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
			ProcessItem s1 = ProcessItemFactory.getProcessElement(ProcessItemType.START, "s1");
			processContainer.addElement(s1);
			
			ProcessItem f1 = ProcessItemFactory.getProcessElement(ProcessItemType.FORK, "f1");
			processContainer.addElement(f1);
			
			ProcessItem a1 = ProcessItemFactory.getProcessElement(ProcessItemType.ACTION, "a1");
			processContainer.addElement(a1);
			
			ProcessItem a2 = ProcessItemFactory.getProcessElement(ProcessItemType.ACTION, "a2");
			processContainer.addElement(a2);
			
			ProcessItem a3 = ProcessItemFactory.getProcessElement(ProcessItemType.ACTION, "a3");
			processContainer.addElement(a3);
			
			ProcessItem j1 = ProcessItemFactory.getProcessElement(ProcessItemType.JOIN, "j1");
			processContainer.addElement(j1);
			
			ProcessItem e1 = ProcessItemFactory.getProcessElement(ProcessItemType.END, "e1");
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
			
		} catch (ProcessException e) {
			e.printStackTrace();
		}
		
		return processContainer;
	}

	public static ProcessContainer getT6() {
		
		ProcessContainer processContainer = new ProcessContainer();
		
		try {
			ProcessItem s1 = ProcessItemFactory.getProcessElement(ProcessItemType.START, "s1");
			processContainer.addElement(s1);
			
			ProcessItem j1 = ProcessItemFactory.getProcessElement(ProcessItemType.JOIN, "j1");
			processContainer.addElement(j1);
			ProcessItem a1 = ProcessItemFactory.getProcessElement(ProcessItemType.ACTION, "a1");
			processContainer.addElement(a1);
			ProcessItem f1 = ProcessItemFactory.getProcessElement(ProcessItemType.FORK, "f1");
			processContainer.addElement(f1);
			ProcessItem a2 = ProcessItemFactory.getProcessElement(ProcessItemType.ACTION, "a2");
			processContainer.addElement(a2);
			ProcessItem f2 = ProcessItemFactory.getProcessElement(ProcessItemType.FORK, "f2");
			processContainer.addElement(f2);
			
			ProcessItem e1 = ProcessItemFactory.getProcessElement(ProcessItemType.END, "e1");
			processContainer.addElement(e1);
			ProcessItem e2 = ProcessItemFactory.getProcessElement(ProcessItemType.END, "e2");
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
}
