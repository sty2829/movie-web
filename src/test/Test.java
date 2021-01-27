package test;

public class Test {

	public static void main(String[] args) {
		
		String col = "code\r\n" + 
				"code_num\r\n" + 
				"dong_code\r\n" + 
				"sido_name\r\n" + 
				"gugun_name\r\n" + 
				"dong_name\r\n" + 
				"lee_name\r\n" + 
				"san\r\n" + 
				"jibun\r\n" + 
				"jibun_sub\r\n" + 
				"major";
		String[] cols = col.split("\r\n");
		
		for(int i=0; i<cols.length; i++) {
			System.out.println(cols[i]);
		}
	}
}
