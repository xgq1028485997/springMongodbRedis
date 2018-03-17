package com.excel.service;

import java.util.List;

public interface LoginService {

	List<Object> getUserList(StringBuffer where,List<Object> parms);

}
