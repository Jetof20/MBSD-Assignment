package main.metamodel;

import java.util.function.BooleanSupplier;

public class Transition{
	
	private String target_name = null;
	private State target = null;
	private String name = null;
	
	private String variable_to_set = null;
	private int set_value = 0;
	private String set_operator = null;
	
	private String variable_to_compare = null;
	private int value_to_compare_to = 0;
	private String operator = null;
	
	
	public Transition(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setTarget(String target) {
		this.target_name = target;
	}
	public void setTarget(State target) {
		this.target = target;
	}
	
	public String getTargetString() {
		return target_name;
	}
	public State getTarget() {
		return target;
	}
	
	public void createOperation(String variable_to_set, String set_operator, int set_value) {
		this.set_value = set_value;
		this.variable_to_set = variable_to_set;
		this.set_operator = set_operator;
	}
	

	public boolean hasOperation() {
		if (variable_to_set == null) {
			return false;
		}
		return true;
	}
	
	public boolean hasOperation(String operation) {
		if (set_operator == operation) {
			return true;
		} else {
			return false;
		}
	}
	
	public int getOperationValue() {
		return set_value;
	}

	public void createConditional(String variable_to_compare,String operator, int value_to_compare_to) {
		this.value_to_compare_to = value_to_compare_to;
		this.variable_to_compare = variable_to_compare;
		this.operator = operator;
	}
	
	public boolean isConditional() {
		if (variable_to_compare == null) {
			return false;
		}
		return true;
	}
	
	public boolean isConditional(String condition_operator) {
		if (operator == condition_operator) {
			return true;
		}
		return false;
	}
	
	public Object getVariableToModify() {
		return variable_to_set;
	}
	
	public Object getConditionVariableName() {
		// TODO Auto-generated method stub
		return variable_to_compare;
	}

	public Integer getConditionComparedValue() {
		// TODO Auto-generated method stub
		return value_to_compare_to;
	}

	public boolean isConditionEqual() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isConditionGreaterThan() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isConditionLessThan() {
		// TODO Auto-generated method stub
		return false;
	}

	public Object getEvent() {
		// TODO Auto-generated method stub
		return null;
	}

	public BooleanSupplier hasSetOperation() {
		// TODO Auto-generated method stub
		return null;
	}

	public BooleanSupplier hasIncrementOperation() {
		// TODO Auto-generated method stub
		return null;
	}

	public BooleanSupplier hasDecrementOperation() {
		// TODO Auto-generated method stub
		return null;
	}

	public Object getOperationVariableName() {
		// TODO Auto-generated method stub
		return null;
	}
}
