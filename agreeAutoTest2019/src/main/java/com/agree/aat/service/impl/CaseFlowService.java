package com.agree.aat.service.impl;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ContextLoader;

import com.agree.aat.dao.ExecuteFlowsCountMapper;
import com.agree.aat.dao.ExecuteFlowsMapper;
import com.agree.aat.dao.ExecutePlanFlowsMapper;
import com.agree.aat.dao.ExecuteResourceMapper;
import com.agree.aat.entity.ExecuteFlows;
import com.agree.aat.entity.ExecuteFlowsCount;
import com.agree.aat.entity.ExecutePlanFlows;
import com.agree.aat.entity.ExecuteResource;
import com.agree.aat.service.IFlowService;
import com.agree.framework.base.Config;
import com.agree.framework.constant.ApplicationKeyConst;
import com.agree.util.CommUtil;
import com.agree.util.FileUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service
public class CaseFlowService implements IFlowService {
	private static final Logger log = LogManager.getLogger(CaseFlowService.class);

	@Autowired
	ExecuteFlowsMapper executeFlowDO;
	@Autowired
	ExecuteResourceMapper executeResDO;
	@Autowired
	ExecutePlanFlowsMapper executePlanFlowsDao;
	@Autowired
	ExecuteFlowsCountMapper executeFlowsCountDao;
	@Autowired
	private Config config;
	@Autowired
	ExecuteFlowsCountMapper executeflowCountDO;

	@Override
	public void addFlowResult(Map executeInfo) throws Exception {
//		String executeType = (String) executeInfo.get("executeType");
		String executeType = (String) executeInfo.get("scriptType");
		ExecuteFlows executeFlow = null;
		// 插入流水
		if (ApplicationKeyConst.CASE_EXECUTE_TYPE_LZ.equals(executeType)) {
			executeFlow = addExecuteRecordCase(executeInfo);
		} else if (ApplicationKeyConst.CASE_EXECUTE_TYPE_ST.equals(executeType)) {
			executeFlow = addExecuteScriptCase(executeInfo);
		}
		List<String> picPathList = (List<String>) executeInfo.get("picPathList");
		for (String picPath : picPathList) {
			ExecuteResource executeFlowAddi = new ExecuteResource();
			executeFlowAddi.setResfilename(picPath);
			executeFlowAddi.setExecuteid(executeFlow.getExecuteflowid());
			String fileName = new File(picPath).getName();
			executeFlowAddi.setResname(fileName);
			if (picPath.endsWith("aattemp.txt")) {
				executeFlowAddi.setRestype("PRI");
			} else {
				executeFlowAddi.setRestype("PIC");
			}
			// 保存执行资源文件
			addExecuteResource(executeFlowAddi);
		}
		
		//计划结果
		if(executeInfo.get("planflowid")!=null&&!"".equals(executeInfo.get("planflowid"))) {
			
			this.addExecuteFlowsCount(executeFlow);
			ExecutePlanFlows pflows = executePlanFlowsDao.selectByPrimaryKey((String)executeInfo.get("planflowid"));
			if("Y".equals(executeFlow.getExecuteresult())||"1".equals(executeFlow.getExecuteresult())) {
				pflows.setSuccss(pflows.getSuccss()+1);
			}else {
				pflows.setFail(pflows.getFail()+1);
			}
			if(pflows.getSuccss()+pflows.getFail()==pflows.getCount()) {
				pflows.setStatus("2");
			}
			pflows.setEnddate((CommUtil.getDateByStr((String) executeInfo.get("executeEndDate"))));
			executePlanFlowsDao.updateByPrimaryKey(pflows);
			
		}
		
		// 解析分析结果
		dataAnalyze(executeInfo, executeFlow);

	}

	private void addExecuteFlowsCount(ExecuteFlows executeFlow) {
		
		ExecuteFlowsCount efCount = executeflowCountDO.getEFCount(executeFlow.getPlanid(),executeFlow.getCaseid(),executeFlow.getPlanflowid());
		if(efCount==null) {
			ExecuteFlowsCount efc = new ExecuteFlowsCount();
			efc.setExeflowscountid(getAlone());
			efc.setPlanid(executeFlow.getPlanid());
			efc.setPlanflowid(executeFlow.getPlanflowid());
			efc.setCaseid(executeFlow.getCaseid());
			efc.setCasename(executeFlow.getCasename());
			efc.setTotal((long)1);
			if("Y".equals(executeFlow.getExecuteresult())) {
				efc.setSuccess((long)1);
			}else {
				efc.setFail((long)1);
			}
			efc.setRemark("");
			executeflowCountDO.insert(efc);
		}else {
			efCount.setTotal(efCount.getTotal()+1);
			if("Y".equals(executeFlow.getExecuteresult())) {
				efCount.setSuccess(efCount.getSuccess()+1);
			}else {
				efCount.setFail(efCount.getFail()+1);
			}
			executeflowCountDO.updateByPrimaryKey(efCount);
		}
	}
	
	
	
    
    /**
     * 获得一个独一无二的标识
     *
     * @return String UUID
     */
    public static String getAlone() {
        String uuid = UUID.randomUUID().toString();
        long currentTimeMillis = System.currentTimeMillis();
        //去掉“-”符号
        String replaceAll = uuid.replaceAll("-", "");
        String tempId = replaceAll + currentTimeMillis;
        return tempId;
    }
    

	private ExecuteFlows addExecuteRecordCase(Map executeInfo) {
		ExecuteFlows executeFlow = new ExecuteFlows();
		String executeType = (String) executeInfo.get("executeType");
		String id = "";
		try {
			id = CommUtil.getUniqueID();
			executeFlow.setExecuteflowid("F" + id);
		} catch (Exception e) {
			log.error(e);
		}
		executeFlow.setCaseid((String) executeInfo.get("caseId"));
		executeFlow.setCasename((String) executeInfo.get("caseName"));
		executeFlow.setTranscode((String) executeInfo.get("transCode"));
		executeFlow.setPlanid((String) executeInfo.get("planid")==null?"":(String) executeInfo.get("planid"));
		executeFlow.setPlanflowid((String) executeInfo.get("planflowid")==null?"":(String) executeInfo.get("planflowid"));
		executeFlow.setTransname((String) executeInfo.get("transName"));
		executeFlow.setTerminalid((String) executeInfo.get("execlientId"));
		executeFlow.setExeclientip((String) executeInfo.get("execlientIp"));
		executeFlow.setExeclientname((String) executeInfo.get("execlientName"));
//		executeFlow.setExecuterId((String) executeInfo.get("executerId"));
		executeFlow.setExecuter((String) executeInfo.get("executer"));
		executeFlow.setExecuteresult((String) executeInfo.get("executeResult"));
		executeFlow.setStartdate(CommUtil.getDateByStr((String) executeInfo.get("executeStartDate")));
		executeFlow.setEnddate((CommUtil.getDateByStr((String) executeInfo.get("executeEndDate"))));
		executeFlow.setLogfile((String) executeInfo.get("abExecuteLog"));
		executeFlow.setExeclienttype((String) executeInfo.get("scriptType"));
//		executeFlow.setExeclienttype((String) executeInfo.get("componentIndex"));
//		executeFlow.setExeclienttype((String) executeInfo.get("performance"));

		executeFlowDO.insert(executeFlow);
		return executeFlow;
	}

	private void addExecuteResource(ExecuteResource executeFlowAddi) {
		try {
			executeFlowAddi.setResourceid("R" + CommUtil.getUniqueID());
		} catch (Exception e) {
			log.error(e);
		}
		executeResDO.insert(executeFlowAddi);

	}

	
	private void dataAnalyze(Map executeInfo, ExecuteFlows executeFlow) {
		String tradeCode = (String) executeInfo.get("transcode");
		String performances =  executeInfo.get("performance")==null?"": executeInfo.get("performance").toString();
		String components =  executeInfo.get("componentIndex")==null?"": executeInfo.get("componentIndex").toString();
		// 与交易流水一一对应
//		String fileName = null;
//		String directory = new SimpleDateFormat("yyyyMMdd").format(new Date());
//		String rootPath = ContextLoader.getCurrentWebApplicationContext().getServletContext()
//				.getRealPath("/components");
//		try {
//			fileName = FileUtil.getUUID();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		if (tradeCode != null && tradeCode.indexOf("-") != -1) {
//			tradeCode = tradeCode.substring(0, tradeCode.indexOf("-"));
//		}
//		String path = rootPath + File.separator + directory + File.separator;
//		fileName = tradeCode + fileName + ".json";
//		String cpath = File.separator + directory + File.separator + fileName;
//		File file = new File(path);
//		if (!file.exists()) {
//			file.mkdirs();
//		}

//		JSONArray jsonArray = new JSONArray();
//		JSONObject componentJSON = JSONObject.fromObject(components);
//		componentJSON.put("automateResult", ApplicationKeyConst.execute_automateResult);
//		componentJSON.put("sizeOf", sizeof);
//		componentJSON.put("bname", bname);
//		jsonArray.add(componentJSON);
//		jsonArray.add(JSONObject.fromObject(performances));
//		FileUtil.writeFileEncoding(jsonArray.toString(), path + fileName, "UTF-8");

		// 添加流水
//		executeFlow.setNote(cpath);
		executeFlow.setPerformance(performances);
		executeFlow.setComponentindex(components);
		executeFlowDO.updateByPrimaryKey(executeFlow);

	}

	private ExecuteFlows addExecuteScriptCase(Map executeInfo) {
		ExecuteFlows executeFlow = new ExecuteFlows();
		String executeType = (String) executeInfo.get("scriptType");
		String id = "";
		try {
			id = CommUtil.getUniqueID();
			executeFlow.setExecuteflowid("F" + id);
		} catch (Exception e) {
			log.error(e);
		}
		executeFlow.setCaseid((String) executeInfo.get("caseId"));
		executeFlow.setCasename((String) executeInfo.get("caseName"));
		executeFlow.setTranscode((String) executeInfo.get("transCode"));
//		executeFlow.setPlanid((String) executeInfo.get("planid")==null?"":(String) executeInfo.get("planid"));
//		executeFlow.setPlanflowid((String) executeInfo.get("planflowid")==null?"":(String) executeInfo.get("planflowid"));
//		executeFlow.setTransname((String) executeInfo.get("transName"));
//		executeFlow.setTerminalid((String) executeInfo.get("execlientId"));
//		executeFlow.setExeclientip((String) executeInfo.get("execlientIp"));
//		executeFlow.setExeclientname((String) executeInfo.get("execlientName"));
//		executeFlow.setExecuterId((String) executeInfo.get("executerId"));
//		executeFlow.setExecuter((String) executeInfo.get("executer"));
		executeFlow.setExecuteresult((String) executeInfo.get("executeResult"));
		executeFlow.setStartdate(CommUtil.getDateByStr((String) executeInfo.get("executeStartDate")));
		executeFlow.setEnddate((CommUtil.getDateByStr((String) executeInfo.get("executeEndDate"))));
		executeFlow.setLogfile((String) executeInfo.get("abExecuteLog"));
		executeFlow.setExeclienttype((String) executeInfo.get("scriptType"));
//		executeFlow.setExeclienttype((String) executeInfo.get("componentIndex"));
//		executeFlow.setExeclienttype((String) executeInfo.get("performance"));

		executeFlowDO.insert(executeFlow);
		return executeFlow;
	}

	@Override
	public List<ExecuteFlows> getResultFlowList(Map param) {
		return executeFlowDO.getExecuteFlowsByPage(param);
	}

	@Override
	public ExecuteFlows getResultFlowById(String exeid) {
		return executeFlowDO.selectByPrimaryKey(exeid);
	}

	@Override
	public void delFlowById(String executeflowid) {
		executeFlowDO.deleteByPrimaryKey(executeflowid);

	}

	@Override
	public List<ExecuteResource> getExecutResByExeId(String exeId) {
		List<ExecuteResource> reslist = executeResDO.getExecutResByExeId(exeId);
		String prefix = config.getFtPath();
		String filename = "D:\\offic\\ftps\\";
		for (ExecuteResource executeResource : reslist) {
			if (executeResource.getResfilename() != null && !"".equals(executeResource.getResfilename())) {
				String fpath = executeResource.getResfilename();
				executeResource.setResfilename("/image/" + fpath);
			}
		}
		return reslist;
	}

	@Override
	public List<ExecuteFlows> getResultFlowListNoPage(Map flowparam) {
		return executeFlowDO.getExecuteFlowNoPage(flowparam);
	}

	@Override
	public List<ExecutePlanFlows> getReportDetailsListByPlanidNoPage(Map flowparam) {
		return executePlanFlowsDao.getReportDetailsListByPlanidNoPage(flowparam);
	}

	@Override
	public List<ExecuteFlowsCount> getExecuteFlowsCountListNoPage(Map flowparam) {
		return executeFlowsCountDao.getExecuteFlowsCountListNoPage(flowparam);
	}
	
	@Override
	public List<ExecuteFlows> getComponentindexByExecuteflowid(String executeflowid) {
		return executeFlowDO.getListByExecuteflowid(executeflowid);
	}

	@Override
	public List<ExecuteFlows> getPerformanceByExecuteflowid(String executeflowid) {
		return executeFlowDO.getListByExecuteflowid(executeflowid);
	}

	@Override
	public List<ExecuteFlows> getComAndPerByExecuteflowid(String executeflowid) {
		return executeFlowDO.getListByExecuteflowid(executeflowid);
	}


}
