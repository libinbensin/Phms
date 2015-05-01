package com.cking.phss.bean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.xinhuaxing.type.HashMap2;
import net.xinhuaxing.util.GlobalUtil;
import net.xinhuaxing.util.ResourcesFactory;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.cking.phss.dto.IDto;
import com.cking.phss.dto.IDtoBase;
import com.cking.phss.dto.innner.LocalRecord;
import com.cking.phss.dto.innner.Record;
import com.cking.phss.global.Global;
import com.cking.phss.net.NetRequest;
import com.cking.phss.sqlite.Jbxx;
import com.cking.phss.sqlite.JbxxBll;
import com.cking.phss.sqlite.Jktj;
import com.cking.phss.sqlite.JktjBll;
import com.cking.phss.sqlite.Sfgl;
import com.cking.phss.sqlite.SfglBll;
import com.cking.phss.sqlite.Sjgl;
import com.cking.phss.sqlite.SjglBll;
import com.cking.phss.util.MyApplication;
import com.cking.phss.xml.util.XmlSerializerUtil;

public class BeanUtil {
	private static BeanUtil mBeanFactory = new BeanUtil();
	private static final String TAG = "BeanUtil";

    public final static String[] JMJBXX_TB = new String[] { Jmjbxx.class.getName(),
            Jmjtxx.class.getName(), Jmjkxx.class.getName(), Jmxwxg.class.getName(), };
    public final static String[] JKTJ_TB = new String[] { Jktj_kstj.class.getName(), };
    public final static String[] SFGL_TB = new String[] { Sfgljl_gxy.class.getName(),
            Sfgljl_tnb.class.getName(), Sfgljl_jsb.class.getName(), Sfgljl_lnsf.class.getName(),
            Sfgljl_cjsf.class.getName(), };
    public final static String[] SJGL_TB = new String[] { Record.class.getName(),
            LocalRecord.class.getName(), };

	private BeanUtil() {
	}

    public static class TableQuryCondition {
        public String residentUUID;
        public String projectUUID;
        public Class<? extends IBean> beanClass;
        public IBean callbackBean;

        public TableQuryCondition(String residentUUID, String projectUUID,
                Class<? extends IBean> beanClass, IBean callbackBean) {
            this.residentUUID = residentUUID;
            this.projectUUID = projectUUID;
            this.beanClass = beanClass;
            this.callbackBean = callbackBean;
        }
    }

    public static class TableQuryResult {
        public boolean isSucc;
        public IBean callbackBean;

        public TableQuryResult(boolean isSucc, IBean callbackBean) {
            this.isSucc = isSucc;
            this.callbackBean = callbackBean;
        }
    }

    public static class SaveToDbResult {
        public boolean isAdd; // true 代表增加
        public String residentUUID;
        public String projectUUID;
        public IBean callbackBean;

        public SaveToDbResult(boolean isAdd, String residentUUID, String projectUUID,
                IBean callbackBean) {
            this.isAdd = isAdd;
            this.residentUUID = residentUUID;
            this.projectUUID = projectUUID;
            this.callbackBean = callbackBean;
        }
    }

	public static abstract class OnResultFromWeb {
		abstract public void onResult(List<IDto> listBean, boolean isSucc);
	}

    public static abstract class OnResultFromWeb2 {
        abstract public void onResult(List<String> listBean, boolean isSucc);
    }

    public static abstract class OnResultTableQury {
        abstract public void onResult(List<TableQuryResult> listBean, boolean isSucc);
    }

	public static abstract class OnResultFromDb {
		abstract public void onResult(List<IBean> listBean, boolean isSucc);
	}
	
    public static abstract class OnResultFromDb2 {
        abstract public void onResult(List<String> listBean, boolean isSucc);
    }

    public static abstract class OnResultSaveToDb {
        abstract public void onResult(List<SaveToDbResult> listBean, boolean isSucc);
    }

	public static BeanUtil getInstance() {
		return mBeanFactory;
	}

	public void getBeanFromWeb(final List<IDto> beanList,
			final OnResultFromWeb l) {
        getBeanFromWeb(Global.webserviceUrl, beanList, l);
	}

	public void saveBeanToWeb(List<IDto> beanList, final OnResultFromWeb l) {
		getBeanFromWeb(beanList, l);
	}

    public void saveBeanToWeb(final String url, List<IDto> beanList, final OnResultFromWeb l) {
        getBeanFromWeb(url, beanList, l);
    }

    public void getBeanFromWeb(final String url, final List<IDto> beanList, final OnResultFromWeb l) {
        final Handler handler = new Handler(Looper.getMainLooper());
        new Thread() {
            @Override
            public void run() {
                final List<IDto> responseBeanList = new ArrayList<IDto>();
                for (IDto bean : beanList) {
                    String responseXmlStr = NetRequest.getXmlByPost(url, XmlSerializerUtil
                            .getInstance().beanToXml(bean));
                    IDto responseBean = (IDto) XmlSerializerUtil.getInstance().beanFromXML(
                            bean.getClass(), responseXmlStr);
                    responseBeanList.add(responseBean);
                }

                Runnable r = new Runnable() {

                    @Override
                    public void run() {
                        boolean isSucc = false;
                        if (responseBeanList != null && responseBeanList.size() > 0) {
                            isSucc = true;

                        }
                        l.onResult(responseBeanList, isSucc);
                    }
                };
                handler.post(r);
                super.run();
            }

        }.start();
    }

    public void beanDbToWeb(final List<TableQuryCondition> tableQuryConditionList,
            final OnResultTableQury l) {
        final Handler handler = new Handler(Looper.getMainLooper());
        new Thread() {
            @Override
            public void run() {
                final List<TableQuryResult> responseBeanList = new ArrayList<TableQuryResult>();
                for (TableQuryCondition condition : tableQuryConditionList) {
                    String beanXml = null;
                    Class<? extends IBean> clazz = condition.beanClass;
                    String residentUUID = condition.residentUUID;
                    String projectUUID = condition.projectUUID;
                    IBean callbackBean = condition.callbackBean;
                    boolean foundTable = false;
                    if (!foundTable) {
                        for (String tbClass : JMJBXX_TB) { // 基本信息表的处理
                            if (tbClass.equals(clazz.getName())) {
                                Jbxx bean = null;
                                bean = JbxxBll.query(clazz.getName(), residentUUID); // 档案信息每人只有一条
                                beanXml = bean.getBean();
                                foundTable = true;
                                break;
                            }
                        }
                    }
                    if (!foundTable) {
                        for (String tbClass : JKTJ_TB) { // 健康体检表的处理
                            if (tbClass.equals(clazz.getName())) {
                                Jktj bean = null;
                                bean = JktjBll.query(clazz.getName(), residentUUID, projectUUID);
                                beanXml = bean.getBean();
                                foundTable = true;
                                break;
                            }
                        }
                    }
                    if (!foundTable) {
                        for (String tbClass : SFGL_TB) { // 随访管理表的处理
                            if (tbClass.equals(clazz.getName())) {
                                Sfgl bean = null;
                                bean = SfglBll.query(clazz.getName(), residentUUID, projectUUID);
                                beanXml = bean.getBean();
                                foundTable = true;
                                break;
                            }
                        }
                    }

                    // 发送数据
                    if (beanXml != null) {
                        String responseXmlStr = NetRequest.getXmlByPost(beanXml);
                        IDtoBase iDtoBase = (IDtoBase) XmlSerializerUtil.getInstance().beanFromXML(
                                IDtoBase.class, responseXmlStr);
                        boolean isSucc = false;
                        if (iDtoBase == null || iDtoBase.response == null) {
                            isSucc = false;
                        } else {
                            if (iDtoBase.response.errMsg.length() > 0) {
                                isSucc = false;
                            } else {
                                isSucc = true;
                            }
                        }
                        TableQuryResult tqr = new TableQuryResult(isSucc, callbackBean);
                        responseBeanList.add(tqr);
                    }
                }

                Runnable r = new Runnable() {

                    @Override
                    public void run() {
                        boolean isSucc = false;
                        if (responseBeanList != null && responseBeanList.size() > 0) {
                            isSucc = true;

                        }
                        l.onResult(responseBeanList, isSucc);
                    }
                };
                handler.post(r);
            }
        }.start();
    }

    /**
     * @param beanList
     * @param onResultTableQury
     */
    public void beanDeleteFromDb(final List<TableQuryCondition> tableQuryConditionList,
            final OnResultTableQury l) {
        final Handler handler = new Handler(Looper.getMainLooper());
        new Thread() {
            @Override
            public void run() {
                final List<TableQuryResult> responseBeanList = new ArrayList<TableQuryResult>();
                for (TableQuryCondition condition : tableQuryConditionList) {
                    String beanXml = null;
                    Class<? extends IBean> clazz = condition.beanClass;
                    String residentUUID = condition.residentUUID;
                    String projectUUID = condition.projectUUID;
                    IBean callbackBean = condition.callbackBean;
                    boolean foundTable = false;
                    if (!foundTable) {
                        for (String tbClass : JMJBXX_TB) { // 基本信息表的处理
                            if (tbClass.equals(clazz.getName())) {
                                JbxxBll.delete(clazz.getName(), residentUUID); // 档案信息每人只有一条
                                foundTable = true;
                                break;
                            }
                        }
                    }
                    if (!foundTable) {
                        for (String tbClass : JKTJ_TB) { // 健康体检表的处理
                            if (tbClass.equals(clazz.getName())) {
                                JktjBll.delete(clazz.getName(), residentUUID, projectUUID);
                                foundTable = true;
                                break;
                            }
                        }
                    }
                    if (!foundTable) {
                        for (String tbClass : SFGL_TB) { // 随访管理表的处理
                            if (tbClass.equals(clazz.getName())) {
                                SfglBll.delete(clazz.getName(), residentUUID, projectUUID);
                                foundTable = true;
                                break;
                            }
                        }
                    }

                    TableQuryResult tqr = new TableQuryResult(true, callbackBean);
                    responseBeanList.add(tqr);
                }

                Runnable r = new Runnable() {

                    @Override
                    public void run() {
                        boolean isSucc = false;
                        if (responseBeanList != null && responseBeanList.size() > 0) {
                            isSucc = true;

                        }
                        l.onResult(responseBeanList, isSucc);
                    }
                };
                handler.post(r);
            }
        }.start();
    }

	public void getJbxxFromDb(final List<Class<? extends IBean>> beanClassList,
			final OnResultFromDb l) {
		final Handler handler = new Handler(Looper.getMainLooper());
		new Thread() {
			@Override
			public void run() {
				List<IBean> beanList = new ArrayList<IBean>();
				boolean resultState = false;
				for (Class<? extends IBean> clazz : beanClassList) {
					Jbxx jbxx = null;
					if (MyApplication.getInstance().getSession()
							.getCurrentResident() != null) {
						String residentUUID = MyApplication.getInstance()
								.getSession().getCurrentResident()
								.getResidentUUID();
						jbxx = JbxxBll.query(clazz.getName(),
								residentUUID);
					}
					if (jbxx == null) {
						beanList.add(null);
					} else {
						beanList.add(XmlSerializerUtil.getInstance()
								.beanFromXML(clazz, jbxx.getBean()));
						resultState = true;
					}
				}
				final boolean isSucc = resultState;
				final List<IBean> retBeanList = beanList;
				Runnable r = new Runnable() {

					@Override
					public void run() {

						l.onResult(retBeanList, isSucc);
					}
				};
				handler.post(r);
			}
		}.start();
	}

	public void saveJbxxToDb(final List<IBean> beanList, final OnResultSaveToDb l) {
		final Handler handler = new Handler(Looper.getMainLooper());
		new Thread() {
			@Override
			public void run() {
                final List<SaveToDbResult> resultList = new ArrayList<SaveToDbResult>();
				for (IBean bean : beanList) {
                    SaveToDbResult result = JbxxBll.saveBean(bean);
                    resultList.add(result);
				}
				final boolean isSucc = true;
				Runnable r = new Runnable() {

					@Override
					public void run() {
                        l.onResult(resultList, isSucc);
					}
				};
				handler.post(r);
			}
		}.start();
	}

    public void saveSjglToDb(final List<IBean> beanList, final String residentUUID,
            final String projectUUID, final String projectType,
 final long downloadTime,
            final OnResultSaveToDb l) {
        final Handler handler = new Handler(Looper.getMainLooper());
        new Thread() {
            @Override
            public void run() {
                final List<SaveToDbResult> resultList = new ArrayList<SaveToDbResult>();
                for (IBean bean : beanList) {
                    SaveToDbResult result = SjglBll.saveBean(bean, residentUUID, projectUUID,
                            projectType, downloadTime);
                    resultList.add(result);
                }
                final boolean isSucc = true;
                Runnable r = new Runnable() {

                    @Override
                    public void run() {
                        l.onResult(resultList, isSucc);
                    }
                };
                handler.post(r);
            }
        }.start();
    }
	
    public void saveSfglToDb(final List<IBean> beanList, final long dateTime,
            final OnResultSaveToDb l) {
        if (dateTime == -1) { // 代表修改
            saveLastSfglToDb(beanList, -1, l);
        } else {
            saveSfglToDb(beanList, dateTime, -1, l);
        }
    }

    public void saveSfglToDb(final List<IBean> beanList, final long dateTime,
            final long downloadDateTime, final OnResultSaveToDb l) {
        final Handler handler = new Handler(Looper.getMainLooper());
        new Thread() {
            @Override
            public void run() {
                final List<SaveToDbResult> resultList = new ArrayList<SaveToDbResult>();
                for (IBean bean : beanList) {
                    SaveToDbResult result = SfglBll.saveBean(bean, dateTime, downloadDateTime);
                    resultList.add(result);
                }
                final boolean isSucc = true;
                Runnable r = new Runnable() {

                    @Override
                    public void run() {
                        l.onResult(resultList, isSucc);
                    }
                };
                handler.post(r);
            }
        }.start();
    }

    public void saveLastSfglToDb(final List<IBean> beanList, final long downloadDateTime,
            final OnResultSaveToDb l) {
        final Handler handler = new Handler(Looper.getMainLooper());
        new Thread() {
            @Override
            public void run() {
                final List<SaveToDbResult> resultList = new ArrayList<SaveToDbResult>();
                if (MyApplication.getInstance().getSession().getCurrentResident() != null) {
                    String residentUUID = MyApplication.getInstance().getSession()
                            .getCurrentResident().getResidentUUID();

                    for (IBean bean : beanList) {
                        Sfgl sfgl = SfglBll.queryLast(bean.getClass().getName(), residentUUID);
                        long dateTime = new Date().getTime();
                        if (null != sfgl) {
                            dateTime = sfgl.getDateTime();
                        }
                        SaveToDbResult result = SfglBll.saveBean(bean, dateTime, downloadDateTime);
                        resultList.add(result);
                    }
                }
                final boolean isSucc = true;
                Runnable r = new Runnable() {

                    @Override
                    public void run() {
                        l.onResult(resultList, isSucc);
                    }
                };
                handler.post(r);
            }
        }.start();
    }

    public void saveSfglToDb2(final List<String> beanList, final long dateTime,
            final OnResultSaveToDb l) {
        if (dateTime == -1) { // 代表修改
            saveLastSfglToDb2(beanList, -1, l);
        } else {
            saveSfglToDb2(beanList, dateTime, -1, l);
        }
    }

    public void saveSfglToDb2(final List<String> xmlTemplateList, final long dateTime,
            final long downloadDateTime, final OnResultSaveToDb l) {
        final Handler handler = new Handler(Looper.getMainLooper());
        new Thread() {
            @Override
            public void run() {
                try {
                    final List<SaveToDbResult> resultList = new ArrayList<SaveToDbResult>();
                    List<String> protocolList = GlobalUtil.getInstance()
                            .convertValueToProtocolBody(xmlTemplateList);

                    int i = 0;
                    for (String protocol : protocolList) {
                        SaveToDbResult result = null;

                        result = SfglBll.saveBean(protocol, xmlTemplateList.get(i), dateTime,
                                downloadDateTime);
                        resultList.add(result);
                        i++;
                    }

                    final boolean isSucc = true;
                    Runnable r = new Runnable() {

                        @Override
                        public void run() {
                            l.onResult(resultList, isSucc);
                        }
                    };
                    handler.post(r);
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    public void saveLastSfglToDb2(final List<String> xmlTemplateList, final long downloadDateTime,
            final OnResultSaveToDb l) {
        final Handler handler = new Handler(Looper.getMainLooper());
        new Thread() {
            @Override
            public void run() {
                try {
                    final List<SaveToDbResult> resultList = new ArrayList<SaveToDbResult>();
                    List<String> protocolList = GlobalUtil.getInstance()
                            .convertValueToProtocolBody(xmlTemplateList);
                    String residentUUID = MyApplication.getInstance().getSession()
                            .getCurrentResident().getResidentUUID();

                    int i = 0;
                    for (String protocol : protocolList) {
                        SaveToDbResult result = null;
                        Sfgl sfgl = SfglBll.queryLast(xmlTemplateList.get(i), residentUUID);
                        long dateTime = new Date().getTime();
                        if (null != sfgl) {
                            dateTime = sfgl.getDateTime();
                        }
                        result = SfglBll.saveBean(protocol, xmlTemplateList.get(i), dateTime,
                                downloadDateTime);
                        resultList.add(result);
                        i++;
                    }

                    final boolean isSucc = true;
                    Runnable r = new Runnable() {

                        @Override
                        public void run() {
                            l.onResult(resultList, isSucc);
                        }
                    };
                    handler.post(r);
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    public void getLastSfglFromDb2(final List<String> xmlTemplateList, final OnResultFromDb2 l) {
        final Handler handler = new Handler(Looper.getMainLooper());
        new Thread() {
            @Override
            public void run() {
                List<String> beanList = new ArrayList<String>();
                boolean resultState = false;
                for (String xmlTemplate : xmlTemplateList) {
                    Sfgl sfgl = null;
                    if (MyApplication.getInstance().getSession().getCurrentResident() != null) {
                        String residentUUID = MyApplication.getInstance().getSession()
                                .getCurrentResident().getResidentUUID();
                        sfgl = SfglBll.queryLast(xmlTemplate, residentUUID);
                    }
                    if (sfgl == null) {
                        beanList.add(null);
                    } else {
                        try {
                            GlobalUtil.getInstance().convertProtocolBodyToValue(sfgl.getBean());
                        } catch (IllegalArgumentException e) {
                            e.printStackTrace();
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InstantiationException e) {
                            e.printStackTrace();
                        }
                        beanList.add(xmlTemplate);
                        resultState = true;
                    }
                }
                final boolean isSucc = resultState;
                final List<String> retBeanList = beanList;
                Runnable r = new Runnable() {

                    @Override
                    public void run() {

                        l.onResult(retBeanList, isSucc);
                    }
                };
                handler.post(r);
            }
        }.start();
    }

	public void getLastSfglFromDb(final List<Class<? extends IBean>> beanClassList,
			final OnResultFromDb l) {
		final Handler handler = new Handler(Looper.getMainLooper());
		new Thread() {
			@Override
			public void run() {
				List<IBean> beanList = new ArrayList<IBean>();
				boolean resultState = false;
				for (Class<? extends IBean> clazz : beanClassList) {
					Sfgl sfgl = null;
					if (MyApplication.getInstance().getSession()
							.getCurrentResident() != null) {
						String residentUUID = MyApplication.getInstance()
								.getSession().getCurrentResident()
								.getResidentUUID();
						sfgl = SfglBll.queryLast(clazz.getName(),
								residentUUID);
					}
					if (sfgl == null) {
						beanList.add(null);
					} else {
						beanList.add(XmlSerializerUtil.getInstance()
								.beanFromXML(clazz, sfgl.getBean()));
						resultState = true;
					}
				}
				final boolean isSucc = resultState;
				final List<IBean> retBeanList = beanList;
				Runnable r = new Runnable() {

					@Override
					public void run() {

						l.onResult(retBeanList, isSucc);
					}
				};
				handler.post(r);
			}
		}.start();
	}

    public void getSfglFromDb(final List<Class<? extends IBean>> beanClassList,
            final String residentUUID, final String projectUUID, final OnResultFromDb l) {
        final Handler handler = new Handler(Looper.getMainLooper());
        new Thread() {
            @Override
            public void run() {
                List<IBean> beanList = new ArrayList<IBean>();
                boolean resultState = false;
                for (Class<? extends IBean> clazz : beanClassList) {
                    Sfgl sfgl = SfglBll.query(clazz.getName(), residentUUID, projectUUID);
                    if (sfgl == null) {
                        beanList.add(null);
                    } else {
                        beanList.add(XmlSerializerUtil.getInstance().beanFromXML(clazz,
                                sfgl.getBean()));
                        resultState = true;
                    }
                }
                final boolean isSucc = resultState;
                final List<IBean> retBeanList = beanList;
                Runnable r = new Runnable() {

                    @Override
                    public void run() {

                        l.onResult(retBeanList, isSucc);
                    }
                };
                handler.post(r);
            }
        }.start();
    }
    public void getSjglListFromDb(final List<Class<? extends IBean>> beanClassList,
            final long beginTime, final long endTime, final OnResultFromDb l) {
        final Handler handler = new Handler(Looper.getMainLooper());
        new Thread() {
            @Override
            public void run() {
                List<IBean> beanList = new ArrayList<IBean>();
                boolean resultState = false;
                for (Class<? extends IBean> clazz : beanClassList) {
                    List<Sjgl> sjgl = null;
                    sjgl = SjglBll.query(clazz.getName(), beginTime, endTime);
                    if (sjgl == null) {
                        beanList.add(null);
                    } else {
                        for (Sjgl s : sjgl) {
                            beanList.add(XmlSerializerUtil.getInstance().beanFromXML(clazz,
                                    s.getBean()));
                        }
                        resultState = true;
                    }
                }
                final boolean isSucc = resultState;
                final List<IBean> retBeanList = beanList;
                Runnable r = new Runnable() {

                    @Override
                    public void run() {

                        l.onResult(retBeanList, isSucc);
                    }
                };
                handler.post(r);
            }
        }.start();
    }

    public void getSfglDownloadTimeFromDb(final List<Class<? extends IBean>> beanClassList,
            final OnResultFromDb l) {
        final Handler handler = new Handler(Looper.getMainLooper());
        new Thread() {
            @Override
            public void run() {
                List<IBean> beanList = new ArrayList<IBean>();
                boolean resultState = false;
                for (Class<? extends IBean> clazz : beanClassList) {
                    Sfgl sfgl = null;
                    if (MyApplication.getInstance().getSession().getCurrentResident() != null) {
                        String residentUUID = MyApplication.getInstance().getSession()
                                .getCurrentResident().getResidentUUID();
                        sfgl = SfglBll.queryLast(clazz.getName(), residentUUID);
                    }
                    if (sfgl == null) {
                        beanList.add(null);
                    } else {
                        beanList.add(XmlSerializerUtil.getInstance().beanFromXML(clazz,
                                sfgl.getBean()));
                        resultState = true;
                    }
                }
                final boolean isSucc = resultState;
                final List<IBean> retBeanList = beanList;
                Runnable r = new Runnable() {

                    @Override
                    public void run() {

                        l.onResult(retBeanList, isSucc);
                    }
                };
                handler.post(r);
            }
        }.start();
    }
	
    public void saveJktjToDb(final List<IBean> beanList, final long dateTime,
            final OnResultSaveToDb l) {
		final Handler handler = new Handler(Looper.getMainLooper());
		new Thread() {
			@Override
			public void run() {
                final List<SaveToDbResult> resultList = new ArrayList<SaveToDbResult>();
				for (IBean bean : beanList) {
                    SaveToDbResult result = JktjBll.saveBean(bean, dateTime);
                    resultList.add(result);
				}
				final boolean isSucc = true;
				Runnable r = new Runnable() {

					@Override
					public void run() {
                        l.onResult(resultList, isSucc);
					}
				};
				handler.post(r);
			}
		}.start();
	}

    public void getJktjFromDb(final List<Class<? extends IBean>> beanClassList,
            final String residentUUID, final String projectUUID, final OnResultFromDb l) {
        final Handler handler = new Handler(Looper.getMainLooper());
        new Thread() {
            @Override
            public void run() {
                List<IBean> beanList = new ArrayList<IBean>();
                boolean resultState = false;
                for (Class<? extends IBean> clazz : beanClassList) {
                    Jktj jktj = JktjBll.query(clazz.getName(), residentUUID, projectUUID);
                    if (jktj == null) {
                        beanList.add(null);
                    } else {
                        beanList.add(XmlSerializerUtil.getInstance().beanFromXML(clazz,
                                jktj.getBean()));
                        resultState = true;
                    }
                }
                final boolean isSucc = resultState;
                final List<IBean> retBeanList = beanList;
                Runnable r = new Runnable() {

                    @Override
                    public void run() {

                        l.onResult(retBeanList, isSucc);
                    }
                };
                handler.post(r);
            }
        }.start();
    }
	
	public void getLastJktjFromDb(final List<Class<? extends IBean>> beanClassList,
			final OnResultFromDb l) {
		final Handler handler = new Handler(Looper.getMainLooper());
		new Thread() {
			@Override
			public void run() {
				List<IBean> beanList = new ArrayList<IBean>();
				boolean resultState = false;
				for (Class<? extends IBean> clazz : beanClassList) {
					Jktj jktj = null;
					if (MyApplication.getInstance().getSession()
							.getCurrentResident() != null) {
						String residentUUID = MyApplication.getInstance()
								.getSession().getCurrentResident()
								.getResidentUUID();
						jktj = JktjBll.queryLast(clazz.getName(),
								residentUUID);
					}
					if (jktj == null) {
						beanList.add(null);
					} else {
						beanList.add(XmlSerializerUtil.getInstance()
								.beanFromXML(clazz, jktj.getBean()));
						resultState = true;
					}
				}
				final boolean isSucc = resultState;
				final List<IBean> retBeanList = beanList;
				Runnable r = new Runnable() {

					@Override
					public void run() {

						l.onResult(retBeanList, isSucc);
					}
				};
				handler.post(r);
			}
		}.start();
	}

    /**
     * @param mJmjbxx
     * @param string
     * @param dataSource
     * @param operType
     * @param time
     * @param name
     */
    public void saveLocalRecord(Context context, Jmjbxx jmjbxx, String projectUUID,
            String projectCode, int dataSource, int operType, long operTime, String className) {
        if (MyApplication.getInstance().getSession().getCurrentResident() != null) {
            LocalRecord record = new LocalRecord();
            String residentUUID = MyApplication.getInstance().getSession().getCurrentResident()
                    .getResidentUUID();
            record.ResidentUUID = residentUUID;
            record.ProjectUUID = projectUUID;
            record.ClassName = className;
            record.ResidentID = jmjbxx.getResidentID();
            record.ResidentName = jmjbxx.getResidentName();
            record.CredentialsNo = jmjbxx.getPaperNum();
            record.Sex = new BeanCD(jmjbxx.getSexCD(), ResourcesFactory.findValue(context, "xb",
                    jmjbxx.getSexCD()));
            record.BirthDay = jmjbxx.getBirthDay();
            record.ProjectType = new BeanCD(projectCode, ResourcesFactory.findValue(context,
                    "scglxmsx", projectCode));
            if (dataSource == 0) { // 如果是0 则不处理, 获取数据来源，数据来源一旦创建就不再更改
                String xmlStr = SjglBll.queryBean(record.ResidentUUID, record.ProjectUUID,
                        record.ClassName);
                if (xmlStr != null) {
                    LocalRecord tmpRecord = (LocalRecord) XmlSerializerUtil.getInstance()
                            .beanFromXML(LocalRecord.class, xmlStr);
                    record.DataSource = tmpRecord.DataSource;
                } else { // 如果数据库中找不到，说明是新增，标记为非档案平台
                    record.DataSource = new BeanCD(2, ResourcesFactory.findValue(context,
                            "scglsjly", 2));
                }
            } else {
                record.DataSource = new BeanCD(dataSource, ResourcesFactory.findValue(context,
                        "scglsjly", dataSource));
            }
            record.OperType = new BeanCD(operType, ResourcesFactory.findValue(context, "scglczbz",
                    operType));
            record.CheckDate = new SimpleDateFormat("yyyy-MM-dd").format(operTime);
            record.Doctor = Global.doctorName;
            record.Upload = new BeanCD("0", "未上传");
            SjglBll.saveBean(record, residentUUID, projectUUID, record.ProjectType.getcD(),
                    new Date().getTime());
        }
    }

    public void getBeanFromWeb2(final List<String> xmlTemplateList, final OnResultFromWeb2 l) {
        getBeanFromWeb2(GlobalUtil.globalValueMap, xmlTemplateList, l);
    }

    public void saveBeanToWeb2(List<String> beanClassList, final OnResultFromWeb2 l) {
        getBeanFromWeb2(beanClassList, l);
    }

    public void getBeanFromWeb2(final HashMap2 valueMapList, final List<String> xmlTemplateList,
            final OnResultFromWeb2 l) {
        final Handler handler = new Handler(Looper.getMainLooper());
        new Thread() {
            @Override
            public void run() {
                try {
                    final List<String> responseBeanList = new ArrayList<String>();

                    List<String> protocolList = GlobalUtil.getInstance()
                            .convertValueToProtocolRequest(valueMapList, xmlTemplateList);
                    for (String protocol : protocolList) {
                        String responseXmlStr = NetRequest.getXmlByPost(protocol);
                        String errorMsg = XmlSerializerUtil.getInstance().getResponseErrorMsg(
                                responseXmlStr);
                        GlobalUtil.getInstance().convertProtocolResponseToValue(valueMapList,
                                responseXmlStr);
                        responseBeanList.add(errorMsg);
                    }

                    Runnable r = new Runnable() {

                        @Override
                        public void run() {
                            boolean isSucc = false;
                            if (responseBeanList != null && responseBeanList.size() > 0) {
                                isSucc = true;

                            }
                            l.onResult(responseBeanList, isSucc);
                        }
                    };
                    handler.post(r);
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                }
                super.run();
            }

        }.start();
    }

    public void saveBeanToWeb2(HashMap2 valueMapList, List<String> beanClassList,
            final OnResultFromWeb2 l) {
        getBeanFromWeb2(valueMapList, beanClassList, l);
    }
}
