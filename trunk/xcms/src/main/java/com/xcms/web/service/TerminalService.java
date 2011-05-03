package com.xcms.web.service;

import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import com.mss.dal.dao.TerminalDao;

@IocBean
@InjectName
public class TerminalService {

	@Inject
	private TerminalDao terminalDao;

	public TerminalDao getTerminalDao() {
		return terminalDao;
	}

	public void setTerminalDao(TerminalDao terminalDao) {
		this.terminalDao = terminalDao;
	}
	
	
}
