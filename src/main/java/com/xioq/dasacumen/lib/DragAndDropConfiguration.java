package com.xioq.dasacumen.lib;

import java.beans.Introspector;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.util.ClassUtils;

import com.xioq.dasacumen.lib.annotations.Draggable;
import com.xioq.dasacumen.lib.annotations.Droppable;
import com.xioq.dasacumen.lib.annotations.DroppableModel;

/**
 * Spring configuration for the Drag and Drop model beans.
 * 
 * Searches the model classes for the Draggable and Droppable annotations, where
 * found beans are created for model names to generate the javascript required
 * for dragging and dropping
 * 
 * TODO Embedded draggables. Eg Supplier within Asset. Custodian within asset
 * 
 * TODO The type should not be the fully qualified name of the class. As exposing class structure to html!!
 * 
 * @see Draggable
 * @see Droppable
 * @author Stephen Elliott
 */
@Configuration()
public class DragAndDropConfiguration extends ClassPathScanningCandidateComponentProvider implements BeanFactoryPostProcessor
{
	/** Package used to search for the DnD annotations */
	private static final String MODEL_PACKAGE = "com.xioq.dasacumen.model";
	
	private static Logger logger = LoggerFactory.getLogger(DragAndDropConfiguration.class);

	private ConfigurableListableBeanFactory beanFactory;
	
	public DragAndDropConfiguration()
	{
		super(false);
	}
	
	/**
	 * Overridden to allow for abstract classes
	 * @param beanDefinition
	 * @return
	 */
	@Override
	protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition)
	{
		return true;
	}
	
	/**
	 * This scans the model package looking for annotated classed with either
	 * Draggable or Droppable.
	 * 
	 * The Draggables are done first as the Droppables are dependant on them. Initialisation exception
	 * is thrown if a droppable depends on a draggable that does not exist.
	 */
	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException
	{ 
		this.beanFactory = beanFactory;
		logger.debug("DragAndDropConfiguration.postProcessBeanFactory");
		
		// First scan the model package for classes with either the Draggable annotation
		addIncludeFilter(new AnnotationTypeFilter(Draggable.class));
		Set<BeanDefinition> candidateComponents = findCandidateComponents(MODEL_PACKAGE);
		
		logger.debug("Found " + candidateComponents.size() + " draggables: " + candidateComponents);
		for (BeanDefinition bd : candidateComponents)
		{
		    try
			{
		    	// This is the annotated model class.
				Class<?> dndModelClass = Class.forName(bd.getBeanClassName());
				
				// Each class marked as draggable may produce multiple draggables.
				if(dndModelClass.isAnnotationPresent(Draggable.class))
				{
					logger.debug("Found draggable: " + dndModelClass);
					Draggable annotation = dndModelClass.getAnnotation(Draggable.class);
					String[] draggableNamesFromClass = annotation.names();
					
					// Draggable for each draggable defined.
					Map<String, Set<String>> drags = new HashMap<String, Set<String>>();
					
					Field[] declaredFields = dndModelClass.getDeclaredFields();
					
					// TODO allow for get methods
					
					for (final Field field : declaredFields)
					{
						annotation = field.getAnnotation(Draggable.class);
						if(null != annotation)
						{
							String fieldName = field.getName();
							if("type".equals(fieldName))
							{
								String msg = String.format("Failed to intialise draggable bean for class %s. Draggable is using a reserved field name of type ", dndModelClass.getSimpleName());
								throw new BeanInitializationException(msg);
							}
							
							String[] draggableNamesFromField = annotation.names();
							// If no drag names provided from the field then use the class names.
							if(draggableNamesFromField.length == 0)
							{
								if(draggableNamesFromClass.length == 0)
								{
									String msg = String.format("Failed to intialise draggable bean for class %s. No class wide draggable names, nor on field: %s", dndModelClass.getSimpleName(), fieldName);
									throw new BeanInitializationException(msg);
								}
								
								draggableNamesFromField = draggableNamesFromClass;
							}
							
							logger.debug("found draggable field: " + fieldName + " draggable:" + annotation);

							for (String dragNames : draggableNamesFromField)
							{
								Set<String> draggableFields = drags.get(dragNames);
								
								if(null == draggableFields)
								{
									draggableFields = new HashSet<String>();
									drags.put(dragNames, draggableFields);
								}
								
								draggableFields.add(fieldName);
							}
						}
					}
					
					for (Entry<String, Set<String>> drag : drags.entrySet())
					{
						String beanName = drag.getKey();
						logger.debug("Registering draggable bean: " + beanName  + "  :  " + drag.getValue());
						
						// Don't allow duplicate names. 
						if(beanFactory.containsBean(beanName))
						{
							String msg = String.format("Failed to intialise draggable bean for class %s. Spring bean already exists for name: ", dndModelClass.getSimpleName(), beanName);
							throw new BeanInitializationException(msg);
						}
						
						beanFactory.registerSingleton(beanName, drag.getValue());
					}
				}
			}
			catch (ClassNotFoundException e)
			{
				// Really should not get here as just got the class from the annotation search.
				throw new RuntimeException("Drag and Drop configuration failed.", e);
			}
		    
		}
		
		// Process the Droppables
		resetFilters(false);
		addIncludeFilter(new AnnotationTypeFilter(DroppableModel.class));
		candidateComponents = findCandidateComponents(MODEL_PACKAGE);
		
		logger.debug("Found " + candidateComponents.size() + " droppables: " + candidateComponents);
		for (BeanDefinition bd : candidateComponents)
		{
		    try
			{
				Class<?> dndModelClass = Class.forName(bd.getBeanClassName());
				
				if(dndModelClass.isAnnotationPresent(DroppableModel.class))
				{
					logger.debug("Found droppable: " + dndModelClass);

					Map<String, Droppable> droppableFields = new HashMap<String, Droppable>();
					
					try
					{
						populateDroppableMap(dndModelClass, droppableFields);
					}
					catch (Exception e)
					{
						String msg = String.format("Failed to intialise droppable bean for class %s.", dndModelClass.getSimpleName());
						throw new BeanInitializationException(msg, e);
					}
					
					String beanName = getDroppableBeanName(dndModelClass);
					logger.debug("Registering droppable bean: " + beanName + "   :   " + droppableFields);
					
					beanFactory.registerSingleton(beanName, droppableFields);
				}
			}
			catch (ClassNotFoundException e)
			{
				// Really should not get here as just got the class from the annotation search.
				throw new RuntimeException("Drag and Drop configuration failed.", e);
			}
		}
		
	}

	/**
	 * A droppable is made up of all the fields that can be dropped on. Each of these
	 * requires the type of draggable it accepts and which field will be copied over.
	 * 
	 * @param dndModelClass
	 * @param droppableFields
	 */
	private void populateDroppableMap(Class<?> dndModelClass, Map<String, Droppable> droppableFields)
	{
		Field[] declaredFields = dndModelClass.getDeclaredFields();
		// TODO allow for setters???
		for (final Field field : declaredFields)
		{
			Droppable annotation = field.getAnnotation(Droppable.class);
			if(null != annotation)
			{
				logger.debug("found droppable field: " + field.getName() + " droppable:" + annotation);
				
				checkForFieldOrGetter(annotation.accepts(), annotation.fieldToCopy());
//				checkForFieldOrGetter(annotation.accepts(), annotation.fieldToCopy());
				
				// TODO Check the deep copy fields that they exist on this droppable model
//				annotation.deepFields();
				
				droppableFields.put(field.getName(), annotation);
			}
		}
	}
	
	/**
	 * Utility method to provide a common place to generate the droppable bean name for a
	 * given model class. 
	 * @param droppableModelClass
	 * @return
	 */
	public static String getDroppableBeanName(Class<?> droppableModelClass)
	{
		String shortClassName = ClassUtils.getShortName(droppableModelClass);
		return getDroppableBeanName(Introspector.decapitalize(shortClassName));
	}
	/** Get the Droppable name from the given model nsame. 
	 * Simply appends Droppable to the end. 
	 */
	public static String getDroppableBeanName(String droppableModelName)
	{
		return droppableModelName + "Droppable";
	}
	
	/**
	 * Utility method to provide a common place to generate the draggable bean name for a
	 * given model class. 
	 */
	public static String getDraggableBeanName(Class<?> draggableModelClass)
	{
		String shortClassName = ClassUtils.getShortName(draggableModelClass);
		return Introspector.decapitalize(shortClassName) + "Draggable";
	}
	
	private void checkForFieldOrGetter(String draggableName, String fieldName)
	{
		Set<String> draggable = beanFactory.getBean(draggableName, Set.class);
		if(null == draggable)
			throw new BeanInitializationException("Target draggable bean: " + draggableName + ", does not exist");
		if(!draggable.contains(fieldName))
			throw new BeanInitializationException("Target draggable bean: " + draggableName + ", does not have a draggable field:" + fieldName);
	}

	/**
	 * Checks to see if the target class has a field or getter method related to
	 * the field name. To get the method name, the first letter of the field
	 * is capitalised and then the word 'get' is prepended.
	 * 
	 * This is used to make sure that the fields to copy exists on the target
	 * draggable.
	 * 
	 * @param target The target class to search for the field. ie. The Draggable
	 * @param fieldName The name of the field to look for
	 * @return true if there is either a field (with draggable annotation) or
	 *         method matching the field name. false otherwise
	 */
	private static void checkForFieldOrGetter(Class target, String fieldName)
	{
		try
		{
			Field field = target.getField(fieldName);
			if(!field.isAnnotationPresent(Draggable.class))
				throw new BeanInitializationException("Draggable class: " + target + ", does not have a draggable field:" + fieldName);
			logger.debug("found field to copy");
			return;
		}
		catch (Exception e)
		{
		}
		
		try
		{
			String methodName = "get" + fieldName.substring(0,1).toUpperCase() + fieldName.substring(1);
			target.getMethod(methodName, null);
			logger.debug("found method to copy");
			return;
		}
		catch (Exception e)
		{
		}
		
		String msg = String.format("Failed to find field '%s' on target draggable: %s ", fieldName, target.getName());
		throw new BeanInitializationException(msg);
	}
}
