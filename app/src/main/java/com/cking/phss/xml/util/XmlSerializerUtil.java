package com.cking.phss.xml.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.util.Log;
import android.util.Xml;
import android.widget.EditText;
import android.widget.TextView;

import com.cking.phss.bean.BeanCD;
import com.cking.phss.bean.IBean;
import com.cking.phss.page.IPage;
import com.cking.phss.util.CheckBoxGroupUtil;
import com.cking.phss.util.RadioGroupUtil;
import com.cking.phss.widget.MultipleChoiceText;
import com.cking.phss.widget.RadioGroup;
import com.cking.phss.widget.SpinnerUtil;

/**
 * @author Loom 提供beanFromXML和beanToXml两个方法，实现bean与xml的互相转换。
 *         其中，bean根据需要，添加XmlTag和XmlAttribute两种Annotation
 */
public class XmlSerializerUtil {
	String TAG = "XmlSerializerUtil";
	static private XmlSerializerUtil mInstence = new XmlSerializerUtil();
	static private String ROOT_TAG = "Body";
    static private String ESPONSE_TAG = "Response";
    static private String ERRMSG_ATTR = "ErrMsg";
	static private String TAG_VALUE = "TagValue";

	private XmlSerializerUtil() {

	};

	public static XmlSerializerUtil getInstance() {
		return mInstence;
	}

	public IBean beanFromXML(Class<? extends IBean> clazz, String xmlStr) {
		if (xmlStr == null || xmlStr.length() == 0) {
			return null;
		}
		try {
			IBean bean = null;
			XmlPullParser parser = Xml.newPullParser();
			byte[] bytes = xmlStr.getBytes();
			ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
			parser.setInput(bais, "utf-8");
			int eventType = parser.getEventType();
			while (eventType != XmlPullParser.END_DOCUMENT) {

				if (parser.getEventType() == XmlPullParser.START_TAG
						&& parser.getName().equals(ROOT_TAG)) {
					return beanFromXmlSeg(parser, ROOT_TAG, clazz);
				}
				eventType = parser.next();

			}
			return bean;
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	private IBean beanFromXmlSeg(XmlPullParser parser, String tagName,
			Class<? extends IBean> clazz) throws InstantiationException,
			IllegalAccessException, XmlPullParserException, IOException,
			IllegalArgumentException, SecurityException,
			InvocationTargetException, NoSuchFieldException {
		IBean bean = null;

		bean = (IBean) clazz.newInstance();

		HashMap<String, Field> fieldsMap = getFieldsMap(clazz
				.getDeclaredFields());

		// 解析Attribute
		int attributeCount = parser.getAttributeCount();
		for (int i = 0; i < attributeCount; i++) {
			String k = parser.getAttributeName(i);
			String v = parser.getAttributeValue(i);
			if (fieldsMap.containsKey(k)) {
				setBasicValue(bean, k, v, fieldsMap.get(k));
			}
		}
		// 解析值
		if (fieldsMap.containsKey(TAG_VALUE)) {
			setBasicValue(bean, TAG_VALUE, parser.nextText(),
					fieldsMap.get(TAG_VALUE));
		}

		// 解析子节点
		int eventType = parser.getEventType();
		while (eventType != XmlPullParser.END_DOCUMENT
				&& !(eventType == XmlPullParser.END_TAG && parser.getName()
						.equals(tagName))) {
			switch (eventType) {
			case XmlPullParser.START_TAG:
				// 解析Tag
				String subTagName = parser.getName();

				if (!fieldsMap.containsKey(subTagName)) {
					break;
				}

				Field field = fieldsMap.get(subTagName);
				if (IBean.class.isAssignableFrom(field.getType())) {
					if (field.isAnnotationPresent(XmlTag.class)) {
						XmlTag xmlTag = field.getAnnotation(XmlTag.class);
						if (xmlTag.hasSubTag()) {
							field.set(
									bean,
									beanFromXmlSeg(parser, subTagName,
											(Class<? extends IBean>) field
													.getType()));
						} else {
							IBean subBean = (IBean) (field.getType()
									.newInstance());
							HashMap<String, Field> subFieldsMap = getFieldsMap(clazz
									.getDeclaredFields());

							int count = parser.getAttributeCount();
							for (int i = 0; i < count; i++) {
								String k = parser.getAttributeName(i);
								String v = parser.getAttributeValue(i);
								if (subFieldsMap.containsKey(k)) {
									setBasicValue(subBean, k, v,
											subFieldsMap.get(k));
								}
							}
							setBasicValue(subBean, TAG_VALUE,
									parser.nextText(),
									subFieldsMap.get(TAG_VALUE));
							field.set(bean, subBean);
						}
					}
				} else if (java.lang.String.class.isAssignableFrom(field
						.getType())) {
					field.set(bean, parser.nextText());
				} else if (java.lang.Integer.class.isAssignableFrom(field
						.getType()) || field.getType().getName().equals("int")) {
					String value = parser.nextText();
					if (value.length() == 0) {
						//field.setInt(bean, 0);
					} else {
						try {
							field.setInt(bean, Integer.parseInt(value));
						} catch (NumberFormatException e) {
							throw (new NumberFormatException("解析错误：需序列化的字段“"
									+ subTagName + "”为整型，但xml中的节点值“" + value
									+ "”无法转换为整型。" + e.toString()));
						}

					}

				} else if (List.class.isAssignableFrom(field.getType())) {
					if (field.isAnnotationPresent(XmlTag.class)) {
						XmlTag xmlTag = field.getAnnotation(XmlTag.class);
						// 不带组Tag的列表
						if (xmlTag.isListWithoutGroupTag()) {
							ArrayList<IBean> beanList = (ArrayList<IBean>) field
									.get(bean);
							if (beanList == null) {
								beanList = new ArrayList<IBean>();
								field.set(bean, beanList);
							}

							beanList.add(beanFromXmlSeg(
									parser,
									subTagName,
									(Class<? extends IBean>) getListItemClass(field)));

						} else {
							// 带组Tag的列表
							field.set(bean,
									parserList(parser, field, bean, subTagName));
						}
					}
				} else {
					throw new RuntimeException(
							"类型错误：需序列化的字段仅能为int或Integer或String类型：");
				}
				break;
			default:
				break;
			}
			eventType = parser.next();
		}

		return bean;
	}

	private Class<?> getListItemClass(Field field) {
		Type genericFieldType = field.getGenericType();
		ParameterizedType aType = (ParameterizedType) genericFieldType;
		Class<?> clazz = (Class<?>) aType.getActualTypeArguments()[0];
		return clazz;
	}

	private void parserSubBeanList(XmlPullParser parser, Field field,
			Field subListField, IBean bean, String parentTag)
			throws XmlPullParserException, IOException, InstantiationException,
			IllegalAccessException {

		Type genericFieldType = subListField.getGenericType();
		ParameterizedType aType = (ParameterizedType) genericFieldType;
		Class<?> clazz = (Class<?>) aType.getActualTypeArguments()[0];

		IBean subBean = (IBean) (field.getType().newInstance());
		field.set(bean, subBean);

		HashMap<String, Field> subFieldsMap = getFieldsMap(subBean.getClass()
				.getDeclaredFields());

		// 解析属性
		int attributeCount = parser.getAttributeCount();
		for (int i = 0; i < attributeCount; i++) {
			String k = parser.getAttributeName(i);
			String v = parser.getAttributeValue(i);
			if (subFieldsMap.containsKey(k)) {
				setBasicValue(subBean, k, v, subFieldsMap.get(k));
			}
		}

		// 解析列表中的bean
		if (IBean.class.isAssignableFrom(clazz)) {
			List<IBean> list = new ArrayList<IBean>();
			int eventType = parser.getEventType();
			while (eventType != XmlPullParser.END_DOCUMENT
					&& !(eventType == XmlPullParser.END_TAG && parser.getName()
							.equals(parentTag))) {
				switch (eventType) {
				case XmlPullParser.START_TAG:
					// 解析Tag
					String subTagName = parser.getName();
					if (!subTagName.equals(clazz.getSimpleName())) {
						break;
					}
					IBean itemBean = (IBean) (clazz.newInstance());
					HashMap<String, Field> itemSubFieldsMap = getFieldsMap(clazz
							.getDeclaredFields());

					// 解析属性
					int count = parser.getAttributeCount();
					for (int i = 0; i < count; i++) {
						String k = parser.getAttributeName(i);
						String v = parser.getAttributeValue(i);
						if (itemSubFieldsMap.containsKey(k)) {
							setBasicValue(itemBean, k, v,
									itemSubFieldsMap.get(k));
						}
					}

					// 解析TagValue
					setBasicValue(itemBean, TAG_VALUE, parser.nextText(),
							itemSubFieldsMap.get(TAG_VALUE));
					list.add(itemBean);

					break;
				default:
					break;
				}
				eventType = parser.next();
			}

			subListField.set(subBean, list);
		}
	}

	/**
	 * 从xml中解析List
	 */
	private List<?> parserList(XmlPullParser parser, Field field, IBean bean,
			String parentTag) throws XmlPullParserException,
			IllegalArgumentException, SecurityException,
			InstantiationException, IllegalAccessException,
			InvocationTargetException, IOException {
		Type genericFieldType = field.getGenericType();
		ParameterizedType aType = (ParameterizedType) genericFieldType;
		Class<?> clazz = (Class<?>) aType.getActualTypeArguments()[0];

		if (IBean.class.isAssignableFrom(clazz)) {
			List<IBean> list = new ArrayList<IBean>();
			int eventType = parser.getEventType();
			while (eventType != XmlPullParser.END_DOCUMENT
					&& !(eventType == XmlPullParser.END_TAG && parser.getName()
							.equals(parentTag))) {
				switch (eventType) {
				case XmlPullParser.START_TAG:
					// 解析Tag
					String subTagName = parser.getName();
					if (!subTagName.equals(clazz.getSimpleName())) {
						break;
					}
					IBean subBean = (IBean) (clazz.newInstance());
					HashMap<String, Field> subFieldsMap = getFieldsMap(clazz
							.getDeclaredFields());

					// 解析属性
					int count = parser.getAttributeCount();
					for (int i = 0; i < count; i++) {
						String k = parser.getAttributeName(i);
						String v = parser.getAttributeValue(i);
						if (subFieldsMap.containsKey(k)) {
							setBasicValue(subBean, k, v, subFieldsMap.get(k));
						}
					}

					// 解析TagValue
					setBasicValue(subBean, TAG_VALUE, parser.nextText(),
							subFieldsMap.get(TAG_VALUE));
					list.add(subBean);

					break;
				default:
					break;
				}
				eventType = parser.next();
			}
			return list;
		} else if (java.lang.String.class.isAssignableFrom(clazz)) {
			// TODO
			// 根据需要解析List<String>
		} else if (java.lang.Integer.class.isAssignableFrom(clazz)) {
			// TODO
			// 根据需要解析List<Integer>
		}
		return null;
	}

	private void setBasicValue(IBean bean, String k, String v, Field field)
			throws IllegalArgumentException, IllegalAccessException {
		if (java.lang.String.class.isAssignableFrom(field.getType())) {
			field.set(bean, v);
		} else if (java.lang.Integer.class.isAssignableFrom(field.getType())
				|| field.getType().getName().equals("int")) {
			if (v.length() == 0) {
				//field.setInt(bean, 0);
			} else {
				try {
					field.setInt(bean, Integer.parseInt(v));
				} catch (NumberFormatException e) {
					throw (new NumberFormatException("解析错误：需序列化的属性“" + k
							+ "”为整型，但xml中的节点值“" + v + "”无法转换为整型。"
							+ e.toString()));
				}

			}

		} else {
			throw new RuntimeException("类型错误：需序列化的属性仅能为int或Integer或String类型：");
		}
	}

	private HashMap<String, Field> getFieldsMap(Field[] fields) {
		HashMap<String, Field> map = new HashMap<String, Field>();
		for (Field field : fields) {
			if (!field.isAccessible()) {
				field.setAccessible(true);
			}
			if (field.isAnnotationPresent(XmlTag.class)) {
				XmlTag xmlTag = field.getAnnotation(XmlTag.class);
				map.put(xmlTag.name(), field);
			} else if (field.isAnnotationPresent(XmlAttribute.class)) {
				XmlAttribute xmlAttribute = field
						.getAnnotation(XmlAttribute.class);
				map.put(xmlAttribute.name(), field);
			}

		}
		return map;
	}

	/**
	 * @param bean
	 *            需要序列化成xml的bean
	 * @return 序列化生成的xml字符串
	 */
	public String beanToXml(IBean bean) {
		StringBuilder sb = new StringBuilder();
		sb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n");
		try {
			sb.append(beanToXmlSeg(ROOT_TAG, bean));
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

	public String beanToXmlSeg(String rootTagName, IBean bean)
			throws SecurityException, NoSuchMethodException,
			IllegalArgumentException, IllegalAccessException,
			InvocationTargetException {
		if (bean == null) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		StringBuilder xmlSubTags = new StringBuilder();
		StringBuilder xmlAttrs = new StringBuilder();

		Class<?> clazz = bean.getClass();
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			if (!field.isAccessible()) {
				field.setAccessible(true);
			}
		}

		for (Field field : fields) {
			if (field.isAnnotationPresent(XmlTag.class)) {
				XmlTag tag = field.getAnnotation(XmlTag.class);
				xmlSubTags.append(writeTag(tag, field, bean));
			} else if (field.isAnnotationPresent(XmlAttribute.class)) {
				XmlAttribute attribute = field
						.getAnnotation(XmlAttribute.class);
				xmlAttrs.append(" " + writeAttribute(attribute, field, bean));
			}
		}

		sb.append("\n<");
		sb.append(rootTagName);
		sb.append(xmlAttrs);
		sb.append(">");
		sb.append(xmlSubTags);
		sb.append("</");
		sb.append(rootTagName);
		sb.append(">");
		return sb.toString();

	}

	private String writeTag(XmlTag tag, Field field, IBean bean)
			throws IllegalArgumentException, IllegalAccessException,
			SecurityException, NoSuchMethodException, InvocationTargetException {

		String name = tag.name();
		String value = "";
		if (java.lang.String.class.isAssignableFrom(field.getType())) {
			if (field.get(bean) == null) {
				return "";
			}
			value = String.valueOf(field.get(bean));
		} else if (java.lang.Integer.class.isAssignableFrom(field.getType())
				|| field.getType().getName().equals("int")) {
			value = String.valueOf(field.get(bean));
		} else if (IBean.class.isAssignableFrom(field.getType())) {
			return beanToXmlSeg(name, (IBean) field.get(bean));
		} else if (List.class.isAssignableFrom(field.getType())) {

			return writeTagList(tag, name, (List<?>) field.get(bean));

		} else {
			throw new RuntimeException(
					"类型错误：需序列化的字段仅能为int或Integer或String类型，但传入参数类型为："
							+ field.getType().toString());
		}
		boolean hasSubTag = tag.hasSubTag();
		if (hasSubTag) {
			StringBuilder sb = new StringBuilder();
			sb.append("<");
			sb.append(name);
			sb.append(">");
			sb.append(value);
			sb.append("</");
			sb.append(name);
			sb.append(">\n");
			return sb.toString();
		} else {
			return value;
		}

	}

	private String writeTagList(XmlTag tag, String name, List<?> list)
			throws SecurityException, IllegalArgumentException,
			NoSuchMethodException, IllegalAccessException,
			InvocationTargetException {
		if (list == null || list.size() == 0) {
			return "";
		}

		Object obj = list.get(0);
		if (IBean.class.isAssignableFrom(obj.getClass())) {
			@SuppressWarnings("unchecked")
			List<IBean> myList = (List<IBean>) list;
			StringBuilder sbSubList = new StringBuilder();
			for(int i=0;i<myList.size();i++){
				String subName = myList.get(i).getClass().getSimpleName();
				sbSubList.append(beanToXmlSeg(subName, myList.get(i)));
			}
			if (tag.isListWithoutGroupTag()) {
				return sbSubList.toString();
			} else {
				StringBuilder sb = new StringBuilder();
				sb.append("\n<");
				sb.append(name);
				sb.append(">");
				sb.append(sbSubList);
				sb.append("</");
				sb.append(name);
				sb.append(">");
				return sb.toString();
			}
			
		} else if (java.lang.String.class.isAssignableFrom(obj.getClass())) {
			// 如果是字符串列表
			// TODO 根据需要开发
			throw new RuntimeException("类型错误：列表类型仅支持List<IBean>");
		} else if (java.lang.Integer.class.isAssignableFrom(obj.getClass())
				|| obj.getClass().getName().equals("int")) {
			// 如果是int列表
			// TODO 根据需要开发
			throw new RuntimeException("类型错误：列表类型仅支持List<IBean>");
		}
		return "";
	}

	private String writeAttribute(XmlAttribute attribute, Field field,
			IBean bean) throws IllegalArgumentException,
			IllegalAccessException, SecurityException, NoSuchMethodException,
			InvocationTargetException {
		String name = attribute.name();
		String value = "";
		if (java.lang.String.class.isAssignableFrom(field.getType())) {
			if (field.get(bean) == null) {
				return "";
			}
			value = (String) field.get(bean);
		} else if (java.lang.Integer.class.isAssignableFrom(field.getType())
				|| field.getType().getName().equals("int")) {
			value = String.valueOf(field.getInt(bean));
		} else {
			throw new RuntimeException("需序列化的字段仅能为int或Integer或String类型：");
		}
		return name + "=\"" + value + "\"";
	}

    /**
     * @param sfglLnsfPage01
     * @param beanList
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     */
    public void setValue(IPage view, IBean bean) throws IllegalArgumentException,
            IllegalAccessException {
        // 获取视图类所有的变量
        HashMap<String, Field> fieldsMap = getFieldsMap(view.getClass().getDeclaredFields());
        // 获取Bean类所有的变量
        HashMap<String, Field> fieldsMapBean = getFieldsMap(bean.getClass().getDeclaredFields());

        // 遍历视图类所有变量
        Iterator iter = fieldsMap.entrySet().iterator();
        int i = 0;
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            Object key = entry.getKey();
            Field val = (Field) entry.getValue();
            Field beanVal = (Field) fieldsMapBean.get(key);
            setValue(view, bean, val, beanVal);
            i++;
        }
    }

    /**
     * @param view
     * @param bean
     * @param val
     * @param beanVal
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     */
    private void setValue(IPage view, IBean bean, Field viewField, Field beanField)
            throws IllegalArgumentException, IllegalAccessException {
        if (TextView.class.isAssignableFrom(viewField.getType())
                || EditText.class.isAssignableFrom(viewField.getType())) {
            TextView obj = (TextView) viewField.get(view);
            if (obj == null) {
                Log.e(TAG, "解析错误:" + viewField.getType());
            }
            if (String.class.isAssignableFrom(beanField.getType())) { // String
                String value = (String) beanField.get(bean);
                if (value == null) {
                    return;
                }
                obj.setText(value);
            } else if (java.lang.Integer.class.isAssignableFrom(beanField.getType())
                    || beanField.getType().getName().equals("int")) {
                int value = (Integer) beanField.get(bean);
                obj.setText(value + "");
            }
        } else if (SpinnerUtil.class.isAssignableFrom(viewField.getType())
                && BeanCD.class.isAssignableFrom(beanField.getType())) {
            SpinnerUtil obj = (SpinnerUtil) viewField.get(view);
            if (obj == null) {
                Log.e(TAG, "解析错误:" + viewField.getType());
            }
            BeanCD value = (BeanCD) beanField.get(bean);
            if (value == null) {
                return;
            }
            obj.setCheckedByBeanCD(value);
        } else if (MultipleChoiceText.class.isAssignableFrom(viewField.getType())
                && BeanCD.class.isAssignableFrom(beanField.getType())) {
            MultipleChoiceText obj = (MultipleChoiceText) viewField.get(view);
            if (obj == null) {
                Log.e(TAG, "解析错误:" + viewField.getType());
            }
            BeanCD value = (BeanCD) beanField.get(bean);
            if (value == null) {
                return;
            }
            obj.setCheckedByBeanCD(value, "\\|");
        } else if (CheckBoxGroupUtil.class.isAssignableFrom(viewField.getType())
                && BeanCD.class.isAssignableFrom(beanField.getType())) {
            CheckBoxGroupUtil obj = (CheckBoxGroupUtil) viewField.get(view);
            if (obj == null) {
                Log.e(TAG, "解析错误:" + viewField.getType());
            }
            BeanCD value = (BeanCD) beanField.get(bean);
            if (value == null) {
                return;
            }
            obj.setCheckedByBeanCD(value);
        } else if (RadioGroupUtil.class.isAssignableFrom(viewField.getType())
                && BeanCD.class.isAssignableFrom(beanField.getType())) {
            RadioGroupUtil obj = (RadioGroupUtil) viewField.get(view);
            if (obj == null) {
                Log.e(TAG, "解析错误:" + viewField.getType());
            }
            BeanCD value = (BeanCD) beanField.get(bean);
            if (value == null) {
                return;
            }
            obj.setCheckedByBeanCD(value);
        } else if (RadioGroup.class.isAssignableFrom(viewField.getType())
                && BeanCD.class.isAssignableFrom(beanField.getType())) {
            RadioGroup obj = (RadioGroup) viewField.get(view);
            if (obj == null) {
                Log.e(TAG, "解析错误:" + viewField.getType());
            }
            BeanCD value = (BeanCD) beanField.get(bean);
            if (value == null) {
                return;
            }
            obj.setCheckedByBeanCD(value);
        }
    }

    /**
     * @param sfglLnsfPage01
     * @param beanList
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     */
    public void getValue(IPage view, IBean bean) throws IllegalArgumentException,
            IllegalAccessException {
        // 获取视图类所有的变量
        HashMap<String, Field> fieldsMap = getFieldsMap(view.getClass().getDeclaredFields());
        // 获取Bean类所有的变量
        HashMap<String, Field> fieldsMapBean = getFieldsMap(bean.getClass().getDeclaredFields());

        // 遍历视图类所有变量
        Iterator iter = fieldsMap.entrySet().iterator();
        int i = 0;
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            Object key = entry.getKey();
            Field val = (Field) entry.getValue();
            Field beanVal = (Field) fieldsMapBean.get(key);
            getValue(view, bean, val, beanVal);
            i++;
        }
    }

    /**
     * @param view
     * @param bean
     * @param viewField
     * @param beanField
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     */
    private void getValue(IPage view, IBean bean, Field viewField, Field beanField)
            throws IllegalArgumentException, IllegalAccessException {

        if (TextView.class.isAssignableFrom(viewField.getType())
                || EditText.class.isAssignableFrom(viewField.getType())) {
            TextView obj = (TextView) viewField.get(view);
            if (obj == null) {
                Log.e(TAG, "解析错误:" + viewField.getType());
            }
            String value = obj.getText().toString();
            if (value == null) {
                return;
            }
            if (String.class.isAssignableFrom(beanField.getType())) { // String
                beanField.set(bean, value);
            } else if (java.lang.Integer.class.isAssignableFrom(beanField.getType())
                    || beanField.getType().getName().equals("int")) {
                if (value.length() == 0) {
                    //beanField.setInt(bean, 0);
                } else {
                    try {
                        beanField.setInt(bean, Integer.parseInt(value));
                    } catch (NumberFormatException e) {
                        throw (new NumberFormatException("解析错误：" + e.toString()));
                    }
                }
            }
        } else if (SpinnerUtil.class.isAssignableFrom(viewField.getType())
                && BeanCD.class.isAssignableFrom(beanField.getType())) {
            SpinnerUtil obj = (SpinnerUtil) viewField.get(view);
            if (obj == null) {
                Log.e(TAG, "解析错误:" + viewField.getType());
            }
            BeanCD value = obj.getCheckedBeanCD();
            if (value == null) {
                return;
            }
            beanField.set(bean, value);
        } else if (MultipleChoiceText.class.isAssignableFrom(viewField.getType())
                && BeanCD.class.isAssignableFrom(beanField.getType())) {
            MultipleChoiceText obj = (MultipleChoiceText) viewField.get(view);
            if (obj == null) {
                Log.e(TAG, "解析错误:" + viewField.getType());
            }
            BeanCD value = obj.getCheckedBeanCD("|");
            if (value == null) {
                return;
            }
            beanField.set(bean, value);
        } else if (CheckBoxGroupUtil.class.isAssignableFrom(viewField.getType())
                && BeanCD.class.isAssignableFrom(beanField.getType())) {
            CheckBoxGroupUtil obj = (CheckBoxGroupUtil) viewField.get(view);
            if (obj == null) {
                Log.e(TAG, "解析错误:" + viewField.getType());
            }
            BeanCD value = obj.getCheckedBeanCD("|");
            if (value == null) {
                return;
            }
            beanField.set(bean, value);
        } else if (RadioGroupUtil.class.isAssignableFrom(viewField.getType())
                && BeanCD.class.isAssignableFrom(beanField.getType())) {
            RadioGroupUtil obj = (RadioGroupUtil) viewField.get(view);
            if (obj == null) {
                Log.e(TAG, "解析错误:" + viewField.getType());
            }
            BeanCD value = obj.getCheckedBeanCD();
            if (value == null) {
                return;
            }
            beanField.set(bean, value);
        } else if (RadioGroup.class.isAssignableFrom(viewField.getType())
                && BeanCD.class.isAssignableFrom(beanField.getType())) {
            RadioGroup obj = (RadioGroup) viewField.get(view);
            if (obj == null) {
                Log.e(TAG, "解析错误:" + viewField.getType());
            }
            BeanCD value = obj.getCheckedBeanCD();
            if (value == null) {
                return;
            }
            beanField.set(bean, value);
        }
    }

    /**
     * @param responseXmlStr
     * @return
     */
    public String getResponseErrorMsg(String xmlStr) {
        try {
            XmlPullParser parser = Xml.newPullParser();
            byte[] bytes = xmlStr.getBytes();
            ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
            parser.setInput(bais, "utf-8");
            int eventType = parser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {

                if (parser.getEventType() == XmlPullParser.START_TAG
                        && parser.getName().equals(ESPONSE_TAG)) {
                    // 解析Attribute
                    int attributeCount = parser.getAttributeCount();
                    for (int i = 0; i < attributeCount; i++) {
                        String k = parser.getAttributeName(i);
                        String v = parser.getAttributeValue(i);
                        if (ERRMSG_ATTR.equals(k)) {
                            return v;
                        }
                    }
                }
                eventType = parser.next();
            }
        } catch (XmlPullParserException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "";
    }
}
