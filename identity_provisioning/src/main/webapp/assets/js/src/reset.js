/*global jQuery:true, _:true, console:true */

// Namespace.
var commit = commit || {};

// DOM Ready.
(function ($) {
	'use strict';

	/**
	The commit.reset namespace manages the validation
	rules for the password reset and forgot user Id screens.

	@namespace reset
	**/
	commit.reset = {
		/**
		Property houses DOM selectors.

		@property selectors
		@type Object
		**/
		selectors: {
			form: '#reset'
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
					commitId: {
						required: true
					},
					firstName: {
						required: true
					},
					lastName: {
						required: true
					},
					city: {
						required: true
					},
					dob: {
						required: true,
						date: true
					}
				},
				messages: {
					commitId: {
						required: 'Please enter your CommIT ID.'
					},
					firstName: {
						required: 'Please enter your first name.'
					},
					lastName: {
						required: 'Please enter your last name.'
					},
					city: {
						required: 'Please enter your city.'
					},
					dob: {
						required: 'Please enter your date of birth.',
						date: 'Please enter a valid date of birth'
					}
				},
				focus: true
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
	commit.reset.initialize();
}(jQuery));