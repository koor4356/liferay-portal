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

package com.liferay.commerce.internal.upgrade.v8_1_0;

import com.liferay.commerce.internal.upgrade.base.BaseCommerceServiceUpgradeProcess;
import com.liferay.commerce.internal.upgrade.v8_1_0.util.CommerceOrderTable;

/**
 * @author Luca Pellizzon
 */
public class CommerceOrderUpgradeProcess
	extends BaseCommerceServiceUpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		addColumn(
			CommerceOrderTable.class, CommerceOrderTable.TABLE_NAME,
			"deliveryCommerceTermEntryId", "LONG");
		addColumn(
			CommerceOrderTable.class, CommerceOrderTable.TABLE_NAME,
			"deliveryCTermEntryDescription", "TEXT null");
		addColumn(
			CommerceOrderTable.class, CommerceOrderTable.TABLE_NAME,
			"deliveryCommerceTermEntryName", "VARCHAR(75) null");
		addColumn(
			CommerceOrderTable.class, CommerceOrderTable.TABLE_NAME,
			"paymentCommerceTermEntryId", "LONG");
		addColumn(
			CommerceOrderTable.class, CommerceOrderTable.TABLE_NAME,
			"paymentCTermEntryDescription", "TEXT null");
		addColumn(
			CommerceOrderTable.class, CommerceOrderTable.TABLE_NAME,
			"paymentCommerceTermEntryName", "VARCHAR(75) null");
	}

}