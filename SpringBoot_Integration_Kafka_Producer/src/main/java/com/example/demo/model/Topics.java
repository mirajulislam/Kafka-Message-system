package com.example.demo.model;

public class Topics {
   private	String topicName;
	private int partition;
    private	int refactor;
    
    
	public Topics() {
		
	}
	public String getTopicName() {
		return topicName;
	}
	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}
	public int getPartition() {
		return partition;
	}
	public void setPartition(int partition) {
		this.partition = partition;
	}
	public int getRefactor() {
		return refactor;
	}
	public void setRefactor(int refactor) {
		this.refactor = refactor;
	}

}
