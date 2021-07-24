package com.yjin.configuration.mybatis.typehandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

/**
 * 내부에서는 Boolean 값을 사용
 * DB에서는 Y, N 값을 사용
 * @author yjin
 */
public class StringValueBooleanTypeHandler implements TypeHandler<Boolean> {

	/**
	 * Boolean 값을 Y, N 값으로 바꾸기
	 */
	@Override
	public void setParameter(PreparedStatement ps, int i, Boolean parameter, JdbcType jdbcType) throws SQLException {
		ps.setString(i, BooleanUtils.toString(parameter, "Y", "N"));
	}

	/**
	 * Y, N 값을 Boolean 값으로 바꾸기
	 */
	@Override
	public Boolean getResult(ResultSet rs, String columnName) throws SQLException {
		return BooleanUtils.toBoolean(rs.getString(columnName));
	}

	@Override
	public Boolean getResult(ResultSet rs, int columnIndex) throws SQLException {
		return BooleanUtils.toBoolean(rs.getString(columnIndex));
	}

	@Override
	public Boolean getResult(CallableStatement cs, int columnIndex) throws SQLException {
		return BooleanUtils.toBoolean(cs.getString(columnIndex));
	}

}
