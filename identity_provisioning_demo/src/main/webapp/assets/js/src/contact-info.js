/*global jQuery:true, _:true, console:true */

// Namespace.
var commit = commit || {};

// DOM Ready.
(function ($) {
	'use strict';

	/**
	The commit.contactinfo namespace manages the validation
	rules for the contact screen.

	@namespace contactinfo
	**/
	commit.contactinfo = {
		/**
		Property houses DOM selectors.

		@property selectors
		@type Object
		**/
		selectors: {
			form: '#contactinfo',
			dobPopover: '#dobPopover',
			dobPopoverContent: '#dobPopoverContent',
			email: '#email',
			primaryEmail: '#primaryEmail',
			emailPopover: '#emailPopover',
			emailPopoverContent: '#emailPopoverContent',
			phonePopover: '#phonePopover',
			phonePopoverContent: '#phonePopoverContent',
			international: '#internationalNumber'
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
		Property houses phone validation rules.

		@property phone
		@type Object
		**/
		phone: {
			us: {
				required: true,
				phoneCommit: true
			},
			international: {
				required: true,
				phoneIntCommit: true
			}
		},

		/**
		Method returns phone validation rules depending upon the
		state of the international checkbox.

		@method getPhoneType
		@return {Object} phone Object containing phone validation rules
		**/
		getPhoneType: function () {
			var international = $(this.selectors.international);
			return (international.is(':checked')) ? this.phone.international : this.phone.us;
		},

		/**
		Method leverages the validator class to configure
		validation rules and messages.

		@method validate
		**/
		validate: function () {
			// Define & initialize.
			var selectors = this.selectors;
			this.validator = new commit.validator({
				selectors: selectors,
				rules: {
					dob: {
						required: true,
						date: true
					},
					email: {
						required: true,
						email: true
					},
					city: {
						required: true,
					},
					phoneNumber: {
						required: true,
						phoneCommit: true
					}
				},
				messages: {
					dob: {
						required: 'Please enter your date of birth.',
						date: 'Please enter a valid date of birth'
					},
					email: {
						required: 'Please enter your email.'
					},
					phoneNumber: {
						required: 'Please enter your phone number.'
					},
					city: {
						required: 'Please enter your city.'
					}
				},
//				formModal: true,
				formPopover: true,
				focus: true,
				verify: true
			});
		},

		/**
		Method is triggered when the international checkbox
		is selected. This method reaches into the validator
		plugin and updates the phone rules on the fly. This
		type of operation can be considered a bit of a hack.
		Currently there is no way to update validation rules
		on the fly with the validation plugin. The code below
		accesses the rules object through the data() method.

		This is a temporary solution. If more complex validation
		patterns are necessary, we should look at extending the
		validation pluign with on the fly updating of valiadation
		rules and properties.

		@method onInternationalSelect
		@param {Object} element Reference to select control
		**/
		onInternationalSelect: function (element) {
			// Define.
			var selectors, form, validator, phoneType;

			// Initialize.
			selectors = this.selectors;
			form = $(selectors.form);
			validator = form.data.validator;
			phoneType = this.getPhoneType();

			// Update validator with phone rules.
			if (form.data().hasOwnProperty('validator')) {
				form.data().validator.settings.rules.phoneNumber = phoneType;
			}
		},

		/**
		Method initializes view-specific popovers.

		@method initializeViewPopovers
		**/
		initializeViewPopovers: function () {
			// Define.
			var emailPopover, emailPopoverContent,
				phonePopover, phonePopoverContent,
				dobPopover, dobPopoverContent;

			// Initialize.
			emailPopover = $(this.selectors.emailPopover);
			emailPopoverContent = $(this.selectors.emailPopoverContent).html();
			phonePopover = $(this.selectors.phonePopover);
			phonePopoverContent = $(this.selectors.phonePopoverContent).html();
			dobPopover = $(this.selectors.dobPopover);
			dobPopoverContent = $(this.selectors.dobPopoverContent).html();

			// Email popover.
			this.utils.popover(emailPopover, emailPopoverContent);

			// DOB popover.
			this.utils.popover(dobPopover, dobPopoverContent);

			// Phone popover.
			this.utils.popover(phonePopover, phonePopoverContent);
		},

		/**
		Method attaches view-specific listeners onto
		the generic form modal.

		@method attachModalListeners
		**/
		attachModalListeners: function () {
			// Define.
			var email, primaryEmail, dialog;

			// Initialize.
			email = $(this.selectors.email);
			primaryEmail = $(this.selectors.primaryEmail);
			dialog = this.validator.dialog;

			// Listen on the modal show event.
			dialog.on('show', _.bind(function (e) {
				primaryEmail.html($.trim(email.val()));
			}, this));

			// Listen on modal hidden event.
			dialog.on('hidden', _.bind(function (e) {
				primaryEmail.html('');
			}, this));
		},

		/**
		Method initializes the screen.

		@method initialize
		**/
		initialize: function () {
			_.bindAll(this);
			this.utils = commit.utils;
			this.initializeViewPopovers();
			this.validate();
//			this.attachModalListeners();
		}
	};

	// Execute validation.
	commit.contactinfo.initialize();
}(jQuery, _));