/**
 * 
 */
package com.cking.phss.bean;

import com.cking.phss.xml.util.XmlTag;

/**
 * @author mm
 *
 */
public class InspectAuxiliary implements IBean {
	// <!--辅助检查项目-->
	@XmlTag(name = "CheckProject")
	public String CheckProject = "";
	// <!-- 辅助检查结果-->
	@XmlTag(name = "CheckResult")
	public String CheckResult = "";
	// <!--检查人-->
	@XmlTag(name = "CheckDoctor")
	public String CheckDoctor = "";
	// <CheckDoctor></CheckDoctor>
	// <!--检查日期-->
	@XmlTag(name = "CheckDate")
	public String CheckDate = "";
}
