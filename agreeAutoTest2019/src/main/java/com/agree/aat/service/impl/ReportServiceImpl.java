package com.agree.aat.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agree.aat.dao.ExecutePlanFlowsMapper;
import com.agree.aat.entity.ExecutePlanFlows;
import com.agree.aat.service.IReportService;

@Service
public class ReportServiceImpl implements IReportService{
	
	private static final Logger log=LogManager.getLogger(ReportServiceImpl.class);
	
	@Autowired
	ExecutePlanFlowsMapper executePlanFlowsDao;

	@Override
	public List<ExecutePlanFlows> getReportDetailsList(Map reportparam) {
		return executePlanFlowsDao.selectAllByPage(reportparam);
	}

	@Override
	public boolean delReportByPlanflowid(String planflowid) {
		return executePlanFlowsDao.deleteByPrimaryKey(planflowid)>0;
		
	}

}
