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

import {
	setExperienceLock,
	storeNewLayoutData,
	updateFragmentEntryLinksEditableValues,
	selectExperience,
	addExperience
} from './utils';

function createExperienceReducer(state, payload) {
	let nextState = state;

	const {fragmentEntryLinks, layoutData, segmentsExperience} = payload;

	const newExperience = {
		...segmentsExperience,
		hasLockedSegmentsExperiment: false
	};

	nextState = addExperience(nextState, newExperience);
	nextState = selectExperience(nextState, newExperience.segmentsExperienceId);
	nextState = storeNewLayoutData(
		nextState,
		newExperience.segmentsExperienceId,
		layoutData
	);
	nextState = updateFragmentEntryLinksEditableValues(
		nextState,
		fragmentEntryLinks
	);

	nextState = setExperienceLock(nextState, {
		hasLockedSegmentsExperiment: newExperience.hasLockedSegmentsExperiment
	});

	// TODO _switchLayoutDataList -> {
	// TODO updatePageEditorLayoutData
	// TODO _updateFragmentEntryLinks
	// TODO _setUsedWidgets

	return nextState;
}

export default createExperienceReducer;
