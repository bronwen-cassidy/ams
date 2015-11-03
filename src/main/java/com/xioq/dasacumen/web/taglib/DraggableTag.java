package com.xioq.dasacumen.web.taglib;

import java.util.Set;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.xioq.dasacumen.lib.DragAndDropConfiguration;
import com.xioq.dasacumen.model.ScratchpadDraggable;

/**
 * Tag to build up all the draggable attributes from a draggable bean.
 * These can then be attached to any draggable html element.
 * @author Stephen Elliott
 */
public class DraggableTag extends TagSupport
{
	private String var;
	private Object bean;
	
	@Override
	public int doEndTag() throws JspException
	{
		if(null != bean && bean instanceof ScratchpadDraggable)
		{
			ScratchpadDraggable draggable = (ScratchpadDraggable)bean;
			
			// Reference to the application context to look up the droppable beans
			WebApplicationContext wac = WebApplicationContextUtils.getWebApplicationContext( pageContext.getServletContext());

			// Look for the draggable bean. If not found then do nothing
			String draggableBeanName = DragAndDropConfiguration.getDraggableBeanName(bean.getClass());
			if(null != draggable.getDraggableNames() && !draggable.getDraggableNames().isEmpty())
				draggableBeanName = draggable.getDraggableNames().iterator().next();
			
			if(wac.containsBean(draggableBeanName))
			{
				Set<String> draggableFields = wac.getBean(draggableBeanName, Set.class);
				StringBuilder dragAttrs = new StringBuilder();
				
				dragAttrs.append("'dnd-type':'");
				dragAttrs.append(draggableBeanName);
				dragAttrs.append("'");
				
				// Use Spring expression to get the value of the draggable field.
				ExpressionParser parser = new SpelExpressionParser();
				EvaluationContext context = new StandardEvaluationContext(bean);
				Expression exp;
				
				for (String dragField : draggableFields)
				{
					System.out.println(dragField);
					dragAttrs.append(", 'dnd-");
					dragAttrs.append(dragField);
					dragAttrs.append("':'");
					
					exp = parser.parseExpression(dragField);
					dragAttrs.append(exp.getValue(context).toString());
					
					dragAttrs.append("'");
				}
				System.out.println(dragAttrs.toString());
				pageContext.setAttribute(var, dragAttrs.toString());
			}
		}
		
		return super.doEndTag();
	}
	
	public void setVar(String var)
	{
		this.var = var;
	}
	public void setBean(Object bean)
	{
		this.bean = bean;
	}
}
