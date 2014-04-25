/*global jQuery:true, _:true, console:true */

// Namespace.
var commit = commit || {};

// DOM Ready.
(function ($) {
	'use strict';

	/**
	The commit.verifyinfo namespace manages the validation
	rules for the verify screen.

	@namespace verifyinfo
	**/
	commit.verifyinfo = {
		/**
		Property houses DOM selectors.

		@property selectors
		@type Object
		**/
		selectors: {
			form: '#verifyinfo',
			primary: '#continueButton',
			links: '.verification-link'
		},

		/**
		Property houses utility methods.

		@property utils
		@type Object
		**/
		utils: {},

		/**
		Property houses validator instance.

		@property validator
		@type Object
		**/
		validator: {},

		/**
		Method leverages the validator class to configure
		validation rules and messages.

		@method validate
		**/
		validate: function () {
			// Define & Initialize.
			var selectors = this.selectors;
			commit.utils.setCookie('verification', true);
			this.validator = new commit.validator({
				selectors: selectors,
				formPopover: true,
				submit: _.bind(function (form) {
					commit.utils.setCookie('verification', false);
					form.submit();
				}, this)
			});
		},

		/**
		Method initializes navigation hooks for
		verification links.

		@method initializeNavigationHooks
		**/
		initializeNavigationHooks: function () {
			var links = $(this.selectors.links);
			_.each(links, function (link) {
				commit.utils.navHook($(link));
			}, this);
		},

		/**
		Method initializes the screen.

		@method initialize
		**/
		initialize: function () {
			_.bindAll(this);
			this.utils = commit.utils;
			this.initializeNavigationHooks();
			this.validate();
		}
	};

	// Execute validation.
	commit.verifyinfo.initialize();
}(jQuery));