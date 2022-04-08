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

package com.liferay.dynamic.data.mapping.model.impl;

import com.liferay.dynamic.data.mapping.model.DDMFormInstanceReport;
import com.liferay.dynamic.data.mapping.model.DDMFormInstanceReportModel;
import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringUtil;

import java.io.Serializable;

import java.lang.reflect.InvocationHandler;

import java.sql.Blob;
import java.sql.Types;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * The base model implementation for the DDMFormInstanceReport service. Represents a row in the &quot;DDMFormInstanceReport&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>DDMFormInstanceReportModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link DDMFormInstanceReportImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see DDMFormInstanceReportImpl
 * @generated
 */
public class DDMFormInstanceReportModelImpl
	extends BaseModelImpl<DDMFormInstanceReport>
	implements DDMFormInstanceReportModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a ddm form instance report model instance should use the <code>DDMFormInstanceReport</code> interface instead.
	 */
	public static final String TABLE_NAME = "DDMFormInstanceReport";

	public static final Object[][] TABLE_COLUMNS = {
		{"mvccVersion", Types.BIGINT}, {"ctCollectionId", Types.BIGINT},
		{"formInstanceReportId", Types.BIGINT}, {"groupId", Types.BIGINT},
		{"companyId", Types.BIGINT}, {"createDate", Types.TIMESTAMP},
		{"modifiedDate", Types.TIMESTAMP}, {"formInstanceId", Types.BIGINT},
		{"data_", Types.CLOB}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("mvccVersion", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("ctCollectionId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("formInstanceReportId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("formInstanceId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("data_", Types.CLOB);
	}

	public static final String TABLE_SQL_CREATE =
		"create table DDMFormInstanceReport (mvccVersion LONG default 0 not null,ctCollectionId LONG default 0 not null,formInstanceReportId LONG not null,groupId LONG,companyId LONG,createDate DATE null,modifiedDate DATE null,formInstanceId LONG,data_ TEXT null,primary key (formInstanceReportId, ctCollectionId))";

	public static final String TABLE_SQL_DROP =
		"drop table DDMFormInstanceReport";

	public static final String ORDER_BY_JPQL =
		" ORDER BY ddmFormInstanceReport.formInstanceReportId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY DDMFormInstanceReport.formInstanceReportId ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long FORMINSTANCEID_COLUMN_BITMASK = 1L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *		#getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long FORMINSTANCEREPORTID_COLUMN_BITMASK = 2L;

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	public static void setEntityCacheEnabled(boolean entityCacheEnabled) {
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	public static void setFinderCacheEnabled(boolean finderCacheEnabled) {
	}

	public DDMFormInstanceReportModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _formInstanceReportId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setFormInstanceReportId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _formInstanceReportId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return DDMFormInstanceReport.class;
	}

	@Override
	public String getModelClassName() {
		return DDMFormInstanceReport.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<DDMFormInstanceReport, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		for (Map.Entry<String, Function<DDMFormInstanceReport, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<DDMFormInstanceReport, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((DDMFormInstanceReport)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<DDMFormInstanceReport, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<DDMFormInstanceReport, Object>
				attributeSetterBiConsumer = attributeSetterBiConsumers.get(
					attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(DDMFormInstanceReport)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<DDMFormInstanceReport, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<DDMFormInstanceReport, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static final Map<String, Function<DDMFormInstanceReport, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<DDMFormInstanceReport, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<DDMFormInstanceReport, Object>>
			attributeGetterFunctions =
				new LinkedHashMap
					<String, Function<DDMFormInstanceReport, Object>>();
		Map<String, BiConsumer<DDMFormInstanceReport, ?>>
			attributeSetterBiConsumers =
				new LinkedHashMap
					<String, BiConsumer<DDMFormInstanceReport, ?>>();

		attributeGetterFunctions.put(
			"mvccVersion", DDMFormInstanceReport::getMvccVersion);
		attributeSetterBiConsumers.put(
			"mvccVersion",
			(BiConsumer<DDMFormInstanceReport, Long>)
				DDMFormInstanceReport::setMvccVersion);
		attributeGetterFunctions.put(
			"ctCollectionId", DDMFormInstanceReport::getCtCollectionId);
		attributeSetterBiConsumers.put(
			"ctCollectionId",
			(BiConsumer<DDMFormInstanceReport, Long>)
				DDMFormInstanceReport::setCtCollectionId);
		attributeGetterFunctions.put(
			"formInstanceReportId",
			DDMFormInstanceReport::getFormInstanceReportId);
		attributeSetterBiConsumers.put(
			"formInstanceReportId",
			(BiConsumer<DDMFormInstanceReport, Long>)
				DDMFormInstanceReport::setFormInstanceReportId);
		attributeGetterFunctions.put(
			"groupId", DDMFormInstanceReport::getGroupId);
		attributeSetterBiConsumers.put(
			"groupId",
			(BiConsumer<DDMFormInstanceReport, Long>)
				DDMFormInstanceReport::setGroupId);
		attributeGetterFunctions.put(
			"companyId", DDMFormInstanceReport::getCompanyId);
		attributeSetterBiConsumers.put(
			"companyId",
			(BiConsumer<DDMFormInstanceReport, Long>)
				DDMFormInstanceReport::setCompanyId);
		attributeGetterFunctions.put(
			"createDate", DDMFormInstanceReport::getCreateDate);
		attributeSetterBiConsumers.put(
			"createDate",
			(BiConsumer<DDMFormInstanceReport, Date>)
				DDMFormInstanceReport::setCreateDate);
		attributeGetterFunctions.put(
			"modifiedDate", DDMFormInstanceReport::getModifiedDate);
		attributeSetterBiConsumers.put(
			"modifiedDate",
			(BiConsumer<DDMFormInstanceReport, Date>)
				DDMFormInstanceReport::setModifiedDate);
		attributeGetterFunctions.put(
			"formInstanceId", DDMFormInstanceReport::getFormInstanceId);
		attributeSetterBiConsumers.put(
			"formInstanceId",
			(BiConsumer<DDMFormInstanceReport, Long>)
				DDMFormInstanceReport::setFormInstanceId);
		attributeGetterFunctions.put("data", DDMFormInstanceReport::getData);
		attributeSetterBiConsumers.put(
			"data",
			(BiConsumer<DDMFormInstanceReport, String>)
				DDMFormInstanceReport::setData);

		_attributeGetterFunctions = Collections.unmodifiableMap(
			attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap(
			(Map)attributeSetterBiConsumers);
	}

	@Override
	public long getMvccVersion() {
		return _mvccVersion;
	}

	@Override
	public void setMvccVersion(long mvccVersion) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_mvccVersion = mvccVersion;
	}

	@Override
	public long getCtCollectionId() {
		return _ctCollectionId;
	}

	@Override
	public void setCtCollectionId(long ctCollectionId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_ctCollectionId = ctCollectionId;
	}

	@Override
	public long getFormInstanceReportId() {
		return _formInstanceReportId;
	}

	@Override
	public void setFormInstanceReportId(long formInstanceReportId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_formInstanceReportId = formInstanceReportId;
	}

	@Override
	public long getGroupId() {
		return _groupId;
	}

	@Override
	public void setGroupId(long groupId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_groupId = groupId;
	}

	@Override
	public long getCompanyId() {
		return _companyId;
	}

	@Override
	public void setCompanyId(long companyId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_companyId = companyId;
	}

	@Override
	public Date getCreateDate() {
		return _createDate;
	}

	@Override
	public void setCreateDate(Date createDate) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_createDate = createDate;
	}

	@Override
	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public boolean hasSetModifiedDate() {
		return _setModifiedDate;
	}

	@Override
	public void setModifiedDate(Date modifiedDate) {
		_setModifiedDate = true;

		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_modifiedDate = modifiedDate;
	}

	@Override
	public long getFormInstanceId() {
		return _formInstanceId;
	}

	@Override
	public void setFormInstanceId(long formInstanceId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_formInstanceId = formInstanceId;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalFormInstanceId() {
		return GetterUtil.getLong(
			this.<Long>getColumnOriginalValue("formInstanceId"));
	}

	@Override
	public String getData() {
		if (_data == null) {
			return "";
		}
		else {
			return _data;
		}
	}

	@Override
	public void setData(String data) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_data = data;
	}

	public long getColumnBitmask() {
		if (_columnBitmask > 0) {
			return _columnBitmask;
		}

		if ((_columnOriginalValues == null) ||
			(_columnOriginalValues == Collections.EMPTY_MAP)) {

			return 0;
		}

		for (Map.Entry<String, Object> entry :
				_columnOriginalValues.entrySet()) {

			if (!Objects.equals(
					entry.getValue(), getColumnValue(entry.getKey()))) {

				_columnBitmask |= _columnBitmasks.get(entry.getKey());
			}
		}

		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(
			getCompanyId(), DDMFormInstanceReport.class.getName(),
			getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public DDMFormInstanceReport toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, DDMFormInstanceReport>
				escapedModelProxyProviderFunction =
					EscapedModelProxyProviderFunctionHolder.
						_escapedModelProxyProviderFunction;

			_escapedModel = escapedModelProxyProviderFunction.apply(
				new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		DDMFormInstanceReportImpl ddmFormInstanceReportImpl =
			new DDMFormInstanceReportImpl();

		ddmFormInstanceReportImpl.setMvccVersion(getMvccVersion());
		ddmFormInstanceReportImpl.setCtCollectionId(getCtCollectionId());
		ddmFormInstanceReportImpl.setFormInstanceReportId(
			getFormInstanceReportId());
		ddmFormInstanceReportImpl.setGroupId(getGroupId());
		ddmFormInstanceReportImpl.setCompanyId(getCompanyId());
		ddmFormInstanceReportImpl.setCreateDate(getCreateDate());
		ddmFormInstanceReportImpl.setModifiedDate(getModifiedDate());
		ddmFormInstanceReportImpl.setFormInstanceId(getFormInstanceId());
		ddmFormInstanceReportImpl.setData(getData());

		ddmFormInstanceReportImpl.resetOriginalValues();

		return ddmFormInstanceReportImpl;
	}

	@Override
	public DDMFormInstanceReport cloneWithOriginalValues() {
		DDMFormInstanceReportImpl ddmFormInstanceReportImpl =
			new DDMFormInstanceReportImpl();

		ddmFormInstanceReportImpl.setMvccVersion(
			this.<Long>getColumnOriginalValue("mvccVersion"));
		ddmFormInstanceReportImpl.setCtCollectionId(
			this.<Long>getColumnOriginalValue("ctCollectionId"));
		ddmFormInstanceReportImpl.setFormInstanceReportId(
			this.<Long>getColumnOriginalValue("formInstanceReportId"));
		ddmFormInstanceReportImpl.setGroupId(
			this.<Long>getColumnOriginalValue("groupId"));
		ddmFormInstanceReportImpl.setCompanyId(
			this.<Long>getColumnOriginalValue("companyId"));
		ddmFormInstanceReportImpl.setCreateDate(
			this.<Date>getColumnOriginalValue("createDate"));
		ddmFormInstanceReportImpl.setModifiedDate(
			this.<Date>getColumnOriginalValue("modifiedDate"));
		ddmFormInstanceReportImpl.setFormInstanceId(
			this.<Long>getColumnOriginalValue("formInstanceId"));
		ddmFormInstanceReportImpl.setData(
			this.<String>getColumnOriginalValue("data_"));

		return ddmFormInstanceReportImpl;
	}

	@Override
	public int compareTo(DDMFormInstanceReport ddmFormInstanceReport) {
		long primaryKey = ddmFormInstanceReport.getPrimaryKey();

		if (getPrimaryKey() < primaryKey) {
			return -1;
		}
		else if (getPrimaryKey() > primaryKey) {
			return 1;
		}
		else {
			return 0;
		}
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof DDMFormInstanceReport)) {
			return false;
		}

		DDMFormInstanceReport ddmFormInstanceReport =
			(DDMFormInstanceReport)object;

		long primaryKey = ddmFormInstanceReport.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	@Override
	public boolean isEntityCacheEnabled() {
		return true;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	@Override
	public boolean isFinderCacheEnabled() {
		return true;
	}

	@Override
	public void resetOriginalValues() {
		_columnOriginalValues = Collections.emptyMap();

		_setModifiedDate = false;

		_columnBitmask = 0;
	}

	@Override
	public CacheModel<DDMFormInstanceReport> toCacheModel() {
		DDMFormInstanceReportCacheModel ddmFormInstanceReportCacheModel =
			new DDMFormInstanceReportCacheModel();

		ddmFormInstanceReportCacheModel.mvccVersion = getMvccVersion();

		ddmFormInstanceReportCacheModel.ctCollectionId = getCtCollectionId();

		ddmFormInstanceReportCacheModel.formInstanceReportId =
			getFormInstanceReportId();

		ddmFormInstanceReportCacheModel.groupId = getGroupId();

		ddmFormInstanceReportCacheModel.companyId = getCompanyId();

		Date createDate = getCreateDate();

		if (createDate != null) {
			ddmFormInstanceReportCacheModel.createDate = createDate.getTime();
		}
		else {
			ddmFormInstanceReportCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			ddmFormInstanceReportCacheModel.modifiedDate =
				modifiedDate.getTime();
		}
		else {
			ddmFormInstanceReportCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		ddmFormInstanceReportCacheModel.formInstanceId = getFormInstanceId();

		ddmFormInstanceReportCacheModel.data = getData();

		String data = ddmFormInstanceReportCacheModel.data;

		if ((data != null) && (data.length() == 0)) {
			ddmFormInstanceReportCacheModel.data = null;
		}

		return ddmFormInstanceReportCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<DDMFormInstanceReport, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 2);

		sb.append("{");

		for (Map.Entry<String, Function<DDMFormInstanceReport, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<DDMFormInstanceReport, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("\"");
			sb.append(attributeName);
			sb.append("\": ");

			Object value = attributeGetterFunction.apply(
				(DDMFormInstanceReport)this);

			if (value == null) {
				sb.append("null");
			}
			else if (value instanceof Blob || value instanceof Date ||
					 value instanceof Map || value instanceof String) {

				sb.append(
					"\"" + StringUtil.replace(value.toString(), "\"", "'") +
						"\"");
			}
			else {
				sb.append(value);
			}

			sb.append(", ");
		}

		if (sb.index() > 1) {
			sb.setIndex(sb.index() - 1);
		}

		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		Map<String, Function<DDMFormInstanceReport, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<DDMFormInstanceReport, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<DDMFormInstanceReport, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(
				attributeGetterFunction.apply((DDMFormInstanceReport)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function<InvocationHandler, DDMFormInstanceReport>
			_escapedModelProxyProviderFunction =
				ProxyUtil.getProxyProviderFunction(
					DDMFormInstanceReport.class, ModelWrapper.class);

	}

	private long _mvccVersion;
	private long _ctCollectionId;
	private long _formInstanceReportId;
	private long _groupId;
	private long _companyId;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private long _formInstanceId;
	private String _data;

	public <T> T getColumnValue(String columnName) {
		columnName = _attributeNames.getOrDefault(columnName, columnName);

		Function<DDMFormInstanceReport, Object> function =
			_attributeGetterFunctions.get(columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((DDMFormInstanceReport)this);
	}

	public <T> T getColumnOriginalValue(String columnName) {
		if (_columnOriginalValues == null) {
			return null;
		}

		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		return (T)_columnOriginalValues.get(columnName);
	}

	private void _setColumnOriginalValues() {
		_columnOriginalValues = new HashMap<String, Object>();

		_columnOriginalValues.put("mvccVersion", _mvccVersion);
		_columnOriginalValues.put("ctCollectionId", _ctCollectionId);
		_columnOriginalValues.put(
			"formInstanceReportId", _formInstanceReportId);
		_columnOriginalValues.put("groupId", _groupId);
		_columnOriginalValues.put("companyId", _companyId);
		_columnOriginalValues.put("createDate", _createDate);
		_columnOriginalValues.put("modifiedDate", _modifiedDate);
		_columnOriginalValues.put("formInstanceId", _formInstanceId);
		_columnOriginalValues.put("data_", _data);
	}

	private static final Map<String, String> _attributeNames;

	static {
		Map<String, String> attributeNames = new HashMap<>();

		attributeNames.put("data_", "data");

		_attributeNames = Collections.unmodifiableMap(attributeNames);
	}

	private transient Map<String, Object> _columnOriginalValues;

	public static long getColumnBitmask(String columnName) {
		return _columnBitmasks.get(columnName);
	}

	private static final Map<String, Long> _columnBitmasks;

	static {
		Map<String, Long> columnBitmasks = new HashMap<>();

		columnBitmasks.put("mvccVersion", 1L);

		columnBitmasks.put("ctCollectionId", 2L);

		columnBitmasks.put("formInstanceReportId", 4L);

		columnBitmasks.put("groupId", 8L);

		columnBitmasks.put("companyId", 16L);

		columnBitmasks.put("createDate", 32L);

		columnBitmasks.put("modifiedDate", 64L);

		columnBitmasks.put("formInstanceId", 128L);

		columnBitmasks.put("data_", 256L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);
	}

	private long _columnBitmask;
	private DDMFormInstanceReport _escapedModel;

}