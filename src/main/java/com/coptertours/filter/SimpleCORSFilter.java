package com.coptertours.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import org.springframework.stereotype.Component;

@Component
@WebFilter("/*")
public class SimpleCORSFilter implements Filter {

	// FilterConfig filterConfig;
	private String allowedHosts = "207.210.193.125";
	private static final String ALLOWED_ALL = "*";

	@Override
	public void init(FilterConfig filterConfig) {
		// this.filterConfig = filterConfig;
		// WebApplicationContext springContext = WebApplicationContextUtils.getWebApplicationContext(filterConfig.getServletContext());
		// Properties properties = (Properties) springContext.getBean(EcomConstants.BEAN_ECOM_WEBSERVICE_PROPERTIES);
		// if (properties != null) {
		// String propertyName = System.getProperty("com.garmin.environment") + ".cors.allowedHosts";
		// String allowedHostsProperty = properties.getProperty(propertyName);
		// setAllowedHosts(StringUtils.isNotBlank(allowedHostsProperty) ? allowedHostsProperty : StringUtils.EMPTY);
		// }
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		// HttpServletRequest httpReq = (HttpServletRequest) req;
		// String origin = httpReq.getHeader("Origin");
		// System.out.println("********************** origin: " + origin + "****************************");
		// if (StringUtils.isNotBlank(origin) && StringUtils.isNotBlank(getAllowedHosts()) && (getAllowedHosts().equals(ALLOWED_ALL) ||
		// getAllowedHosts().contains(origin))) {
		// HttpServletResponse response = (HttpServletResponse) res;
		// // since the request came from a trusted server, set the Allow headers to allow cross-domain call
		// response.setHeader("Access-Control-Allow-Origin", ALLOWED_ALL);
		// response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
		// response.setHeader("Access-Control-Max-Age", "3600");
		// response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
		// }
		chain.doFilter(req, res);
	}

	@Override
	public void destroy() {
		// NOT IMPLEMENTED
	}

	protected String getAllowedHosts() {
		return allowedHosts;
	}

	protected void setAllowedHosts(String allowedHosts) {
		this.allowedHosts = allowedHosts;
	}

}
