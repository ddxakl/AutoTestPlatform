package com.agree.aat.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.agree.aat.entity.ExpectResult;
import com.agree.aat.service.IExpectResultService;
import com.agree.framework.base.ServiceReturn;
import com.agree.framework.constant.ResultCodeEnum;
import com.agree.system.entity.ExecuteCase;
import com.agree.system.entity.ExecuteItem;
import com.agree.system.service.IExecuteCaseService;
import com.agree.system.service.IExecuteItemService;
import com.agree.system.service.ISystemUserService;
import com.agree.util.ExportExcelUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 录制相关操作
 * @author xrp09
 *
 */
@RestController
@RequestMapping("playback")
public class recordController {
	
	private static final Logger log=LogManager.getLogger(recordController.class);
	@Autowired
	IExecuteCaseService executeCaseService;
	@Autowired
	IExecuteItemService executeItemService;
	@Autowired
	ISystemUserService systemUserService;
	@Autowired
	IExpectResultService expectResSerivce;
	
	@RequestMapping(value = "/record", method = RequestMethod.GET)
    public Map<String, Object> getRecord(HttpServletRequest request) {
		ServiceReturn result = null;
        Map<String, Object> map = new HashMap<String, Object>();
        Integer pageSize = Integer.valueOf(request.getParameter("pageSize") == null ? "10" : request.getParameter("pageSize"));// 每页显示的size
        Integer draw = Integer.valueOf(request.getParameter("draw") == null ? "0" : request.getParameter("draw"));// 记录操作的次数 每次加1
        Integer page = Integer.valueOf(request.getParameter("page") == null ? "0" : request.getParameter("page"));//
        String casebelong = request.getParameter("casebelong");
        String casename = request.getParameter("casename");
        String abversion = request.getParameter("abversion");
        String tradecodes = request.getParameter("tradecodes");
        Map executeCase = new HashMap<>();
        executeCase.put("casebelong", casebelong);
        executeCase.put("casename", casename);
        executeCase.put("abversion", abversion);
        executeCase.put("casetype", "RP");
        executeCase.put("tradecodes", tradecodes);
        PageHelper.startPage(page, pageSize);
		List<ExecuteCase> executeCaseList = executeCaseService.getAllExecuteCase(executeCase);
        final PageInfo<ExecuteCase> pageInfo = new PageInfo<>(executeCaseList);
        map.put("draw", draw);
        map.put("total", pageInfo.getTotal());//数据总条数
        map.put("pageData", executeCaseList);//数据集合
		try {
			result = new ServiceReturn(ResultCodeEnum.RESULT_SUCCESS);
			result.setData(executeCaseList);
		} catch (Exception e) {
			result = new ServiceReturn(ResultCodeEnum.RESULT_FAIL);
			log.error("查询录制案例出错："+e.getMessage());
		}
        return map;
    }
	
	@RequestMapping(value = "/getListByCaseid", method = RequestMethod.POST)
	public Map<String, Object> getListByCaseid(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
        String caseid = request.getParameter("caseid");
		List<ExecuteCase> caseList = executeCaseService.getListByCaseid(caseid);
		String casedesc = caseList.get(0).getCasedesc();
		if (casedesc==null) {
			caseList.get(0).setCasedesc("");
		}
		map.put("dataList", caseList);
		return map;
	}
	
	@RequestMapping(value = "/existByCaseidentifier", method = RequestMethod.POST)
	public boolean existByCaseidentifier(HttpServletRequest request) {
        String caseidentifier = request.getParameter("caseIdentifier");
		List<ExecuteCase> caseList = executeCaseService.existByCaseidentifier(caseidentifier);
		if (caseList.size()>0) {
			return true;
		}
		return false;
	}
	
	@RequestMapping(value = "/selectBatchcaseByCaseid", method = RequestMethod.POST)
	public boolean selectBatchcaseByCaseid(HttpServletRequest request) {
        String caseid = request.getParameter("caseid");
		String batchcaseString = executeCaseService.selectBatchcaseByCaseid(caseid);
		if ("T".equals(batchcaseString)) {
			return true;
		}
		return false;
	}
	
	@RequestMapping(value = "/selectCaseidsByBatchcase", method = RequestMethod.POST)
	public List<String> selectCaseidsByBatchcase() {
		List<String> caseidList = executeCaseService.selectCaseidsByBatchcase("T");
		return caseidList;
	}
	
	
	/**
	  *  判断别名是否重复
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getItemAliasByCaseid", method = RequestMethod.POST)
	public String getItemAliasByCaseid(HttpServletRequest request) {
        String caseid = request.getParameter("caseid");
        String input = request.getParameter("input");
		List<ExecuteItem> aliasList = executeItemService.getItemAliasByCaseid(caseid);
		for (int i = 0; i < aliasList.size(); i++) {
			if (aliasList.get(i).getItemalias()!=null) {
				if (aliasList.get(i).getItemalias().replace(" ", "").equals(input.replace(" ", ""))) {
					return "alias";
				}
			}
		}
		List<ExecuteItem> itemIndexList = executeItemService.getItemIndexByCaseid(caseid);
		for (int i = 0; i < itemIndexList.size(); i++) {
			if (String.valueOf(itemIndexList.get(i).getItemindex()).equals(input.replace(" ", ""))) {
				return "index";
			}
		}
		List<ExecuteItem> itemCodeList = executeItemService.getItemCodeByCaseid(caseid);
		for (int i = 0; i < itemCodeList.size(); i++) {
			if (itemCodeList.get(i).getItemcode()!=null) {
				if (itemCodeList.get(i).getItemcode().replace(" ", "").equals(input.replace(" ", ""))) {
					return "code";
				}
			}
		}
		return "ok";
	}
	
	/**
	  *  查询所有预期结果
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/expectRes", method = RequestMethod.POST)
	public List<ExpectResult> getExpectRes() {
		List<ExpectResult> expResList = expectResSerivce.getExpectRes();
		return expResList;
	}
	
	/**
	  *  查询所有预期结果名称
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/expectResName", method = RequestMethod.POST)
	public List<String> getAllExpectResName() {
		List<String> expResNameList = expectResSerivce.getAllExpectResName();
		return expResNameList;
	}
	
	/**
     * <B>方法名称：删除案例</B><BR>
     * <B>概要说明：</B><BR>
     *
     * @return
     */
    @RequestMapping(value = "/removes", method = RequestMethod.POST)
    public String removeByCaseid(HttpServletRequest request) {
        String caseid = request.getParameter("caseid");
        if (!"".equals(caseid) && null != caseid) {
        	executeCaseService.removeByCaseid(caseid);
		}else {
			log.error("caseid为空!");
		}
        return "";
    }
    
    /**
     * <B>方法名称：删除案例</B><BR>
     * <B>概要说明：</B><BR>
     *
     * @return
     */
    @RequestMapping(value = "/removesItem", method = RequestMethod.POST)
    public String removeByItemid(HttpServletRequest request) {
        String itemid = request.getParameter("itemid");
        if (!"".equals(itemid) && null != itemid) {
        	ExecuteItem executeItem = executeCaseService.getListByItemid(itemid);
        	String caseid = executeItem.getCaseid();
        	long itemindex = executeItem.getItemindex();
        	executeCaseService.removeByItemid(itemid);
        	List<String> itemidList = executeCaseService.getItemidByCaseid(caseid);
        	for (String rtnItemid : itemidList) {
        		long rtnItemindex = executeCaseService.getItemindexByItemid(rtnItemid);
        		if (rtnItemindex > itemindex) {
        			executeCaseService.updateItemIndex(rtnItemid, (rtnItemindex-1));
				}
			}
		}else {
			log.error("itemid为空!");
		}
        return "";
    }
    
    /**
     * <B>方法名称：上移更新索引</B><BR>
     * <B>概要说明：</B><BR>
     *
     * @return
     */
    @RequestMapping(value = "/updateItemindexUp", method = RequestMethod.POST)
    public String updateItemindexUp(HttpServletRequest request) {
        String itemid = request.getParameter("itemid");
        if (!"".equals(itemid) && null != itemid) {
        	ExecuteItem executeItem = executeCaseService.getListByItemid(itemid);
        	long itemindex = executeItem.getItemindex();
        	String caseid = executeItem.getCaseid();
        	List<String> itemidList = executeCaseService.getItemidByCaseid(caseid);
        	for (String rtnItemid : itemidList) {
        		long rtnItemindex = executeCaseService.getItemindexByItemid(rtnItemid);
        		if (rtnItemindex==(itemindex-1)) {
        			executeCaseService.updateItemIndex(rtnItemid, itemindex);
        			executeCaseService.updateItemIndex(itemid, (itemindex-1));
        			return "";
				}
			}
		}else {
			log.error("itemid为空!");
		}
        return "";
    }
    
    /**
     * <B>方法名称：下移更新索引</B><BR>
     * <B>概要说明：</B><BR>
     *
     * @return
     */
    @RequestMapping(value = "/updateItemindexDown", method = RequestMethod.POST)
    public String updateItemindexDown(HttpServletRequest request) {
        String itemid = request.getParameter("itemid");
        if (!"".equals(itemid) && null != itemid) {
        	ExecuteItem executeItem = executeCaseService.getListByItemid(itemid);
        	long itemindex = executeItem.getItemindex();
        	String caseid = executeItem.getCaseid();
        	List<String> itemidList = executeCaseService.getItemidByCaseid(caseid);
        	for (String rtnItemid : itemidList) {
        		long rtnItemindex = executeCaseService.getItemindexByItemid(rtnItemid);
        		if (rtnItemindex==(itemindex)+1) {
        			executeCaseService.updateItemIndex(rtnItemid, itemindex);
        			executeCaseService.updateItemIndex(itemid, (itemindex+1));
        			return "";
				}
			}
		}else {
			log.error("itemid为空!");
		}
        return "";
    }
    
    /**
     * <B>方法名称：统计有多少个item</B><BR>
     * <B>概要说明：</B><BR>
     *
     * @return
     */
    @RequestMapping(value = "/countItem", method = RequestMethod.POST)
    public String countItem(HttpServletRequest request) {
        String caseid = request.getParameter("caseid");
        List<String> itemidList = new ArrayList<String>();
        if (!"".equals(caseid) && null != caseid) {
        	itemidList = executeCaseService.getItemidByCaseid(caseid);
		}else {
			log.error("caseid为空!");
		}
        return String.valueOf(itemidList.size());
    }
    
    /**
     * <B>方法名称：新增一条数据项</B><BR>
     * <B>概要说明：</B><BR>
     *
     * @return
     */
    @RequestMapping(value = "/insertItem", method = RequestMethod.POST)
    public String insertItem(HttpServletRequest request) {
    	String itemid = "I"+System.currentTimeMillis();
    	ExecuteItem executeItem = new ExecuteItem();
    	executeItem.setItemid(itemid);
    	executeItem.setItemcode(request.getParameter("itemcode"));
    	executeItem.setItemvalue(request.getParameter("itemvalue"));
    	executeItem.setAddvalue(null);
    	executeItem.setTradecode(request.getParameter("tradecode"));
    	executeItem.setPagecode(request.getParameter("pagecode"));
    	executeItem.setItemtype(request.getParameter("itemtype"));
    	executeItem.setCreatedate(new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss").format(new Date()));
    	executeItem.setCaseid(request.getParameter("caseid"));
    	executeItem.setItemindex(Long.parseLong(request.getParameter("itemindex"))+1);
    	executeItem.setItemname(request.getParameter("itemname"));
    	executeItem.setItemevent(request.getParameter("itemevent"));
    	executeItem.setItemalias(null);
    	executeItem.setIsscreen("N");
    	executeItem.setRelationvalue(null);
    	executeItemService.insertSelective(executeItem);
        return itemid;
    }
    
    /**
     * <B>方法名称：修改item</B><BR>
     * <B>概要说明：</B><BR>
     *
     * @return
     */
    @RequestMapping(value = "/uptItemByItemid", method = RequestMethod.POST)
    public void uptItemByItemid(HttpServletRequest request) {
    	ExecuteItem executeItem = new ExecuteItem();
    	executeItem.setItemid(request.getParameter("itemid"));
    	executeItem.setItemcode(request.getParameter("itemcode"));
    	executeItem.setItemvalue(request.getParameter("itemvalue"));
    	executeItem.setTradecode(request.getParameter("tradecode"));
    	executeItem.setPagecode(request.getParameter("pagecode"));
    	executeItem.setItemtype(request.getParameter("itemtype"));
    	executeItem.setItemname(request.getParameter("itemname"));
    	executeItem.setItemevent(request.getParameter("itemevent"));
    	executeItemService.uptItemByItemid(executeItem);
    }
    
    /**
     * <B>方法名称：查询案例信息</B><BR>
     * <B>概要说明：</B><BR>
     *
     * @return
     */
    @RequestMapping(value = "/selectcaseinfo/{caseid}", method = RequestMethod.GET)
    public Map<String, Object> getCaseDataList(@PathVariable("caseid") String caseid, HttpServletRequest request) {
    	Map<String, Object> map = new HashMap<String, Object>(); 
		Integer pageSize = Integer.valueOf(request.getParameter("pageSize")==null?"10":request.getParameter("pageSize"));// 每页显示的size
		Integer draw = Integer.valueOf(request.getParameter("draw")==null?"0":request.getParameter("draw"));// 记录操作的次数 每次加1
		Integer page = Integer.valueOf(request.getParameter("page")==null?"0":request.getParameter("page"));// 记录操作的次数 每次加1
		PageHelper.startPage(page,pageSize);	
		
    	List<ExecuteItem> caseInfoList = executeItemService.getCaseInfo(caseid);
		final PageInfo<ExecuteItem> pageInfo = new PageInfo<>(caseInfoList);
		
		map.put("draw", draw); 
	    map.put("total",pageInfo.getTotal());//数据总条数 
	    map.put("pageData", caseInfoList);//数据集合 
    	
        return map;
    }
    
    /**
     * <B>方法名称：双击修改item</B><BR>
     * <B>概要说明：</B><BR>
     *
     * @return
     */
    @RequestMapping(value = "/updateItem", method = RequestMethod.POST)
    public String updateItem(HttpServletRequest request) {
        String itemid = request.getParameter("itemid");
        String column = request.getParameter("column");
        String input = request.getParameter("input");
        if (!"".equals(itemid) && null != itemid) {
        	if ("6".equals(column)) {
        		ExecuteItem itemInfo = executeItemService.getItemInfo(itemid);
        		String isscreen = itemInfo.getIsscreen();
        		if (isscreen!=null && isscreen.equals(input)) {
					return "0";
				}else {
					executeCaseService.updateByItemid(itemid, column, input);
				}
			}else {
				executeCaseService.updateByItemid(itemid, column, input);
			}
		}else {
			log.error("itemid为空!");
		}
        return "";
    }
    
    /**
     * 修改组件回显
     * @param request
     * @return
     */
    @RequestMapping(value = "/getItemListByItemid", method = RequestMethod.POST)
	public Map<String, Object> getItemListByItemid(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
        String itemid = request.getParameter("itemid");
		List<ExecuteItem> itemList = executeItemService.getItemListByItemid(itemid);
		map.put("dataList", itemList);
		return map;
	}
    
    /**
     * <B>方法名称：导出案例数据到excel.xlsx</B><BR>
     * <B>概要说明：</B><BR>
     *
     * @return
     */
    @RequestMapping(value = "/exportData", method = RequestMethod.POST)
    public void exportData(HttpServletRequest request, HttpServletResponse response) {
    	// 定义表的标题
        String title = "录制案例数据导出表";
        //定义表的列名
        String[] rowsName = new String[] { "案例编号", "案例名称", "案例类型", "关联交易码", "案例描述", "创建人", "创建时间" };
        //定义表的内容
        List<Object[]> dataList = new ArrayList<Object[]>();
        String parameter = request.getParameter("exportHidden");
        JSONArray json = JSONArray.fromObject(parameter);
		JSONObject[] jsb = new JSONObject[json.size()];
        for (int i = 0; i < json.size(); i++) {
        	jsb[i] = json.getJSONObject(i);
        	Object[] objs = new Object[7];
    		objs[0] = jsb[i].get("caseidentifier");
    		objs[1] = jsb[i].get("casename");
    		objs[2] = jsb[i].get("casetype");
    		objs[3] = jsb[i].get("tradecodes");
    		objs[4] = jsb[i].get("casedesc");
    		objs[5] = jsb[i].get("userid");
    		objs[6] = jsb[i].get("createdate");
            dataList.add(objs);
		}
		
		// 创建ExportExcel对象
		ExportExcelUtil excelUtil = new ExportExcelUtil();
		try{
            String fileName= new String("案例集合.xlsx".getBytes("UTF-8"),"iso-8859-1"); //excel文件名
            excelUtil.exportExcel(title,rowsName,dataList,fileName,response);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
//    /**
//     * <B>方法名称：</B><BR>
//     * <B>概要说明：已添加预期结果</B><BR>
//     *
//     * @return
//     */
//    @RequestMapping(value = "/getExpectResult/{itemid}", method = RequestMethod.GET)
//    public Map<String, Object> getExpectResult(@PathVariable("itemid") String itemid, HttpServletRequest request) {
//    	Map<String, Object> map = new HashMap<String, Object>(); 
//		Integer pageSize = Integer.valueOf(request.getParameter("pageSize")==null?"10":request.getParameter("pageSize"));// 每页显示的size
//		Integer draw = Integer.valueOf(request.getParameter("draw")==null?"0":request.getParameter("draw"));// 记录操作的次数 每次加1
//		Integer page = Integer.valueOf(request.getParameter("page")==null?"0":request.getParameter("page"));// 记录操作的次数 每次加1
//		PageHelper.startPage(page,pageSize);	
//		
//    	List<TransItemExpectResult> list = executeItemService.getExpectResult(itemid);
//		final PageInfo<TransItemExpectResult> pageInfo = new PageInfo<>(list);
//		
//		map.put("draw", draw); 
//	    map.put("total",pageInfo.getTotal());//数据总条数 
//	    map.put("pageData", list);//数据集合 
//        return map;
//    }
    
    
	@RequestMapping(value = "/downloadFile/{caseid}", method = RequestMethod.GET)
	public void downloadFile(@PathVariable("caseid") String caseid, HttpServletResponse response, HttpServletRequest request) {
		ServletOutputStream outStr = null;
		FileInputStream dis = null;
		byte[] b = new byte[1024];
		int dr = 0;
		File file = new File(request.getSession().getServletContext().getRealPath("/"));
		String realPath = file.getParent() + File.separator + "resource" + File.separator + "assets" + File.separator + caseid + File.separator + "dataPlugin";
		File realfile = new File(realPath);
		File[] tempList = realfile.listFiles();
		String filename = realPath + File.separator + tempList[0].getName();
		response.setCharacterEncoding("utf-8");
		// 设置响应的内容类型
		response.setContentType("application/octet-stream");
		// 设置文件的名称和格式
		try {
			response.addHeader("Content-Disposition", "attachment;filename=" + new String(tempList[0].getName().getBytes("gb2312"),"ISO-8859-1"));// 设置名称格式，没有这个中文名称无法显示
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		try {
			dis = new FileInputStream(filename);
			response.setContentLength(dis.available());
			outStr = response.getOutputStream();
			while ((dr = dis.read(b)) != -1) {
				outStr.write(b, 0, dr);
			}
			outStr.flush();
		} catch (Exception e) {
			log.error("导出文件文件出错:{}", e);
		} finally {
			try {
				outStr.close();
			} catch (Exception e) {
				log.error("关闭流对象出错 e:{}", e);
			}
		}
	}
    

}
