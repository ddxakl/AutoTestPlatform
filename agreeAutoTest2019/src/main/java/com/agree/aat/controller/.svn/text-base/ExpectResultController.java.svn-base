package com.agree.aat.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.agree.aat.entity.ExpectResult;
import com.agree.aat.entity.TransItemExpectResult;
import com.agree.aat.service.IExpectResultService;
import com.agree.aat.service.impl.CaseFlowService;
import com.agree.framework.base.ServiceReturn;
import com.agree.framework.constant.ResultCodeEnum;
import com.agree.system.entity.SystemTerminal;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@RestController
@RequestMapping("/expect")
public class ExpectResultController {
	
	
	private static final Logger log = LogManager.getLogger(ExpectResultController.class);

	@Autowired
	IExpectResultService expectrexultService;
	

	@RequestMapping(value = "/expectList", method = RequestMethod.GET)
    public Map<String, Object> getEcList(HttpServletRequest request) {
		ServiceReturn result = null;
        Map<String, Object> map = new HashMap<String, Object>();
        Integer pageSize = Integer.valueOf(request.getParameter("pageSize") == null ? "10" : request.getParameter("pageSize"));// 每页显示的size
        Integer draw = Integer.valueOf(request.getParameter("draw") == null ? "0" : request.getParameter("draw"));// 记录操作的次数 每次加1
        Integer page = Integer.valueOf(request.getParameter("page") == null ? "0" : request.getParameter("page"));//
        String name = request.getParameter("name");
        Map param = new HashMap<>();
        param.put("expectresultname", name);
        PageHelper.startPage(page, pageSize);
		List<ExpectResult> ExpectResulttList = expectrexultService.getExpectList(param);
        final PageInfo<ExpectResult> pageInfo = new PageInfo<>(ExpectResulttList);
        map.put("draw", draw);
        map.put("total", pageInfo.getTotal());//数据总条数
        map.put("pageData", ExpectResulttList);//数据集合
		try {
			result = new ServiceReturn(ResultCodeEnum.RESULT_SUCCESS);
			result.setData(ExpectResulttList);
		} catch (Exception e) {
			result = new ServiceReturn(ResultCodeEnum.RESULT_FAIL);
			log.error(e.getMessage());
		}
        return map;
    }
	
	
	@RequestMapping(value = "/itemexpectList/{itemid}", method = RequestMethod.GET)
    public Map<String, Object> itemexpectList(@PathVariable("itemid") String itemid,HttpServletRequest request) {
		ServiceReturn result = null;
        Map<String, Object> map = new HashMap<String, Object>();
        Integer pageSize = Integer.valueOf(request.getParameter("pageSize") == null ? "10" : request.getParameter("pageSize"));// 每页显示的size
        Integer draw = Integer.valueOf(request.getParameter("draw") == null ? "0" : request.getParameter("draw"));// 记录操作的次数 每次加1
        Integer page = Integer.valueOf(request.getParameter("page") == null ? "0" : request.getParameter("page"));//
        String name = request.getParameter("name");
        Map param = new HashMap<>();
        param.put("expectresultname", name);
        param.put("itemid", itemid);
        PageHelper.startPage(page, pageSize);
		List<TransItemExpectResult> ExpectResulttList = expectrexultService.getitemExpectList(param);
        final PageInfo<TransItemExpectResult> pageInfo = new PageInfo<>(ExpectResulttList);
        map.put("draw", draw);
        map.put("total", pageInfo.getTotal());//数据总条数
        map.put("pageData", ExpectResulttList);//数据集合
		try {
			result = new ServiceReturn(ResultCodeEnum.RESULT_SUCCESS);
			result.setData(ExpectResulttList);
		} catch (Exception e) {
			result = new ServiceReturn(ResultCodeEnum.RESULT_FAIL);
			log.error(e.getMessage());
		}
        return map;
    }
	
	
	@RequestMapping(value = "/addExpect", method = RequestMethod.POST)
    public ModelAndView addExpect(ExpectResult expect, Model model) {
		
		  if(expectrexultService.addExpect(expect)){ 
			  	model.addAttribute(ResultCodeEnum.KEY,ResultCodeEnum.RESULT_SUCCESS); 
			  }else{
				model.addAttribute(ResultCodeEnum.KEY,ResultCodeEnum.RESULT_FAIL); }		 
		  ModelAndView mv = new ModelAndView("/aat/testService/expectResult/expectResult");
		return mv;
    }
	
	@RequestMapping(value = "/uptExpect", method = RequestMethod.POST)
    public ModelAndView uptExpect(ExpectResult expect, Model model) {
		if (expectrexultService.uptExpect(expect)) {
			model.addAttribute(ResultCodeEnum.KEY, ResultCodeEnum.RESULT_SUCCESS);
		} else {
			model.addAttribute(ResultCodeEnum.KEY, ResultCodeEnum.RESULT_FAIL);
		}
		
		ModelAndView mv = new ModelAndView("/aat/testService/expectResult/expectResult");
		return mv;
    }
	
	
	
	@RequestMapping(value = "/delExpect", method = RequestMethod.GET)
    public ModelAndView delExpect(String expectresultid, Model model) {
		if (expectrexultService.delExpect(expectresultid)) {
			model.addAttribute(ResultCodeEnum.KEY, ResultCodeEnum.RESULT_SUCCESS);
		} else {
			model.addAttribute(ResultCodeEnum.KEY, ResultCodeEnum.RESULT_FAIL);
		}
		ModelAndView mv = new ModelAndView("/aat/testService/expectResult/expectResult");
		return mv;
    }
	
	
	@RequestMapping(value = "/getList", method = RequestMethod.POST)
    public Map<String, Object> getList(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        String expectresultid = request.getParameter("expectresultid");
		ExpectResult executeClientList = expectrexultService.getExpectResult(expectresultid);
		map.put("data", executeClientList);
        return map;
    }
	
	
  @RequestMapping(value = "/removesItemExpect", method = RequestMethod.POST)
  public ServiceReturn getEcListStatus1(HttpServletRequest request) {
		ServiceReturn result = null;
		try {
			String expectid = request.getParameter("expectid");
			expectrexultService.removesItemExpect(expectid);
			result = new ServiceReturn(ResultCodeEnum.RESULT_SUCCESS);
		} catch (Exception e) {
			result = new ServiceReturn(ResultCodeEnum.RESULT_FAIL);
			log.error(e.getMessage());
		}
      return result;
  }
  
  @RequestMapping(value = "/addItemExpect", method = RequestMethod.POST)
  public ServiceReturn addItemExpect(HttpServletRequest request) {
		ServiceReturn result = null;
		try {
			Map param = new HashMap();
			param.put("itemid", request.getParameter("itemid"));
			param.put("expectresultid", request.getParameter("expectresultid"));
			expectrexultService.addItemExpect(param);
			result = new ServiceReturn(ResultCodeEnum.RESULT_SUCCESS);
		} catch (Exception e) {
			result = new ServiceReturn(ResultCodeEnum.RESULT_FAIL);
			log.error(e.getMessage());
		}
      return result;
  }
  
  
	@RequestMapping(value = "/updateItemExpect", method = RequestMethod.POST)
    public ServiceReturn updateItemExpect(HttpServletRequest request) {
		ServiceReturn sr = null;
		try {
			String expression = request.getParameter("input");
			String expectid = request.getParameter("expectid");
			expectrexultService.updateItemExpect(expectid,expression);
			sr = new ServiceReturn((ResultCodeEnum.RESULT_SUCCESS));
		} catch (Exception e) {
			log.error(e.getMessage());
			sr = new ServiceReturn((ResultCodeEnum.RESULT_FAIL));
		}
		return sr;
    }
	
	
	/**
	 * 查询案例列表返回expectresultid
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getExpResListRtnEid", method = RequestMethod.POST)
	public List<String> getExpResListRtnEid() {
		List<String> expectresultidList = expectrexultService.getExpResListRtnEid();
		return expectresultidList;
	}
	
}
