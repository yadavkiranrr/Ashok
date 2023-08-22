package debug;

public class JavaDebug {

	public void add(int a, int b) {

		System.out.println("Execution started");
		int c = a + b;
		System.out.println(c);
		System.out.println("Execution Ended");
	}

	public void multi(int a, int b) {

		System.out.println("Execution started");
		int c = a * b;
		System.out.println(c);
		System.out.println("Execution Ended");
	}
	
	public static void main(String[] args) {
		
		JavaDebug java=new JavaDebug();
		java.add(2,5);
		java.multi(2, 6);
		
	}

}
