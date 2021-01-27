package com.movie.test.dao;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Map;

public interface JusoAddressDAO {

	public int insertJusoAddress(List<Map<String,String>> JusoAddressList);
	
	public void insertJusoAddress(PreparedStatement ps, List<Map<String,String>> JusoAddressList);
}
