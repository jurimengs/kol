package com.org.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspFactory;
import javax.servlet.jsp.PageContext;

import org.springframework.stereotype.Controller;

import com.jspsmart.upload.File;
import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;
import com.org.common.CommonConstant;
import com.org.common.PageConstant;
import com.org.log.LogUtil;
import com.org.log.impl.LogUtilMg;
import com.org.servlet.CommonController;
import com.org.servlet.SmpHttpServlet;
import com.org.util.CT;
import com.org.utils.DateUtil;

/**
 * 用户请求
 * @author Administrator
 * 
 */
@Controller
public class FileController extends SmpHttpServlet implements CommonController {
	private static final long serialVersionUID = 1L;
	//private static final String excelUploadPath = SmpPropertyUtil.getValue("filepath", "upload_file_path");
	private static final String excelUploadPath = "c:/";

	public FileController() {
		super();
	}

	public void post(HttpServletRequest request, HttpServletResponse response)
			throws ServletException {
		try {} catch (Exception se) {
			se.printStackTrace();
		}
	}
	
	/**
	 * 上传文件
	 * 
	 * @param request
	 * @param response
	 * @throws Exception 
	 * @throws IOException
	 */
	public void uploadFile(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		StringBuffer pathTemp = new StringBuffer();
		
		// 基本目录 / 商户ID / 日期 / 文件名
		String dateStr = DateUtil.getCurrentShortDateStr();
		pathTemp.append(excelUploadPath).append("/").append(dateStr);
		java.io.File dir = new java.io.File(pathTemp.toString());
		// 保证目录存在
		if(!dir.exists()){
			dir.mkdirs();
		}
		
		String targetUrl = PageConstant.SUCCESS;
		LogUtil.log(CT.LOG_CATEGORY_SERVLET, "文件上传", null, LogUtilMg.LOG_INFO, CT.LOG_PATTERN_SERVLET);
		// 新建一个SmartUpload对象
		SmartUpload su = null;
		try {
			su = initSmartUpload(request, response);
		} catch (java.lang.SecurityException e) {
			//e.printStackTrace();
			request.getSession().setAttribute(CT.RESP_CODE_NAME, "ERROR");
			request.getSession().setAttribute(CT.RESP_RESULT_NAME, "上传文件类型错误");
			this.redirect(PageConstant.ERROR, response);
			return;
		}
        //如果要实现文件的批量上传，则只需用for循环，将getFile(0)中的0改为i即可
		File file = su.getFiles().getFile(0);
		String fileName = new String(file.getFileName().getBytes(), "UTF-8");
		pathTemp.append("/").append(fileName);
		
		// 先保存到临时目录
		file.saveAs(pathTemp.toString());
		// 从临时目录拿到文件
		java.io.File temp = new java.io.File(pathTemp.toString());
		
	}

	private SmartUpload initSmartUpload(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, SmartUploadException {
		SmartUpload su = new SmartUpload();
		// 上传初始化
		JspFactory jspFactory = null;
        jspFactory = JspFactory.getDefaultFactory();
        PageContext pc = jspFactory.getPageContext((HttpServlet)request.getSession().getAttribute(CommonConstant.SERVLET),request,response,"",true,8192,true);
		su.initialize(pc);
		LogUtil.log(CT.LOG_CATEGORY_SERVLET, "上传请求内容字节长度: " + request.getContentLength(), null, LogUtilMg.LOG_INFO, CT.LOG_PATTERN_SERVLET);
		// 设定上传限制
		// 1.限制每个上传文件的最大长度。
		su.setMaxFileSize(5000000);
		// 2.限制总上传数据的长度。
		su.setTotalMaxFileSize(50000000);
		// 3.设定允许上传的文件（通过扩展名限制）,仅允许doc,txt文件。
		su.setAllowedFilesList("xls,xlsx");
		// 4.设定禁止上传的文件（通过扩展名限制）,禁止上传带有exe,bat,jsp,htm,html扩展名的文件和没有扩展名的文件。
		su.setDeniedFilesList("exe,bat,jsp,htm,html,,");
		// 上传文件
		su.upload();
				
		return su;
	}
	
}
