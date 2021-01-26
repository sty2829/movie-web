package test;

public class TimeTest {

	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		System.out.println(startTime);
		
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		long endTime = System.currentTimeMillis();
		long executeTime = (endTime - startTime)/1000;
		System.out.println("실행시간 : " + executeTime + "초");
	}
}
