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

package com.liferay.kris.dialogflow.service.persistence.impl;

import com.liferay.kris.dialogflow.exception.NoSuchApiAiDataException;
import com.liferay.kris.dialogflow.model.ApiAiData;
import com.liferay.kris.dialogflow.model.ApiAiDataTable;
import com.liferay.kris.dialogflow.model.impl.ApiAiDataImpl;
import com.liferay.kris.dialogflow.model.impl.ApiAiDataModelImpl;
import com.liferay.kris.dialogflow.service.persistence.ApiAiDataPersistence;
import com.liferay.kris.dialogflow.service.persistence.ApiAiDataUtil;
import com.liferay.kris.dialogflow.service.persistence.impl.constants.APIAIPersistenceConstants;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.configuration.Configuration;
import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.SessionFactory;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.service.persistence.BasePersistence;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;

import java.io.Serializable;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;

import java.sql.Timestamp;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import javax.sql.DataSource;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * The persistence implementation for the api ai data service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = {ApiAiDataPersistence.class, BasePersistence.class})
public class ApiAiDataPersistenceImpl
	extends BasePersistenceImpl<ApiAiData> implements ApiAiDataPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>ApiAiDataUtil</code> to access the api ai data persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		ApiAiDataImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathWithPaginationFindByUuid;
	private FinderPath _finderPathWithoutPaginationFindByUuid;
	private FinderPath _finderPathCountByUuid;

	/**
	 * Returns all the api ai datas where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching api ai datas
	 */
	@Override
	public List<ApiAiData> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the api ai datas where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ApiAiDataModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of api ai datas
	 * @param end the upper bound of the range of api ai datas (not inclusive)
	 * @return the range of matching api ai datas
	 */
	@Override
	public List<ApiAiData> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the api ai datas where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ApiAiDataModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of api ai datas
	 * @param end the upper bound of the range of api ai datas (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching api ai datas
	 */
	@Override
	public List<ApiAiData> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<ApiAiData> orderByComparator) {

		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the api ai datas where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ApiAiDataModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of api ai datas
	 * @param end the upper bound of the range of api ai datas (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching api ai datas
	 */
	@Override
	public List<ApiAiData> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<ApiAiData> orderByComparator,
		boolean useFinderCache) {

		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByUuid;
				finderArgs = new Object[] {uuid};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByUuid;
			finderArgs = new Object[] {uuid, start, end, orderByComparator};
		}

		List<ApiAiData> list = null;

		if (useFinderCache) {
			list = (List<ApiAiData>)finderCache.getResult(
				finderPath, finderArgs);

			if ((list != null) && !list.isEmpty()) {
				for (ApiAiData apiAiData : list) {
					if (!uuid.equals(apiAiData.getUuid())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler sb = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					3 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				sb = new StringBundler(3);
			}

			sb.append(_SQL_SELECT_APIAIDATA_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				sb.append(_FINDER_COLUMN_UUID_UUID_3);
			}
			else {
				bindUuid = true;

				sb.append(_FINDER_COLUMN_UUID_UUID_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(ApiAiDataModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindUuid) {
					queryPos.add(uuid);
				}

				list = (List<ApiAiData>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first api ai data in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching api ai data
	 * @throws NoSuchApiAiDataException if a matching api ai data could not be found
	 */
	@Override
	public ApiAiData findByUuid_First(
			String uuid, OrderByComparator<ApiAiData> orderByComparator)
		throws NoSuchApiAiDataException {

		ApiAiData apiAiData = fetchByUuid_First(uuid, orderByComparator);

		if (apiAiData != null) {
			return apiAiData;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append("}");

		throw new NoSuchApiAiDataException(sb.toString());
	}

	/**
	 * Returns the first api ai data in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching api ai data, or <code>null</code> if a matching api ai data could not be found
	 */
	@Override
	public ApiAiData fetchByUuid_First(
		String uuid, OrderByComparator<ApiAiData> orderByComparator) {

		List<ApiAiData> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last api ai data in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching api ai data
	 * @throws NoSuchApiAiDataException if a matching api ai data could not be found
	 */
	@Override
	public ApiAiData findByUuid_Last(
			String uuid, OrderByComparator<ApiAiData> orderByComparator)
		throws NoSuchApiAiDataException {

		ApiAiData apiAiData = fetchByUuid_Last(uuid, orderByComparator);

		if (apiAiData != null) {
			return apiAiData;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append("}");

		throw new NoSuchApiAiDataException(sb.toString());
	}

	/**
	 * Returns the last api ai data in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching api ai data, or <code>null</code> if a matching api ai data could not be found
	 */
	@Override
	public ApiAiData fetchByUuid_Last(
		String uuid, OrderByComparator<ApiAiData> orderByComparator) {

		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<ApiAiData> list = findByUuid(
			uuid, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the api ai datas before and after the current api ai data in the ordered set where uuid = &#63;.
	 *
	 * @param apiAiDataId the primary key of the current api ai data
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next api ai data
	 * @throws NoSuchApiAiDataException if a api ai data with the primary key could not be found
	 */
	@Override
	public ApiAiData[] findByUuid_PrevAndNext(
			long apiAiDataId, String uuid,
			OrderByComparator<ApiAiData> orderByComparator)
		throws NoSuchApiAiDataException {

		uuid = Objects.toString(uuid, "");

		ApiAiData apiAiData = findByPrimaryKey(apiAiDataId);

		Session session = null;

		try {
			session = openSession();

			ApiAiData[] array = new ApiAiDataImpl[3];

			array[0] = getByUuid_PrevAndNext(
				session, apiAiData, uuid, orderByComparator, true);

			array[1] = apiAiData;

			array[2] = getByUuid_PrevAndNext(
				session, apiAiData, uuid, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected ApiAiData getByUuid_PrevAndNext(
		Session session, ApiAiData apiAiData, String uuid,
		OrderByComparator<ApiAiData> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_APIAIDATA_WHERE);

		boolean bindUuid = false;

		if (uuid.isEmpty()) {
			sb.append(_FINDER_COLUMN_UUID_UUID_3);
		}
		else {
			bindUuid = true;

			sb.append(_FINDER_COLUMN_UUID_UUID_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				sb.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						sb.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN);
					}
					else {
						sb.append(WHERE_LESSER_THAN);
					}
				}
			}

			sb.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						sb.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC);
					}
					else {
						sb.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			sb.append(ApiAiDataModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		if (bindUuid) {
			queryPos.add(uuid);
		}

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(apiAiData)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<ApiAiData> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the api ai datas where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (ApiAiData apiAiData :
				findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(apiAiData);
		}
	}

	/**
	 * Returns the number of api ai datas where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching api ai datas
	 */
	@Override
	public int countByUuid(String uuid) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUuid;

		Object[] finderArgs = new Object[] {uuid};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_APIAIDATA_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				sb.append(_FINDER_COLUMN_UUID_UUID_3);
			}
			else {
				bindUuid = true;

				sb.append(_FINDER_COLUMN_UUID_UUID_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindUuid) {
					queryPos.add(uuid);
				}

				count = (Long)query.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_UUID_UUID_2 =
		"apiAiData.uuid = ?";

	private static final String _FINDER_COLUMN_UUID_UUID_3 =
		"(apiAiData.uuid IS NULL OR apiAiData.uuid = '')";

	private FinderPath _finderPathFetchByUUID_G;
	private FinderPath _finderPathCountByUUID_G;

	/**
	 * Returns the api ai data where uuid = &#63; and groupId = &#63; or throws a <code>NoSuchApiAiDataException</code> if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching api ai data
	 * @throws NoSuchApiAiDataException if a matching api ai data could not be found
	 */
	@Override
	public ApiAiData findByUUID_G(String uuid, long groupId)
		throws NoSuchApiAiDataException {

		ApiAiData apiAiData = fetchByUUID_G(uuid, groupId);

		if (apiAiData == null) {
			StringBundler sb = new StringBundler(6);

			sb.append(_NO_SUCH_ENTITY_WITH_KEY);

			sb.append("uuid=");
			sb.append(uuid);

			sb.append(", groupId=");
			sb.append(groupId);

			sb.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(sb.toString());
			}

			throw new NoSuchApiAiDataException(sb.toString());
		}

		return apiAiData;
	}

	/**
	 * Returns the api ai data where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching api ai data, or <code>null</code> if a matching api ai data could not be found
	 */
	@Override
	public ApiAiData fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the api ai data where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching api ai data, or <code>null</code> if a matching api ai data could not be found
	 */
	@Override
	public ApiAiData fetchByUUID_G(
		String uuid, long groupId, boolean useFinderCache) {

		uuid = Objects.toString(uuid, "");

		Object[] finderArgs = null;

		if (useFinderCache) {
			finderArgs = new Object[] {uuid, groupId};
		}

		Object result = null;

		if (useFinderCache) {
			result = finderCache.getResult(
				_finderPathFetchByUUID_G, finderArgs);
		}

		if (result instanceof ApiAiData) {
			ApiAiData apiAiData = (ApiAiData)result;

			if (!Objects.equals(uuid, apiAiData.getUuid()) ||
				(groupId != apiAiData.getGroupId())) {

				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_SQL_SELECT_APIAIDATA_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				sb.append(_FINDER_COLUMN_UUID_G_UUID_3);
			}
			else {
				bindUuid = true;

				sb.append(_FINDER_COLUMN_UUID_G_UUID_2);
			}

			sb.append(_FINDER_COLUMN_UUID_G_GROUPID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindUuid) {
					queryPos.add(uuid);
				}

				queryPos.add(groupId);

				List<ApiAiData> list = query.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						finderCache.putResult(
							_finderPathFetchByUUID_G, finderArgs, list);
					}
				}
				else {
					ApiAiData apiAiData = list.get(0);

					result = apiAiData;

					cacheResult(apiAiData);
				}
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		if (result instanceof List<?>) {
			return null;
		}
		else {
			return (ApiAiData)result;
		}
	}

	/**
	 * Removes the api ai data where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the api ai data that was removed
	 */
	@Override
	public ApiAiData removeByUUID_G(String uuid, long groupId)
		throws NoSuchApiAiDataException {

		ApiAiData apiAiData = findByUUID_G(uuid, groupId);

		return remove(apiAiData);
	}

	/**
	 * Returns the number of api ai datas where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching api ai datas
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUUID_G;

		Object[] finderArgs = new Object[] {uuid, groupId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_APIAIDATA_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				sb.append(_FINDER_COLUMN_UUID_G_UUID_3);
			}
			else {
				bindUuid = true;

				sb.append(_FINDER_COLUMN_UUID_G_UUID_2);
			}

			sb.append(_FINDER_COLUMN_UUID_G_GROUPID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindUuid) {
					queryPos.add(uuid);
				}

				queryPos.add(groupId);

				count = (Long)query.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_UUID_G_UUID_2 =
		"apiAiData.uuid = ? AND ";

	private static final String _FINDER_COLUMN_UUID_G_UUID_3 =
		"(apiAiData.uuid IS NULL OR apiAiData.uuid = '') AND ";

	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 =
		"apiAiData.groupId = ?";

	private FinderPath _finderPathWithPaginationFindByUuid_C;
	private FinderPath _finderPathWithoutPaginationFindByUuid_C;
	private FinderPath _finderPathCountByUuid_C;

	/**
	 * Returns all the api ai datas where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching api ai datas
	 */
	@Override
	public List<ApiAiData> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(
			uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the api ai datas where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ApiAiDataModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of api ai datas
	 * @param end the upper bound of the range of api ai datas (not inclusive)
	 * @return the range of matching api ai datas
	 */
	@Override
	public List<ApiAiData> findByUuid_C(
		String uuid, long companyId, int start, int end) {

		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the api ai datas where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ApiAiDataModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of api ai datas
	 * @param end the upper bound of the range of api ai datas (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching api ai datas
	 */
	@Override
	public List<ApiAiData> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<ApiAiData> orderByComparator) {

		return findByUuid_C(
			uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the api ai datas where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ApiAiDataModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of api ai datas
	 * @param end the upper bound of the range of api ai datas (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching api ai datas
	 */
	@Override
	public List<ApiAiData> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<ApiAiData> orderByComparator,
		boolean useFinderCache) {

		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByUuid_C;
				finderArgs = new Object[] {uuid, companyId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByUuid_C;
			finderArgs = new Object[] {
				uuid, companyId, start, end, orderByComparator
			};
		}

		List<ApiAiData> list = null;

		if (useFinderCache) {
			list = (List<ApiAiData>)finderCache.getResult(
				finderPath, finderArgs);

			if ((list != null) && !list.isEmpty()) {
				for (ApiAiData apiAiData : list) {
					if (!uuid.equals(apiAiData.getUuid()) ||
						(companyId != apiAiData.getCompanyId())) {

						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler sb = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					4 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				sb = new StringBundler(4);
			}

			sb.append(_SQL_SELECT_APIAIDATA_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				sb.append(_FINDER_COLUMN_UUID_C_UUID_3);
			}
			else {
				bindUuid = true;

				sb.append(_FINDER_COLUMN_UUID_C_UUID_2);
			}

			sb.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(ApiAiDataModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindUuid) {
					queryPos.add(uuid);
				}

				queryPos.add(companyId);

				list = (List<ApiAiData>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first api ai data in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching api ai data
	 * @throws NoSuchApiAiDataException if a matching api ai data could not be found
	 */
	@Override
	public ApiAiData findByUuid_C_First(
			String uuid, long companyId,
			OrderByComparator<ApiAiData> orderByComparator)
		throws NoSuchApiAiDataException {

		ApiAiData apiAiData = fetchByUuid_C_First(
			uuid, companyId, orderByComparator);

		if (apiAiData != null) {
			return apiAiData;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append(", companyId=");
		sb.append(companyId);

		sb.append("}");

		throw new NoSuchApiAiDataException(sb.toString());
	}

	/**
	 * Returns the first api ai data in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching api ai data, or <code>null</code> if a matching api ai data could not be found
	 */
	@Override
	public ApiAiData fetchByUuid_C_First(
		String uuid, long companyId,
		OrderByComparator<ApiAiData> orderByComparator) {

		List<ApiAiData> list = findByUuid_C(
			uuid, companyId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last api ai data in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching api ai data
	 * @throws NoSuchApiAiDataException if a matching api ai data could not be found
	 */
	@Override
	public ApiAiData findByUuid_C_Last(
			String uuid, long companyId,
			OrderByComparator<ApiAiData> orderByComparator)
		throws NoSuchApiAiDataException {

		ApiAiData apiAiData = fetchByUuid_C_Last(
			uuid, companyId, orderByComparator);

		if (apiAiData != null) {
			return apiAiData;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append(", companyId=");
		sb.append(companyId);

		sb.append("}");

		throw new NoSuchApiAiDataException(sb.toString());
	}

	/**
	 * Returns the last api ai data in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching api ai data, or <code>null</code> if a matching api ai data could not be found
	 */
	@Override
	public ApiAiData fetchByUuid_C_Last(
		String uuid, long companyId,
		OrderByComparator<ApiAiData> orderByComparator) {

		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<ApiAiData> list = findByUuid_C(
			uuid, companyId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the api ai datas before and after the current api ai data in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param apiAiDataId the primary key of the current api ai data
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next api ai data
	 * @throws NoSuchApiAiDataException if a api ai data with the primary key could not be found
	 */
	@Override
	public ApiAiData[] findByUuid_C_PrevAndNext(
			long apiAiDataId, String uuid, long companyId,
			OrderByComparator<ApiAiData> orderByComparator)
		throws NoSuchApiAiDataException {

		uuid = Objects.toString(uuid, "");

		ApiAiData apiAiData = findByPrimaryKey(apiAiDataId);

		Session session = null;

		try {
			session = openSession();

			ApiAiData[] array = new ApiAiDataImpl[3];

			array[0] = getByUuid_C_PrevAndNext(
				session, apiAiData, uuid, companyId, orderByComparator, true);

			array[1] = apiAiData;

			array[2] = getByUuid_C_PrevAndNext(
				session, apiAiData, uuid, companyId, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected ApiAiData getByUuid_C_PrevAndNext(
		Session session, ApiAiData apiAiData, String uuid, long companyId,
		OrderByComparator<ApiAiData> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				5 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(4);
		}

		sb.append(_SQL_SELECT_APIAIDATA_WHERE);

		boolean bindUuid = false;

		if (uuid.isEmpty()) {
			sb.append(_FINDER_COLUMN_UUID_C_UUID_3);
		}
		else {
			bindUuid = true;

			sb.append(_FINDER_COLUMN_UUID_C_UUID_2);
		}

		sb.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				sb.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						sb.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN);
					}
					else {
						sb.append(WHERE_LESSER_THAN);
					}
				}
			}

			sb.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						sb.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC);
					}
					else {
						sb.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			sb.append(ApiAiDataModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		if (bindUuid) {
			queryPos.add(uuid);
		}

		queryPos.add(companyId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(apiAiData)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<ApiAiData> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the api ai datas where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (ApiAiData apiAiData :
				findByUuid_C(
					uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(apiAiData);
		}
	}

	/**
	 * Returns the number of api ai datas where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching api ai datas
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUuid_C;

		Object[] finderArgs = new Object[] {uuid, companyId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_APIAIDATA_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				sb.append(_FINDER_COLUMN_UUID_C_UUID_3);
			}
			else {
				bindUuid = true;

				sb.append(_FINDER_COLUMN_UUID_C_UUID_2);
			}

			sb.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindUuid) {
					queryPos.add(uuid);
				}

				queryPos.add(companyId);

				count = (Long)query.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_UUID_C_UUID_2 =
		"apiAiData.uuid = ? AND ";

	private static final String _FINDER_COLUMN_UUID_C_UUID_3 =
		"(apiAiData.uuid IS NULL OR apiAiData.uuid = '') AND ";

	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 =
		"apiAiData.companyId = ?";

	private FinderPath _finderPathWithPaginationFindByUserId;
	private FinderPath _finderPathWithoutPaginationFindByUserId;
	private FinderPath _finderPathCountByUserId;

	/**
	 * Returns all the api ai datas where userId = &#63; and createDate = &#63;.
	 *
	 * @param userId the user ID
	 * @param createDate the create date
	 * @return the matching api ai datas
	 */
	@Override
	public List<ApiAiData> findByUserId(long userId, Date createDate) {
		return findByUserId(
			userId, createDate, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the api ai datas where userId = &#63; and createDate = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ApiAiDataModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param createDate the create date
	 * @param start the lower bound of the range of api ai datas
	 * @param end the upper bound of the range of api ai datas (not inclusive)
	 * @return the range of matching api ai datas
	 */
	@Override
	public List<ApiAiData> findByUserId(
		long userId, Date createDate, int start, int end) {

		return findByUserId(userId, createDate, start, end, null);
	}

	/**
	 * Returns an ordered range of all the api ai datas where userId = &#63; and createDate = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ApiAiDataModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param createDate the create date
	 * @param start the lower bound of the range of api ai datas
	 * @param end the upper bound of the range of api ai datas (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching api ai datas
	 */
	@Override
	public List<ApiAiData> findByUserId(
		long userId, Date createDate, int start, int end,
		OrderByComparator<ApiAiData> orderByComparator) {

		return findByUserId(
			userId, createDate, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the api ai datas where userId = &#63; and createDate = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ApiAiDataModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param createDate the create date
	 * @param start the lower bound of the range of api ai datas
	 * @param end the upper bound of the range of api ai datas (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching api ai datas
	 */
	@Override
	public List<ApiAiData> findByUserId(
		long userId, Date createDate, int start, int end,
		OrderByComparator<ApiAiData> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByUserId;
				finderArgs = new Object[] {userId, _getTime(createDate)};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByUserId;
			finderArgs = new Object[] {
				userId, _getTime(createDate), start, end, orderByComparator
			};
		}

		List<ApiAiData> list = null;

		if (useFinderCache) {
			list = (List<ApiAiData>)finderCache.getResult(
				finderPath, finderArgs);

			if ((list != null) && !list.isEmpty()) {
				for (ApiAiData apiAiData : list) {
					if ((userId != apiAiData.getUserId()) ||
						!Objects.equals(
							createDate, apiAiData.getCreateDate())) {

						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler sb = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					4 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				sb = new StringBundler(4);
			}

			sb.append(_SQL_SELECT_APIAIDATA_WHERE);

			sb.append(_FINDER_COLUMN_USERID_USERID_2);

			boolean bindCreateDate = false;

			if (createDate == null) {
				sb.append(_FINDER_COLUMN_USERID_CREATEDATE_1);
			}
			else {
				bindCreateDate = true;

				sb.append(_FINDER_COLUMN_USERID_CREATEDATE_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(ApiAiDataModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(userId);

				if (bindCreateDate) {
					queryPos.add(new Timestamp(createDate.getTime()));
				}

				list = (List<ApiAiData>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first api ai data in the ordered set where userId = &#63; and createDate = &#63;.
	 *
	 * @param userId the user ID
	 * @param createDate the create date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching api ai data
	 * @throws NoSuchApiAiDataException if a matching api ai data could not be found
	 */
	@Override
	public ApiAiData findByUserId_First(
			long userId, Date createDate,
			OrderByComparator<ApiAiData> orderByComparator)
		throws NoSuchApiAiDataException {

		ApiAiData apiAiData = fetchByUserId_First(
			userId, createDate, orderByComparator);

		if (apiAiData != null) {
			return apiAiData;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("userId=");
		sb.append(userId);

		sb.append(", createDate=");
		sb.append(createDate);

		sb.append("}");

		throw new NoSuchApiAiDataException(sb.toString());
	}

	/**
	 * Returns the first api ai data in the ordered set where userId = &#63; and createDate = &#63;.
	 *
	 * @param userId the user ID
	 * @param createDate the create date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching api ai data, or <code>null</code> if a matching api ai data could not be found
	 */
	@Override
	public ApiAiData fetchByUserId_First(
		long userId, Date createDate,
		OrderByComparator<ApiAiData> orderByComparator) {

		List<ApiAiData> list = findByUserId(
			userId, createDate, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last api ai data in the ordered set where userId = &#63; and createDate = &#63;.
	 *
	 * @param userId the user ID
	 * @param createDate the create date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching api ai data
	 * @throws NoSuchApiAiDataException if a matching api ai data could not be found
	 */
	@Override
	public ApiAiData findByUserId_Last(
			long userId, Date createDate,
			OrderByComparator<ApiAiData> orderByComparator)
		throws NoSuchApiAiDataException {

		ApiAiData apiAiData = fetchByUserId_Last(
			userId, createDate, orderByComparator);

		if (apiAiData != null) {
			return apiAiData;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("userId=");
		sb.append(userId);

		sb.append(", createDate=");
		sb.append(createDate);

		sb.append("}");

		throw new NoSuchApiAiDataException(sb.toString());
	}

	/**
	 * Returns the last api ai data in the ordered set where userId = &#63; and createDate = &#63;.
	 *
	 * @param userId the user ID
	 * @param createDate the create date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching api ai data, or <code>null</code> if a matching api ai data could not be found
	 */
	@Override
	public ApiAiData fetchByUserId_Last(
		long userId, Date createDate,
		OrderByComparator<ApiAiData> orderByComparator) {

		int count = countByUserId(userId, createDate);

		if (count == 0) {
			return null;
		}

		List<ApiAiData> list = findByUserId(
			userId, createDate, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the api ai datas before and after the current api ai data in the ordered set where userId = &#63; and createDate = &#63;.
	 *
	 * @param apiAiDataId the primary key of the current api ai data
	 * @param userId the user ID
	 * @param createDate the create date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next api ai data
	 * @throws NoSuchApiAiDataException if a api ai data with the primary key could not be found
	 */
	@Override
	public ApiAiData[] findByUserId_PrevAndNext(
			long apiAiDataId, long userId, Date createDate,
			OrderByComparator<ApiAiData> orderByComparator)
		throws NoSuchApiAiDataException {

		ApiAiData apiAiData = findByPrimaryKey(apiAiDataId);

		Session session = null;

		try {
			session = openSession();

			ApiAiData[] array = new ApiAiDataImpl[3];

			array[0] = getByUserId_PrevAndNext(
				session, apiAiData, userId, createDate, orderByComparator,
				true);

			array[1] = apiAiData;

			array[2] = getByUserId_PrevAndNext(
				session, apiAiData, userId, createDate, orderByComparator,
				false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected ApiAiData getByUserId_PrevAndNext(
		Session session, ApiAiData apiAiData, long userId, Date createDate,
		OrderByComparator<ApiAiData> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				5 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(4);
		}

		sb.append(_SQL_SELECT_APIAIDATA_WHERE);

		sb.append(_FINDER_COLUMN_USERID_USERID_2);

		boolean bindCreateDate = false;

		if (createDate == null) {
			sb.append(_FINDER_COLUMN_USERID_CREATEDATE_1);
		}
		else {
			bindCreateDate = true;

			sb.append(_FINDER_COLUMN_USERID_CREATEDATE_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				sb.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						sb.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN);
					}
					else {
						sb.append(WHERE_LESSER_THAN);
					}
				}
			}

			sb.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						sb.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC);
					}
					else {
						sb.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			sb.append(ApiAiDataModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(userId);

		if (bindCreateDate) {
			queryPos.add(new Timestamp(createDate.getTime()));
		}

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(apiAiData)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<ApiAiData> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the api ai datas where userId = &#63; and createDate = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @param createDate the create date
	 */
	@Override
	public void removeByUserId(long userId, Date createDate) {
		for (ApiAiData apiAiData :
				findByUserId(
					userId, createDate, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(apiAiData);
		}
	}

	/**
	 * Returns the number of api ai datas where userId = &#63; and createDate = &#63;.
	 *
	 * @param userId the user ID
	 * @param createDate the create date
	 * @return the number of matching api ai datas
	 */
	@Override
	public int countByUserId(long userId, Date createDate) {
		FinderPath finderPath = _finderPathCountByUserId;

		Object[] finderArgs = new Object[] {userId, _getTime(createDate)};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_APIAIDATA_WHERE);

			sb.append(_FINDER_COLUMN_USERID_USERID_2);

			boolean bindCreateDate = false;

			if (createDate == null) {
				sb.append(_FINDER_COLUMN_USERID_CREATEDATE_1);
			}
			else {
				bindCreateDate = true;

				sb.append(_FINDER_COLUMN_USERID_CREATEDATE_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(userId);

				if (bindCreateDate) {
					queryPos.add(new Timestamp(createDate.getTime()));
				}

				count = (Long)query.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_USERID_USERID_2 =
		"apiAiData.userId = ? AND ";

	private static final String _FINDER_COLUMN_USERID_CREATEDATE_1 =
		"apiAiData.createDate IS NULL";

	private static final String _FINDER_COLUMN_USERID_CREATEDATE_2 =
		"apiAiData.createDate = ?";

	public ApiAiDataPersistenceImpl() {
		Map<String, String> dbColumnNames = new HashMap<String, String>();

		dbColumnNames.put("uuid", "uuid_");
		dbColumnNames.put("type", "type_");

		setDBColumnNames(dbColumnNames);

		setModelClass(ApiAiData.class);

		setModelImplClass(ApiAiDataImpl.class);
		setModelPKClass(long.class);

		setTable(ApiAiDataTable.INSTANCE);
	}

	/**
	 * Caches the api ai data in the entity cache if it is enabled.
	 *
	 * @param apiAiData the api ai data
	 */
	@Override
	public void cacheResult(ApiAiData apiAiData) {
		entityCache.putResult(
			ApiAiDataImpl.class, apiAiData.getPrimaryKey(), apiAiData);

		finderCache.putResult(
			_finderPathFetchByUUID_G,
			new Object[] {apiAiData.getUuid(), apiAiData.getGroupId()},
			apiAiData);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the api ai datas in the entity cache if it is enabled.
	 *
	 * @param apiAiDatas the api ai datas
	 */
	@Override
	public void cacheResult(List<ApiAiData> apiAiDatas) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (apiAiDatas.size() > _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (ApiAiData apiAiData : apiAiDatas) {
			if (entityCache.getResult(
					ApiAiDataImpl.class, apiAiData.getPrimaryKey()) == null) {

				cacheResult(apiAiData);
			}
		}
	}

	/**
	 * Clears the cache for all api ai datas.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(ApiAiDataImpl.class);

		finderCache.clearCache(ApiAiDataImpl.class);
	}

	/**
	 * Clears the cache for the api ai data.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(ApiAiData apiAiData) {
		entityCache.removeResult(ApiAiDataImpl.class, apiAiData);
	}

	@Override
	public void clearCache(List<ApiAiData> apiAiDatas) {
		for (ApiAiData apiAiData : apiAiDatas) {
			entityCache.removeResult(ApiAiDataImpl.class, apiAiData);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(ApiAiDataImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(ApiAiDataImpl.class, primaryKey);
		}
	}

	protected void cacheUniqueFindersCache(
		ApiAiDataModelImpl apiAiDataModelImpl) {

		Object[] args = new Object[] {
			apiAiDataModelImpl.getUuid(), apiAiDataModelImpl.getGroupId()
		};

		finderCache.putResult(_finderPathCountByUUID_G, args, Long.valueOf(1));
		finderCache.putResult(
			_finderPathFetchByUUID_G, args, apiAiDataModelImpl);
	}

	/**
	 * Creates a new api ai data with the primary key. Does not add the api ai data to the database.
	 *
	 * @param apiAiDataId the primary key for the new api ai data
	 * @return the new api ai data
	 */
	@Override
	public ApiAiData create(long apiAiDataId) {
		ApiAiData apiAiData = new ApiAiDataImpl();

		apiAiData.setNew(true);
		apiAiData.setPrimaryKey(apiAiDataId);

		String uuid = PortalUUIDUtil.generate();

		apiAiData.setUuid(uuid);

		apiAiData.setCompanyId(CompanyThreadLocal.getCompanyId());

		return apiAiData;
	}

	/**
	 * Removes the api ai data with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param apiAiDataId the primary key of the api ai data
	 * @return the api ai data that was removed
	 * @throws NoSuchApiAiDataException if a api ai data with the primary key could not be found
	 */
	@Override
	public ApiAiData remove(long apiAiDataId) throws NoSuchApiAiDataException {
		return remove((Serializable)apiAiDataId);
	}

	/**
	 * Removes the api ai data with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the api ai data
	 * @return the api ai data that was removed
	 * @throws NoSuchApiAiDataException if a api ai data with the primary key could not be found
	 */
	@Override
	public ApiAiData remove(Serializable primaryKey)
		throws NoSuchApiAiDataException {

		Session session = null;

		try {
			session = openSession();

			ApiAiData apiAiData = (ApiAiData)session.get(
				ApiAiDataImpl.class, primaryKey);

			if (apiAiData == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchApiAiDataException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(apiAiData);
		}
		catch (NoSuchApiAiDataException noSuchEntityException) {
			throw noSuchEntityException;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected ApiAiData removeImpl(ApiAiData apiAiData) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(apiAiData)) {
				apiAiData = (ApiAiData)session.get(
					ApiAiDataImpl.class, apiAiData.getPrimaryKeyObj());
			}

			if (apiAiData != null) {
				session.delete(apiAiData);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (apiAiData != null) {
			clearCache(apiAiData);
		}

		return apiAiData;
	}

	@Override
	public ApiAiData updateImpl(ApiAiData apiAiData) {
		boolean isNew = apiAiData.isNew();

		if (!(apiAiData instanceof ApiAiDataModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(apiAiData.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(apiAiData);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in apiAiData proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom ApiAiData implementation " +
					apiAiData.getClass());
		}

		ApiAiDataModelImpl apiAiDataModelImpl = (ApiAiDataModelImpl)apiAiData;

		if (Validator.isNull(apiAiData.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			apiAiData.setUuid(uuid);
		}

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date date = new Date();

		if (isNew && (apiAiData.getCreateDate() == null)) {
			if (serviceContext == null) {
				apiAiData.setCreateDate(date);
			}
			else {
				apiAiData.setCreateDate(serviceContext.getCreateDate(date));
			}
		}

		if (!apiAiDataModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				apiAiData.setModifiedDate(date);
			}
			else {
				apiAiData.setModifiedDate(serviceContext.getModifiedDate(date));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(apiAiData);
			}
			else {
				apiAiData = (ApiAiData)session.merge(apiAiData);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			ApiAiDataImpl.class, apiAiDataModelImpl, false, true);

		cacheUniqueFindersCache(apiAiDataModelImpl);

		if (isNew) {
			apiAiData.setNew(false);
		}

		apiAiData.resetOriginalValues();

		return apiAiData;
	}

	/**
	 * Returns the api ai data with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the api ai data
	 * @return the api ai data
	 * @throws NoSuchApiAiDataException if a api ai data with the primary key could not be found
	 */
	@Override
	public ApiAiData findByPrimaryKey(Serializable primaryKey)
		throws NoSuchApiAiDataException {

		ApiAiData apiAiData = fetchByPrimaryKey(primaryKey);

		if (apiAiData == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchApiAiDataException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return apiAiData;
	}

	/**
	 * Returns the api ai data with the primary key or throws a <code>NoSuchApiAiDataException</code> if it could not be found.
	 *
	 * @param apiAiDataId the primary key of the api ai data
	 * @return the api ai data
	 * @throws NoSuchApiAiDataException if a api ai data with the primary key could not be found
	 */
	@Override
	public ApiAiData findByPrimaryKey(long apiAiDataId)
		throws NoSuchApiAiDataException {

		return findByPrimaryKey((Serializable)apiAiDataId);
	}

	/**
	 * Returns the api ai data with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param apiAiDataId the primary key of the api ai data
	 * @return the api ai data, or <code>null</code> if a api ai data with the primary key could not be found
	 */
	@Override
	public ApiAiData fetchByPrimaryKey(long apiAiDataId) {
		return fetchByPrimaryKey((Serializable)apiAiDataId);
	}

	/**
	 * Returns all the api ai datas.
	 *
	 * @return the api ai datas
	 */
	@Override
	public List<ApiAiData> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the api ai datas.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ApiAiDataModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of api ai datas
	 * @param end the upper bound of the range of api ai datas (not inclusive)
	 * @return the range of api ai datas
	 */
	@Override
	public List<ApiAiData> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the api ai datas.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ApiAiDataModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of api ai datas
	 * @param end the upper bound of the range of api ai datas (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of api ai datas
	 */
	@Override
	public List<ApiAiData> findAll(
		int start, int end, OrderByComparator<ApiAiData> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the api ai datas.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ApiAiDataModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of api ai datas
	 * @param end the upper bound of the range of api ai datas (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of api ai datas
	 */
	@Override
	public List<ApiAiData> findAll(
		int start, int end, OrderByComparator<ApiAiData> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindAll;
				finderArgs = FINDER_ARGS_EMPTY;
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindAll;
			finderArgs = new Object[] {start, end, orderByComparator};
		}

		List<ApiAiData> list = null;

		if (useFinderCache) {
			list = (List<ApiAiData>)finderCache.getResult(
				finderPath, finderArgs);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_APIAIDATA);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_APIAIDATA;

				sql = sql.concat(ApiAiDataModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<ApiAiData>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the api ai datas from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (ApiAiData apiAiData : findAll()) {
			remove(apiAiData);
		}
	}

	/**
	 * Returns the number of api ai datas.
	 *
	 * @return the number of api ai datas
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_APIAIDATA);

				count = (Long)query.uniqueResult();

				finderCache.putResult(
					_finderPathCountAll, FINDER_ARGS_EMPTY, count);
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	@Override
	public Set<String> getBadColumnNames() {
		return _badColumnNames;
	}

	@Override
	protected EntityCache getEntityCache() {
		return entityCache;
	}

	@Override
	protected String getPKDBName() {
		return "apiAiDataId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_APIAIDATA;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return ApiAiDataModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the api ai data persistence.
	 */
	@Activate
	public void activate() {
		_valueObjectFinderCacheListThreshold = GetterUtil.getInteger(
			PropsUtil.get(PropsKeys.VALUE_OBJECT_FINDER_CACHE_LIST_THRESHOLD));

		_finderPathWithPaginationFindAll = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0],
			new String[0], true);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0],
			new String[0], true);

		_finderPathCountAll = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0], new String[0], false);

		_finderPathWithPaginationFindByUuid = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {"uuid_"}, true);

		_finderPathWithoutPaginationFindByUuid = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] {String.class.getName()}, new String[] {"uuid_"},
			true);

		_finderPathCountByUuid = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] {String.class.getName()}, new String[] {"uuid_"},
			false);

		_finderPathFetchByUUID_G = new FinderPath(
			FINDER_CLASS_NAME_ENTITY, "fetchByUUID_G",
			new String[] {String.class.getName(), Long.class.getName()},
			new String[] {"uuid_", "groupId"}, true);

		_finderPathCountByUUID_G = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] {String.class.getName(), Long.class.getName()},
			new String[] {"uuid_", "groupId"}, false);

		_finderPathWithPaginationFindByUuid_C = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			},
			new String[] {"uuid_", "companyId"}, true);

		_finderPathWithoutPaginationFindByUuid_C = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] {String.class.getName(), Long.class.getName()},
			new String[] {"uuid_", "companyId"}, true);

		_finderPathCountByUuid_C = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] {String.class.getName(), Long.class.getName()},
			new String[] {"uuid_", "companyId"}, false);

		_finderPathWithPaginationFindByUserId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUserId",
			new String[] {
				Long.class.getName(), Date.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			},
			new String[] {"userId", "createDate"}, true);

		_finderPathWithoutPaginationFindByUserId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUserId",
			new String[] {Long.class.getName(), Date.class.getName()},
			new String[] {"userId", "createDate"}, true);

		_finderPathCountByUserId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUserId",
			new String[] {Long.class.getName(), Date.class.getName()},
			new String[] {"userId", "createDate"}, false);

		_setApiAiDataUtilPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		_setApiAiDataUtilPersistence(null);

		entityCache.removeCache(ApiAiDataImpl.class.getName());
	}

	private void _setApiAiDataUtilPersistence(
		ApiAiDataPersistence apiAiDataPersistence) {

		try {
			Field field = ApiAiDataUtil.class.getDeclaredField("_persistence");

			field.setAccessible(true);

			field.set(null, apiAiDataPersistence);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	@Override
	@Reference(
		target = APIAIPersistenceConstants.SERVICE_CONFIGURATION_FILTER,
		unbind = "-"
	)
	public void setConfiguration(Configuration configuration) {
	}

	@Override
	@Reference(
		target = APIAIPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setDataSource(DataSource dataSource) {
		super.setDataSource(dataSource);
	}

	@Override
	@Reference(
		target = APIAIPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	@Reference
	protected EntityCache entityCache;

	@Reference
	protected FinderCache finderCache;

	private static Long _getTime(Date date) {
		if (date == null) {
			return null;
		}

		return date.getTime();
	}

	private static final String _SQL_SELECT_APIAIDATA =
		"SELECT apiAiData FROM ApiAiData apiAiData";

	private static final String _SQL_SELECT_APIAIDATA_WHERE =
		"SELECT apiAiData FROM ApiAiData apiAiData WHERE ";

	private static final String _SQL_COUNT_APIAIDATA =
		"SELECT COUNT(apiAiData) FROM ApiAiData apiAiData";

	private static final String _SQL_COUNT_APIAIDATA_WHERE =
		"SELECT COUNT(apiAiData) FROM ApiAiData apiAiData WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "apiAiData.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No ApiAiData exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No ApiAiData exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		ApiAiDataPersistenceImpl.class);

	private static final Set<String> _badColumnNames = SetUtil.fromArray(
		new String[] {"uuid", "type"});

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

	@Reference
	private ApiAiDataModelArgumentsResolver _apiAiDataModelArgumentsResolver;

}