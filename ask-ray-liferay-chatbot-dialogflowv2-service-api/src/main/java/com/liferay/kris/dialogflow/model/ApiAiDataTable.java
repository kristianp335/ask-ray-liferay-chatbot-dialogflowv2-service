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

import com.liferay.petra.sql.dsl.Column;
import com.liferay.petra.sql.dsl.base.BaseTable;

import java.sql.Types;

import java.util.Date;

/**
 * The table class for the &quot;APIAI_ApiAiData&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see ApiAiData
 * @generated
 */
public class ApiAiDataTable extends BaseTable<ApiAiDataTable> {

	public static final ApiAiDataTable INSTANCE = new ApiAiDataTable();

	public final Column<ApiAiDataTable, String> uuid = createColumn(
		"uuid_", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<ApiAiDataTable, Long> apiAiDataId = createColumn(
		"apiAiDataId", Long.class, Types.BIGINT, Column.FLAG_PRIMARY);
	public final Column<ApiAiDataTable, Long> groupId = createColumn(
		"groupId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<ApiAiDataTable, Long> companyId = createColumn(
		"companyId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<ApiAiDataTable, Long> userId = createColumn(
		"userId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<ApiAiDataTable, String> userName = createColumn(
		"userName", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<ApiAiDataTable, Date> createDate = createColumn(
		"createDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<ApiAiDataTable, Date> modifiedDate = createColumn(
		"modifiedDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<ApiAiDataTable, String> type = createColumn(
		"type_", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<ApiAiDataTable, String> result = createColumn(
		"result", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<ApiAiDataTable, String> action = createColumn(
		"action", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<ApiAiDataTable, String> fulfillment = createColumn(
		"fulfillment", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<ApiAiDataTable, String> speech = createColumn(
		"speech", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);

	private ApiAiDataTable() {
		super("APIAI_ApiAiData", ApiAiDataTable::new);
	}

}