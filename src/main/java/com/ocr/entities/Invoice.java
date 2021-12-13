package com.ocr.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Invoice {

	@Id
	private String id;
	private String transmitterName;
	private String fileName;
    private String transmitterAddress;
    private String transmitterCif;
    private String receptorName;
    private String receptorAddress;
    private String receptorCif;
    private String reference;
    private String providerNif;
    private String providerCountry;
    private String providerCode;
    private String invoiceNumber;
    private String invoiceType;
    private String invoiceDate;
    private String invoiceVigencyDate;
    private String invoiceContableDate;
    private String concept;
    private String description;
    private String units;
    private String imports;
    private String oprtationDescription;
    private String bruteTotal;
    private String base;
    private String base0;
    private String base1;
    private String base2;
    private String base3;
    private String iva;
    private String iva0;
    private String iva1;
    private String iva2;
    private String iva3;
    private String total;
    private String irpf;
    private String retentionType;
    private String retentionBase;
    private String retentionImports;
    private String surchargesPercentage;
    private String surchargesBase;
    private String surchargesImports;
    private String suppliers;
    private String paymentType;
    private String status;
    private String category;
    private String types;
    private String owner;
    private String vigencyDate;
    
    private Double importsD;
    private Double bruteTotalD;
    private Double baseD;
    private Double ivaD;
    private Double totalD;
    private Double irpfD;
    private Double retentionBaseD;
    private Double retentionImportsD;
    private Double surchargesPercentageD;
    private Double surchargesBaseD;
    private Double surchargesImportsD;
    
    private Double base0D;
    private Double base1D;
    private Double base2D;
    private Double base3D;
    private Double iva0D;
    private Double iva1D;
    private Double iva2D;
    private Double iva3D;
    private Date invoiceContableDateDt;
    
    private String businessOwner;
    private String sublevel1;
    private String sublevel2;
    private String percentageBase;
    
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getTransmitterAddress() {
		return transmitterAddress;
	}
	public void setTransmitterAddress(String transmitterAddress) {
		this.transmitterAddress = transmitterAddress;
	}
	public String getTransmitterCif() {
		return transmitterCif;
	}
	public void setTransmitterCif(String transmitterCif) {
		this.transmitterCif = transmitterCif;
	}
	public String getReceptorName() {
		return receptorName;
	}
	public void setReceptorName(String receptorName) {
		this.receptorName = receptorName;
	}
	public String getReceptorAddress() {
		return receptorAddress;
	}
	public void setReceptorAddress(String receptorAddress) {
		this.receptorAddress = receptorAddress;
	}
	public String getReference() {
		return reference;
	}
	public void setReference(String reference) {
		this.reference = reference;
	}
	public String getProviderNif() {
		return providerNif;
	}
	public void setProviderNif(String providerNif) {
		this.providerNif = providerNif;
	}
	public String getReceptorCif() {
		return receptorCif;
	}
	public void setReceptorCif(String receptorCif) {
		this.receptorCif = receptorCif;
	}
	public String getProviderCountry() {
		return providerCountry;
	}
	public void setProviderCountry(String providerCountry) {
		this.providerCountry = providerCountry;
	}
	public String getInvoiceNumber() {
		return invoiceNumber;
	}
	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}
	public String getProviderCode() {
		return providerCode;
	}
	public void setProviderCode(String providerCode) {
		this.providerCode = providerCode;
	}
	public String getInvoiceType() {
		return invoiceType;
	}
	public void setInvoiceType(String invoiceType) {
		this.invoiceType = invoiceType;
	}
	public String getInvoiceDate() {
		return invoiceDate;
	}
	public void setInvoiceDate(String invoiceDate) {
		this.invoiceDate = invoiceDate;
	}
	public String getInvoiceVigencyDate() {
		return invoiceVigencyDate;
	}
	public void setInvoiceVigencyDate(String invoiceVigencyDate) {
		this.invoiceVigencyDate = invoiceVigencyDate;
	}
	public String getInvoiceContableDate() {
		return invoiceContableDate;
	}
	public void setInvoiceContableDate(String invoiceContableDate) {
		this.invoiceContableDate = invoiceContableDate;
	}
	public String getConcept() {
		return concept;
	}
	public void setConcept(String concept) {
		this.concept = concept;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getImports() {
		return imports;
	}
	public void setImports(String imports) {
		this.imports = imports;
	}
	public String getUnits() {
		return units;
	}
	public void setUnits(String units) {
		this.units = units;
	}
	public String getOprtationDescription() {
		return oprtationDescription;
	}
	public void setOprtationDescription(String oprtationDescription) {
		this.oprtationDescription = oprtationDescription;
	}
	public String getBruteTotal() {
		return bruteTotal;
	}
	public void setBruteTotal(String bruteTotal) {
		this.bruteTotal = bruteTotal;
	}
	public String getBase() {
		return base;
	}
	public void setBase(String base) {
		this.base = base;
	}
	public String getIva() {
		return iva;
	}
	public void setIva(String iva) {
		this.iva = iva;
	}
	public String getIrpf() {
		return irpf;
	}
	public void setIrpf(String irpf) {
		this.irpf = irpf;
	}
	public String getRetentionType() {
		return retentionType;
	}
	public void setRetentionType(String retentionType) {
		this.retentionType = retentionType;
	}
	public String getRetentionBase() {
		return retentionBase;
	}
	public void setRetentionBase(String retentionBase) {
		this.retentionBase = retentionBase;
	}
	public String getRetentionImports() {
		return retentionImports;
	}
	public void setRetentionImports(String retentionImports) {
		this.retentionImports = retentionImports;
	}
	public String getSurchargesPercentage() {
		return surchargesPercentage;
	}
	public void setSurchargesPercentage(String surchargesPercentage) {
		this.surchargesPercentage = surchargesPercentage;
	}
	public String getSurchargesBase() {
		return surchargesBase;
	}
	public void setSurchargesBase(String surchargesBase) {
		this.surchargesBase = surchargesBase;
	}
	public String getSurchargesImports() {
		return surchargesImports;
	}
	public void setSurchargesImports(String surchargesImports) {
		this.surchargesImports = surchargesImports;
	}
	public String getSuppliers() {
		return suppliers;
	}
	public void setSuppliers(String suppliers) {
		this.suppliers = suppliers;
	}
	public String getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTransmitterName() {
		return transmitterName;
	}
	public void setTransmitterName(String transmitterName) {
		this.transmitterName = transmitterName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getTypes() {
		return types;
	}
	public void setTypes(String types) {
		this.types = types;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public String getVigencyDate() {
		return vigencyDate;
	}
	public void setVigencyDate(String vigencyDate) {
		this.vigencyDate = vigencyDate;
	}
	public Double getImportsD() {
		return importsD;
	}
	public void setImportsD(Double importsD) {
		this.importsD = importsD;
	}
	public Double getBruteTotalD() {
		return bruteTotalD;
	}
	public void setBruteTotalD(Double bruteTotalD) {
		this.bruteTotalD = bruteTotalD;
	}
	public Double getBaseD() {
		return baseD;
	}
	public void setBaseD(Double baseD) {
		this.baseD = baseD;
	}
	public Double getIvaD() {
		return ivaD;
	}
	public void setIvaD(Double ivaD) {
		this.ivaD = ivaD;
	}
	public Double getTotalD() {
		return totalD;
	}
	public void setTotalD(Double totalD) {
		this.totalD = totalD;
	}
	public Double getIrpfD() {
		return irpfD;
	}
	public void setIrpfD(Double irpfD) {
		this.irpfD = irpfD;
	}
	public Double getRetentionBaseD() {
		return retentionBaseD;
	}
	public void setRetentionBaseD(Double retentionBaseD) {
		this.retentionBaseD = retentionBaseD;
	}
	public Double getRetentionImportsD() {
		return retentionImportsD;
	}
	public void setRetentionImportsD(Double retentionImportsD) {
		this.retentionImportsD = retentionImportsD;
	}
	public Double getSurchargesPercentageD() {
		return surchargesPercentageD;
	}
	public void setSurchargesPercentageD(Double surchargesPercentageD) {
		this.surchargesPercentageD = surchargesPercentageD;
	}
	public Double getSurchargesBaseD() {
		return surchargesBaseD;
	}
	public void setSurchargesBaseD(Double surchargesBaseD) {
		this.surchargesBaseD = surchargesBaseD;
	}
	public Double getSurchargesImportsD() {
		return surchargesImportsD;
	}
	public void setSurchargesImportsD(Double surchargesImportsD) {
		this.surchargesImportsD = surchargesImportsD;
	}
	public String getBusinessOwner() {
		return businessOwner;
	}
	public void setBusinessOwner(String businessOwner) {
		this.businessOwner = businessOwner;
	}
	public String getSublevel1() {
		return sublevel1;
	}
	public void setSublevel1(String sublevel1) {
		this.sublevel1 = sublevel1;
	}
	public String getSublevel2() {
		return sublevel2;
	}
	public void setSublevel2(String sublevel2) {
		this.sublevel2 = sublevel2;
	}
	public String getPercentageBase() {
		return percentageBase;
	}
	public void setPercentageBase(String percentageBase) {
		this.percentageBase = percentageBase;
	}
	public String getBase1() {
		return base1;
	}
	public void setBase1(String base1) {
		this.base1 = base1;
	}
	public String getBase2() {
		return base2;
	}
	public void setBase2(String base2) {
		this.base2 = base2;
	}
	public String getBase3() {
		return base3;
	}
	public void setBase3(String base3) {
		this.base3 = base3;
	}
	public String getIva1() {
		return iva1;
	}
	public void setIva1(String iva1) {
		this.iva1 = iva1;
	}
	public String getIva2() {
		return iva2;
	}
	public void setIva2(String iva2) {
		this.iva2 = iva2;
	}
	public String getIva3() {
		return iva3;
	}
	public void setIva3(String iva3) {
		this.iva3 = iva3;
	}
	public String getBase0() {
		return base0;
	}
	public void setBase0(String base0) {
		this.base0 = base0;
	}
	public String getIva0() {
		return iva0;
	}
	public void setIva0(String iva0) {
		this.iva0 = iva0;
	}
	public Double getBase0D() {
		return base0D;
	}
	public void setBase0D(Double base0d) {
		base0D = base0d;
	}
	public Double getBase1D() {
		return base1D;
	}
	public void setBase1D(Double base1d) {
		base1D = base1d;
	}
	public Double getBase2D() {
		return base2D;
	}
	public void setBase2D(Double base2d) {
		base2D = base2d;
	}
	public Double getBase3D() {
		return base3D;
	}
	public void setBase3D(Double base3d) {
		base3D = base3d;
	}
	public Double getIva0D() {
		return iva0D;
	}
	public void setIva0D(Double iva0d) {
		iva0D = iva0d;
	}
	public Double getIva1D() {
		return iva1D;
	}
	public void setIva1D(Double iva1d) {
		iva1D = iva1d;
	}
	public Double getIva3D() {
		return iva3D;
	}
	public void setIva3D(Double iva3d) {
		iva3D = iva3d;
	}
	public Double getIva2D() {
		return iva2D;
	}
	public void setIva2D(Double iva2d) {
		iva2D = iva2d;
	}
	public Date getInvoiceContableDateDt() {
		return invoiceContableDateDt;
	}
	public void setInvoiceContableDateDt(Date invoiceContableDateDt) {
		this.invoiceContableDateDt = invoiceContableDateDt;
	}
    
    
}