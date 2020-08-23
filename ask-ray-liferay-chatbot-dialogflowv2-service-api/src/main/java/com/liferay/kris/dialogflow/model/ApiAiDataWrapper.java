/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.kris.dialogflow.model;

import com.liferay.exportimport.kernel.lar.StagedModelType;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.osgi.annotation.versioning.ProviderType;

/**
 * <p>
 * This class is a wrapper for {@link ApiAiData}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ApiAiData
 * @generated
 */
@ProviderType
public class ApiAiDataWrapper
	extends BaseModelWrapper<ApiAiData>
	implements ApiAiData, ModelWrapper<ApiAiData> {

	public ApiAiDataWrapper(ApiAiData apiAiData) {
		super(apiAiData);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("apiAiDataId", getApiAiDataId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("type", getType());
		attributes.put("result", getResult());
		attributes.put("action", getAction());
		attributes.put("fulfillment", getFulfillment());
		attributes.put("speech", getSpeech());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long apiAiDataId = (Long)attributes.get("apiAiDataId");

		if (apiAiDataId != null) {
			setApiAiDataId(apiAiDataId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		String userName = (String)attributes.get("userName");

		if (userName != null) {
			setUserName(userName);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		String type = (String)attributes.get("type");

		if (type != null) {
			setType(type);
		}

		String result = (String)attributes.get("result");

		if (result != null) {
			setResult(result);
		}

		String action = (String)attributes.get("action");

		if (action != null) {
			setAction(action);
		}

		String fulfillment = (String)attributes.get("fulfillment");

		if (fulfillment != null) {
			setFulfillment(fulfillment);
		}

		String speech = (String)attributes.get("speech");

		if (speech != null) {
			setSpeech(speech);
		}
	}

	/**
	 * Returns the action of this api ai data.
	 *
	 * @return the action of this api ai data
	 */
	@Override
	public String getAction() {
		return model.getAction();
	}

	/**
	 * Returns the api ai data ID of this api ai data.
	 *
	 * @return the api ai data ID of this api ai data
	 */
	@Override
	public long getApiAiDataId() {
		return model.getApiAiDataId();
	}

	/**
	 * Returns the company ID of this api ai data.
	 *
	 * @return the company ID of this api ai data
	 */
	@Override
	public long getCompanyId() {
		return model.getCompanyId();
	}

	/**
	 * Returns the create date of this api ai data.
	 *
	 * @return the create date of this api ai data
	 */
	@Override
	public Date getCreateDate() {
		return model.getCreateDate();
	}

	/**
	 * Returns the fulfillment of this api ai data.
	 *
	 * @return the fulfillment of this api ai data
	 */
	@Override
	public String getFulfillment() {
		return model.getFulfillment();
	}

	/**
	 * Returns the group ID of this api ai data.
	 *
	 * @return the group ID of this api ai data
	 */
	@Override
	public long getGroupId() {
		return model.getGroupId();
	}

	/**
	 * Returns the modified date of this api ai data.
	 *
	 * @return the modified date of this api ai data
	 */
	@Override
	public Date getModifiedDate() {
		return model.getModifiedDate();
	}

	/**
	 * Returns the primary key of this api ai data.
	 *
	 * @return the primary key of this api ai data
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the result of this api ai data.
	 *
	 * @return the result of this api ai data
	 */
	@Override
	public String getResult() {
		return model.getResult();
	}

	/**
	 * Returns the speech of this api ai data.
	 *
	 * @return the speech of this api ai data
	 */
	@Override
	public String getSpeech() {
		return model.getSpeech();
	}

	/**
	 * Returns the type of this api ai data.
	 *
	 * @return the type of this api ai data
	 */
	@Override
	public String getType() {
		return model.getType();
	}

	/**
	 * Returns the user ID of this api ai data.
	 *
	 * @return the user ID of this api ai data
	 */
	@Override
	public long getUserId() {
		return model.getUserId();
	}

	/**
	 * Returns the user name of this api ai data.
	 *
	 * @return the user name of this api ai data
	 */
	@Override
	public String getUserName() {
		return model.getUserName();
	}

	/**
	 * Returns the user uuid of this api ai data.
	 *
	 * @return the user uuid of this api ai data
	 */
	@Override
	public String getUserUuid() {
		return model.getUserUuid();
	}

	/**
	 * Returns the uuid of this api ai data.
	 *
	 * @return the uuid of this api ai data
	 */
	@Override
	public String getUuid() {
		return model.getUuid();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the action of this api ai data.
	 *
	 * @param action the action of this api ai data
	 */
	@Override
	public void setAction(String action) {
		model.setAction(action);
	}

	/**
	 * Sets the api ai data ID of this api ai data.
	 *
	 * @param apiAiDataId the api ai data ID of this api ai data
	 */
	@Override
	public void setApiAiDataId(long apiAiDataId) {
		model.setApiAiDataId(apiAiDataId);
	}

	/**
	 * Sets the company ID of this api ai data.
	 *
	 * @param companyId the company ID of this api ai data
	 */
	@Override
	public void setCompanyId(long companyId) {
		model.setCompanyId(companyId);
	}

	/**
	 * Sets the create date of this api ai data.
	 *
	 * @param createDate the create date of this api ai data
	 */
	@Override
	public void setCreateDate(Date createDate) {
		model.setCreateDate(createDate);
	}

	/**
	 * Sets the fulfillment of this api ai data.
	 *
	 * @param fulfillment the fulfillment of this api ai data
	 */
	@Override
	public void setFulfillment(String fulfillment) {
		model.setFulfillment(fulfillment);
	}

	/**
	 * Sets the group ID of this api ai data.
	 *
	 * @param groupId the group ID of this api ai data
	 */
	@Override
	public void setGroupId(long groupId) {
		model.setGroupId(groupId);
	}

	/**
	 * Sets the modified date of this api ai data.
	 *
	 * @param modifiedDate the modified date of this api ai data
	 */
	@Override
	public void setModifiedDate(Date modifiedDate) {
		model.setModifiedDate(modifiedDate);
	}

	/**
	 * Sets the primary key of this api ai data.
	 *
	 * @param primaryKey the primary key of this api ai data
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the result of this api ai data.
	 *
	 * @param result the result of this api ai data
	 */
	@Override
	public void setResult(String result) {
		model.setResult(result);
	}

	/**
	 * Sets the speech of this api ai data.
	 *
	 * @param speech the speech of this api ai data
	 */
	@Override
	public void setSpeech(String speech) {
		model.setSpeech(speech);
	}

	/**
	 * Sets the type of this api ai data.
	 *
	 * @param type the type of this api ai data
	 */
	@Override
	public void setType(String type) {
		model.setType(type);
	}

	/**
	 * Sets the user ID of this api ai data.
	 *
	 * @param userId the user ID of this api ai data
	 */
	@Override
	public void setUserId(long userId) {
		model.setUserId(userId);
	}

	/**
	 * Sets the user name of this api ai data.
	 *
	 * @param userName the user name of this api ai data
	 */
	@Override
	public void setUserName(String userName) {
		model.setUserName(userName);
	}

	/**
	 * Sets the user uuid of this api ai data.
	 *
	 * @param userUuid the user uuid of this api ai data
	 */
	@Override
	public void setUserUuid(String userUuid) {
		model.setUserUuid(userUuid);
	}

	/**
	 * Sets the uuid of this api ai data.
	 *
	 * @param uuid the uuid of this api ai data
	 */
	@Override
	public void setUuid(String uuid) {
		model.setUuid(uuid);
	}

	@Override
	public StagedModelType getStagedModelType() {
		return model.getStagedModelType();
	}

	@Override
	protected ApiAiDataWrapper wrap(ApiAiData apiAiData) {
		return new ApiAiDataWrapper(apiAiData);
	}

}