package test;

import java.util.HashMap;
import java.util.Map;

//select dong_code, sido_name, gugun_name, dong_name, 
//lee_name, mot, bunji, ho, load_code, base, build_num, 
//build_sub_num, jibun, move_code from jtest.jibun_address;



public class SplitTest {

	public static void main(String[] args) {
		String addr = "3611010700|세종특별자치시||나성동||0|776|0|"
				+ "361102000002|0|194|0|21554|";
		String[] addrs = addr.split("\\|");
		
		String  col = "DONG_CODE\r\n" + 
				"SIDO_NAME\r\n" + 
				"GUGUN_NAME\r\n" + 
				"DONG_NAME\r\n" + 
				"LEE_NAME\r\n" + 
				"MOT\r\n" + 
				"BUNJI\r\n" + 
				"HO\r\n" + 
				"LOAD_CODE\r\n" + 
				"BASE\r\n" + 
				"BUILD_NUM\r\n" + 
				"BUILD_SUB_NUM\r\n" + 
				"JIBUN\r\n" + 
				"MOVE_CODE";
		String[] cols = col.split("\r\n");
		Map<String,String> addrMap = new HashMap<>();
		for(int i=0; i<addrs.length; i++) {
			addrMap.put(cols[i], addrs[i]);
		}
		System.out.println(addrMap);
	}
}
