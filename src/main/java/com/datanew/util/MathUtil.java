package com.datanew.util;

import java.math.BigDecimal;

/**
 * @author 沈超奇 数字运算工具集
 */
public class MathUtil {
	/**
	 * 两Double型数据相加
	 * 
	 * @param value
	 * @param addValue
	 * @return
	 */
	public static Double addDoubles(Double value, Double addValue) {
		BigDecimal sum = new BigDecimal(0);
		if (value != null) {
			sum = new BigDecimal(value.toString());
		}
		if (addValue != null) {
			sum = sum.add(new BigDecimal(addValue.toString()));
		}
		
		BigDecimal one = new BigDecimal("1");
		sum = sum.divide(one, 8, BigDecimal.ROUND_HALF_UP);
		
		return new Double(sum.doubleValue());
	}
	/**
	 * 两Double型数据相减
	 * 
	 * @param value
	 * @param delValue
	 * @return
	 */
	public static Double delDoubles(Double value, Double addValue) {
		BigDecimal sum = new BigDecimal("0");
		if (value != null) {
			sum = new BigDecimal(value.toString());
		}
		if (addValue != null) {
			sum = sum.subtract(new BigDecimal(addValue.toString()));
		}
		
		BigDecimal one = new BigDecimal("1");
		sum = sum.divide(one, 8, BigDecimal.ROUND_HALF_UP);
		return new Double(sum.doubleValue());
	}

	/**
	 * 两数相乘
	 * 
	 * @param value1
	 * @param value2
	 * @return Double
	 */
	public static Double multiply(Object value1, Object value2) {
		if (value1 == null || value2 == null) {
			return new Double(0);
		}
		BigDecimal val1 = new BigDecimal(value1.toString());
		BigDecimal val2 = new BigDecimal(value2.toString());

		BigDecimal one = new BigDecimal("1");
		BigDecimal bd = (val1.multiply(val2)).divide(one, 8, BigDecimal.ROUND_HALF_UP);
		return new Double(bd.doubleValue());
	}

	/**
	 * 两Integer对象相加
	 * 
	 * @param value
	 * @param addValue
	 * @return
	 */
	public static Integer addInteger(Integer value, Integer addValue) {
		int sum = 0;
		if (value != null) {
			sum = value.intValue();
		}
		if (addValue != null) {
			sum += addValue.intValue();
		}
		return new Integer(sum);
	}
	
	public static Long addLong(Long value, Long addValue)
	{
		long sum = 0L;
		if (value != null)
			sum = value.longValue();
		if (addValue != null)
			sum += addValue.longValue();
		return new Long(sum);
	}

}
