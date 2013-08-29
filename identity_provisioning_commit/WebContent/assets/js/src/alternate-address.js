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
			zip: '#postalCode',
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
					},
					state: {
						required: true
					},
					province: {
						required: true
					},
					postalCode: {
						required: true,
						postalCodeCommit: true
					}
				},
				messages: {
					addressLine1: {
						required: 'Please enter your address.'
					},
					city: {
						required: 'Please enter your city.'
					},
					state: {
						required: 'Please select a state.'
					},
					province: {
						required: 'Please enter a province.'
					},
					postalCode: {
						required: 'Please enter your zip code.'
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
			} else if (country === 'usa') {
				if (options.clear) {
					this.utils.resetSelect(state);
					this.utils.resetInput(province);
				}
				stateGroup.show();
			} else {
				provinceGroup.hide();
				stateGroup.hide();
			}
		},

		/**
		Method toggles the validation on the zip code based upon
		the country select box.

		@method togglePostalValidation
		**/
		togglePostalValidation: function (options) {
			// Define.
			var country, zip, control;

			// Initialize.
			options = (options && options.hasOwnProperty('clear')) ? options : {clear: false};
			country = this.utils.lowerCase($.trim($(this.selectors.country).val()));
			zip = $(this.selectors.zip);
			control = zip.closest(this.selectors.controlGroup);

			// Add & remove rules based upon the value of the country.
			if (country === 'usa') {
				if (options.clear) {
					this.utils.resetInput(zip);
				}
				zip.rules('add', {
					required: true,
					postalCodeCommit: true
				});
			} else {
				if (options.clear) {
					this.utils.resetInput(zip);
				}
				zip.rules('remove');
				control.removeClass('error');
				control.find('.help-block').hide();
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
			this.togglePostalValidation({clear: true});
		},

		/**
		Method initializes the screen.

		@method initialize
		**/
		initialize: function () {
			_.bindAll(this);
			this.utils = commit.utils;
			this.validate();
			this.toggleCountryControls();
			this.togglePostalValidation();
		}
	};

	// Execute validation.
	commit.alternateaddress.initialize();
}(jQuery));