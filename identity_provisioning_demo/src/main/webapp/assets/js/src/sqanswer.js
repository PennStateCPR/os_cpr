/*global jQuery:true, _:true, console:true */

// Namespace.
var commit = commit || {};

// DOM Ready.
(function ($) {
	'use strict';

	/**
	The commit.sqanswer namespace manages the validation
	rules for the security question & answer screens.

	@namespace sqanswer
	**/
	commit.sqanswer = {
		/**
		Property houses DOM selectors.

		@property selectors
		@type Object
		**/
		selectors: {
			form: '#sqanswer'
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
					securityAnswer: {
						required: true
					}
				},
				messages: {
					securityAnswer: {
						required: 'Please provide an answer.'
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
	commit.sqanswer.initialize();
}(jQuery));