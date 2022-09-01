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

package com.liferay.kris.dialogflow.service.http;

import com.liferay.kris.dialogflow.service.ApiAiDataServiceUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.HttpPrincipal;
import com.liferay.portal.kernel.service.http.TunnelUtil;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;

/**
 * Provides the HTTP utility for the
 * <code>ApiAiDataServiceUtil</code> service
 * utility. The
 * static methods of this class calls the same methods of the service utility.
 * However, the signatures are different because it requires an additional
 * <code>HttpPrincipal</code> parameter.
 *
 * <p>
 * The benefits of using the HTTP utility is that it is fast and allows for
 * tunneling without the cost of serializing to text. The drawback is that it
 * only works with Java.
 * </p>
 *
 * <p>
 * Set the property <b>tunnel.servlet.hosts.allowed</b> in portal.properties to
 * configure security.
 * </p>
 *
 * <p>
 * The HTTP utility is only generated for remote services.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class ApiAiDataServiceHttp {

	public static java.util.List<com.liferay.kris.dialogflow.model.ApiAiData>
		getRecentConversation(
			HttpPrincipal httpPrincipal,
			com.liferay.portal.kernel.service.ServiceContext serviceContext,
			int records) {

		try {
			MethodKey methodKey = new MethodKey(
				ApiAiDataServiceUtil.class, "getRecentConversation",
				_getRecentConversationParameterTypes0);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, serviceContext, records);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (java.util.List<com.liferay.kris.dialogflow.model.ApiAiData>)
				returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static String addApiAiDataPersistence(
		HttpPrincipal httpPrincipal,
		com.liferay.portal.kernel.service.ServiceContext serviceContext,
		String query, String authtoken, String speech, String action,
		String fulfillment, String result) {

		try {
			MethodKey methodKey = new MethodKey(
				ApiAiDataServiceUtil.class, "addApiAiDataPersistence",
				_addApiAiDataPersistenceParameterTypes1);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, serviceContext, query, authtoken, speech, action,
				fulfillment, result);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (String)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(ApiAiDataServiceHttp.class);

	private static final Class<?>[] _getRecentConversationParameterTypes0 =
		new Class[] {
			com.liferay.portal.kernel.service.ServiceContext.class, int.class
		};
	private static final Class<?>[] _addApiAiDataPersistenceParameterTypes1 =
		new Class[] {
			com.liferay.portal.kernel.service.ServiceContext.class,
			String.class, String.class, String.class, String.class,
			String.class, String.class
		};

}