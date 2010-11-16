package com.ssi.acl.dal.dao.impl;

import java.util.HashMap;
import java.util.List;

import com.ssi.acl.dal.dao.IDictionaryDAO;
import com.ssi.acl.dal.domain.Dictionary;
import com.ssi.common.dal.BaseDAOImpl;

public class DictionaryDAOImpl extends BaseDAOImpl implements IDictionaryDAO {

	public int delete(Integer id) {
		int result = -1;
		result = getEntityDelegate().delete("DELETE_TBL_DICTIONARY_BY_ID",id,getRoute());
		return result;
	}

	public List<Dictionary> find(HashMap map) {
		List<Dictionary> list = null;
		list = getQueryDelegate().queryForList("FIND_DICTIONARY_BY_HASH", map, getRoute());
		return list;
	}

	public int getCount(HashMap map) {
		int count = 0;
		count = (Integer)getQueryDelegate().queryForObject("GET_TBL_DICTIONARY_COUNT",map,getRoute());
		return count;
	}

	public int insert(Dictionary dic) {
		int result = -1;
		result = (Integer)getEntityDelegate().insert("INSERT_TBL_DICTIONARY", dic, getRoute());
		return result;
	}

	public int update(Dictionary dic) {
		int result = -1;
		result = getEntityDelegate().update("UPDATE_TBL_DICTIONARY", dic, getRoute());
		return result;
	}

}
