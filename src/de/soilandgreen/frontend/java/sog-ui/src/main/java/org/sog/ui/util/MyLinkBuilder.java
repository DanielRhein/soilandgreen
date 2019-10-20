package net.artelis.wita.ui.util;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.thymeleaf.context.IExpressionContext;
import org.thymeleaf.linkbuilder.AbstractLinkBuilder;
import org.thymeleaf.linkbuilder.ILinkBuilder;
import org.thymeleaf.linkbuilder.StandardLinkBuilder;

public class MyLinkBuilder extends AbstractLinkBuilder implements ILinkBuilder {

    private StandardLinkBuilder delgate = new StandardLinkBuilder();

    @Override
    public final String buildLink(final IExpressionContext context, final String base, final Map<String, Object> parameters) {
        if (parameters == null) {
            return delgate.buildLink(context, base, parameters);
        }

        Map<String, Object> newParams = new HashMap<>();
        for (Map.Entry<String, Object> entry : parameters.entrySet()) {
            if (entry.getValue() == null) {
                continue;
            }
            if (entry.getValue() instanceof Collection) {
                Collection<?> list = (Collection<?>) entry.getValue();
                if (list.isEmpty()) {
                    continue;
                }
            }
            if (StringUtils.isEmpty(entry.getValue() + "")) {
                continue;
            }
            newParams.put(entry.getKey(), entry.getValue());
        }

        return delgate.buildLink(context, base, newParams);

    }

}
