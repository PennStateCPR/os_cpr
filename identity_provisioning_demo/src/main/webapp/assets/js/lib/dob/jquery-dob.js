/*global jQuery:true, _:true, console:true */
(function ($) {
	'use strict';

	$.fn.dob = function (options) {
		// Define.
		var dateObj, settings;

		/**
		Property houses reference to calculated date.

		@property dateObj
		@type Object
		**/
		dateObj = {
			month: undefined,
			day: undefined,
			year: undefined
		};

		/**
		Property houses reference to settings.

		@property settings
		@type Object
		**/
		settings = $.extend({
			dobMonth: '#dobMonth',
			dobDay: '#dobDay',
			dobYear: '#dobYear',
			dobLoader: '#dobLoader',
			dob: '#dob'
		}, options);

		/**
		Method appends a leading zero.

		@method addLeadingZero
		**/
		function addLeadingZero(value) {
			value = parseInt(value, 10);
			if (value < 10) {
				value = '0' + value.toString();
			}

			return value;
		}

		/**
		Method removes leading zero.

		@method removeLeadingZero
		**/
		function removeLeadingZero(value) {
			value = parseInt(value, 10);

			return value;
		}

		/**
		Method returns a value for year.

		@method getYearFromSelect
		**/
		function getYearFromSelect(element) {
			var dobYear = element.find(settings.dobYear),
				value = dobYear.val();

			return value;
		}

		/**
		Method returns a value for day.

		@method getDayFromSelect
		**/
		function getDayFromSelect(element) {
			var dobDay = element.find(settings.dobDay),
				value = dobDay.val();

			return value;
		}

		/**
		Method returns a value for month.

		@method getMonthFromSelect
		**/
		function getMonthFromSelect(element) {
			var dobMonth = element.find(settings.dobMonth),
				value = dobMonth.val();

			return value;
		}

		/**
		Method returns a formatted date of birth.

		@method getDateOfBirth
		**/
		function getDateOfBirth(element) {
			// Define.
			var dobMonth, dobDay, dobYear, formatted;

			// Initialize.
			dobMonth = getMonthFromSelect(element);
			dobDay = getDayFromSelect(element);
			dobYear = getYearFromSelect(element);
			formatted = dobMonth + '/' + dobDay + '/' + dobYear;

			return formatted;
		}

		/**
		Method builds out day select box.

		@method buildDays
		**/
		function buildDays (element) {
			// Define.
			var dobDay, month, year, days, day, template, value;

			// Initialize.
			dobDay = element.find(settings.dobDay);
			month = (removeLeadingZero(getMonthFromSelect(element)));
			year = getYearFromSelect(element);
			days = new Date(year, month, 0).getDate();
			day = getDayFromSelect(element) || dateObj.day;
			day = removeLeadingZero(day);
			template = '';
			value = 0;

			// Iterate over days.
			for (var i = 1; i <= days; i++) {
				value = addLeadingZero(i);
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
			day = addLeadingZero(day);

			// Update selection.
			dobDay.val(day);

			// Reveal select.
			dobDay.css({'visibility': 'visible'});
		}

		/**
		Method builds out year select box.

		@method buildYears
		**/
		function buildYears(element) {
			// Define.
			var dobYear = element.find(settings.dobYear),
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
			dobYear.val(dateObj.year);

			// Reveal select.
			dobYear.css({'visibility': 'visible'});
		}

		/**
		Method builds out month select box.

		@method buildMonths
		**/
		function buildMonths(element) {
			// Define.
			var months, dob, dobMonth, template, value;

			// Initialize.
			months = ['Jan.', 'Feb.', 'Mar.', 'Apr.', 'May', 'Jun.', 'Jul.', 'Aug.', 'Sept.', 'Oct.', 'Nov.', 'Dec.'];
			dobMonth = element.find(settings.dobMonth);
			template = '';
			value = 0;

			// Iterate over months.
			_.each(months, _.bind(function (month, idx) {
				value = addLeadingZero((idx + 1));
				template = template + '<option value="' + value + '">' + month + '</option>';
			}));

			// Append.
			dobMonth.html(template);

			// Update selection.
			dobMonth.val(dateObj.month);

			// Reveal select.
			dobMonth.css({'visibility': 'visible'});
		}

		/**
		Method builds a date object based upon an
		existing date, or today's date, when an
		existing date cannot be found.

		@method buildDate
		**/
		function buildDate(element) {
			// Define.
			var dob, date, month, year, day;

			// Initialize.
			dob = element.find(settings.dob);

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
			dateObj.month = addLeadingZero(month);
			dateObj.day = addLeadingZero(day);
			dateObj.year = year;
		}

		/**
		Method builds all the select dropdowns
		required for the DOB.

		@method buildSelects
		**/
		function buildSelects(element) {
			// Define & initialize.
			var dob = element.find(settings.dob),
				loader = element.find(settings.dobLoader);

			// Build out selects.
			buildDate(element);
			buildMonths(element);
			buildYears(element);
			buildDays(element);

			// Update input field.
			dob.val(getDateOfBirth(element));

			// Reveal DOB.
			loader.fadeOut();
		}

		/**
		Method initializes listeners.

		@method initializeListeners
		**/
		function initializeListeners(element) {
			// Define.
			var dob = element.find(settings.dob),
				control = element,
				select = control.find('select');

			// Change listener.
			select.on('change', _.bind(function (evt) {
				// Define.
				var targetId = ('#' + $(evt.target).attr('id'));

				// Re-build the days when the month or year
				// drop-down is changed.
				if (targetId !== settings.dobDay) {
					buildDays(element);
				}

				// Update dob input.
				dob.val(getDateOfBirth(element));
			}, this));
		}

		return this.each(function (idx, elem) {
			var element = $(elem).addClass('dob');
			buildSelects(element);
			initializeListeners(element);
		});
	};

}(jQuery));