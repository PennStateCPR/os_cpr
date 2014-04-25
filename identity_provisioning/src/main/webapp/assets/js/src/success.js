/*global jQuery:true, _:true, console:true */

// Namespace.
var commit = commit || {};

// DOM Ready.
(function ($) {
	'use strict';

	/**
	The commit.success namespace manages the success screen.

	@namespace success
	**/
	commit.success = {
		/**
		Property houses DOM selectors.

		@property selectors
		@type Object
		**/
		selectors: {
			primary: '#continueButton'
		},

		/**
		Property houses utility methods.

		@property utils
		@type Object
		**/
		utils: {},

		/**
		Method initializes the screen.

		@method buildReturnUrl
		@param {String} url
		**/
		buildReturnUrl: function (url) {
			// Define.
			var label, primary;

			// Initialize.
			primary = $(this.selectors.primary);
			label = 'Return';

			// Apply.
			primary.attr('href', url).html(label);

			// Circumvent data loss messages.
			commit.utils.navHook(primary);
		},

		/**
		Method initializes the screen.

		@method initialize
		**/
		initialize: function (url) {
			_.bindAll(this);
			this.utils = commit.utils;
			this.buildReturnUrl(url);
		}
	};
}(jQuery));