package com.cking.phss.dto;

import com.cking.phss.xml.util.XmlAttribute;
import com.cking.phss.xml.util.XmlTag;


/**
 * @author Loom
 */
public class Jmjbxx00008 implements IDto {
//	<?xml version="1.0" encoding="utf-8"?>
//	<Body>
//	<Request OrgCode="11213" operType="11213">
//	<Type>1</Type>
//	<UserID>1000</UserID>
//	<FamilyID>12312312</FamilyID>
//	</Request>
//	<Response>
//	<NationalityCD ID="222">
//	1111
//	</NationalityCD>
//	</Response>
//	</Body>

	@XmlTag(name = "Request")
	Request request = new Request();
	
	@XmlTag(name = "Response")
	Response response = new Response();
	
	class Request implements IDto{
		@XmlAttribute(name = "OrgCode")
		String orgCode = "11213";
		@XmlAttribute(name = "OperType")
		String operType = "11213";
		
		
		@XmlTag(name = "Type")
		int type = 1;
		@XmlTag(name = "UserID")
		String userID="1000";
		@XmlTag(name = "FamilyID")
		String familyID = "";
	}
	
	class Response implements IDto{
		@XmlTag(name = "NationalityCD")
		NationalityCD nationalityCD = new NationalityCD();
	}

	class NationalityCD implements IDto{
		@XmlAttribute(name = "ID")
		String id = "222";
		@XmlTag(name="TagValue",hasSubTag=false)
		String tagValue = "1111";
	}
	
	class FolkCD implements IDto{
		@XmlAttribute(name="ID")
		int id = 111; 
	}
}
