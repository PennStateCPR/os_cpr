/*global jQuery:true, _:true, console:true */

// Namespace.
var commit = commit || {};

// DOM Ready.
(function ($) {
	'use strict';

	/**
	The commit.alternateaddress namespace manages the validation
	rules for the alternate address screen.

	@namespace alternateaddress
	**/
	commit.alternateaddress = {
		/**
		Property houses DOM selectors.

		@property selectors
		@type Object
		**/
		selectors: {
			form: '#alternate',
			country: '#country',
			state: '#state',
			province: '#province',
			controlGroup: '.control-group',
			helpInline: '.help-inline'
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
					addressLine1: {
						required: true
					},
					city: {
						required: true
					}
				},
				messages: {
					addressLine1: {
						required: 'Please enter your address.'
					},
					city: {
						required: 'Please enter your city.'
					}
				},
				formPopover: true,
				focus: true,
				verify: true
			});
		},

		/**
		Method toggles the state and province controls based upon
		the country select box.

		@method toggleCountryControls
		@param {Object} options Object hash of options
		**/
		toggleCountryControls: function (options) {
			// Define.
			var country, state, province, stateGroup, provinceGroup;

			// Initialize.
			options = (options && options.hasOwnProperty('clear')) ? options : {clear: false};
			country = this.utils.lowerCase($.trim($(this.selectors.country).val()));
			state = $(this.selectors.state);
			province = $(this.selectors.province);
			stateGroup = state.closest(this.selectors.controlGroup);
			provinceGroup = province.closest(this.selectors.controlGroup);

			// Hide groups.
			stateGroup.hide();
			provinceGroup.hide();

			// Show & reset groups.
			if (country === 'can') {
				if (options.clear) {
					this.utils.resetSelect(state);
					this.utils.resetInput(province);
				}
				provinceGroup.show();
			} else {
				if (options.clear) {
					this.utils.resetSelect(state);
					this.utils.resetInput(province);
				}
				stateGroup.show();
			}
		},

		/**
		Method is triggered when the country select control changes.
		Method updates the state of the state select control, which
		is conditionally required.

		@method onCountryChange
		@param {Object} element DOM select node
		**/
		onCountryChange: function (element) {
			this.toggleCountryControls({clear: true});
		},

		/**
		Method initializes the screen.

		@method initialize
		**/
		initialize: function () {
			_.bindAll(this);
			this.utils = commit.utils;
			this.toggleCountryControls();
			this.validate();
		}
	};

	// Execute validation.
	commit.alternateaddress.initialize();
}(jQuery));