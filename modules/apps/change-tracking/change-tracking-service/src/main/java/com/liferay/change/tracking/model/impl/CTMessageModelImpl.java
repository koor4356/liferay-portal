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

package com.liferay.change.tracking.model.impl;

import com.liferay.change.tracking.model.CTMessage;
import com.liferay.change.tracking.model.CTMessageModel;
import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.ProxyUtil;

import java.io.Serializable;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;

import java.sql.Types;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * The base model implementation for the CTMessage service. Represents a row in the &quot;CTMessage&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>CTMessageModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link CTMessageImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CTMessageImpl
 * @generated
 */
public class CTMessageModelImpl
	extends BaseModelImpl<CTMessage> implements CTMessageModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a ct message model instance should use the <code>CTMessage</code> interface instead.
	 */
	public static final String TABLE_NAME = "CTMessage";

	public static final Object[][] TABLE_COLUMNS = {
		{"mvccVersion", Types.BIGINT}, {"ctMessageId", Types.BIGINT},
		{"companyId", Types.BIGINT}, {"ctCollectionId", Types.BIGINT},
		{"messageContent", Types.CLOB}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("mvccVersion", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("ctMessageId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("ctCollectionId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("messageContent", Types.CLOB);
	}

	public static final String TABLE_SQL_CREATE =
		"create table CTMessage (mvccVersion LONG default 0 not null,ctMessageId LONG not null primary key,companyId LONG,ctCollectionId LONG,messageContent TEXT null)";

	public static final String TABLE_SQL_DROP = "drop table CTMessage";

	public static final String ORDER_BY_JPQL =
		" ORDER BY ctMessage.ctMessageId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY CTMessage.ctMessageId ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	public static final long CTCOLLECTIONID_COLUMN_BITMASK = 1L;

	public static final long CTMESSAGEID_COLUMN_BITMASK = 2L;

	public static void setEntityCacheEnabled(boolean entityCacheEnabled) {
		_entityCacheEnabled = entityCacheEnabled;
	}

	public static void setFinderCacheEnabled(boolean finderCacheEnabled) {
		_finderCacheEnabled = finderCacheEnabled;
	}

	public CTMessageModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _ctMessageId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setCtMessageId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _ctMessageId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return CTMessage.class;
	}

	@Override
	public String getModelClassName() {
		return CTMessage.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<CTMessage, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		for (Map.Entry<String, Function<CTMessage, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<CTMessage, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName, attributeGetterFunction.apply((CTMessage)this));
		}

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<CTMessage, Object>> attributeSetterBiConsumers =
			getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<CTMessage, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(CTMessage)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<CTMessage, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<CTMessage, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, CTMessage>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			CTMessage.class.getClassLoader(), CTMessage.class,
			ModelWrapper.class);

		try {
			Constructor<CTMessage> constructor =
				(Constructor<CTMessage>)proxyClass.getConstructor(
					InvocationHandler.class);

			return invocationHandler -> {
				try {
					return constructor.newInstance(invocationHandler);
				}
				catch (ReflectiveOperationException
							reflectiveOperationException) {

					throw new InternalError(reflectiveOperationException);
				}
			};
		}
		catch (NoSuchMethodException noSuchMethodException) {
			throw new InternalError(noSuchMethodException);
		}
	}

	private static final Map<String, Function<CTMessage, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<CTMessage, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<CTMessage, Object>> attributeGetterFunctions =
			new LinkedHashMap<String, Function<CTMessage, Object>>();
		Map<String, BiConsumer<CTMessage, ?>> attributeSetterBiConsumers =
			new LinkedHashMap<String, BiConsumer<CTMessage, ?>>();

		attributeGetterFunctions.put("mvccVersion", CTMessage::getMvccVersion);
		attributeSetterBiConsumers.put(
			"mvccVersion",
			(BiConsumer<CTMessage, Long>)CTMessage::setMvccVersion);
		attributeGetterFunctions.put("ctMessageId", CTMessage::getCtMessageId);
		attributeSetterBiConsumers.put(
			"ctMessageId",
			(BiConsumer<CTMessage, Long>)CTMessage::setCtMessageId);
		attributeGetterFunctions.put("companyId", CTMessage::getCompanyId);
		attributeSetterBiConsumers.put(
			"companyId", (BiConsumer<CTMessage, Long>)CTMessage::setCompanyId);
		attributeGetterFunctions.put(
			"ctCollectionId", CTMessage::getCtCollectionId);
		attributeSetterBiConsumers.put(
			"ctCollectionId",
			(BiConsumer<CTMessage, Long>)CTMessage::setCtCollectionId);
		attributeGetterFunctions.put(
			"messageContent", CTMessage::getMessageContent);
		attributeSetterBiConsumers.put(
			"messageContent",
			(BiConsumer<CTMessage, String>)CTMessage::setMessageContent);

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
		_mvccVersion = mvccVersion;
	}

	@Override
	public long getCtMessageId() {
		return _ctMessageId;
	}

	@Override
	public void setCtMessageId(long ctMessageId) {
		_ctMessageId = ctMessageId;
	}

	@Override
	public long getCompanyId() {
		return _companyId;
	}

	@Override
	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	@Override
	public long getCtCollectionId() {
		return _ctCollectionId;
	}

	@Override
	public void setCtCollectionId(long ctCollectionId) {
		_columnBitmask |= CTCOLLECTIONID_COLUMN_BITMASK;

		if (!_setOriginalCtCollectionId) {
			_setOriginalCtCollectionId = true;

			_originalCtCollectionId = _ctCollectionId;
		}

		_ctCollectionId = ctCollectionId;
	}

	public long getOriginalCtCollectionId() {
		return _originalCtCollectionId;
	}

	@Override
	public String getMessageContent() {
		if (_messageContent == null) {
			return "";
		}
		else {
			return _messageContent;
		}
	}

	@Override
	public void setMessageContent(String messageContent) {
		_messageContent = messageContent;
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(
			getCompanyId(), CTMessage.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public CTMessage toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, CTMessage>
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
		CTMessageImpl ctMessageImpl = new CTMessageImpl();

		ctMessageImpl.setMvccVersion(getMvccVersion());
		ctMessageImpl.setCtMessageId(getCtMessageId());
		ctMessageImpl.setCompanyId(getCompanyId());
		ctMessageImpl.setCtCollectionId(getCtCollectionId());
		ctMessageImpl.setMessageContent(getMessageContent());

		ctMessageImpl.resetOriginalValues();

		return ctMessageImpl;
	}

	@Override
	public int compareTo(CTMessage ctMessage) {
		long primaryKey = ctMessage.getPrimaryKey();

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
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CTMessage)) {
			return false;
		}

		CTMessage ctMessage = (CTMessage)obj;

		long primaryKey = ctMessage.getPrimaryKey();

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

	@Override
	public boolean isEntityCacheEnabled() {
		return _entityCacheEnabled;
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _finderCacheEnabled;
	}

	@Override
	public void resetOriginalValues() {
		CTMessageModelImpl ctMessageModelImpl = this;

		ctMessageModelImpl._originalCtCollectionId =
			ctMessageModelImpl._ctCollectionId;

		ctMessageModelImpl._setOriginalCtCollectionId = false;

		ctMessageModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<CTMessage> toCacheModel() {
		CTMessageCacheModel ctMessageCacheModel = new CTMessageCacheModel();

		ctMessageCacheModel.mvccVersion = getMvccVersion();

		ctMessageCacheModel.ctMessageId = getCtMessageId();

		ctMessageCacheModel.companyId = getCompanyId();

		ctMessageCacheModel.ctCollectionId = getCtCollectionId();

		ctMessageCacheModel.messageContent = getMessageContent();

		String messageContent = ctMessageCacheModel.messageContent;

		if ((messageContent != null) && (messageContent.length() == 0)) {
			ctMessageCacheModel.messageContent = null;
		}

		return ctMessageCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<CTMessage, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			4 * attributeGetterFunctions.size() + 2);

		sb.append("{");

		for (Map.Entry<String, Function<CTMessage, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<CTMessage, Object> attributeGetterFunction =
				entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(attributeGetterFunction.apply((CTMessage)this));
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
		Map<String, Function<CTMessage, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			5 * attributeGetterFunctions.size() + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<CTMessage, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<CTMessage, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(attributeGetterFunction.apply((CTMessage)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function<InvocationHandler, CTMessage>
			_escapedModelProxyProviderFunction = _getProxyProviderFunction();

	}

	private static boolean _entityCacheEnabled;
	private static boolean _finderCacheEnabled;

	private long _mvccVersion;
	private long _ctMessageId;
	private long _companyId;
	private long _ctCollectionId;
	private long _originalCtCollectionId;
	private boolean _setOriginalCtCollectionId;
	private String _messageContent;
	private long _columnBitmask;
	private CTMessage _escapedModel;

}