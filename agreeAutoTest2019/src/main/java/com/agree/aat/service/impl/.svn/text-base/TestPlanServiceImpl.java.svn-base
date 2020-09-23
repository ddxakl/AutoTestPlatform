package com.agree.aat.service.impl;

import java.io.File;
import java.io.OutputStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import com.agree.aat.controller.recordController;
import com.agree.aat.dao.ExecutePlanFlowsMapper;
import com.agree.aat.dao.ExecutePlanMapper;
import com.agree.aat.dao.PlanCaseMapper;
import com.agree.aat.dao.TransItemExpectResultMapper;
import com.agree.aat.entity.AgentCaseBean;
import com.agree.aat.entity.ExecutePlan;
import com.agree.aat.entity.ExecutePlanFlows;
import com.agree.aat.entity.PlanCase;
import com.agree.aat.entity.Record;
import com.agree.aat.entity.TransItemExpectResult;
import com.agree.aat.service.ITestPlanService;
import com.agree.framework.constant.ApplicationKeyConst;
import com.agree.system.dao.ExecuteCaseMapper;
import com.agree.system.dao.ExecuteItemMapper;
import com.agree.system.dao.SystemTerminalMapper;
import com.agree.system.dao.SystemUserMapper;
import com.agree.system.entity.ExecuteCase;
import com.agree.system.entity.ExecuteItem;
import com.agree.system.entity.SystemTerminal;
import com.agree.system.entity.SystemUser;
import com.agree.system.service.ISystemUserService;
import com.agree.util.CommUtil;
import com.agree.util.FileUtil;
import com.agree.util.StringUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service
public class TestPlanServiceImpl implements ITestPlanService{
	
	private static final Logger log=LogManager.getLogger(recordController.class);

	@Autowired
	ExecutePlanMapper planDO;
	@Autowired
	SystemTerminalMapper systemTerminalDO;
	@Autowired
	SystemUserMapper userDo;
	@Autowired
	ExecuteCaseMapper executeCaseDao;
	@Autowired
	private ExecuteItemMapper executeItemDao;
	@Autowired
	private PlanCaseMapper planCaseDO;
	@Autowired
	private ExecutePlanFlowsMapper planflowDO;
	@Autowired
	ExecutePlanMapper executePlanDao;
	@Autowired
	private HttpSession session;
	@Autowired
	TransItemExpectResultMapper itemExpectResultDO;
	
	private boolean execFlag = false;
	
	Map<String, List<Map<String, String>>> excelData = null;
	
	@Override
	public List<ExecutePlan> getPlanList(Map map) {
		List<ExecutePlan> planList = planDO.getPlanList(map);
		return planList;
	}

	@Override
	public boolean addTPlan(ExecutePlan tplan) {
		SystemUser user = (SystemUser)session.getAttribute(ApplicationKeyConst.USER_INFO);
		SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");
		String planid = "P"+System.currentTimeMillis();
		tplan.setPlanid(planid);
		tplan.setCount((long)0);
		tplan.setExecuteresult("0");
		tplan.setCreatedate(new Date());
		tplan.setExecuter(user.getUsername());
		return  planDO.insertSelective(tplan)>0;
		
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

	@Override
	public void uptTPlan(String planId) {
		
	}

	@Override
	public void findCaseAndExecute(JSONObject json) throws Exception {
		String execlientId = json.getString("executeclientid");
		String userId = json.getString("userId");
		String type = json.getString("type");//案例执行数据类型：RP:数据项值数据执行
		
		List<String> transCaseList = json.getJSONArray("caseList"); //执行的用例
		int seqNo=0;//多次执行加入序号
		for (String jsonObject : transCaseList) {
//			Record record = (Record) JSONObject.toBean(jsonObject,Record.class);
//			caseIdList.add(record.getCaseid());
			seqNo++;
			AgentCaseBean caseBean = assemblingAgentCaseBean((Map)json,type,userId,execlientId,seqNo,jsonObject);
			this.executeCaseInfo(caseBean);
		}
		
	}

	
	private String executeCaseInfo(AgentCaseBean caseBean) {
		String executeInfo="";
		if (1>0) {			
			JSONArray executeCaseInfoArray=new JSONArray();			
			SystemTerminal configMachine=systemTerminalDO.selectByPrimaryKey(caseBean.getExeclientId());

			executeCaseInfoArray.add(caseBean.getCasejson());
			try{
				Socket sockectClient=new Socket(configMachine.getIp(),Integer.parseInt(configMachine.getPort()));							
				sockectClient.setSoTimeout(5*1000);
				//发送报文长度
				String executeCaseInfoLen=StringUtils.fillZeroLeft(""+executeCaseInfoArray.toString().getBytes("UTF-8").length,8);
				OutputStream os = sockectClient.getOutputStream();
				//发送报文内容
				String sendInfo=executeCaseInfoLen+executeCaseInfoArray.toString();
				log.info("发送案列执行报文内容：["+sendInfo+"]");
				os.write(sendInfo.getBytes("UTF-8"));
				os.flush();				
				sockectClient.close();				
			}catch(Exception e){
				log.error(e.getMessage());
				return "案例执行超时！";
			}
		}
		return executeInfo;
		
	}

	
	private AgentCaseBean assemblingAgentCaseBean(Map param, String executeType,String userId, String execlientId, int seqNo, String caseId) throws Exception {
		AgentCaseBean caseBean = new AgentCaseBean();
		caseBean.setExecuteType(executeType);	
		//设置基础信息
		JSONObject transCaseInfo = new JSONObject();
		transCaseInfo.put("scriptType", executeType);
		transCaseInfo.put("userId", userId);
		transCaseInfo.put("transCode", executeType);
		setPubInfo(caseBean,userId,execlientId);
		param.put("caseId", caseId);
		param.put("seqNo", String.valueOf(seqNo));
		if(param.get("planflowid")!=null) {
			transCaseInfo.put("planid", param.get("planid"));
			transCaseInfo.put("planflowid", param.get("planflowid"));
		}
		if(ApplicationKeyConst.CASE_EXECUTE_TYPE_LZ.equals(executeType)){
			caseBean.setCaseId(caseId);
			caseBean.setExeclientId(execlientId);
			//设置案例序号
			caseBean.setSeqNo(seqNo+"");
			//设置外挂数据路径
			caseBean.setFilePath("");
			// 设置录制流程案例信息
			setRecFlowCaseInfo(caseBean,transCaseInfo,param);	
		}
		caseBean.setCasejson(transCaseInfo);
		return caseBean;
	}
	

	private void setRecFlowCaseInfo(AgentCaseBean caseBean, JSONObject transCaseInfo, Map params) throws Exception {
		
		String caseId = (String)params.get("caseId");
		String type =  (String)params.get("type");
		String seqNo = (String) params.get("seqNo");
//		String filePath = (String)params.get("filePath");
		String clientId=(String)params.get("executeclientid");
		String userId=(String)params.get("userId");
		
		SystemTerminal baseExecuteClient=this.systemTerminalDO.selectByPrimaryKey(clientId);
		if (baseExecuteClient == null){
			throw new Exception("执行机信息缺失!");
		}
		SystemUser user =userDo.selectUserByName(userId);
		if (user == null){
			throw new Exception("用户信息缺失!");
		}
		ExecuteCase recFlowCase = this.executeCaseDao.selectByPrimaryKey(caseId);
		if(recFlowCase==null){
			throw new Exception("录制案例信息缺失!");
		}
		caseBean.setBaseExecuteClient(baseExecuteClient);
		JSONArray agentTransCase = new JSONArray();
		JSONObject agentCaseStep = new JSONObject();
		JSONArray agentCaseItemList = new JSONArray();
		transCaseInfo.put("execlientId", baseExecuteClient.getTerminalid());
		transCaseInfo.put("execlientIp", baseExecuteClient.getIp());
		transCaseInfo.put("execlientName", baseExecuteClient.getName());
		transCaseInfo.put("executerId", String.valueOf(user.getUserid()));
		transCaseInfo.put("executer", user.getUsername());
		transCaseInfo.put("executeType", (String)params.get("executeType"));
		transCaseInfo.put("caseId", caseId);
		transCaseInfo.put("caseName", recFlowCase.getCasename());
		transCaseInfo.put("transName",recFlowCase.getCasename());
		transCaseInfo.put("transCode", "");
		transCaseInfo.put("transPath", "");		
		transCaseInfo.put("transDesc", recFlowCase.getCasedesc());
		transCaseInfo.put("agentMode", "1");	
		transCaseInfo.put("sceneId", "");
		transCaseInfo.put("sceneName", "");		
		
		agentCaseStep.put("stepName", "步骤一");
		agentCaseStep.put("stepType", "I");	
		
		
		Map<String, List<Map<String,String>>> content = null;//excel中数据内容
		int rowNo = 0;
		if("T".equals(recFlowCase.getBatchcase())){
			rowNo = Integer.parseInt(seqNo);
			content=readContent(recFlowCase.getCaseid());
		}
		
		List<ExecuteItem> ExecuteItem1 = this.executeItemDao.getCaseInfoByCaseid(caseId);
		if(ExecuteItem1 == null){
			transCaseInfo.put("transCode", ExecuteItem1.get(0).getTradecode());
			for(ExecuteItem item:ExecuteItem1){
				if("T".equals(recFlowCase.getBatchcase())&&!content.isEmpty()&&item.getItemvalue()!=null&&!"".equals(item.getItemvalue())){
					replaceRecFlowCaseItem(caseBean,item,rowNo,content,type);
				}
				JSONObject agentCaseItem = new JSONObject();
				agentCaseItem.put("itemName", item.getItemname());
				agentCaseItem.put("itemCode", item.getItemcode());
				agentCaseItem.put("objectType", item.getItemtype());
				agentCaseItem.put("itemValue", item.getItemvalue());
				agentCaseItem.put("addiValue", item.getAddvalue());
				agentCaseItem.put("pageCode", item.getPagecode());
				agentCaseItem.put("isscreen", item.getIsscreen());
				agentCaseItem.put("relation", item.getRelationvalue()==null?"":item.getRelationvalue());
				//填充表达式
				if(item.getRelationvalue()!=null&&!"".equals(item.getRelationvalue())) {
					String relation = item.getRelationvalue();
					if(relation.contains("GET")) {
						agentCaseItem.put("relation", setRelationValue("GET",item,ExecuteItem1));
					}
					
					if(relation.contains("SET")) {
						agentCaseItem.put("relation", setRelationValue("SET",item,ExecuteItem1));
					}
				}
				
				List<TransItemExpectResult> expectList = itemExpectResultDO.getItemExpectResultByItemId(item.getItemid());
				if(expectList != null && expectList.size() > 0){
					List<Map<String,String>> list = new ArrayList<Map<String,String>>();	
					for (TransItemExpectResult transItemExpectResult : expectList) {
						String expression = transItemExpectResult.getExpression();
						 String expectresultcode = transItemExpectResult.getExpectresultcode();
//						 {text6}==\"张三\"  
						 String thrmenod=null;					
						 if(ApplicationKeyConst.AT_EXPECTRESULT_CheckCompoentResult.equals(expectresultcode)) {
							 ArrayList<String> testecpectResult = CommUtil.testecpectResult(expression);
//							 expression
							 for (String dkhexpression : testecpectResult) {
								String  fileds =dkhexpression.substring(1, dkhexpression.length()); //text6  6   别名   id
								String simplexp = parseExpectResultexp(fileds,item, ExecuteItem1);
								expression = expression.replace(dkhexpression, simplexp);
							}
						 }
						 
						 
						Map<String,String> param=new HashMap<String, String>();
						param.put("expectResultCode", transItemExpectResult.getExpectresultcode());
						param.put("expectSql", ""+transItemExpectResult.getRemark());
						param.put("expectJdbc", ""+transItemExpectResult.getIndex1());
						param.put("expression", expression);
						//表达式
//						if(ApplicationKeyConst.) {}
						list.add(param);
					}
					Map<Object,Object> map = new HashMap<Object,Object>();
					map.put("resultMap", JSONArray.fromObject(list).toString());
					agentCaseItem.putAll(map);
				}
				agentCaseItemList.add(agentCaseItem);
			}	
		}else{
			List<ExecuteItem> listItem = this.executeItemDao.getCaseInfoByCaseid(caseId);
			if(listItem.get(0).getTradecode()!=null) {//null 没有
				transCaseInfo.put("transCode", listItem.get(0).getTradecode());
			}
			for(ExecuteItem item:listItem){
				if("T".equals(recFlowCase.getBatchcase())&&!content.isEmpty()&&item.getItemvalue()!=null&&!"".equals(item.getItemvalue())){
					replaceRecFlowCaseItem(caseBean,item,rowNo,content,type);
				}		
				if("".equals(transCaseInfo.getString("transCode"))&&!"".equals(item.getTradecode())&&item.getTradecode()!=null) {
					transCaseInfo.put("transCode", item.getTradecode());
				}
				JSONObject agentCaseItem = new JSONObject();
				agentCaseItem.put("itemName", item.getItemname());
//				agentCaseItem.put("itemName", item.getItemcode());
				agentCaseItem.put("itemCode", item.getItemcode());
				agentCaseItem.put("objectType", item.getItemtype());
				agentCaseItem.put("itemValue", item.getItemvalue());
				agentCaseItem.put("addiValue", item.getAddvalue());
				agentCaseItem.put("pageCode", item.getPagecode());
				agentCaseItem.put("isscreen", item.getIsscreen());
				agentCaseItem.put("relation", item.getRelationvalue()==null?"":item.getRelationvalue());
				
				if(item.getRelationvalue()!=null&&!"".equals(item.getRelationvalue())) {
					String relation = item.getRelationvalue();
					if(relation.contains("GET")) {
						agentCaseItem.put("relation", setRelationValue("GET",item,ExecuteItem1));
					}
					
					if(relation.contains("SET")) {
						agentCaseItem.put("relation", setRelationValue("SET",item,ExecuteItem1));
					}
				}
				
				
				List<TransItemExpectResult> expectList = itemExpectResultDO.getItemExpectResultByItemId(item.getItemid());
				if(expectList != null && expectList.size() > 0){
					List<Map<String,String>> list = new ArrayList<Map<String,String>>();	
					for (TransItemExpectResult transItemExpectResult : expectList) {
						String expression = transItemExpectResult.getExpression();
						 String expectresultcode = transItemExpectResult.getExpectresultcode();
//						 {text6}==\"张三\"  
						 String thrmenod=null;					
						 if(ApplicationKeyConst.AT_EXPECTRESULT_CheckCompoentResult.equals(expectresultcode)) {
							 ArrayList<String> testecpectResult = CommUtil.testecpectResult(expression);
//							 expression
							 for (String dkhexpression : testecpectResult) {
								String  fileds =dkhexpression.substring(1, dkhexpression.length()-1); //text6  6   别名   id
								String simplexp = parseExpectResultexp(fileds,item, ExecuteItem1);
								expression = expression.replace(dkhexpression, simplexp);
							}
						 }
						 
						Map<String,String> param=new HashMap<String, String>();
						param.put("expectResultCode", transItemExpectResult.getExpectresultcode());
						param.put("expectSql", ""+transItemExpectResult.getRemark());
						param.put("expectJdbc", ""+transItemExpectResult.getIndex1());
						param.put("expression", expression);
						//表达式
//						if(ApplicationKeyConst.) {}
						list.add(param);
					}
					Map<Object,Object> map = new HashMap<Object,Object>();
					map.put("resultMap", JSONArray.fromObject(list).toString());
					agentCaseItem.putAll(map);
				}
				agentCaseItemList.add(agentCaseItem);
			}	
		}		
		agentCaseStep.put("stepItemList", agentCaseItemList);
		agentTransCase.add(agentCaseStep);
		transCaseInfo.put("sceneStepList", agentTransCase);		
	}
	
	
	
	public String parseExpectResultexp(String word,ExecuteItem item, List<ExecuteItem> itemList) {
		
		String newexpresion = null;
		for (ExecuteItem executeItem : itemList) {
			
			String itemindex = String.valueOf(executeItem.getItemindex());
			String itemalias = executeItem.getItemalias();
			String itemcode = executeItem.getItemcode();
			if(word.equals(itemindex)) {
				newexpresion="{"+executeItem.getTradecode()+"."+executeItem.getPagecode()+"."+executeItem.getItemcode()+"}";
				break;
			}
			if(word.equals(itemalias)) {
				newexpresion="{"+executeItem.getTradecode()+"."+executeItem.getPagecode()+"."+executeItem.getItemcode()+"}";
				break;
			}
			if(word.equals(itemcode)) {
				newexpresion="{"+executeItem.getTradecode()+"."+executeItem.getPagecode()+"."+executeItem.getItemcode()+"}";
				break;
			}
			}
		
		if(newexpresion==null) {
			newexpresion="{"+item.getTradecode()+"."+item.getPagecode()+"."+word+"}";
		}
		
		return newexpresion;
	}
	
	
	
	
	private String setRelationValue(String typeo,ExecuteItem item, List<ExecuteItem> executeItem1) {
//		"$SET{"+item.getTradecode()+"."+item.getPagecode()+"."+item.getItemcode()+"}$"
		String newRelationVlaue="";
		String relationvalue = item.getRelationvalue();
		if(!relationvalue.contains("{")||!relationvalue.contains("}")) {
			log.error("@setRelationValue : 表达式格式有误！");
			return "";
		}
		boolean breakm = false;
		String[] relations=null;
		relationvalue = relationvalue.replace(typeo, "");
		
		if(relationvalue.contains(",")) {
			relations = relationvalue.split(",");
		}else if(relationvalue.contains("，")){
			relations = relationvalue.split("，");
		}else {
			relationvalue=relationvalue.substring(1, relationvalue.length()-1);
		}
		
		if(relations!=null&&relations.length>1) {
			newRelationVlaue="$GET";
			for (String string : relations) {
				string=string.substring(1, string.length()-1);
				
				for (ExecuteItem executeItem : executeItem1) {
					String itemindex = String.valueOf(executeItem.getItemindex());
					String itemalias = executeItem.getItemalias();
					String itemcode = executeItem.getItemcode();
					
					if(string.equals(itemindex)) {
					newRelationVlaue+="{"+executeItem.getTradecode()+"."+executeItem.getPagecode()+"."+executeItem.getItemcode()+"},";
					breakm=true;
					break;
					}
					if(string.equals(itemalias)) {
						newRelationVlaue+="{"+executeItem.getTradecode()+"."+executeItem.getPagecode()+"."+executeItem.getItemcode()+"},";
						breakm=true;
						break;
					}
					if(string.equals(itemcode)) {
						newRelationVlaue+="{"+executeItem.getTradecode()+"."+executeItem.getPagecode()+"."+executeItem.getItemcode()+"},";
						breakm=true;
						break;
					}
				}
				newRelationVlaue+="{"+item.getTradecode()+"."+item.getPagecode()+"."+string+"},";
			}
			
			
			if(newRelationVlaue.lastIndexOf(",")!=-1) {
				newRelationVlaue=newRelationVlaue.substring(0, newRelationVlaue.length());
				newRelationVlaue+="$";
			}
			
		}else {
			
			for (ExecuteItem executeItem : executeItem1) {
				log.debug("relationvalue 的 值 ：" +relationvalue);
				
//				1 编号 2 别名 3 组件代码  均不匹配作为组件代码传递
				String itemindex = String.valueOf(executeItem.getItemindex());
				String itemalias = executeItem.getItemalias();
				String itemcode = executeItem.getItemcode();
				
				if(typeo.equals("SET")) {
					if(relationvalue.equals(itemindex)) {
						newRelationVlaue="$SET{"+executeItem.getTradecode()+"."+executeItem.getPagecode()+"."+executeItem.getItemcode()+"}$";
						break;
					}
					if(relationvalue.equals(itemalias)) {
						newRelationVlaue="$SET{"+executeItem.getTradecode()+"."+executeItem.getPagecode()+"."+executeItem.getItemcode()+"}$";
						break;
					}
					if(relationvalue.equals(itemcode)) {
						newRelationVlaue="$SET{"+executeItem.getTradecode()+"."+executeItem.getPagecode()+"."+executeItem.getItemcode()+"}$";
						break;
					}
				}else {
						if(relationvalue.equals(itemindex)) {
							newRelationVlaue="$GET{"+executeItem.getTradecode()+"."+executeItem.getPagecode()+"."+executeItem.getItemcode()+"}$";
							break;
						}
						if(relationvalue.equals(itemalias)) {
							newRelationVlaue="$GET{"+executeItem.getTradecode()+"."+executeItem.getPagecode()+"."+executeItem.getItemcode()+"}$";
							break;
						}
						if(relationvalue.equals(itemcode)) {
							newRelationVlaue="$GET{"+executeItem.getTradecode()+"."+executeItem.getPagecode()+"."+executeItem.getItemcode()+"}$";
							break;
						}
				}
				
			}
			
			if("".equals(newRelationVlaue)) {
				if(typeo.equals("SET")) {
					newRelationVlaue="$SET{"+item.getTradecode()+"."+item.getPagecode()+"."+relationvalue+"}$";
				}else {
					newRelationVlaue="$GET{"+item.getTradecode()+"."+item.getPagecode()+"."+relationvalue+"}$";
				}
			}
		}
		
		return newRelationVlaue;
	}


	
	
	private Map<String, List<Map<String, String>>> readContent(String caseid) throws Exception {
		//获取执行数据文件
		if(excelData==null) {
			String rootPath = this.getClass().getResource("/").getPath().replaceAll("^\\/", ""); 
		    rootPath = rootPath.replace("WEB-INF/classes/", "");//webapps/agreeAutoTest2019/
		    String rootPathParent = new File(rootPath).getParentFile().getAbsolutePath(); //webapps
		    String realPath = rootPathParent+ApplicationKeyConst.AT_ASSET_PATH + caseid + "/dataPlugin";
		    File file = new File(realPath);
			File[] tempList = file.listFiles();
			log.info("外挂文件名称："+tempList[0].getName());
			
			try {
				excelData = FileUtil.readEXCEL(realPath+File.separator+tempList[0].getName());
			}catch(NumberFormatException e){
				log.error(e);
				throw new Exception("公共数据配置错误");
			} catch (Exception e) {
				log.error(e);
				e.printStackTrace();
			}
		}
		return excelData;
	}

	/**
	 * @param params
	 * @throws Exception 
	 */
	public void replaceRecFlowCaseItem(AgentCaseBean caseBean, ExecuteItem caseitem, int rowNo,
			Map<String, List<Map<String, String>>> content, String type) throws Exception {
		String value = caseitem.getItemvalue();
		String valueItem = caseitem.getItemalias();
		if (value.startsWith("$") && value.endsWith("$") ) {
			
			// 需要替换数据 批量执行需要序号标示到第几条数据（发执行的时候带入序号）
			// 单条执行给定序号 or 引用给定序号
			String itemVal = value.substring(1, value.length() - 1);
			String sheetName = null;
			int rows = 0;
			int rw = 1;
			int end = 1;
			// 解析sheet页$sheet1.name$
			String sheetValue="";
			// 如果有配置rowNo就取配置的
			if (rows <= 0) {
				rw = rowNo;//
			} else {
				rw = rows;
			}
			
			if (itemVal.indexOf('.') == -1) {//除序号全部执行
				sheetName=itemVal;
				// 修改引用
				if (content.get(sheetName) == null) {
					throw new Exception(sheetName + "页不存在或为数据为空！");
				}
				if(!content.get(sheetName).get(rw - 1).containsKey(valueItem)) {
					return;
				}
				sheetValue = content.get(sheetName).get(rw - 1).get(valueItem);
				if (sheetValue == null||"".equals(sheetValue)) {
					return;
				}
				caseitem.setItemvalue(sheetValue);
			}else {
				sheetName = itemVal.substring(0, itemVal.indexOf("."));
				// 修改引用
				if (content.get(sheetName) == null) {
					throw new Exception(sheetName + "页不存在或为数据为空！");
				}
				// 解析引用的变量
				if (itemVal.lastIndexOf('.') > itemVal.indexOf('.')) {
					end = itemVal.lastIndexOf('.');
				} else {
					end = itemVal.length();
				}
				
				try {
					rows = Integer.parseInt(itemVal.substring(itemVal.indexOf('.') + 1, end));
				} catch (NumberFormatException e) {
					log.error(e);
					throw new Exception("外挂数据引用配置错误" + value);

				}
//				// 如果有配置rowNo就取配置的
//				if (rows <= 0) {
//					rw = rowNo;//
//				} else {
//					rw = rows;
//				}
				if(!content.get(sheetName).get(rw - 1).containsKey(valueItem)) {
					return;
				}
				sheetValue = content.get(sheetName).get(rw - 1).get(valueItem);
				if (sheetValue == null||"".equals(sheetValue)) {
					return;
				}
				caseitem.setItemvalue(sheetValue);
			}
			
		}
	}
	
	
	public static void main(String[] args) {
		ExecuteItem item = new ExecuteItem();
		item.setItemvalue("$aaaa$");
		try {
			new TestPlanServiceImpl().replaceRecFlowCaseItem(null, item, 1, null, "");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 设置公共信息
	 * @param caseBean
	 * @param userId
	 * @param clientId
	 * @throws AATException
	 */
	public void setPubInfo(AgentCaseBean caseBean,String userId,String execlientId) throws Exception{
		/* 获取执行用户信息 */
		
//		SystemUser user =userDo.selectUserByName(userId);
		SystemUser user =userDo.selectUserByName(userId);
		if(user!=null) {
			Map<String,String> userMap = new HashMap<String,String>();
			userMap.put("userId", String.valueOf(user.getUserid()));
			userMap.put("userName", user.getUsername());
			caseBean.setUserMap(userMap);
		}
	
		
		/* 获取执行机信息 */
		SystemTerminal systemTerminal = systemTerminalDO.selectByPrimaryKey(execlientId);
		if (systemTerminal == null){
			throw new Exception("执行机信息缺失!");
		}
		caseBean.setBaseExecuteClient(systemTerminal);
	}

	@Override
	public void delPlan(String planId) {
//		planCaseDO.deleteByPlanid(planId);
		planDO.deleteByPrimaryKey(planId);
		
	}

	@Override
	public List<PlanCase> getPlanCaseList(Map param) {
		List<PlanCase> list = planCaseDO.getPlanCaseList(param);
		return list;
	}

	@Override
	public boolean delPlanCase(Map<String,String> map) {
		return planCaseDO.deleteByPrimaryKey(map.get("id"))>0;
	}

	//TODO 添加轮次案例
	@Override
	public boolean addplancase(Map<String, String> map) {
		String caseid = map.get("caseid");
		ExecuteCase selectByPrimaryKey = executeCaseDao.selectByPrimaryKey(caseid);
		PlanCase pcase = new PlanCase();
		BeanUtils.copyProperties(selectByPrimaryKey,pcase,"planid");
		pcase.setPlanid(map.get("planid"));
		pcase.setId("P"+System.currentTimeMillis());
		return planCaseDO.insertSelective(pcase)>0;
	}

	@Override
	public boolean updateplan(ExecutePlan testplan) {
		return planDO.updateByRecord(testplan)>0;
	}

	@Override
	public void updatePlanExecuteclient(Map<String, String> map) {
		List<String> nameList = new ArrayList<String>();
		String executeclientid = map.get("executeclientid");
		String[] ids = executeclientid.split(",");
		for (int i = 0; i < ids.length; i++) {
			nameList.add(systemTerminalDO.selectByPrimaryKey(ids[i]).getName());
		}
		
		ExecutePlan selectByPrimaryKey = planDO.selectByPrimaryKey(map.get("planid"));
		String str = "";
		if (nameList.size() > 0) {
			for (int i = 0; i < nameList.size(); i++) {
				if (i != nameList.size()-1) {
					str += nameList.get(i)+",";
				}else {
					str += nameList.get(i);
				}
			}
		}
		selectByPrimaryKey.setTargetterminals(str);
		map.put("targetname", str);
		planDO.updateByPrimaryKey(selectByPrimaryKey);
	}

	@Override
	public List<ExecuteCase> getExecuteCaseList(Map param) {
		List<ExecuteCase> planCaseList = executeCaseDao.getPlanCaseList(param);
		return planCaseList;
	}

	@Override
	public void executeplan(Map<String, String> map) {
		String planid = map.get("planid");
		ExecutePlan tplan = planDO.selectByPrimaryKey(planid);
		Map<String,String> param = new HashMap<String,String>();
		List<PlanCase> planCaseList = planCaseDO.getPlanCaseList(map);
//		tplan
		String planflowid=this.addexeplanflows(tplan);
		param.put("planflowid", planflowid);
		param.put("planid", tplan.getPlanid());
		//TODO 现在是多个执行机
		List<String> terNameList = new ArrayList<String>();
		List<SystemTerminal> terList = new ArrayList<SystemTerminal>();
		String target = tplan.getTargetterminals();
		String[] ters = target.split(",");
		for (int i = 0; i < ters.length; i++) {
			terNameList.add(ters[i]);
		}
		for (int i = 0; i < terNameList.size(); i++) {
			terList.add(systemTerminalDO.getEClientByName(terNameList.get(i))); //根据执行机的名字查所有
		}
		
		int idx = -1;
		int n = 2;
		int m = -1;
		String terId = "";
		for (PlanCase planCase : planCaseList) {
			idx++;
			if(planCase.getCasetype().equals(ApplicationKeyConst.CASE_EXECUTE_TYPE_LZ)) {
				try {
					param.put("type", planCase.getCasetype());
					param.put("caseId", planCase.getCaseid());
					param.put("seqNo", String.valueOf(0));
					param.put("filePath", "");
					if (terList.size() >= planCaseList.size()) {
						param.put("executeclientid",terList.get(idx).getTerminalid());
						terId = terList.get(idx).getTerminalid();
					}else if(terList.size() < planCaseList.size() && idx < terList.size()) {
						param.put("executeclientid",terList.get(idx).getTerminalid());
						terId = terList.get(idx).getTerminalid();
					}else if(terList.size() < planCaseList.size() && idx >= terList.size() && idx <= (terList.size()*n-1)){
						param.put("executeclientid",terList.get(idx-terList.size()*(n-1)).getTerminalid());
						m++;
						if (m==terList.size()) {
							n++;
						}
						terId = terList.get(idx-terList.size()*(n-1)).getTerminalid();
					}
					param.put("userId", tplan.getExecuter());
					AgentCaseBean assemblingAgentCaseBean = assemblingAgentCaseBean(param, planCase.getCasetype(), tplan.getExecuter(), terId, 0, planCase.getCaseid());
					this.executeCaseInfo(assemblingAgentCaseBean);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	private String addexeplanflows(ExecutePlan tplan) {
		ExecutePlanFlows executePlanFlows = new ExecutePlanFlows();
		executePlanFlows.setPlanflowid("PF"+System.currentTimeMillis());
		executePlanFlows.setPlanname(tplan.getPlanname());
		executePlanFlows.setPlandesc(tplan.getPlandesc());
		executePlanFlows.setPlanid(tplan.getPlanid());
		executePlanFlows.setExecuter(tplan.getExecuter());
		executePlanFlows.setCount(tplan.getCount());
		executePlanFlows.setFail((long)0);
		executePlanFlows.setSuccss((long)0);
		executePlanFlows.setTargetterminals(tplan.getTargetterminals());
		SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		executePlanFlows.setStartdate(CommUtil.getDateByStr(dateFormat.format(new Date())));
		executePlanFlows.setStatus("1");
		executePlanFlows.setRemark("");
		tplan.setExecuteresult("1");
		planDO.updateByPrimaryKey(tplan);
		planflowDO.insert(executePlanFlows);
		return executePlanFlows.getPlanflowid();
	}

	@Override
	public void updateCountAddByPlanid(String planid) {
		executePlanDao.updateCountAddByPlanid(planid);
	}

	@Override
	public void updateCountMinByPlanid(String planid) {
		executePlanDao.updateCountMinByPlanid(planid);
	}

	@Override
	public List<ExecutePlan> getPlanListById(String planid) {
		return planDO.getPlanListById(planid);
	}

	@Override
	public Set<String> getTransItem(String caseId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean getExeFlag() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void resetExecFlag() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public JSONArray getTransItems(String caseId) {
		int loop = 0;
		JSONArray jsonarr = new JSONArray();// 引用最多的作为循环次数
		List<ExecuteItem> ExecuteItem = this.executeItemDao.getCaseInfoByCaseid(caseId);
		try {
			excelData = readContent(caseId);
			System.out.println(excelData);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Set<String> PubData = new HashSet<String>();// 一个案例中有可以跨sheet页引用
		for (ExecuteItem executeItem : ExecuteItem) {
			String itemalias = executeItem.getItemalias();
			String itemvalue = executeItem.getItemvalue();
			if (executeItem.getItemvalue() != null && executeItem.getItemvalue().indexOf('$') != -1) {
				if (executeItem.getItemvalue().lastIndexOf('.') != -1) {
					execFlag = true;
				}else {
					PubData.add(getSheetNopage(executeItem.getItemvalue()));
					
				}
			}
		}

		// {Sheet1=[{but=a1}, {but=a2}, {but=a3}, {but=a4}, {but=a5}, {but=a6},
		// {but=a7}]}

		if (!this.getExeFlag()) {
			for (String str : PubData) {
				if (PubData.size() == 1) {
					if (excelData.get(str) == null) {
						log.debug(str + " 页数据不存在！");
						return null;
					}
					loop = excelData.get(str).size();
				} else {// TODO 多种情况待完善
					if (excelData.get(str) == null) {
						log.debug(str + " 页数据不存在！");
						return null;
					}
					loop = loop < excelData.get(str).size() ? loop : excelData.get(str).size();
				}
			}
		} else {
			loop = 1;
		}

		this.resetExecFlag();
		for (int i = 0; i < loop; i++) {
			jsonarr.add(caseId);
		}

		return jsonarr;
	}
	
	
	private String getSheetNo(String value){
		
		return value.substring(1, value.indexOf('.'));
	}

	private String getSheetNopage(String value){
		
		return value.substring(1, value.length() - 1);
	}

	@Override
	public List<String> getPlanCaseListRtnPid() {
		return planCaseDO.getPlanCaseListRtnPid();
	}

	@Override
	public List<String> getLatestPlanid(String executer) {
		return planDO.getLatestPlanid(executer);
	}

	

}
