package com.agree.framework.interceptor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ServletContextAware;

import com.agree.framework.constant.ApplicationKeyConst;
import com.agree.system.entity.DictType;
import com.agree.system.entity.DictValue;
import com.agree.system.entity.Dictionary;
import com.agree.system.service.IDictManagmentService;

@Service
public class DicInit implements InitializingBean, ServletContextAware {

	private static final Logger logger = LoggerFactory.getLogger(DicInit.class);

	@Autowired
	private IDictManagmentService dictService;

	@Override
	public void setServletContext(ServletContext servletContext) {
		logger.info("加载系统参数...");
		List<DictType> dicType = dictService.selectAllType();
		List<DictValue> dic = dictService.selectAllValue();


		logger.info("发送命令给代理机,同步设备状态");
		synDevice();
		if (dic.size() == 0 || dicType.size() == 0) {
			logger.info("字典初始化失败");
			return;
		}

		Map<String, List<Dictionary>> dictMap = new HashMap<String, List<Dictionary>>();
		for (DictType entry : dicType) {
			String dicttypeId = entry.getDicttypeid();
			List<Dictionary> tempList = new ArrayList<Dictionary>();
			for (DictValue d : dic) {
				if (dicttypeId.equals(d.getDicttypeid())) {
					Dictionary dict = new Dictionary();
					dict.setDictName(d.getDictname());
					dict.setDictValue(d.getDictvalue());
					dict.setDictValueDesc(d.getDictvalue() + "-" + d.getDictname());
					tempList.add(dict);
				}
			}
			dictMap.put(dicttypeId, tempList);
		}
		servletContext.setAttribute(ApplicationKeyConst.SYS_DICTIONARY, dictMap);
	}

	private void synDevice() {
		/*
		 * List<Executor> list = executorDao.selectByIPS(1); JSONObject jobj = new
		 * JSONObject(); jobj.accumulate("type",ICommunicationKeys.SYN_DEVICE_STATUS);
		 * jobj.accumulate("name",ICommunicationKeys.SYN_DEVICE_STATUS);
		 * list.forEach((executor)->{ ClientSocketUtil.sendMsg(executor.getExecutorIp(),
		 * executor.getExecutorPort(), jobj); });
		 */
	}

	@Override
	public void afterPropertiesSet() throws Exception {

	}

}
