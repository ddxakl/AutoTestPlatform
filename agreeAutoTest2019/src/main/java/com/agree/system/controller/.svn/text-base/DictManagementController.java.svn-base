package com.agree.system.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.agree.framework.constant.ResultCodeEnum;
import com.agree.system.entity.DictType;
import com.agree.system.entity.DictValue;
import com.agree.system.entity.SystemUser;
import com.agree.system.service.IDictManagmentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/dictionaryManagement")
public class DictManagementController {
	
	private static final Logger log=LogManager.getLogger(DictManagementController.class);
	
	@Autowired
	private IDictManagmentService dictService;
	
	@RequestMapping("/dictionary")
	public String dictionaryManage() {
		return "aat/dictionary/dictionary";
	}
	
	/**
	 * <B>方法名称：获取全部</B><BR>
	 * <B>概要说明：</B><BR>
	 * @return
	 */
	@RequestMapping(value = "/getList", method = RequestMethod.GET)
	@ResponseBody
	public List<DictType> getList() {
		return dictService.selectAllType();
	}
	
	@RequestMapping(value = "/getListById", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getListById(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
        String dicttypeid = request.getParameter("dicttypeid");
		List<DictType> dictList = dictService.getListById(dicttypeid);
		map.put("dataList", dictList);
		return map;
	}
	
	@RequestMapping(value="/dicttypeList",method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getDictTypeList(HttpServletRequest request){
		Map<String, Object> map = new HashMap<String, Object>(); 
		
		Integer pageSize = Integer.valueOf(request.getParameter("pageSize")==null?"10":request.getParameter("pageSize"));// 每页显示的size
		Integer draw = Integer.valueOf(request.getParameter("draw")==null?"0":request.getParameter("draw"));// 记录操作的次数 每次加1
		Integer page = Integer.valueOf(request.getParameter("page")==null?"0":request.getParameter("page"));// 记录操作的次数 每次加1
		String dictcname = request.getParameter("dictcname");
		String dictename = request.getParameter("dictename");
		Map dictInfo =new HashMap<>();
		dictInfo.put("dictcname", dictcname);
		dictInfo.put("dictename", dictename);
		PageHelper.startPage(page,pageSize);
		List<DictType> dicttypeList = dictService.getAllDict(dictInfo);
		final PageInfo<DictType> pageInfo = new PageInfo<>(dicttypeList);
		
		map.put("draw", draw); 
	    map.put("total",pageInfo.getTotal());//数据总条数 
	    map.put("pageData", dicttypeList);//数据集合 
		return map;
	}
	
	/**
	 * <B>方法名称：添加字典类型</B><BR>
	 * <B>概要说明：</B><BR>
	 * @return
	 */	
	@RequestMapping(value="/dictadd",method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView dictAdd(DictType dicttype, Model model){	
		  if(dictService.addDict(dicttype)){ 
			  model.addAttribute(ResultCodeEnum.KEY,ResultCodeEnum.RESULT_SUCCESS); 
		  }else{
			  model.addAttribute(ResultCodeEnum.KEY,ResultCodeEnum.RESULT_FAIL); 
		  }		 
		  ModelAndView mv = new ModelAndView("/aat/dictionary/dictionary");
		  return mv;
	}
	
	/**
	 * <B>方法名称：修改字典类型</B><BR>
	 * <B>概要说明：</B><BR>
	 * @return
	 */
	@RequestMapping(value="/dictupt",method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView dictUpt(DictType dicttype, Model model){
		 if(dictService.uptDict(dicttype)){ 
			 model.addAttribute(ResultCodeEnum.KEY,ResultCodeEnum.RESULT_SUCCESS); 
		 }else{ 
			 model.addAttribute(ResultCodeEnum.KEY,ResultCodeEnum.RESULT_FAIL); 
		 }		 
		 ModelAndView mv = new ModelAndView("/aat/dictionary/dictionary");
		 return mv;
	}
	
	/**
	 * <B>方法名称：删除字典类型</B><BR>
	 * <B>概要说明：</B><BR>
	 * @return
	 */
	@RequestMapping(value = "/dictdlt", method = RequestMethod.GET)
	@ResponseBody
    public ModelAndView dictdlt(String dicttypeid, Model model) {
		if (dictService.dltDict(dicttypeid)) {
			model.addAttribute(ResultCodeEnum.KEY, ResultCodeEnum.RESULT_SUCCESS);
		} else {
			model.addAttribute(ResultCodeEnum.KEY, ResultCodeEnum.RESULT_FAIL);
		}
		ModelAndView mv = new ModelAndView("/aat/dictionary/dictionary");
		return mv;
    }
	
	/**
	 * <B>方法名称：获取对应字典值</B><BR>
	 * <B>概要说明：</B><BR>
	 * @return
	 */
	@RequestMapping(value="/dictVals",method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getVals(HttpServletRequest request) {  
		Map<String, Object> map = new HashMap<String, Object>(); 
		
        Integer pageSize = Integer.valueOf(request.getParameter("pageSize")==null?"100000":request.getParameter("pageSize"));// 每页显示的size
		Integer draw = Integer.valueOf(request.getParameter("draw")==null?"0":request.getParameter("draw"));// 记录操作的次数 每次加1
		Integer page = Integer.valueOf(request.getParameter("page")==null?"0":request.getParameter("page"));// 记录操作的次数 每次加1
		String dicttypeid = request.getParameter("dicttypeid");
//		PageHelper.startPage(page,pageSize);

		List<DictValue> dictvalueList = dictService.getDiVByTid(dicttypeid);
		final PageInfo<DictValue> pageInfo = new PageInfo<>(dictvalueList);
		
		map.put("draw", draw); 
	    map.put("total",pageInfo.getTotal());//数据总条数 
	    map.put("pageData", dictvalueList);//数据集合 
		return map;
	}
	
	/**
	 * <B>方法名称：删除字典类型</B><BR>
	 * <B>概要说明：</B><BR>
	 * 
	 * @return
	 */
	@RequestMapping(value = "/removes", method = RequestMethod.POST)
	@ResponseBody
	public String divsDel(HttpServletRequest request, Model model) {
		String parameter = request.getParameter("result");
		JSONArray json = JSONArray.fromObject(parameter);
		JSONObject[] jsb = new JSONObject[json.size()];
		String[] ids = new String[json.size()];
		for (int i = 0; i < json.size(); i++) {
			jsb[i] = json.getJSONObject(i);
			if (jsb[i].containsKey("dicttypeid")) {
				ids[i] = jsb[i].get("dicttypeid").toString();
			} else {
			}
		}
		if (dictService.delDis(ids)) {
			model.addAttribute(ResultCodeEnum.KEY, ResultCodeEnum.RESULT_SUCCESS);
		}
		return "";
	}
	
	/**
	 * <B>方法名称：添加修改字典值</B><BR>
	 * <B>概要说明：</B><BR>
	 * 
	 * @return
	 */
	@RequestMapping(value = "/dictValsupt", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> dictValsupt(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>(); 
		String dicttypeid = request.getParameter("dicttypeid");//需要修改的字典类型ID
		String dictData = request.getParameter("dictData");
		JSONArray dicttype = JSONArray.fromObject(dictData);
	
		JSONObject[] jsb = new JSONObject[dicttype.size()];
		for (int i = 0; i < dicttype.size(); i++) {
			
				jsb[i] = dicttype.getJSONObject(i);

				jsb[i].put("dicttypeid", jsb[i].get("dictitem"));
				jsb[i].put("dictname", jsb[i].get("dictname"));
				jsb[i].put("dictvalue", jsb[i].get("dictvalue"));
				jsb[i].put("dictdesc", jsb[i].get("dictdesc"));
				
				jsb[i].remove("dictitem");
		}
		ArrayList<DictValue> divList = new ArrayList<DictValue>();
		for(int i=0;i<jsb.length;i++) {
			DictValue div =(DictValue)JSONObject.toBean(jsb[i],DictValue.class);
			divList.add(div);
		}	
	
		boolean uptDictval = dictService.uptDictval(divList, dicttypeid);
			
		if (uptDictval) {
			map.put("RESULT", "SUCCESS");			
		}else {
			map.put("RESULT", "FAIL");
		}	
		return map;
	}
	
	/**
	 * <B>方法名称：删除字典类型下的字典值</B><BR>
	 * <B>概要说明：</B><BR>
	 * 
	 * @return
	 */
	@RequestMapping(value = "/deletes", method = RequestMethod.POST)
	@ResponseBody
	public String rolesDel(HttpServletRequest request, Model model) {
		String parameter = request.getParameter("result");
		JSONArray json = JSONArray.fromObject(parameter);
		JSONObject[] jsb = new JSONObject[json.size()];
		String[] ids = new String[json.size()];
		for (int i = 0; i < json.size(); i++) {
			jsb[i] = json.getJSONObject(i);
			if (jsb[i].containsKey("dictvalueid")) {
				ids[i] = jsb[i].get("dictvalueid").toString();
			} else {

			}
		}
		if (dictService.delDiv(ids)) {
			model.addAttribute(ResultCodeEnum.KEY, ResultCodeEnum.RESULT_SUCCESS);
		}
		return "";
	}

}
