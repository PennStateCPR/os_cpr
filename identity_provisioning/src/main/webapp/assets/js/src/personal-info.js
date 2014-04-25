/*global jQuery:true, _:true, console:true */

// Namespace.
var commit = commit || {};

// DOM Ready.
(function ($) {
	'use strict';

	/**
	The commit.personalinfo namespace manages the validation
	rules for the personal information screen.

	@namespace personalinfo
	**/
	commit.personalinfo = {
		/**
		Property houses DOM selectors.

		@property selectors
		@type Object
		**/
		selectors: {
			form: '#personalinfo',
			month: '#birthMonth',
			day: '#birthDay',
			year: '#birthYear',
			controlGroup: '.control-group',
			valid: '.valid'
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
					birthMonth: {
						required: true,
						digits: true,
						maxlength: 2,
						minlength: 2
					},
					birthDay: {
						required: true,
						digits: true,
						maxlength: 2,
						minlength: 2
					},
					birthYear: {
						required: true,
						digits: true,
						maxlength: 4,
						minlength: 4
					}
				},
				messages: {
					birthMonth: 'Birth month is required.',
					birthDay: 'Birth day is required.',
					birthYear: 'Birth year is required.'
				},
				formPopover: true,
				focus: true,
				verify: true,
				highlight: _.bind(function (element) {
					var group = $(element).closest(this.selectors.controlGroup).addClass('error'),
						input = $(element).removeClass('valid');
				}, this),
				unhighlight: _.bind(function (element) {
					var group = $(element).closest(this.selectors.controlGroup),
						input = $(element).addClass('valid'),
						length = group.find(this.selectors.valid).length;

					if (length >= 3) {
						group.removeClass('error');
					}
				}, this)
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
	commit.personalinfo.initialize();
}(jQuery));