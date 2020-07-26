/*
 * Created on 2005-11-3
 *
 */
package com.hzzk.common.util;

import com.hzzk.common.exception.BusinessException;
import org.apache.commons.beanutils.PropertyUtils;

/**
 * @author 沈超奇
 * 对象属性复制工具
 */
public class PropertyUtil {
    public static void copy(Object to, Object from){
        try {
            PropertyUtils.copyProperties(to, from);
        } catch (Exception e) {
            throw new BusinessException("实体与DTO之间转换出错", e);
        }
    }
}
