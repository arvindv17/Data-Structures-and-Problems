package com.neu.algo.assignment1;

public class StackWithLinkedList {

private static class StackNode<String>{
        
        private String data;
        private StackNode<String> next;
        
        public StackNode(String data){
            this.data = data;
        }
        
        
        
    }
private StackNode<String> top;
	public String pop(){
		 if(top == null){
	            String errorMessage = (String) "Stack Empty";
	            return errorMessage;
	        }else{
	            String item = top.data;
	            top = top.next;
	            return item;
	        }
	}
	
	public String peek(){
		
		if(top == null){
            String errorMessage = (String) "Stack empty";
            return errorMessage;
        }else{
            return top.data;
        }
		
	}
	
	public void push(String input){
		// insert code here
		StackNode<String> t = new StackNode<String>(input);
        t.next = top;
        top = t;
	}
	
	public boolean isEmpty(){
		  return top == null;
	}

}
