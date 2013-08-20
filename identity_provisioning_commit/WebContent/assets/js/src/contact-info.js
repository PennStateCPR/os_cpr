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
			dobControl: '#dobControl',
			dob: '#dob',
			dobMonth: '#dob_dateLists_month_list',
			dobDay: '#dob_dateLists_day_list',
			dobYear: '#dob_dateLists_year_list',
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
					}
				},
				formModal: true,
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
		Method sets the year on the year select dropdown.

		@method setYearOnSelect
		@param {Number} year The year to select
		**/
		setYearOnSelect: function (year) {
			var dobYear = $(this.selectors.dobYear);
			dobYear.val(year);
		},

		/**
		Method returns the year from the year select dropdown.

		@method getYearFromSelect
		**/
		getYearFromSelect: function () {
			var dobYear = $(this.selectors.dobYear);
			return parseInt(dobYear.val(), 10);
		},

		/**
		Method returns a formatted date of birth.

		@method getDateOfBirth
		**/
		getDateOfBirth: function () {
			// Define.
			var dobMonth, dobDay, dobYear;

			// Initialize.
			dobMonth = $(this.selectors.dobMonth);
			dobDay = $(this.selectors.dobDay);
			dobYear = $(this.selectors.dobYear);

			return dobMonth.val() + '/' + dobDay.val() + '/' + dobYear.val();
		},

		/**
		Method initializes jQuery Datepicker.

		@method initializeDatepicker
		**/
		initializeDatepicker: function () {
			// Define.
			var dob = $(this.selectors.dob),
				control = $(this.selectors.dobControl),
				date = new Date(),
				month = ((date.getMonth()) + 1),
				day = date.getDate(),
				yearEnd = ((date.getFullYear()) - 1),
				yearStart = (yearEnd - 150);

			// Initialize dropdown.
			dob.dateDropDowns({
				dateFormat: 'mm/dd/yyyy',
				monthNames: ['Jan.', 'Feb.', 'Mar.', 'Apr.', 'May', 'Jun.', 'Jul.', 'Aug.', 'Sept.', 'Oct.', 'Nov.', 'Dec.'],
				yearStart: yearStart,
				yearEnd: yearEnd
			});

			// Set initial date only when none exists.
			if (this.getYearFromSelect() === yearStart) {
				this.setYearOnSelect(yearEnd);
			}

			// Update DOB value.
			dob.val(this.getDateOfBirth());

			// Reveal control.
			control.show();
		},

		/**
		Method initializes Datepicker listeners.

		@method initializeDatepickerListeners
		**/
		initializeDatepickerListeners: function () {
			// Define.
			var dob = $(this.selectors.dob),
				control = $(this.selectors.dobControl),
				select = control.find('select');

			// Change listener.
			select.on('change', _.bind(function (evt) {
				dob.val(this.getDateOfBirth());
				console.log(dob.val());
			}, this));
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
			this.initializeDatepicker();
			this.initializeDatepickerListeners();
			this.initializeViewPopovers();
			this.validate();
			this.attachModalListeners();
		}
	};

	// Execute validation.
	commit.contactinfo.initialize();
}(jQuery));