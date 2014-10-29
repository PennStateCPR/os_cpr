/*global jQuery:true, _:true, console:true */

// Namespace.
var commit = commit || {};

// DOM Ready.
(function ($) {
	'use strict';

	/**
	The commit.legalname namespace manages the validation
	rules for the legalname screen.

	@namespace legalname
	**/
	commit.legalname = {
		/**
		Property houses DOM selectors.

		@property selectors
		@type Object
		**/
		selectors: {
			form: '#legalname'
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
			this.validator = new commit.validator({
				selectors: selectors,
				rules: {
					firstName: {
						required: true
					},
					lastName: {
						required: true
					}
				},
				messages: {
					firstName: {
						required: 'Please enter your first name.'
					},
					lastName: {
						required: 'Please enter your last name.'
					}
				},
				formPopover: true,
				focus: true,
				verify: true
			});
		},

		/**
		Method initializes the screen.

		@method initialize
		**/
		initialize: function () {
			_.bindAll(this);
			this.utils = commit.utils;
			this.validate();
		}
	};

	// Execute validation.
	commit.legalname.initialize();
}(jQuery));