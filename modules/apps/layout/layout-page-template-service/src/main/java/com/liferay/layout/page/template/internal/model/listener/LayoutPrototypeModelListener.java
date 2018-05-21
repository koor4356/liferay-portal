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

package com.liferay.layout.page.template.internal.model.listener;

import com.liferay.layout.page.template.constants.LayoutPageTemplateEntryTypeConstants;
import com.liferay.layout.page.template.model.LayoutPageTemplateEntry;
import com.liferay.layout.page.template.service.LayoutPageTemplateEntryLocalService;
import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.LayoutPrototype;
import com.liferay.portal.kernel.model.ModelListener;
import com.liferay.portal.kernel.service.CompanyLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.util.Locale;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Jürgen Kappler
 */
@Component(immediate = true, service = ModelListener.class)
public class LayoutPrototypeModelListener
	extends BaseModelListener<LayoutPrototype> {

	@Override
	public void onAfterCreate(LayoutPrototype layoutPrototype)
		throws ModelListenerException {

		try {
			if (_getLayoutPageTemplateEntry(layoutPrototype) != null) {
				return;
			}

			long layoutPrototypeId = layoutPrototype.getLayoutPrototypeId();
			long companyId = layoutPrototype.getCompanyId();
			long userId = layoutPrototype.getUserId();
			String nameXML = layoutPrototype.getName();

			Map<Locale, String> nameMap = LocalizationUtil.getLocalizationMap(
				nameXML);

			Locale defaultLocale = LocaleUtil.fromLanguageId(
				LocalizationUtil.getDefaultLanguageId(nameXML));

			String name = nameMap.get(defaultLocale);

			Company company = _companyLocalService.getCompany(companyId);

			_layoutPageTemplateEntryLocalService.addLayoutPageTemplateEntry(
				userId, company.getGroupId(), 0, name,
				LayoutPageTemplateEntryTypeConstants.TYPE_WIDGET_PAGE,
				layoutPrototypeId, WorkflowConstants.STATUS_APPROVED,
				new ServiceContext());
		}
		catch (PortalException pe) {
			if (_log.isDebugEnabled()) {
				_log.debug(pe, pe);
			}

			throw new ModelListenerException();
		}
	}

	@Override
	public void onBeforeRemove(LayoutPrototype layoutPrototype)
		throws ModelListenerException {

		try {
			LayoutPageTemplateEntry layoutPageTemplateEntry =
				_getLayoutPageTemplateEntry(layoutPrototype);

			if (layoutPageTemplateEntry != null) {
				_layoutPageTemplateEntryLocalService.
					deleteLayoutPageTemplateEntry(layoutPageTemplateEntry);
			}
		}
		catch (PortalException pe) {
			if (_log.isDebugEnabled()) {
				_log.debug(pe, pe);
			}

			throw new ModelListenerException();
		}
	}

	private LayoutPageTemplateEntry _getLayoutPageTemplateEntry(
			LayoutPrototype layoutPrototype)
		throws PortalException {

		long companyId = layoutPrototype.getCompanyId();

		Company company = _companyLocalService.getCompany(companyId);

		return _layoutPageTemplateEntryLocalService.
			fetchFirstLayoutPageTemplateEntry(
				company.getGroupId(), layoutPrototype.getLayoutPrototypeId());
	}

	private static final Log _log = LogFactoryUtil.getLog(
		LayoutPrototypeModelListener.class);

	@Reference
	private CompanyLocalService _companyLocalService;

	@Reference(unbind = "-")
	private LayoutPageTemplateEntryLocalService
		_layoutPageTemplateEntryLocalService;

}