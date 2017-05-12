package org.yserver.utils;

import org.apache.commons.lang3.Validate;
import org.springframework.util.StringUtils;
import org.yserver.utils.ex.SystemException;
import org.yserver.y;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;

public final class ReflectionUtil
{

    /**
     * 对于被cglib AOP过的对象包含此符号
     */
    public static final String CGLIB_CLASS_SEPARATOR = "$$";

    private ReflectionUtil()
    {
    }

    /**
     * 调用Getter方法.
     */
    public static Object invokeGetter(Object obj, String propertyName)
    {
        String getterMethodName = "get" + StringUtils.capitalize(propertyName);
        try
        {
            return invokeMethod(obj, getterMethodName, new Class[]{}, new Object[]{});
        }
        catch (Exception ignore)
        {
            return null;
        }
    }

    /**
     * 调用Setter方法.使用value的Class来查找Setter方法.
     */
    public static void invokeSetter(Object obj, String propertyName, Object value)
    {
        invokeSetter(obj, propertyName, value, null);
    }

    /**
     * 调用Setter方法.
     *
     * @param propertyType 用于查找Setter方法,为空时使用value的Class替代.
     */
    public static void invokeSetter(Object obj, String propertyName, Object value, Class<?> propertyType)
    {
        Class<?> type = propertyType != null ? propertyType : value.getClass();
        String setterMethodName = "set" + StringUtils.capitalize(propertyName);
        invokeMethod(obj, setterMethodName, new Class[]{type}, new Object[]{value});
    }

    /**
     * 直接读取对象属性值, 无视private/protected修饰符, 不经过getter函数.
     */
    public static Object getFieldValue(final Object obj, final String fieldName)
    {
        Field field = getAccessibleField(obj, fieldName);

        if (field == null)
        {
            throw new IllegalArgumentException("Could not find field [" + fieldName + "] on target [" + obj + "]");
        }
        Object result = null;
        try
        {
            result = field.get(obj);
        }
        catch (IllegalAccessException e)
        {
            y.log().error(e.getMessage());
        }
        return result;
    }

    /**
     * 直接设置对象属性值, 无视private/protected修饰符, 不经过setter函数.
     */
    public static void setFieldValue(final Object obj, final String fieldName, final Object value)
    {
        Field field = getAccessibleField(obj, fieldName);

        if (field == null)
        {
            throw new IllegalArgumentException("Could not find field [" + fieldName + "] on target [" + obj + "]");
        }

        try
        {
            field.set(obj, value);
        }
        catch (IllegalAccessException e)
        {
            y.log().error(e.getMessage());
        }
    }

    /**
     * 对于被cglib AOP过的对象, 取得真实的Class类型.
     */
    public static Class<?> getUserClass(Class<?> clazz)
    {
        if (clazz != null && clazz.getName().contains(CGLIB_CLASS_SEPARATOR))
        {
            Class<?> superClass = clazz.getSuperclass();
            if (superClass != null && !Object.class.equals(superClass))
            {
                return superClass;
            }
        }
        return clazz;
    }

    /**
     * 直接调用对象方法, 无视private/protected修饰符. 用于一次性调用的情况.
     */
    public static Object invokeMethod(final Object obj, final String methodName, final Class<?>[] parameterTypes, final Object[] args)
    {
        Method method = getAccessibleMethod(obj, methodName, parameterTypes);
        if (method == null)
        {
            throw new IllegalArgumentException("Could not find method [" + methodName + "] on target [" + obj + "]");
        }
        try
        {
            return method.invoke(obj, args);
        }
        catch (Exception e)
        {
            throw new SystemException(e.getMessage(), e);
        }
    }

    /**
     * 循环向上转型, 获取对象的DeclaredField, 并强制设置为可访问. 如向上转型到Object仍无法找到, 返回null.
     */
    public static Field getAccessibleField(final Object obj, final String fieldName)
    {
        Validate.notNull(obj, "object can't be null");
        for (Class<?> superClass = obj.getClass(); superClass != Object.class; superClass = superClass.getSuperclass())
        {
            try
            {
                Field field = superClass.getDeclaredField(fieldName);
                field.setAccessible(true);
                return field;
            }
            catch (NoSuchFieldException e)
            {
                y.log().error(e.getMessage());
            }
        }
        return null;
    }

    /**
     * 循环向上转型, 获取对象的DeclaredMethod,并强制设置为可访问. 如向上转型到Object仍无法找到, 返回null.
     * 用于方法需要被多次调用的情况. 先使用本函数先取得Method,然后调用Method.invoke(Object obj, Object...
     * args)
     */
    public static Method getAccessibleMethod(final Object obj, final String methodName, final Class<?>... parameterTypes)
    {
        Validate.notNull(obj, "object can't be null");

        for (Class<?> superClass = obj.getClass(); superClass != Object.class; superClass = superClass.getSuperclass())
        {
            try
            {
                Method method = superClass.getDeclaredMethod(methodName, parameterTypes);

                method.setAccessible(true);

                return method;

            }
            catch (NoSuchMethodException e)
            {
                y.log().error(e.getMessage());
            }
        }
        return null;
    }

    /**
     * 通过反射, 获得Class定义中声明的父类的泛型参数的类型. 如无法找到, 返回Object.class. eg. public UserDao
     * extends HibernateDao<User>
     *
     * @param clazz The class to introspect
     * @return the first generic declaration, or Object.class if cannot be
     * determined
     */
    @SuppressWarnings("unchecked")
    public static <T> Class<T> getSuperClassGenricType(@SuppressWarnings("rawtypes") final Class clazz)
    {
        return getSuperClassGenricType(clazz, 0);
    }

    /**
     * 通过反射, 获得Class定义中声明的父类的泛型参数的类型. 如无法找到, 返回Object.class.
     *
     * @param clazz clazz The class to introspect
     * @param index the Index of the generic ddeclaration,start from 0.
     * @return the index generic declaration, or Object.class if cannot be
     * determined
     */
    @SuppressWarnings("rawtypes")
    public static Class getSuperClassGenricType(final Class clazz, final int index)
    {

        Type genType = clazz.getGenericSuperclass();

        if (!(genType instanceof ParameterizedType))
        {
            return Object.class;
        }

        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();

        if (index >= params.length || index < 0)
        {
            return Object.class;
        }
        if (!(params[index] instanceof Class))
        {
            return Object.class;
        }

        return (Class) params[index];
    }

    /***
     * 简单判断对象中所以的字段十分为空,不判断继承属性
     *
     * @return
     */
    public static boolean isFieldNullSimple(Object object, String... excludeField)
    {
        List<String> excludefieldName = new ArrayList<String>();
        if (null != excludeField)
        {
            excludefieldName = Arrays.asList(excludeField);
        }
        Field[] fields = object.getClass().getDeclaredFields();
        Object result = null;
        try
        {
            for (Field field : fields)
            {
                if (excludefieldName.contains(field.getName()))
                {
                    continue;
                }
                result = field.get(object);
                if (null != result)
                {
                    return false;
                }
            }
        }
        catch (IllegalAccessException e)
        {
            y.log().error(e.getMessage());
        }
        return true;
    }

    /**
     * 将对象的file组装成MAP
     *
     * @param object
     * @return
     */
    public static Map<String, Object> getFieldMapForClass(Object object)
    {
        Map<String, Object> parameterMap = null;
        try
        {
            parameterMap = new HashMap<String, Object>();
            Field[] fields = object.getClass().getDeclaredFields();
            // set self property
            for (Field field : fields)
            {
                populateFieldMap(field, object, parameterMap);
            }
            // set parent property
            Field[] parentFields = object.getClass().getSuperclass().getDeclaredFields();
            for (Field parentField : parentFields)
            {
                populateFieldMap(parentField, object, parameterMap);
            }
        }
        catch (Exception e)
        {
            throw new SystemException(e.getMessage(), e);
        }
        return parameterMap;
    }

    private static void populateFieldMap(Field field, Object object, Map<String, Object> fieldMap) throws IllegalAccessException
    {
        boolean isAccessible = field.isAccessible();
        field.setAccessible(true);
        fieldMap.put(field.getName(), field.get(object));
        field.setAccessible(isAccessible);
    }

}
