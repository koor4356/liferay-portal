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

package com.liferay.json.storage.model.impl;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.json.storage.model.JSONStorageEntry;
import com.liferay.json.storage.model.JSONStorageEntryModel;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

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
 * The base model implementation for the JSONStorageEntry service. Represents a row in the &quot;JSONStorageEntry&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>JSONStorageEntryModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link JSONStorageEntryImpl}.
 * </p>
 *
 * @author Preston Crary
 * @see JSONStorageEntryImpl
 * @generated
 */
public class JSONStorageEntryModelImpl
	extends BaseModelImpl<JSONStorageEntry> implements JSONStorageEntryModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a json storage entry model instance should use the <code>JSONStorageEntry</code> interface instead.
	 */
	public static final String TABLE_NAME = "JSONStorageEntry";

	public static final Object[][] TABLE_COLUMNS = {
		{"mvccVersion", Types.BIGINT}, {"ctCollectionId", Types.BIGINT},
		{"jsonStorageEntryId", Types.BIGINT}, {"companyId", Types.BIGINT},
		{"classNameId", Types.BIGINT}, {"classPK", Types.BIGINT},
		{"parentJSONStorageEntryId", Types.BIGINT}, {"index_", Types.INTEGER},
		{"key_", Types.VARCHAR}, {"type_", Types.INTEGER},
		{"valueLong", Types.BIGINT}, {"valueString", Types.CLOB}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("mvccVersion", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("ctCollectionId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("jsonStorageEntryId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("classNameId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("classPK", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("parentJSONStorageEntryId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("index_", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("key_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("type_", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("valueLong", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("valueString", Types.CLOB);
	}

	public static final String TABLE_SQL_CREATE =
		"create table JSONStorageEntry (mvccVersion LONG default 0 not null,ctCollectionId LONG default 0 not null,jsonStorageEntryId LONG not null,companyId LONG,classNameId LONG,classPK LONG,parentJSONStorageEntryId LONG,index_ INTEGER,key_ VARCHAR(255) null,type_ INTEGER,valueLong LONG,valueString TEXT null,primary key (jsonStorageEntryId, ctCollectionId))";

	public static final String TABLE_SQL_DROP = "drop table JSONStorageEntry";

	public static final String ORDER_BY_JPQL =
		" ORDER BY jsonStorageEntry.index ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY JSONStorageEntry.index_ ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long CLASSNAMEID_COLUMN_BITMASK = 1L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long CLASSPK_COLUMN_BITMASK = 2L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long COMPANYID_COLUMN_BITMASK = 4L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long INDEX_COLUMN_BITMASK = 8L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long KEY_COLUMN_BITMASK = 16L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long PARENTJSONSTORAGEENTRYID_COLUMN_BITMASK = 32L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long TYPE_COLUMN_BITMASK = 64L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long VALUELONG_COLUMN_BITMASK = 128L;

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

	public JSONStorageEntryModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _jsonStorageEntryId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setJsonStorageEntryId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _jsonStorageEntryId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return JSONStorageEntry.class;
	}

	@Override
	public String getModelClassName() {
		return JSONStorageEntry.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<JSONStorageEntry, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		for (Map.Entry<String, Function<JSONStorageEntry, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<JSONStorageEntry, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((JSONStorageEntry)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<JSONStorageEntry, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<JSONStorageEntry, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(JSONStorageEntry)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<JSONStorageEntry, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<JSONStorageEntry, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static final Map<String, Function<JSONStorageEntry, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<JSONStorageEntry, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<JSONStorageEntry, Object>>
			attributeGetterFunctions =
				new LinkedHashMap<String, Function<JSONStorageEntry, Object>>();
		Map<String, BiConsumer<JSONStorageEntry, ?>>
			attributeSetterBiConsumers =
				new LinkedHashMap<String, BiConsumer<JSONStorageEntry, ?>>();

		attributeGetterFunctions.put(
			"mvccVersion", JSONStorageEntry::getMvccVersion);
		attributeSetterBiConsumers.put(
			"mvccVersion",
			(BiConsumer<JSONStorageEntry, Long>)
				JSONStorageEntry::setMvccVersion);
		attributeGetterFunctions.put(
			"ctCollectionId", JSONStorageEntry::getCtCollectionId);
		attributeSetterBiConsumers.put(
			"ctCollectionId",
			(BiConsumer<JSONStorageEntry, Long>)
				JSONStorageEntry::setCtCollectionId);
		attributeGetterFunctions.put(
			"jsonStorageEntryId", JSONStorageEntry::getJsonStorageEntryId);
		attributeSetterBiConsumers.put(
			"jsonStorageEntryId",
			(BiConsumer<JSONStorageEntry, Long>)
				JSONStorageEntry::setJsonStorageEntryId);
		attributeGetterFunctions.put(
			"companyId", JSONStorageEntry::getCompanyId);
		attributeSetterBiConsumers.put(
			"companyId",
			(BiConsumer<JSONStorageEntry, Long>)JSONStorageEntry::setCompanyId);
		attributeGetterFunctions.put(
			"classNameId", JSONStorageEntry::getClassNameId);
		attributeSetterBiConsumers.put(
			"classNameId",
			(BiConsumer<JSONStorageEntry, Long>)
				JSONStorageEntry::setClassNameId);
		attributeGetterFunctions.put("classPK", JSONStorageEntry::getClassPK);
		attributeSetterBiConsumers.put(
			"classPK",
			(BiConsumer<JSONStorageEntry, Long>)JSONStorageEntry::setClassPK);
		attributeGetterFunctions.put(
			"parentJSONStorageEntryId",
			JSONStorageEntry::getParentJSONStorageEntryId);
		attributeSetterBiConsumers.put(
			"parentJSONStorageEntryId",
			(BiConsumer<JSONStorageEntry, Long>)
				JSONStorageEntry::setParentJSONStorageEntryId);
		attributeGetterFunctions.put("index", JSONStorageEntry::getIndex);
		attributeSetterBiConsumers.put(
			"index",
			(BiConsumer<JSONStorageEntry, Integer>)JSONStorageEntry::setIndex);
		attributeGetterFunctions.put("key", JSONStorageEntry::getKey);
		attributeSetterBiConsumers.put(
			"key",
			(BiConsumer<JSONStorageEntry, String>)JSONStorageEntry::setKey);
		attributeGetterFunctions.put("type", JSONStorageEntry::getType);
		attributeSetterBiConsumers.put(
			"type",
			(BiConsumer<JSONStorageEntry, Integer>)JSONStorageEntry::setType);
		attributeGetterFunctions.put(
			"valueLong", JSONStorageEntry::getValueLong);
		attributeSetterBiConsumers.put(
			"valueLong",
			(BiConsumer<JSONStorageEntry, Long>)JSONStorageEntry::setValueLong);
		attributeGetterFunctions.put(
			"valueString", JSONStorageEntry::getValueString);
		attributeSetterBiConsumers.put(
			"valueString",
			(BiConsumer<JSONStorageEntry, String>)
				JSONStorageEntry::setValueString);

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
	public long getJsonStorageEntryId() {
		return _jsonStorageEntryId;
	}

	@Override
	public void setJsonStorageEntryId(long jsonStorageEntryId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_jsonStorageEntryId = jsonStorageEntryId;
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

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalCompanyId() {
		return GetterUtil.getLong(
			this.<Long>getColumnOriginalValue("companyId"));
	}

	@Override
	public String getClassName() {
		if (getClassNameId() <= 0) {
			return "";
		}

		return PortalUtil.getClassName(getClassNameId());
	}

	@Override
	public void setClassName(String className) {
		long classNameId = 0;

		if (Validator.isNotNull(className)) {
			classNameId = PortalUtil.getClassNameId(className);
		}

		setClassNameId(classNameId);
	}

	@Override
	public long getClassNameId() {
		return _classNameId;
	}

	@Override
	public void setClassNameId(long classNameId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_classNameId = classNameId;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalClassNameId() {
		return GetterUtil.getLong(
			this.<Long>getColumnOriginalValue("classNameId"));
	}

	@Override
	public long getClassPK() {
		return _classPK;
	}

	@Override
	public void setClassPK(long classPK) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_classPK = classPK;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalClassPK() {
		return GetterUtil.getLong(this.<Long>getColumnOriginalValue("classPK"));
	}

	@Override
	public long getParentJSONStorageEntryId() {
		return _parentJSONStorageEntryId;
	}

	@Override
	public void setParentJSONStorageEntryId(long parentJSONStorageEntryId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_parentJSONStorageEntryId = parentJSONStorageEntryId;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalParentJSONStorageEntryId() {
		return GetterUtil.getLong(
			this.<Long>getColumnOriginalValue("parentJSONStorageEntryId"));
	}

	@Override
	public int getIndex() {
		return _index;
	}

	@Override
	public void setIndex(int index) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_index = index;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public int getOriginalIndex() {
		return GetterUtil.getInteger(
			this.<Integer>getColumnOriginalValue("index_"));
	}

	@Override
	public String getKey() {
		if (_key == null) {
			return "";
		}
		else {
			return _key;
		}
	}

	@Override
	public void setKey(String key) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_key = key;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public String getOriginalKey() {
		return getColumnOriginalValue("key_");
	}

	@Override
	public int getType() {
		return _type;
	}

	@Override
	public void setType(int type) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_type = type;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public int getOriginalType() {
		return GetterUtil.getInteger(
			this.<Integer>getColumnOriginalValue("type_"));
	}

	@Override
	public long getValueLong() {
		return _valueLong;
	}

	@Override
	public void setValueLong(long valueLong) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_valueLong = valueLong;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalValueLong() {
		return GetterUtil.getLong(
			this.<Long>getColumnOriginalValue("valueLong"));
	}

	@Override
	public String getValueString() {
		if (_valueString == null) {
			return "";
		}
		else {
			return _valueString;
		}
	}

	@Override
	public void setValueString(String valueString) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_valueString = valueString;
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
			getCompanyId(), JSONStorageEntry.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public JSONStorageEntry toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, JSONStorageEntry>
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
		JSONStorageEntryImpl jsonStorageEntryImpl = new JSONStorageEntryImpl();

		jsonStorageEntryImpl.setMvccVersion(getMvccVersion());
		jsonStorageEntryImpl.setCtCollectionId(getCtCollectionId());
		jsonStorageEntryImpl.setJsonStorageEntryId(getJsonStorageEntryId());
		jsonStorageEntryImpl.setCompanyId(getCompanyId());
		jsonStorageEntryImpl.setClassNameId(getClassNameId());
		jsonStorageEntryImpl.setClassPK(getClassPK());
		jsonStorageEntryImpl.setParentJSONStorageEntryId(
			getParentJSONStorageEntryId());
		jsonStorageEntryImpl.setIndex(getIndex());
		jsonStorageEntryImpl.setKey(getKey());
		jsonStorageEntryImpl.setType(getType());
		jsonStorageEntryImpl.setValueLong(getValueLong());
		jsonStorageEntryImpl.setValueString(getValueString());

		jsonStorageEntryImpl.resetOriginalValues();

		return jsonStorageEntryImpl;
	}

	@Override
	public JSONStorageEntry cloneWithOriginalValues() {
		JSONStorageEntryImpl jsonStorageEntryImpl = new JSONStorageEntryImpl();

		jsonStorageEntryImpl.setMvccVersion(
			this.<Long>getColumnOriginalValue("mvccVersion"));
		jsonStorageEntryImpl.setCtCollectionId(
			this.<Long>getColumnOriginalValue("ctCollectionId"));
		jsonStorageEntryImpl.setJsonStorageEntryId(
			this.<Long>getColumnOriginalValue("jsonStorageEntryId"));
		jsonStorageEntryImpl.setCompanyId(
			this.<Long>getColumnOriginalValue("companyId"));
		jsonStorageEntryImpl.setClassNameId(
			this.<Long>getColumnOriginalValue("classNameId"));
		jsonStorageEntryImpl.setClassPK(
			this.<Long>getColumnOriginalValue("classPK"));
		jsonStorageEntryImpl.setParentJSONStorageEntryId(
			this.<Long>getColumnOriginalValue("parentJSONStorageEntryId"));
		jsonStorageEntryImpl.setIndex(
			this.<Integer>getColumnOriginalValue("index_"));
		jsonStorageEntryImpl.setKey(
			this.<String>getColumnOriginalValue("key_"));
		jsonStorageEntryImpl.setType(
			this.<Integer>getColumnOriginalValue("type_"));
		jsonStorageEntryImpl.setValueLong(
			this.<Long>getColumnOriginalValue("valueLong"));
		jsonStorageEntryImpl.setValueString(
			this.<String>getColumnOriginalValue("valueString"));

		return jsonStorageEntryImpl;
	}

	@Override
	public int compareTo(JSONStorageEntry jsonStorageEntry) {
		int value = 0;

		if (getIndex() < jsonStorageEntry.getIndex()) {
			value = -1;
		}
		else if (getIndex() > jsonStorageEntry.getIndex()) {
			value = 1;
		}
		else {
			value = 0;
		}

		if (value != 0) {
			return value;
		}

		return 0;
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof JSONStorageEntry)) {
			return false;
		}

		JSONStorageEntry jsonStorageEntry = (JSONStorageEntry)object;

		long primaryKey = jsonStorageEntry.getPrimaryKey();

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

		_columnBitmask = 0;
	}

	@Override
	public CacheModel<JSONStorageEntry> toCacheModel() {
		JSONStorageEntryCacheModel jsonStorageEntryCacheModel =
			new JSONStorageEntryCacheModel();

		jsonStorageEntryCacheModel.mvccVersion = getMvccVersion();

		jsonStorageEntryCacheModel.ctCollectionId = getCtCollectionId();

		jsonStorageEntryCacheModel.jsonStorageEntryId = getJsonStorageEntryId();

		jsonStorageEntryCacheModel.companyId = getCompanyId();

		jsonStorageEntryCacheModel.classNameId = getClassNameId();

		jsonStorageEntryCacheModel.classPK = getClassPK();

		jsonStorageEntryCacheModel.parentJSONStorageEntryId =
			getParentJSONStorageEntryId();

		jsonStorageEntryCacheModel.index = getIndex();

		jsonStorageEntryCacheModel.key = getKey();

		String key = jsonStorageEntryCacheModel.key;

		if ((key != null) && (key.length() == 0)) {
			jsonStorageEntryCacheModel.key = null;
		}

		jsonStorageEntryCacheModel.type = getType();

		jsonStorageEntryCacheModel.valueLong = getValueLong();

		jsonStorageEntryCacheModel.valueString = getValueString();

		String valueString = jsonStorageEntryCacheModel.valueString;

		if ((valueString != null) && (valueString.length() == 0)) {
			jsonStorageEntryCacheModel.valueString = null;
		}

		return jsonStorageEntryCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<JSONStorageEntry, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 2);

		sb.append("{");

		for (Map.Entry<String, Function<JSONStorageEntry, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<JSONStorageEntry, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("\"");
			sb.append(attributeName);
			sb.append("\": ");

			Object value = attributeGetterFunction.apply(
				(JSONStorageEntry)this);

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
		Map<String, Function<JSONStorageEntry, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<JSONStorageEntry, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<JSONStorageEntry, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(attributeGetterFunction.apply((JSONStorageEntry)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function<InvocationHandler, JSONStorageEntry>
			_escapedModelProxyProviderFunction =
				ProxyUtil.getProxyProviderFunction(
					JSONStorageEntry.class, ModelWrapper.class);

	}

	private long _mvccVersion;
	private long _ctCollectionId;
	private long _jsonStorageEntryId;
	private long _companyId;
	private long _classNameId;
	private long _classPK;
	private long _parentJSONStorageEntryId;
	private int _index;
	private String _key;
	private int _type;
	private long _valueLong;
	private String _valueString;

	public <T> T getColumnValue(String columnName) {
		columnName = _attributeNames.getOrDefault(columnName, columnName);

		Function<JSONStorageEntry, Object> function =
			_attributeGetterFunctions.get(columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((JSONStorageEntry)this);
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
		_columnOriginalValues.put("jsonStorageEntryId", _jsonStorageEntryId);
		_columnOriginalValues.put("companyId", _companyId);
		_columnOriginalValues.put("classNameId", _classNameId);
		_columnOriginalValues.put("classPK", _classPK);
		_columnOriginalValues.put(
			"parentJSONStorageEntryId", _parentJSONStorageEntryId);
		_columnOriginalValues.put("index_", _index);
		_columnOriginalValues.put("key_", _key);
		_columnOriginalValues.put("type_", _type);
		_columnOriginalValues.put("valueLong", _valueLong);
		_columnOriginalValues.put("valueString", _valueString);
	}

	private static final Map<String, String> _attributeNames;

	static {
		Map<String, String> attributeNames = new HashMap<>();

		attributeNames.put("index_", "index");
		attributeNames.put("key_", "key");
		attributeNames.put("type_", "type");

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

		columnBitmasks.put("jsonStorageEntryId", 4L);

		columnBitmasks.put("companyId", 8L);

		columnBitmasks.put("classNameId", 16L);

		columnBitmasks.put("classPK", 32L);

		columnBitmasks.put("parentJSONStorageEntryId", 64L);

		columnBitmasks.put("index_", 128L);

		columnBitmasks.put("key_", 256L);

		columnBitmasks.put("type_", 512L);

		columnBitmasks.put("valueLong", 1024L);

		columnBitmasks.put("valueString", 2048L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);
	}

	private long _columnBitmask;
	private JSONStorageEntry _escapedModel;

}