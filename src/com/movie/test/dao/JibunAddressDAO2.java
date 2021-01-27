package com.movie.test.dao;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Map;

public interface JibunAddressDAO2 {

	public int insertJibunAddress(PreparedStatement ps, List<Map<String,String>> JibunAddressList);
}
