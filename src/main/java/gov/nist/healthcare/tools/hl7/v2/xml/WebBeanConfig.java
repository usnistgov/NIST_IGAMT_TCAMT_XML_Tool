/**
 * This software was developed at the National Institute of Standards and Technology by employees
 * of the Federal Government in the course of their official duties. Pursuant to title 17 Section 105 of the
 * United States Code this software is not subject to copyright protection and is in the public domain.
 * This is an experimental system. NIST assumes no responsibility whatsoever for its use by other parties,
 * and makes no guarantees, expressed or implied, about its quality, reliability, or any other characteristic.
 * We would appreciate acknowledgement if the software is used. This software can be redistributed and/or
 * modified freely provided that any derivative works bear some notice that they are derived from it, and any
 * modified versions bear some notice that they have been modified.
 */

package gov.nist.healthcare.tools.hl7.v2.xml;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import gov.nist.healthcare.tools.hl7.v2.igamt.lite.domain.DTComponent;
import gov.nist.healthcare.tools.hl7.v2.igamt.lite.domain.IGDocumentConfiguration;
import gov.nist.healthcare.tools.hl7.v2.igamt.lite.domain.VariesMapItem;

/**
 * @author Harold Affo (NIST)
 * 
 */

@Configuration
public class WebBeanConfig {
	
	@Bean
	IGDocumentConfiguration igDocumentConfig() {
		IGDocumentConfiguration config = new IGDocumentConfiguration();
		config.setStatuses(toSet(new String[] { "Draft", "Active","Superceded", "Withdrawn" }));
		config.setDomainVersions(toSet(new String[] {"2.1", "2.2", "2.3", "2.3.1", "2.4", "2.5", "2.5.1", "2.6", "2.7", "2.7.1", "2.8", "2.8.1", "2.8.2"}));
		config.setSchemaVersions(toSet(new String[] {"1.0", "1.5", "2.0","2.5"}));
		config.setUsages(toSet(new String[] { "R", "RE", "O", "C", "X" }));
		config.setConditionalUsage(toSet(new String[] { "R", "RE", "O", "X" }));
		config.setCodeUsages(toSet(new String[] { "R", "P", "E" }));
		config.setCodeSources(toSet(new String[] { "HL7", "Local", "Redefined","SDO" }));
		config.setTableStabilities(toSet(new String[] { "Static", "Dynamic" }));
		config.setTableContentDefinitions(toSet(new String[] { "Extensional", "Intensional" }));
		config.setTableExtensibilities(toSet(new String[] { "Open", "Closed" }));
		config.setConstraintVerbs(toSet(new String[] { "SHALL be", "SHALL NOT be", "should be", "should not be", "may be", "may not be", "is", "is not" }));
		config.setConditionalConstraintVerbs(toSet(new String[] { "is", "is not" }));
		config.setConstraintTypes(toSet(new String[] { 
				"valued",
				"a literal value", 
				"one of list values", 
				"one of codes in ValueSet",
				"formatted value",
				"identical to another node",
				"equal to another node",
				"not-equal to another node",
				"greater than another node",
				"equal to or greater than another node",
				"less than another node",
				"equal to or less than another node",
				"equal to",
				"not-equal to",
				"greater than",
				"equal to or greater than",
				"less than",
				"equal to or less than",
				"valued sequentially starting with the value '1'"
		}));
		config.setPredefinedFormats(toSet(new String[] { "Positive Integer", "ISO-compliant OID",
				"Alphanumeric", "YYYY", "YYYYMM", "YYYYMMDD", "YYYYMMDDhh",
				"YYYYMMDDhhmm", "YYYYMMDDhhmmss", "YYYYMMDDhhmmss.sss",
				"YYYY+-ZZZZ", "YYYYMM+-ZZZZ", "YYYYMMDD+-ZZZZ",
				"YYYYMMDDhh+-ZZZZ", "YYYYMMDDhhmm+-ZZZZ",
				"YYYYMMDDhhmmss+-ZZZZ", "YYYYMMDDhhmmss.sss+-ZZZZ", "Regular expression"}));
		
		//ID, IS
		//CE, CF, CWE, CNE, CSU
		//HD, AUI, CK, CN, CNN, CX, EI, ERL, ELD, PLN, PPN, XCN
		
		config.setValueSetAllowedDTs(toSet(new String[] {"ID", "IS", "CE", "CF", "CWE", "CNE", "CSU", "HD", "AUI", "CK", "CN", "CNN", "CX", "EI", "ERL", "ELD", "PLN", "PPN", "XCN"}));
		config.setCodedElementDTs(toSet(new String[] {"CE", "CF", "CWE", "CNE", "CSU"}));
		config.setSingleValueSetDTs(toSet(new String[] {"ID", "IS", "ST", "NM"})); //ST and NM are partial
		Set<DTComponent> valueSetAllowedComponents = new HashSet<DTComponent>();
		valueSetAllowedComponents.add(new DTComponent("AD", 3));
		valueSetAllowedComponents.add(new DTComponent("AD", 4));
		valueSetAllowedComponents.add(new DTComponent("AD", 5));
		valueSetAllowedComponents.add(new DTComponent("CNS", 7));
		valueSetAllowedComponents.add(new DTComponent("CSU", 2));
		valueSetAllowedComponents.add(new DTComponent("CSU", 5));
		valueSetAllowedComponents.add(new DTComponent("CSU", 11));
		valueSetAllowedComponents.add(new DTComponent("CSU", 14));
		valueSetAllowedComponents.add(new DTComponent("LA2", 11));
		valueSetAllowedComponents.add(new DTComponent("LA2", 12));
		valueSetAllowedComponents.add(new DTComponent("LA2", 13));
		valueSetAllowedComponents.add(new DTComponent("OSD", 2));
		valueSetAllowedComponents.add(new DTComponent("OSD", 4));
		valueSetAllowedComponents.add(new DTComponent("XAD", 3));
		valueSetAllowedComponents.add(new DTComponent("XAD", 4));
		valueSetAllowedComponents.add(new DTComponent("XAD", 5));
		valueSetAllowedComponents.add(new DTComponent("XON", 3));
		valueSetAllowedComponents.add(new DTComponent("XON", 10));
		config.setValueSetAllowedComponents(valueSetAllowedComponents);
		HashMap<String, Set<String>> bindingLocationListByHL7Version = new HashMap<String, Set<String>>();
		// "2.1", "2.2", "2.3", "2.3.1", "2.4", "2.5", "2.5.1", "2.6", "2.7", "2.7.1", "2.8", "2.8.1", "2.8.2"
		bindingLocationListByHL7Version.put("2.1",   toSet(new String[] {"1", "4", "1 or 4"}));
		bindingLocationListByHL7Version.put("2.2",   toSet(new String[] {"1", "4", "1 or 4"}));
		bindingLocationListByHL7Version.put("2.3",   toSet(new String[] {"1", "4", "1 or 4"}));
		bindingLocationListByHL7Version.put("2.3.1", toSet(new String[] {"1", "4", "1 or 4"}));
		bindingLocationListByHL7Version.put("2.4",   toSet(new String[] {"1", "4", "1 or 4"}));
		bindingLocationListByHL7Version.put("2.5",   toSet(new String[] {"1", "4", "1 or 4"}));
		bindingLocationListByHL7Version.put("2.5.1", toSet(new String[] {"1", "4", "1 or 4"}));
		bindingLocationListByHL7Version.put("2.6",   toSet(new String[] {"1", "4", "1 or 4"}));
		bindingLocationListByHL7Version.put("2.7",   toSet(new String[] {"1", "4", "10", "1 or 4", "1 or 4 or 10"}));
		bindingLocationListByHL7Version.put("2.7.1", toSet(new String[] {"1", "4", "10", "1 or 4", "1 or 4 or 10"}));
		bindingLocationListByHL7Version.put("2.8",   toSet(new String[] {"1", "4", "10", "1 or 4", "1 or 4 or 10"}));
		bindingLocationListByHL7Version.put("2.8.1", toSet(new String[] {"1", "4", "10", "1 or 4", "1 or 4 or 10"}));
		bindingLocationListByHL7Version.put("2.8.2", toSet(new String[] {"1", "4", "10", "1 or 4", "1 or 4 or 10"}));
		config.setBindingLocationListByHL7Version(bindingLocationListByHL7Version);
		
		Set<VariesMapItem> variesMapItems = new HashSet<VariesMapItem>();
		variesMapItems.add(new VariesMapItem("2.8.2", "OBX", "5", "2", "0125"));
		variesMapItems.add(new VariesMapItem("2.8.2", "MFA", "5", "6", "0355"));
		variesMapItems.add(new VariesMapItem("2.8.2", "MFE", "4", "5", "0355"));
		variesMapItems.add(new VariesMapItem("2.8.1", "OBX", "5", "2", "0125"));
		variesMapItems.add(new VariesMapItem("2.8.1", "MFA", "5", "6", "0355"));
		variesMapItems.add(new VariesMapItem("2.8.1", "MFE", "4", "5", "0355"));
		variesMapItems.add(new VariesMapItem("2.8",   "OBX", "5", "2", "0125"));
		variesMapItems.add(new VariesMapItem("2.8",   "MFA", "5", "6", "0355"));
		variesMapItems.add(new VariesMapItem("2.8",   "MFE", "4", "5", "0355"));
		variesMapItems.add(new VariesMapItem("2.7.1", "OBX", "5", "2", "0125"));
		variesMapItems.add(new VariesMapItem("2.7.1", "MFA", "5", "6", "0355"));
		variesMapItems.add(new VariesMapItem("2.7.1", "MFE", "4", "5", "0355"));
		variesMapItems.add(new VariesMapItem("2.7",   "OBX", "5", "2", "0125"));
		variesMapItems.add(new VariesMapItem("2.7",   "MFA", "5", "6", "0355"));
		variesMapItems.add(new VariesMapItem("2.7",   "MFE", "4", "5", "0355"));
		variesMapItems.add(new VariesMapItem("2.6",   "OBX", "5", "2", "0125"));
		variesMapItems.add(new VariesMapItem("2.6",   "MFA", "5", "6", "0355"));
		variesMapItems.add(new VariesMapItem("2.6",   "MFE", "4", "5", "0355"));
		variesMapItems.add(new VariesMapItem("2.5.1", "OBX", "5", "2", "0125"));
		variesMapItems.add(new VariesMapItem("2.5.1", "MFA", "5", "6", "0355"));
		variesMapItems.add(new VariesMapItem("2.5.1", "MFE", "4", "5", "0355"));
		variesMapItems.add(new VariesMapItem("2.5",   "OBX", "5", "2", "0125"));
		variesMapItems.add(new VariesMapItem("2.5",   "MFA", "5", "6", "0355"));
		variesMapItems.add(new VariesMapItem("2.5",   "MFE", "4", "5", "0355"));
		variesMapItems.add(new VariesMapItem("2.4",   "OBX", "5", "2", "0125"));
		variesMapItems.add(new VariesMapItem("2.4",   "MFA", "5", "6", "0355"));
		variesMapItems.add(new VariesMapItem("2.4",   "MFE", "4", "5", "0355"));
		variesMapItems.add(new VariesMapItem("2.3.1", "OBX", "5", "2", "0125"));
		variesMapItems.add(new VariesMapItem("2.3.1", "MFA", "5", "6", "0355"));
		variesMapItems.add(new VariesMapItem("2.3.1", "MFE", "4", "5", "0355"));
		variesMapItems.add(new VariesMapItem("2.3",   "OBX", "5", "2", "0125"));
		variesMapItems.add(new VariesMapItem("2.3",   "MFE", "4", "5", "0355"));
		variesMapItems.add(new VariesMapItem("2.2",   "OBX", "5", "2", "0125"));
		config.setVariesMapItems(variesMapItems);
		return config;
	}
	
	private Set<String> toSet(String[] values) {
		Set<String> res = new HashSet<String>();
		for (String v : values) {
			res.add(v);
		}
		return res;
	}
}
