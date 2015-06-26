package eu.kielczewski.example.core;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PackageItem {
	
	@NotNull
    @JsonProperty
    private BigDecimal packageId;
	
	@NotNull
    @JsonProperty
    private BigDecimal itemId;

	public BigDecimal getPackageId() {
		return packageId;
	}

	public void setPackageId(BigDecimal packageId) {
		this.packageId = packageId;
	}

	public BigDecimal getItemId() {
		return itemId;
	}

	public void setItemId(BigDecimal itemId) {
		this.itemId = itemId;
	}
}
