create table APIAI_ApiAiData (
	uuid_ VARCHAR(75) null,
	apiAiDataId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	type_ VARCHAR(75) null,
	result VARCHAR(2048) null,
	action VARCHAR(2048) null,
	fulfillment VARCHAR(2048) null,
	speech VARCHAR(2048) null
);