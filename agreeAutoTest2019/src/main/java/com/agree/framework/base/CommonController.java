package com.agree.framework.base;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.agree.framework.constant.ApplicationKeyConst;
import com.agree.framework.constant.ResultCodeEnum;

@Controller
@RequestMapping("/common")
public class CommonController extends BaseController {

	@Autowired
	private ServletContext context;

	@RequestMapping(value="/getSysDictionaryItem/{item_id}",method=RequestMethod.GET)
	@ResponseBody
	public ServiceReturn getSysDictionaryItem(@PathVariable("item_id") String itemId){
		Map map = (Map)context.getAttribute(ApplicationKeyConst.SYS_DICTIONARY);
		if(map==null){
			return buildReturn(null, ResultCodeEnum.RESULT_FAIL);
		}
		return buildReturn((List)map.get(itemId), ResultCodeEnum.RESULT_SUCCESS);
	}
}	
