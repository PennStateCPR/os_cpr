/*global jQuery:true, _:true, console:true */

// Namespace.
var commit = commit || {};

// DOM Ready.
(function ($) {
	'use strict';

	/**
	The commit.securityquestions namespace manages the validation
	rules for the security questions screen.

	@namespace securityquestions
	**/
	commit.securityquestions = {
		/**
		Property houses DOM selectors.

		@property selectors
		@type Object
		**/
		selectors: {
			form: '#securityquestions'
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
					securityQuestion1: {
						required: true
					},
					securityQuestion1Answer: {
						required: true
					},
					securityQuestion2: {
						required: true
					},
					securityQuestion2Answer: {
						required: true
					},
					securityQuestion3: {
						required: true
					},
					securityQuestion3Answer: {
						required: true
					}
				},
				messages: {
					securityQuestion1: {
						required: 'Please select a question.'
					},
					securityQuestion1Answer: {
						required: 'Please provide an answer.'
					},
					securityQuestion2: {
						required: 'Please select a question.'
					},
					securityQuestion2Answer: {
						required: 'Please provide an answer.'
					},
					securityQuestion3: {
						required: 'Please select a question.'
					},
					securityQuestion3Answer: {
						required: 'Please provide an answer.'
					}
				},
				formPopover: true,
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
	commit.securityquestions.initialize();
}(jQuery));