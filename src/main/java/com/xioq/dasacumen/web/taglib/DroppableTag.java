package com.xioq.dasacumen.web.taglib;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.jsp.JspException;

import org.springframework.util.StringUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.tags.RequestContextAwareTag;

import com.xioq.dasacumen.lib.DragAndDropConfiguration;
import com.xioq.dasacumen.lib.annotations.Droppable;
import com.xioq.dasacumen.lib.utilities.StringUtil;

/**
 * Tag to build up all the draggable attributes from a draggable bean.
 * These can then be attached to any draggable html element.
 * @author Stephen Elliott
 */
public class DroppableTag extends RequestContextAwareTag
{
	private String var;
	private Object bean;
	private String formId;
	
	@Override
	public int doEndTag() throws JspException
	{
		if(null != bean)
		{
			// Reference to the application context to look up the droppable beans
			WebApplicationContext wac = getRequestContext().getWebApplicationContext();

			// Look for the draggable bean. If not found then do nothing
			String droppableBeanName = DragAndDropConfiguration.getDroppableBeanName(bean.getClass());
//			draggableBeanName = draggable.getDraggableNames().iterator().next();
			
			if(wac.containsBean(droppableBeanName))
			{
				Map<String, Droppable> droppableFields = wac.getBean(droppableBeanName, Map.class);
				String dragAttrs = buildJavaScriptAttrs(droppableBeanName, droppableFields);
				System.out.println(dragAttrs);
				doOutput(dragAttrs);
			}
		}
		
		return super.doEndTag();
	}
	
	/**
	 * Outputs the droppable attributes to either a script element or to a page
	 * context attribute, depending on which variable is set.
	 * 
	 * @param dragAttrs
	 * @throws JspException
	 */
	private void doOutput(String dragAttrs) throws JspException
	{
		if (!StringUtil.isEmpty(formId))
		{
			try
			{
				pageContext.getOut().print("<script>");
				pageContext.getOut().print("$('#");
				pageContext.getOut().print(formId);
				pageContext.getOut().print("').data({");
				pageContext.getOut().print(dragAttrs);
				pageContext.getOut().print("});");
				pageContext.getOut().print("</script>");
			}
			catch (IOException ex)
			{
				throw new JspException("Unable to write to JspWriter", ex);
			}
		}
		else if (!StringUtil.isEmpty(var))
			pageContext.setAttribute(var, dragAttrs);
		else
			throw new JspException("Either var or formId must be supplied.");
		// TODO Could get the outer form tag and attach to that
	}

	/**
	 * Builds up a javascript data object of all the droppable fields and attributes.
	 * @param droppableBeanName
	 * @param droppableFields
	 * @return
	 */
	private String buildJavaScriptAttrs(String droppableBeanName, Map<String, Droppable> drop)
	{
		StringBuilder dragAttrs = new StringBuilder();
		
		dragAttrs.append("'dnd-drop':{");
		
		// Each class has lots of fields each could accept a different draggable
		for (Iterator<Entry<String, Droppable>> iterator = drop.entrySet().iterator(); iterator.hasNext();)
		{
			Entry<String, Droppable> dropField = iterator.next();
			
			dragAttrs.append("'");
			dragAttrs.append(dropField.getKey());
			dragAttrs.append("':{");
			
			Droppable droppable = dropField.getValue();
			addJSDataField(dragAttrs, "dnd-accepts", droppable.accepts(), false);
			addJSDataField(dragAttrs, "dnd-field-to-copy", droppable.fieldToCopy(), false);
			addJSDataField(dragAttrs, "dnd-deep-fields", StringUtils.arrayToCommaDelimitedString(droppable.deepFields()), true);
			
			dragAttrs.append("}");
			
			if(iterator.hasNext())
				dragAttrs.append(", \n\t\t");
		}

		dragAttrs.append("}");

		return dragAttrs.toString();
	}
	
	private static void addJSDataField(StringBuilder dragAttrs, String name, String val, boolean last)
	{
		dragAttrs.append("'");
		dragAttrs.append(name);
		dragAttrs.append("':'");
		dragAttrs.append(val);
		dragAttrs.append("'");
		if(!last)
			dragAttrs.append(", ");
	}
	
	public void setVar(String var)
	{
		this.var = var;
	}
	public void setFormId(String formId)
	{
		this.formId = formId;
	}
	public void setBean(Object bean)
	{
		this.bean = bean;
	}

	@Override
	protected int doStartTagInternal() throws Exception
	{
		return SKIP_BODY;
	}
}
