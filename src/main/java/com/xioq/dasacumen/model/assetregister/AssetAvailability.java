package com.xioq.dasacumen.model.assetregister;

import java.util.Date;
import java.util.Set;

/**
 * AssetAvailability object.
 * 
 * @author MWalsh
 *
 */
public class AssetAvailability {

	private String assetName;
	private Date availabilityDate;
	private Assets asset;
	private Boolean available;
	private Long durationNotAvailable;
	
	public Set<AssetSchedule> getAssetSchedules() {
		return assetSchedules;
	}
	public void setAssetSchedules(Set<AssetSchedule> assetSchedules) {
		this.assetSchedules = assetSchedules;
	}
	private Set<AssetSchedule> assetSchedules;

	public String getAssetName() {
		return assetName;
	}
	public void setAssetName(String assetName) {
		this.assetName = assetName;
	}
	public Date getAvailabilityDate() {
		return availabilityDate;
	}
	public void setAvailabilityDate(Date availabilityDate) {
		this.availabilityDate = availabilityDate;
	}
	public Assets getAsset() {
		return asset;
	}
	public void setAsset(Assets asset) {
		this.asset = asset;
	}
	public Boolean getAvailable() {
		return available;
	}
	public void setAvailable(Boolean available) {
		this.available = available;
	}
	public Long getDurationNotAvailable() {
		return durationNotAvailable;
	}
	public void setDurationNotAvailable(Long durationNotAvailable) {
		this.durationNotAvailable = durationNotAvailable;
	}
	
}
