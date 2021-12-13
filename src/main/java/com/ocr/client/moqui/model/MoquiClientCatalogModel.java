package com.ocr.client.moqui.model;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.ObjectMapper;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "partyId", "pseudoId", "partyTypeEnumId", "disabled", "customerStatusId", "ownerPartyId",
		"externalId", "dataSourceId", "gatewayCimId", "comments", "shippingInstructions", "hasDuplicates",
		"lastDupCheckDate", "mergedToPartyId", "lastUpdatedStamp", "organizationName", "officeSiteName",
		"annualRevenue", "numEmployees", "idValue", "issuedBy", "issuedByPartyId", "expireDate", "toName", "attnName",
		"address1", "address2", "unitNumber", "directions", "city", "cityGeoId", "schoolDistrictGeoId", "countyGeoId",
		"stateProvinceGeoId", "countryGeoId", "postalCode", "postalCodeExt", "postalCodeGeoId", "geoPointId",
		"commercial", "accessCode", "telecomContactMechId", "emailContactMechId", "shipGatewayAddressId", "countryCode",
		"areaCode", "contactNumber", "askForName", "roleTypeId", "postalAddressMechPurposeId", "telecomMechPurposeId" })
@Generated("jsonschema2pojo")
public class MoquiClientCatalogModel {

	@JsonProperty("partyId")
	private String partyId;
	@JsonProperty("pseudoId")
	private String pseudoId;
	@JsonProperty("partyTypeEnumId")
	private String partyTypeEnumId;
	@JsonProperty("disabled")
	private String disabled;
	@JsonProperty("customerStatusId")
	private String customerStatusId;
	@JsonProperty("ownerPartyId")
	private String ownerPartyId;
	@JsonProperty("externalId")
	private String externalId;
	@JsonProperty("dataSourceId")
	private String dataSourceId;
	@JsonProperty("gatewayCimId")
	private String gatewayCimId;
	@JsonProperty("comments")
	private String comments;
	@JsonProperty("shippingInstructions")
	private String shippingInstructions;
	@JsonProperty("hasDuplicates")
	private String hasDuplicates;
	@JsonProperty("lastDupCheckDate")
	private String lastDupCheckDate;
	@JsonProperty("mergedToPartyId")
	private String mergedToPartyId;
	@JsonProperty("lastUpdatedStamp")
	private String lastUpdatedStamp;
	@JsonProperty("organizationName")
	private String organizationName;
	@JsonProperty("officeSiteName")
	private String officeSiteName;
	@JsonProperty("annualRevenue")
	private Integer annualRevenue;
	@JsonProperty("numEmployees")
	private Integer numEmployees;
	@JsonProperty("idValue")
	private String idValue;
	@JsonProperty("issuedBy")
	private String issuedBy;
	@JsonProperty("issuedByPartyId")
	private String issuedByPartyId;
	@JsonProperty("expireDate")
	private String expireDate;
	@JsonProperty("toName")
	private String toName;
	@JsonProperty("attnName")
	private String attnName;
	@JsonProperty("address1")
	private String address1;
	@JsonProperty("address2")
	private String address2;
	@JsonProperty("unitNumber")
	private String unitNumber;
	@JsonProperty("directions")
	private String directions;
	@JsonProperty("city")
	private String city;
	@JsonProperty("cityGeoId")
	private String cityGeoId;
	@JsonProperty("schoolDistrictGeoId")
	private String schoolDistrictGeoId;
	@JsonProperty("countyGeoId")
	private String countyGeoId;
	@JsonProperty("stateProvinceGeoId")
	private String stateProvinceGeoId;
	@JsonProperty("countryGeoId")
	private String countryGeoId;
	@JsonProperty("postalCode")
	private String postalCode;
	@JsonProperty("postalCodeExt")
	private String postalCodeExt;
	@JsonProperty("postalCodeGeoId")
	private String postalCodeGeoId;
	@JsonProperty("geoPointId")
	private String geoPointId;
	@JsonProperty("commercial")
	private String commercial;
	@JsonProperty("accessCode")
	private String accessCode;
	@JsonProperty("telecomContactMechId")
	private String telecomContactMechId;
	@JsonProperty("emailContactMechId")
	private String emailContactMechId;
	@JsonProperty("shipGatewayAddressId")
	private String shipGatewayAddressId;
	@JsonProperty("countryCode")
	private String countryCode;
	@JsonProperty("areaCode")
	private String areaCode;
	@JsonProperty("contactNumber")
	private String contactNumber;
	@JsonProperty("askForName")
	private String askForName;
	@JsonProperty("roleTypeId")
	private String roleTypeId;
	@JsonProperty("postalAddressMechPurposeId")
	private String postalAddressMechPurposeId;
	@JsonProperty("telecomMechPurposeId")
	private String telecomMechPurposeId;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("partyId")
	public String getPartyId() {
		return partyId;
	}

	@JsonProperty("partyId")
	public void setPartyId(String partyId) {
		this.partyId = partyId;
	}

	@JsonProperty("pseudoId")
	public String getPseudoId() {
		return pseudoId;
	}

	@JsonProperty("pseudoId")
	public void setPseudoId(String pseudoId) {
		this.pseudoId = pseudoId;
	}

	@JsonProperty("partyTypeEnumId")
	public String getPartyTypeEnumId() {
		return partyTypeEnumId;
	}

	@JsonProperty("partyTypeEnumId")
	public void setPartyTypeEnumId(String partyTypeEnumId) {
		this.partyTypeEnumId = partyTypeEnumId;
	}

	@JsonProperty("disabled")
	public String getDisabled() {
		return disabled;
	}

	@JsonProperty("disabled")
	public void setDisabled(String disabled) {
		this.disabled = disabled;
	}

	@JsonProperty("customerStatusId")
	public String getCustomerStatusId() {
		return customerStatusId;
	}

	@JsonProperty("customerStatusId")
	public void setCustomerStatusId(String customerStatusId) {
		this.customerStatusId = customerStatusId;
	}

	@JsonProperty("ownerPartyId")
	public String getOwnerPartyId() {
		return ownerPartyId;
	}

	@JsonProperty("ownerPartyId")
	public void setOwnerPartyId(String ownerPartyId) {
		this.ownerPartyId = ownerPartyId;
	}

	@JsonProperty("externalId")
	public String getExternalId() {
		return externalId;
	}

	@JsonProperty("externalId")
	public void setExternalId(String externalId) {
		this.externalId = externalId;
	}

	@JsonProperty("dataSourceId")
	public String getDataSourceId() {
		return dataSourceId;
	}

	@JsonProperty("dataSourceId")
	public void setDataSourceId(String dataSourceId) {
		this.dataSourceId = dataSourceId;
	}

	@JsonProperty("gatewayCimId")
	public String getGatewayCimId() {
		return gatewayCimId;
	}

	@JsonProperty("gatewayCimId")
	public void setGatewayCimId(String gatewayCimId) {
		this.gatewayCimId = gatewayCimId;
	}

	@JsonProperty("comments")
	public String getComments() {
		return comments;
	}

	@JsonProperty("comments")
	public void setComments(String comments) {
		this.comments = comments;
	}

	@JsonProperty("shippingInstructions")
	public String getShippingInstructions() {
		return shippingInstructions;
	}

	@JsonProperty("shippingInstructions")
	public void setShippingInstructions(String shippingInstructions) {
		this.shippingInstructions = shippingInstructions;
	}

	@JsonProperty("hasDuplicates")
	public String getHasDuplicates() {
		return hasDuplicates;
	}

	@JsonProperty("hasDuplicates")
	public void setHasDuplicates(String hasDuplicates) {
		this.hasDuplicates = hasDuplicates;
	}

	@JsonProperty("lastDupCheckDate")
	public String getLastDupCheckDate() {
		return lastDupCheckDate;
	}

	@JsonProperty("lastDupCheckDate")
	public void setLastDupCheckDate(String lastDupCheckDate) {
		this.lastDupCheckDate = lastDupCheckDate;
	}

	@JsonProperty("mergedToPartyId")
	public String getMergedToPartyId() {
		return mergedToPartyId;
	}

	@JsonProperty("mergedToPartyId")
	public void setMergedToPartyId(String mergedToPartyId) {
		this.mergedToPartyId = mergedToPartyId;
	}

	@JsonProperty("lastUpdatedStamp")
	public String getLastUpdatedStamp() {
		return lastUpdatedStamp;
	}

	@JsonProperty("lastUpdatedStamp")
	public void setLastUpdatedStamp(String lastUpdatedStamp) {
		this.lastUpdatedStamp = lastUpdatedStamp;
	}

	@JsonProperty("organizationName")
	public String getOrganizationName() {
		return organizationName;
	}

	@JsonProperty("organizationName")
	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	@JsonProperty("officeSiteName")
	public String getOfficeSiteName() {
		return officeSiteName;
	}

	@JsonProperty("officeSiteName")
	public void setOfficeSiteName(String officeSiteName) {
		this.officeSiteName = officeSiteName;
	}

	@JsonProperty("annualRevenue")
	public Integer getAnnualRevenue() {
		return annualRevenue;
	}

	@JsonProperty("annualRevenue")
	public void setAnnualRevenue(Integer annualRevenue) {
		this.annualRevenue = annualRevenue;
	}

	@JsonProperty("numEmployees")
	public Integer getNumEmployees() {
		return numEmployees;
	}

	@JsonProperty("numEmployees")
	public void setNumEmployees(Integer numEmployees) {
		this.numEmployees = numEmployees;
	}

	@JsonProperty("idValue")
	public String getIdValue() {
		return idValue;
	}

	@JsonProperty("idValue")
	public void setIdValue(String idValue) {
		this.idValue = idValue;
	}

	@JsonProperty("issuedBy")
	public String getIssuedBy() {
		return issuedBy;
	}

	@JsonProperty("issuedBy")
	public void setIssuedBy(String issuedBy) {
		this.issuedBy = issuedBy;
	}

	@JsonProperty("issuedByPartyId")
	public String getIssuedByPartyId() {
		return issuedByPartyId;
	}

	@JsonProperty("issuedByPartyId")
	public void setIssuedByPartyId(String issuedByPartyId) {
		this.issuedByPartyId = issuedByPartyId;
	}

	@JsonProperty("expireDate")
	public String getExpireDate() {
		return expireDate;
	}

	@JsonProperty("expireDate")
	public void setExpireDate(String expireDate) {
		this.expireDate = expireDate;
	}

	@JsonProperty("toName")
	public String getToName() {
		return toName;
	}

	@JsonProperty("toName")
	public void setToName(String toName) {
		this.toName = toName;
	}

	@JsonProperty("attnName")
	public String getAttnName() {
		return attnName;
	}

	@JsonProperty("attnName")
	public void setAttnName(String attnName) {
		this.attnName = attnName;
	}

	@JsonProperty("address1")
	public String getAddress1() {
		return address1;
	}

	@JsonProperty("address1")
	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	@JsonProperty("address2")
	public String getAddress2() {
		return address2;
	}

	@JsonProperty("address2")
	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	@JsonProperty("unitNumber")
	public String getUnitNumber() {
		return unitNumber;
	}

	@JsonProperty("unitNumber")
	public void setUnitNumber(String unitNumber) {
		this.unitNumber = unitNumber;
	}

	@JsonProperty("directions")
	public String getDirections() {
		return directions;
	}

	@JsonProperty("directions")
	public void setDirections(String directions) {
		this.directions = directions;
	}

	@JsonProperty("city")
	public String getCity() {
		return city;
	}

	@JsonProperty("city")
	public void setCity(String city) {
		this.city = city;
	}

	@JsonProperty("cityGeoId")
	public String getCityGeoId() {
		return cityGeoId;
	}

	@JsonProperty("cityGeoId")
	public void setCityGeoId(String cityGeoId) {
		this.cityGeoId = cityGeoId;
	}

	@JsonProperty("schoolDistrictGeoId")
	public String getSchoolDistrictGeoId() {
		return schoolDistrictGeoId;
	}

	@JsonProperty("schoolDistrictGeoId")
	public void setSchoolDistrictGeoId(String schoolDistrictGeoId) {
		this.schoolDistrictGeoId = schoolDistrictGeoId;
	}

	@JsonProperty("countyGeoId")
	public String getCountyGeoId() {
		return countyGeoId;
	}

	@JsonProperty("countyGeoId")
	public void setCountyGeoId(String countyGeoId) {
		this.countyGeoId = countyGeoId;
	}

	@JsonProperty("stateProvinceGeoId")
	public String getStateProvinceGeoId() {
		return stateProvinceGeoId;
	}

	@JsonProperty("stateProvinceGeoId")
	public void setStateProvinceGeoId(String stateProvinceGeoId) {
		this.stateProvinceGeoId = stateProvinceGeoId;
	}

	@JsonProperty("countryGeoId")
	public String getCountryGeoId() {
		return countryGeoId;
	}

	@JsonProperty("countryGeoId")
	public void setCountryGeoId(String countryGeoId) {
		this.countryGeoId = countryGeoId;
	}

	@JsonProperty("postalCode")
	public String getPostalCode() {
		return postalCode;
	}

	@JsonProperty("postalCode")
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	@JsonProperty("postalCodeExt")
	public String getPostalCodeExt() {
		return postalCodeExt;
	}

	@JsonProperty("postalCodeExt")
	public void setPostalCodeExt(String postalCodeExt) {
		this.postalCodeExt = postalCodeExt;
	}

	@JsonProperty("postalCodeGeoId")
	public String getPostalCodeGeoId() {
		return postalCodeGeoId;
	}

	@JsonProperty("postalCodeGeoId")
	public void setPostalCodeGeoId(String postalCodeGeoId) {
		this.postalCodeGeoId = postalCodeGeoId;
	}

	@JsonProperty("geoPointId")
	public String getGeoPointId() {
		return geoPointId;
	}

	@JsonProperty("geoPointId")
	public void setGeoPointId(String geoPointId) {
		this.geoPointId = geoPointId;
	}

	@JsonProperty("commercial")
	public String getCommercial() {
		return commercial;
	}

	@JsonProperty("commercial")
	public void setCommercial(String commercial) {
		this.commercial = commercial;
	}

	@JsonProperty("accessCode")
	public String getAccessCode() {
		return accessCode;
	}

	@JsonProperty("accessCode")
	public void setAccessCode(String accessCode) {
		this.accessCode = accessCode;
	}

	@JsonProperty("telecomContactMechId")
	public String getTelecomContactMechId() {
		return telecomContactMechId;
	}

	@JsonProperty("telecomContactMechId")
	public void setTelecomContactMechId(String telecomContactMechId) {
		this.telecomContactMechId = telecomContactMechId;
	}

	@JsonProperty("emailContactMechId")
	public String getEmailContactMechId() {
		return emailContactMechId;
	}

	@JsonProperty("emailContactMechId")
	public void setEmailContactMechId(String emailContactMechId) {
		this.emailContactMechId = emailContactMechId;
	}

	@JsonProperty("shipGatewayAddressId")
	public String getShipGatewayAddressId() {
		return shipGatewayAddressId;
	}

	@JsonProperty("shipGatewayAddressId")
	public void setShipGatewayAddressId(String shipGatewayAddressId) {
		this.shipGatewayAddressId = shipGatewayAddressId;
	}

	@JsonProperty("countryCode")
	public String getCountryCode() {
		return countryCode;
	}

	@JsonProperty("countryCode")
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	@JsonProperty("areaCode")
	public String getAreaCode() {
		return areaCode;
	}

	@JsonProperty("areaCode")
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	@JsonProperty("contactNumber")
	public String getContactNumber() {
		return contactNumber;
	}

	@JsonProperty("contactNumber")
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	@JsonProperty("askForName")
	public String getAskForName() {
		return askForName;
	}

	@JsonProperty("askForName")
	public void setAskForName(String askForName) {
		this.askForName = askForName;
	}

	@JsonProperty("roleTypeId")
	public String getRoleTypeId() {
		return roleTypeId;
	}

	@JsonProperty("roleTypeId")
	public void setRoleTypeId(String roleTypeId) {
		this.roleTypeId = roleTypeId;
	}

	@JsonProperty("postalAddressMechPurposeId")
	public String getPostalAddressMechPurposeId() {
		return postalAddressMechPurposeId;
	}

	@JsonProperty("postalAddressMechPurposeId")
	public void setPostalAddressMechPurposeId(String postalAddressMechPurposeId) {
		this.postalAddressMechPurposeId = postalAddressMechPurposeId;
	}

	@JsonProperty("telecomMechPurposeId")
	public String getTelecomMechPurposeId() {
		return telecomMechPurposeId;
	}

	@JsonProperty("telecomMechPurposeId")
	public void setTelecomMechPurposeId(String telecomMechPurposeId) {
		this.telecomMechPurposeId = telecomMechPurposeId;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

	public String toJson() {

		MoquiClientCatalogModel model = new MoquiClientCatalogModel();

		ObjectMapper objectMapper = new ObjectMapper();

		try {

			// get MoquiClientCatalogModel object as a json string
			String jsonStr = objectMapper.writeValueAsString(model);

			return jsonStr;
		}

		catch (IOException e) {
			e.printStackTrace();

			return null;
		}

	}

}
