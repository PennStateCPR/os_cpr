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
			dobMonth: '#dobMonth',
			dobDay: '#dobDay',
			dobYear: '#dobYear',
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
		Property houses reference to date.

		@property date
		@type Object
		**/
		date: {
			month: undefined,
			day: undefined,
			year: undefined
		},

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
		Method appends a leading zero.

		@method addLeadingZero
		**/
		addLeadingZero: function (value) {
			value = parseInt(value, 10);
			if (value < 10) {
				value = '0' + value.toString();
			}

			return value;
		},

		/**
		Method removes leading zero.

		@method removeLeadingZero
		**/
		removeLeadingZero: function (value) {
			value = parseInt(value, 10);

			return value;
		},

		/**
		Method returns a value for year.

		@method getYearFromSelect
		**/
		getYearFromSelect: function () {
			var dobYear = $(this.selectors.dobYear),
				value = dobYear.val();

			return value;
		},

		/**
		Method returns a value for day.

		@method getDayFromSelect
		**/
		getDayFromSelect: function () {
			var dobDay = $(this.selectors.dobDay),
				value = dobDay.val();

			return value;
		},

		/**
		Method returns a value for month.

		@method getMonthFromSelect
		**/
		getMonthFromSelect: function () {
			var dobMonth = $(this.selectors.dobMonth),
				value = dobMonth.val();

			return value;
		},

		/**
		Method returns a formatted date of birth.

		@method getDateOfBirth
		**/
		getDateOfBirth: function () {
			// Define.
			var dobMonth, dobDay, dobYear, formatted;

			// Initialize.
			dobMonth = this.getMonthFromSelect();
			dobDay = this.getDayFromSelect();
			dobYear = this.getYearFromSelect();
			formatted = dobMonth + '/' + dobDay + '/' + dobYear;

			return formatted;
		},

		/**
		Method builds out month select box.

		@method buildMonths
		**/
		buildMonths: function () {
			// Define.
			var months, dob, dobMonth, template, value;

			// Initialize.
			months = ['Jan.', 'Feb.', 'Mar.', 'Apr.', 'May', 'Jun.', 'Jul.', 'Aug.', 'Sept.', 'Oct.', 'Nov.', 'Dec.'];
			dobMonth = $(this.selectors.dobMonth);
			template = '';
			value = 0;

			// Iterate over months.
			_.each(months, _.bind(function (month, idx) {
				value = this.addLeadingZero((idx + 1));
				template = template + '<option value="' + value + '">' + month + '</option>';
			}, this));

			// Append.
			dobMonth.html(template);

			// Update selection.
			dobMonth.val(this.date.month);
		},

		/**
		Method builds out day select box.

		@method buildDays
		**/
		buildDays: function () {
			// Define.
			var dobDay, month, year, days, day, template, value;

			// Initialize.
			dobDay = $(this.selectors.dobDay);
			month = (this.removeLeadingZero(this.getMonthFromSelect()));
			year = this.getYearFromSelect();
			days = new Date(year, month, 0).getDate();
			day = this.getDayFromSelect() || this.date.day;
			day = this.removeLeadingZero(day);
			template = '';
			value = 0;

			// Iterate over days.
			for (var i = 1; i <= days; i++) {
				value = this.addLeadingZero(i);
				template = template + '<option value="' + value + '">' + value + '</option>';
			}

			// Append.
			dobDay.html(template);

			// When the current day exceeds the
			// total number of days in a month
			// set day to the total number of days.
			if (day >= days) {
				day = days;
			}
			day = this.addLeadingZero(day);

			// Update selection.
			dobDay.val(day);
		},

		/**
		Method builds out year select box.

		@method buildYears
		**/
		buildYears: function () {
			// Define.
			var dobYear = $(this.selectors.dobYear),
				date = new Date(),
				yearEnd = ((date.getFullYear()) - 1),
				yearStart = (yearEnd - 100),
				template = '';

			// Iterate over days.
			for (var i = yearStart; i <= yearEnd; i++) {
				template = template + '<option value="' + i + '">' + i + '</option>';
			}

			// Append.
			dobYear.html(template);

			// Update selection.
			dobYear.val(this.date.year);
		},

		/**
		Method builds a date object based upon an
		existing date, or today's date, when an
		existing date cannot be found.

		@method buildDate
		**/
		buildDate: function () {
			// Define.
			var dob, date, month, year, day;

			// Initialize.
			dob = $(this.selectors.dob);

			// Parse date depending upon the value of DOB.
			if (_.isEmpty(dob.val())) {
				date = new Date();
				month = (date.getMonth() + 1);
				year = (date.getFullYear() - 1);
				day = date.getDate();
			} else {
				date = dob.val().split('/');
				month = date[0];
				day = date[1];
				year = date[2];
			}

			// Update date reference.
			this.date.month = this.addLeadingZero(month);
			this.date.day = this.addLeadingZero(day);
			this.date.year = year;
		},

		/**
		Method builds all the select dropdowns
		required for the DOB.

		@method buildDate
		**/
		buildSelects: function () {
			// Define & initialize.
			var dob = $(this.selectors.dob);

			// Build out selects.
			this.buildDate();
			this.buildMonths();
			this.buildYears();
			this.buildDays();

			// Update input field.
			dob.val(this.getDateOfBirth());
		},

		/**
		Method initializes the Datepicker.

		@method initializeDatepicker
		**/
		initializeDatepicker: function () {
			this.buildSelects();
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
				// Define.
				var targetId = ('#' + $(evt.target).attr('id'));

				// Re-build the days when the month or year
				// drop-down is changed.
				if (targetId !== this.selectors.dobDay) {
					this.buildDays();
				}

				// Update dob input.
				dob.val(this.getDateOfBirth());
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
}(jQuery, _));