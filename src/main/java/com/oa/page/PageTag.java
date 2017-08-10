package com.oa.page;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * 分页标签
 * @author Dwen
 * @version v 0.1 2013-8-19 下午02:57:50
 */
public class PageTag extends TagSupport {

	private String url;

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}

	public int doStartTag() throws JspException {
		HttpServletRequest request = (HttpServletRequest) this.pageContext
				.getRequest();
		// 获取分页bean
		Page page = (Page) request.getAttribute("page");
		// 写流
		JspWriter out = this.pageContext.getOut();
		// 拼接分页信息
		StringBuilder s = new StringBuilder();
		s.append("<script type=\"text/javascript\">function p_submit(current){$(\"#current_ID\").val(current);$(\"#pageForm_ID\").submit();}</script>");
		s.append("&nbsp;"+ page.getCurrent() +"/" + page.getTotalPage() + "&nbsp;页 &nbsp; ");
		// 设置当前页第一条记录索引
		String current = request.getParameter("current");
		page.setCurrent(current);
		// 首页 上一页 1 下一页 尾页
		// 总记录数大于0才，生成分页标签
		if (page.getTotalCount() > 0) {
			s.append("<a href=\"javascript:p_submit(1)\">首页</a>&nbsp;");
			s.append("<a href=\"javascript:p_submit(" + page.getPrevPage()
					+ ")\">上一页</a>&nbsp;");
			s.append("<a href=\"javascript:p_submit(" + page.getNextPage()
					+ ")\">下一页</a>&nbsp;");
			s.append("<a href=\"javascript:p_submit(" + page.getTotalPage()
					+ ")\">尾页</a>&nbsp;");
		}
		s.append("&nbsp;共&nbsp;" + page.getTotalCount() + "&nbsp;条记录");
		try {
			out.println(s.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return SKIP_BODY;
	}
}
