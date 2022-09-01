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

package com.liferay.kris.dialogflow.service;

import com.liferay.kris.dialogflow.model.ApiAiData;

import java.util.List;

/**
 * Provides the remote service utility for ApiAiData. This utility wraps
 * <code>com.liferay.kris.dialogflow.service.impl.ApiAiDataServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see ApiAiDataService
 * @generated
 */
public class ApiAiDataServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.kris.dialogflow.service.impl.ApiAiDataServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static String addApiAiDataPersistence(
		com.liferay.portal.kernel.service.ServiceContext serviceContext,
		String query, String authtoken, String speech, String action,
		String fulfillment, String result) {

		return getService().addApiAiDataPersistence(
			serviceContext, query, authtoken, speech, action, fulfillment,
			result);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static List<ApiAiData> getRecentConversation(
		com.liferay.portal.kernel.service.ServiceContext serviceContext,
		int records) {

		return getService().getRecentConversation(serviceContext, records);
	}

	public static ApiAiDataService getService() {
		return _service;
	}

	private static volatile ApiAiDataService _service;

}