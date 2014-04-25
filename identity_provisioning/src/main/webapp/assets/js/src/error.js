/*global jQuery:true, _:true, console:true */

// Namespace.
var commit = commit || {};

// DOM Ready.
(function ($) {
	'use strict';

	/**
	The commit.error namespace manages the error screen.

	@namespace error
	**/
	commit.error = {
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
		@param {String} exception Error string
		@param {String} fallbackUrl Fallback url
		**/
		buildReturnUrl: function (exception, fallbackUrl) {
			// Define.
			var url, label, primary;

			// Initialize.
			primary = $(this.selectors.primary),
			url = commit.utils.getCookie('idp') || '/IdentityProvisioning';

			// Logic.
			if (exception === 'datalock') {
				//url = '/IdentityProvisioning';
				label = 'Continue';
			} else if (exception === 'other') {
				//url = commit.utils.getCookie('raHome');
				label = 'OK';
			} else {
				//url = commit.utils.getCookie('raReferrer');
				//url = (!url || url === '') ? '/IdentityProvisioning' : url;
				label = 'OK';
			}

			// Apply.
			primary.attr('href', url).html(label);

			// Circumvent data loss messages.
			commit.utils.navHook(primary);
		},

		/**
		Method initializes the error screen.

		@method initialize
		**/
		initialize: function (exception) {
			_.bindAll(this);
			this.utils = commit.utils;
			this.buildReturnUrl(exception);
		}
	};
}(jQuery));