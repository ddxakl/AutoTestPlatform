package com.agree.system.controller;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.agree.framework.base.ServiceReturn;
import com.agree.framework.constant.ResultCodeEnum;
import com.agree.system.entity.SystemTerminal;
import com.agree.system.service.IExecuteClientService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@RestController
@RequestMapping("/executeclient")
public class ExecuteClientCotroller {
	
	private static final Logger log=LogManager.getLogger(ExecuteClientCotroller.class);
	
	@Autowired
	IExecuteClientService executeClientService;
	
	@RequestMapping(value = "/getList", method = RequestMethod.GET)
    public Map<String, Object> getList(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        String terminalid = request.getParameter("terminalid");
		List<SystemTerminal> executeClientList = executeClientService.getList(terminalid);
		map.put("dataList", executeClientList);
        return map;
    }

	@RequestMapping(value = "/ecList", method = RequestMethod.GET)
    public Map<String, Object> getEcList(HttpServletRequest request) {
		ServiceReturn result = null;
        Map<String, Object> map = new HashMap<String, Object>();
        Integer pageSize = Integer.valueOf(request.getParameter("pageSize") == null ? "10" : request.getParameter("pageSize"));// 每页显示的size
        Integer draw = Integer.valueOf(request.getParameter("draw") == null ? "0" : request.getParameter("draw"));// 记录操作的次数 每次加1
        Integer page = Integer.valueOf(request.getParameter("page") == null ? "0" : request.getParameter("page"));//
        String name = request.getParameter("name");
        String ip = request.getParameter("ip");
        String status = request.getParameter("status");
        if ("禁用".equals(status)) {
        	status = "0";
		}else if ("启用".equals(status)) {
			status = "1";
		}else {
			status = "";
		}
        Map executeClient = new HashMap<>();
        executeClient.put("name", name);
        executeClient.put("ip", ip);
        executeClient.put("status", status);
        PageHelper.startPage(page, pageSize);
		List<SystemTerminal> executeClientList = executeClientService.getEClientList(executeClient);
        final PageInfo<SystemTerminal> pageInfo = new PageInfo<>(executeClientList);
        map.put("draw", draw);
        map.put("total", pageInfo.getTotal());//数据总条数
        map.put("pageData", executeClientList);//数据集合
		try {
			result = new ServiceReturn(ResultCodeEnum.RESULT_SUCCESS);
			result.setData(executeClientList);
		} catch (Exception e) {
			result = new ServiceReturn(ResultCodeEnum.RESULT_FAIL);
			log.error(e.getMessage());
		}
        return map;
    }
	
	@RequestMapping(value = "/ecListstatus1", method = RequestMethod.GET)
    public ServiceReturn getEcListStatus1(HttpServletRequest request) {
		ServiceReturn result = null;
		try {
			List<SystemTerminal> executeClientList = executeClientService.getEClientByStatus1();
			result = new ServiceReturn(ResultCodeEnum.RESULT_SUCCESS);
			result.setData(executeClientList);
		} catch (Exception e) {
			result = new ServiceReturn(ResultCodeEnum.RESULT_FAIL);
			log.error(e.getMessage());
		}
        return result;
    }
	
	
	@RequestMapping(value = "/terminaladd", method = RequestMethod.POST)
    public ModelAndView terminaladd(SystemTerminal sysTerminal, Model model) {
		String name = sysTerminal.getName();
		String ip = sysTerminal.getIp();
		String port = sysTerminal.getPort();
		String descinfos = sysTerminal.getDescinfos();
		String status = "0";
		
		sysTerminal.setName(name);
		sysTerminal.setIp(ip);
		sysTerminal.setPort(port);
		sysTerminal.setDescinfos(descinfos);
		sysTerminal.setStatus(status);
		
		if (executeClientService.addTerminal(sysTerminal)) {
			model.addAttribute(ResultCodeEnum.KEY, ResultCodeEnum.RESULT_SUCCESS);
		} else {
			model.addAttribute(ResultCodeEnum.KEY, ResultCodeEnum.RESULT_FAIL);
		}
		
		ModelAndView mv = new ModelAndView("/aat/testService/executeTerminal/executeTerminal");
		return mv;
    }
	
	@RequestMapping(value = "/terminalupt", method = RequestMethod.POST)
    public ModelAndView terminalupt(SystemTerminal sysTerminal, Model model) {
		String terminalid = sysTerminal.getTerminalid();
		String name = sysTerminal.getName();
		String ip = sysTerminal.getIp();
		String port = sysTerminal.getPort();
		String descinfos = sysTerminal.getDescinfos();
		
		sysTerminal.setTerminalid(terminalid);
		sysTerminal.setName(name);
		sysTerminal.setIp(ip);
		sysTerminal.setPort(port);
		sysTerminal.setDescinfos(descinfos);
		
		if (executeClientService.uptTerminal(sysTerminal)) {
			model.addAttribute(ResultCodeEnum.KEY, ResultCodeEnum.RESULT_SUCCESS);
		} else {
			model.addAttribute(ResultCodeEnum.KEY, ResultCodeEnum.RESULT_FAIL);
		}
		
		ModelAndView mv = new ModelAndView("/aat/testService/executeTerminal/executeTerminal");
		return mv;
    }
	
	@RequestMapping(value = "/terminaldlt", method = RequestMethod.GET)
    public ModelAndView terminaldlt(String terminalid, Model model) {
		if (executeClientService.dltTerminal(terminalid)) {
			model.addAttribute(ResultCodeEnum.KEY, ResultCodeEnum.RESULT_SUCCESS);
		} else {
			model.addAttribute(ResultCodeEnum.KEY, ResultCodeEnum.RESULT_FAIL);
		}
		ModelAndView mv = new ModelAndView("/aat/testService/executeTerminal/executeTerminal");
		return mv;
    }
	
	@RequestMapping(value = "/isEnable", method = RequestMethod.GET)
    public ModelAndView isEnable(String terminalid, String status, Model model) {
		if ("0".equals(status)) {
			status = "1";
		}else if ("1".equals(status)){
			status = "0";
		}else {
			status = "";
		}
		if (executeClientService.updateStatus(terminalid, status)) {
			model.addAttribute(ResultCodeEnum.KEY, ResultCodeEnum.RESULT_SUCCESS);
		} else {
			model.addAttribute(ResultCodeEnum.KEY, ResultCodeEnum.RESULT_FAIL);
		}
		ModelAndView mv = new ModelAndView("/aat/testService/executeTerminal/executeTerminal");
		return mv;
    }
	
	@RequestMapping(value = "/sltTerminalByName", method = RequestMethod.GET)
    public boolean sltTerminalByName(HttpServletRequest request) {
		String name = request.getParameter("name");
		List<SystemTerminal> list = executeClientService.sltTerminalByName(name);
		if (list.size()>0) {
			return true;
		}
		return false;
    }
	
	@RequestMapping(value = "/sltTerminalByName2", method = RequestMethod.GET)
    public boolean sltTerminalByName2(HttpServletRequest request) {
		String name = request.getParameter("name");
		List<SystemTerminal> list = executeClientService.sltTerminalByName(name);
		if (list.size()>1) {
			return true;
		}
		return false;
    }
	
	@RequestMapping(value = "/sltTerByName", method = RequestMethod.POST)
    public String sltTerByName(HttpServletRequest request) {
		String name = request.getParameter("name");
		List<SystemTerminal> list = executeClientService.sltTerByName(name);
		String terminalid = list.get(0).getTerminalid();
		return terminalid;
    }
}
