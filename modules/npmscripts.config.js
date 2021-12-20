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

const CHECK_AND_FIX_GLOBS = [
	'!settings.json',
	'!tsconfig.json',
	'/{,dxp/}*.{js,ts}',
	'/{,dxp/}apps/*/*/*.{js,ts,tsx}',
	'/{,dxp/}apps/*/*/*/*.{js,ts,tsx}',
	'/{,dxp/}apps/*/*/*/{src,test}/**/*.{js,scss,ts,tsx}',
	'/{,dxp/}apps/*/*/*/{src}/**/*.{jsp,jspf}',
	'/{,dxp/}apps/*/*/{src,test}/**/*.{js,scss,ts,tsx}',
	'/{,dxp/}apps/*/*/{src}/**/*.{jsp,jspf}',
];

module.exports = {
	build: {
		bundler: {
			config: {
				imports: {
					'@liferay/document-library-preview-css': {
						'/': '*',
					},
					'@liferay/fragment-renderer-collection-filter-impl': {
						'/': '*',
					},
					'@liferay/frontend-data-set-web': {
						'/': '*',
					},
					'@liferay/frontend-js-a11y-web': {
						'/': '*',
					},
					'@liferay/frontend-js-react-web': {
						'/': '*',
						'classnames': '*',
						'formik': '*',
						'prop-types': '*',
						'react': '*',
						'react-dnd': '*',
						'react-dnd-html5-backend': '*',
						'react-dom': '*',
					},
					'@liferay/frontend-js-state-web': {
						'/': '*',
					},
					'@liferay/frontend-taglib': {
						'/': '*',
					},
					'@liferay/layout-content-page-editor-web': {
						'/': '*',
					},
					'@liferay/map-common': {
						'/': '*',
					},
					'@liferay/map-google-maps': {
						'/': '*',
					},
					'@liferay/map-openstreetmap': {
						'/': '*',
					},
					'asset-taglib': {
						'/': '*',
					},
					'commerce-frontend-js': {
						'/': '*',
					},
					'data-engine-js-components-web': {
						'/': '*',
					},
					'data-engine-taglib': {
						'/': '*',
					},
					'dynamic-data-mapping-form-builder': {
						'/': '*',
					},
					'dynamic-data-mapping-form-field-type': {
						'/': '*',
					},
					'dynamic-data-mapping-form-renderer': {
						'/': '*',
					},
					'dynamic-data-mapping-form-web': {
						'/': '*',
					},
					'frontend-editor-ckeditor-web': {
						'/': '*',
					},
					'frontend-js-components-web': {
						'/': '*',
					},
					'frontend-js-metal-web': {
						'incremental-dom': '*',
						'incremental-dom-string': '*',
						'metal': '*',
						'metal-affix': '*',
						'metal-ajax': '*',
						'metal-anim': '*',
						'metal-aop': '*',
						'metal-assertions': '*',
						'metal-clipboard': '*',
						'metal-component': '*',
						'metal-debounce': '*',
						'metal-dom': '*',
						'metal-drag-drop': '*',
						'metal-events': '*',
						'metal-incremental-dom': '*',
						'metal-jsx': '*',
						'metal-key': '*',
						'metal-keyboard-focus': '*',
						'metal-multimap': '*',
						'metal-pagination': '*',
						'metal-path-parser': '*',
						'metal-position': '*',
						'metal-promise': '*',
						'metal-router': '*',
						'metal-scrollspy': '*',
						'metal-soy': '*',
						'metal-soy-bundle': '*',
						'metal-state': '*',
						'metal-storage': '*',
						'metal-structs': '*',
						'metal-throttle': '*',
						'metal-toggler': '*',
						'metal-uri': '*',
						'metal-useragent': '*',
						'metal-web-component': '*',
						'querystring': '*',
						'xss-filters': '*',
					},
					'frontend-js-node-shims': {
						assert: '*',
						buffer: '*',
						domain: '*',
						events: '*',
						os: '*',
						path: '*',
						process: '*',
						string_decoder: '*',
						timers: '*',
						url: '*',
						util: '*',
					},
					'frontend-js-recharts': {
						recharts: '*',
					},
					'frontend-js-spa-web': {
						senna: '*',
					},
					'frontend-js-web': {
						'/': '*',
					},
					'frontend-taglib-chart': {
						'billboard.js': '*',
						'clay-charts': '*',
						'd3': '*',
						'd3-array': '*',
						'd3-axis': '*',
						'd3-brush': '*',
						'd3-chord': '*',
						'd3-collection': '*',
						'd3-color': '*',
						'd3-contour': '*',
						'd3-dispatch': '*',
						'd3-drag': '*',
						'd3-dsv': '*',
						'd3-ease': '*',
						'd3-fetch': '*',
						'd3-force': '*',
						'd3-format': '*',
						'd3-geo': '*',
						'd3-hierarchy': '*',
						'd3-interpolate': '*',
						'd3-path': '*',
						'd3-polygon': '*',
						'd3-quadtree': '*',
						'd3-random': '*',
						'd3-scale': '*',
						'd3-scale-chromatic': '*',
						'd3-selection': '*',
						'd3-shape': '*',
						'd3-time': '*',
						'd3-time-format': '*',
						'd3-timer': '*',
						'd3-transition': '*',
						'd3-voronoi': '*',
						'd3-zoom': '*',
					},
					'frontend-taglib-clay': {
						'/': '*',
						'@clayui/alert': '*',
						'@clayui/autocomplete': '*',
						'@clayui/badge': '*',
						'@clayui/breadcrumb': '*',
						'@clayui/button': '*',
						'@clayui/card': '*',
						'@clayui/charts': '*',
						'@clayui/color-picker': '*',
						'@clayui/css': '*',
						'@clayui/data-provider': '*',
						'@clayui/date-picker': '*',
						'@clayui/drop-down': '*',
						'@clayui/empty-state': '*',
						'@clayui/form': '*',
						'@clayui/icon': '*',
						'@clayui/label': '*',
						'@clayui/layout': '*',
						'@clayui/link': '*',
						'@clayui/list': '*',
						'@clayui/loading-indicator': '*',
						'@clayui/management-toolbar': '*',
						'@clayui/modal': '*',
						'@clayui/multi-select': '*',
						'@clayui/multi-step-nav': '*',
						'@clayui/nav': '*',
						'@clayui/navigation-bar': '*',
						'@clayui/pagination': '*',
						'@clayui/pagination-bar': '*',
						'@clayui/panel': '*',
						'@clayui/popover': '*',
						'@clayui/progress-bar': '*',
						'@clayui/shared': '*',
						'@clayui/slider': '*',
						'@clayui/sticker': '*',
						'@clayui/table': '*',
						'@clayui/tabs': '*',
						'@clayui/time-picker': '*',
						'@clayui/tooltip': '*',
						'@clayui/upper-toolbar': '*',
						'clay': '*',
						'clay-alert': '*',
						'clay-autocomplete': '*',
						'clay-badge': '*',
						'clay-button': '*',
						'clay-card': '*',
						'clay-card-grid': '*',
						'clay-checkbox': '*',
						'clay-collapse': '*',
						'clay-component': '*',
						'clay-data-provider': '*',
						'clay-dataset-display': '*',
						'clay-dropdown': '*',
						'clay-icon': '*',
						'clay-label': '*',
						'clay-link': '*',
						'clay-list': '*',
						'clay-loading-indicator': '*',
						'clay-management-toolbar': '*',
						'clay-modal': '*',
						'clay-multi-select': '*',
						'clay-navigation-bar': '*',
						'clay-pagination': '*',
						'clay-pagination-bar': '*',
						'clay-portal': '*',
						'clay-progress-bar': '*',
						'clay-radio': '*',
						'clay-select': '*',
						'clay-sticker': '*',
						'clay-table': '*',
						'clay-tooltip': '*',
					},
					'item-selector-taglib': {
						'/': '*',
					},
				},
			},
			exclude: {
				'*': ['**/tests/**/*', '**/test/**/*', '**/__tests__/**/*'],
				'@babel/code-frame': true,
				'@babel/generator': true,
				'@babel/helper-annotate-as-pure': true,
				'@babel/helper-builder-binary-assignment-operator-visitor': true,
				'@babel/helper-call-delegate': true,
				'@babel/helper-define-map': true,
				'@babel/helper-explode-assignable-expression': true,
				'@babel/helper-function-name': true,
				'@babel/helper-get-function-arity': true,
				'@babel/helper-hoist-variables': true,
				'@babel/helper-member-expression-to-functions': true,
				'@babel/helper-module-imports': true,
				'@babel/helper-module-transforms': true,
				'@babel/helper-optimise-call-expression': true,
				'@babel/helper-plugin-utils': true,
				'@babel/helper-regex': true,
				'@babel/helper-remap-async-to-generator': true,
				'@babel/helper-replace-supers': true,
				'@babel/helper-simple-access': true,
				'@babel/helper-split-export-declaration': true,
				'@babel/helper-wrap-function': true,
				'@babel/highlight': true,
				'@babel/parser': true,
				'@babel/plugin-proposal-async-generator-functions': true,
				'@babel/plugin-proposal-json-strings': true,
				'@babel/plugin-proposal-object-rest-spread': true,
				'@babel/plugin-proposal-optional-catch-binding': true,
				'@babel/plugin-proposal-unicode-property-regex': true,
				'@babel/plugin-syntax-async-generators': true,
				'@babel/plugin-syntax-json-strings': true,
				'@babel/plugin-syntax-object-rest-spread': true,
				'@babel/plugin-syntax-optional-catch-binding': true,
				'@babel/plugin-transform-arrow-functions': true,
				'@babel/plugin-transform-async-to-generator': true,
				'@babel/plugin-transform-block-scoped-functions': true,
				'@babel/plugin-transform-block-scoping': true,
				'@babel/plugin-transform-classes': true,
				'@babel/plugin-transform-computed-properties': true,
				'@babel/plugin-transform-destructuring': true,
				'@babel/plugin-transform-dotall-regex': true,
				'@babel/plugin-transform-duplicate-keys': true,
				'@babel/plugin-transform-exponentiation-operator': true,
				'@babel/plugin-transform-for-of': true,
				'@babel/plugin-transform-function-name': true,
				'@babel/plugin-transform-literals': true,
				'@babel/plugin-transform-modules-amd': true,
				'@babel/plugin-transform-modules-commonjs': true,
				'@babel/plugin-transform-modules-systemjs': true,
				'@babel/plugin-transform-modules-umd': true,
				'@babel/plugin-transform-named-capturing-groups-regex': true,
				'@babel/plugin-transform-new-target': true,
				'@babel/plugin-transform-object-super': true,
				'@babel/plugin-transform-parameters': true,
				'@babel/plugin-transform-regenerator': true,
				'@babel/plugin-transform-shorthand-properties': true,
				'@babel/plugin-transform-spread': true,
				'@babel/plugin-transform-sticky-regex': true,
				'@babel/plugin-transform-template-literals': true,
				'@babel/plugin-transform-typeof-symbol': true,
				'@babel/plugin-transform-unicode-regex': true,
				'@babel/preset-env': true,
				'@babel/template': true,
				'@babel/traverse': true,
				'@babel/types': true,
				'ansi-styles': true,
				'bootstrap': true,
				'browserslist': true,
				'caniuse-lite': true,
				'chalk': true,
				'commander': true,
				'electron-to-chromium': true,
				'lodash': true,
				'regenerate-unicode-properties': true,
				'regenerator-transform': true,
				'regexp-tree': true,
				'rw': true,
				'semver': true,
				'source-map': true,
			},
		},
		dependencies: [
			'asset-taglib',
			'commerce-frontend-taglib',
			'commerce-product-options-web',
			'data-engine-taglib',
			'dynamic-data-mapping-form-builder',
			'dynamic-data-mapping-form-field-type',
			'dynamic-data-mapping-form-renderer',
		],
	},
	federation: {
		mode: 'disabled',
	},
	global: {
		check: CHECK_AND_FIX_GLOBS,
		fix: CHECK_AND_FIX_GLOBS,
	},
};
