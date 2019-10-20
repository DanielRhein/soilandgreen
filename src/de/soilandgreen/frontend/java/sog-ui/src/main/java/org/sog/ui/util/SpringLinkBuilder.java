package net.artelis.wita.ui.util;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.thymeleaf.context.IExpressionContext;
import org.thymeleaf.context.IWebContext;
import org.thymeleaf.linkbuilder.StandardLinkBuilder;

public class SpringLinkBuilder extends StandardLinkBuilder {

    @Override
    protected String computeContextPath(IExpressionContext context, String base, Map<String, Object> parameters) {
        final HttpServletRequest request = ((IWebContext) context).getRequest();
        String forwardedPrefix = request.getHeader("X-Forwarded-Prefix");
        if (StringUtils.isNotBlank(forwardedPrefix)) {
            return StringUtils.stripEnd(forwardedPrefix, "/");
        }
        return super.computeContextPath(context, base, parameters);
    }

}
