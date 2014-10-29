/*global jQuery:true, _:true, console:true */

// Namespace.
var commit = commit || {};

// DOM Ready.
(function ($) {
	'use strict';

	/**
	The Validator class wraps the jquery.validation plugin.

	@class Validator
	@constructor
	**/
	commit.validator = function (options) {
		var self = {
			/**
			Property houses class defaults.

			@property defaults
			@type Object
			**/
			defaults: {
				selectors: {
					controls: '.controls',
					controlGroup: '.control-group',
					hidden: '#btnsubmit',
					helpInline: '.help-inline',
					formPopover: '#formPopover',
					formPopoverContent: '#formPopoverContent',
					formModal: '#formModal',
					formModalYes: '#formModalYes',
					formModalNo: '#formModalNo',
					primary: '#continueButton',
					back: '#backButton'
				},
				rules: {},
				messages: {},
				formPopover: false,
				formModal: false,
				focus: false,
				verify: false
			},

			/**
			Property houses class options.

			@property options
			@type Object
			**/
			options: {},

			/**
			Property houses DOM selectors.

			@property selectors
			@type Object
			**/
			selectors: {},

			/**
			Property houses utility methods.

			@property utils
			@type Object
			**/
			utils: {},

			/**
			Property houses dialog instance.

			@property dialog
			@type Object
			**/
			dialog: {},

			/**
			Property houses validation rules.

			@property rules
			@type Object
			**/
			rules: {},

			/**
			Property houses validation messages.

			@property messages
			@type Object
			**/
			messages: {},

			/**
			Method merges class defaults with passed in options.

			@method mergeOptions
			**/
			mergeOptions: function (options) {
				this.options = $.extend(true, {}, this.defaults, options);

				// Selectors property.
				if (_.isEmpty(this.options.selectors)) {
					throw new Error('Unexpected value. Object cannot be empty.');
				}

				// Highlight property.
				if (this.options.highlight && typeof this.options.highlight !== 'function') {
					throw new Error('Unexpected value. Expected a function instead of ' + typeof this.options.highlight + '.');
				}

				// Unhighlight property.
				if (this.options.unhighlight && typeof this.options.unhighlight !== 'function') {
					throw new Error('Unexpected value. Expected a function instead of ' + typeof this.options.unhighlight + '.');
				}

				// Submit property.
				if (this.options.submit && typeof this.options.submit !== 'function') {
					throw new Error('Unexpected value. Expected a function instead of ' + typeof this.options.submit + '.');
				}

				// Form Popover property.
				if (this.options.formPopover && typeof this.options.formPopover !== 'boolean') {
					throw new Error('Unexpected value. Expected a boolean instead of ' + typeof this.options.formPopover + '.');
				}

				// Form Modal property.
				if (this.options.formModal && typeof this.options.formModal !== 'boolean') {
					throw new Error('Unexpected value. Expected a boolean instead of ' + typeof this.options.formModal + '.');
				}

				// Focus property.
				if (this.options.focus && typeof this.options.focus !== 'boolean') {
					throw new Error('Unexpected value. Expected a boolean instead of ' + typeof this.options.focus + '.');
				}

				// Verify property.
				if (this.options.verify && typeof this.options.verify !== 'boolean') {
					throw new Error('Unexpected value. Expected a boolean instead of ' + typeof this.options.verify + '.');
				}

				this.selectors = this.options.selectors;
				this.rules = this.options.rules;
				this.messages = this.options.messages;
			},

			/**
			Method houses the default highlight implementation.

			@method highlight
			@param {Object} element DOM form control
			**/
			highlight: function (element) {
				$(element).closest(this.selectors.controlGroup).addClass('error');
			},

			/**
			Method houses the default unhighlight implementation.

			@method unhighlight
			@param {Object} element DOM form control
			**/
			unhighlight: function (element) {
				$(element).closest(this.selectors.controlGroup).removeClass('error');
			},

			/**
			Method leverages and configures the jquery-validate plugin
			to provide client-side validation.

			@method validate
			**/
			validate: function () {
				// Define & Initialize.
				var form = $(this.selectors.form),
					back = form.find(this.selectors.back);

				// Set form cookie.
				commit.utils.setCookie('referrer', form.attr('name'));

				// Circumvent data loss messages.
				commit.utils.navHook(back);

				// Configure Plugin.
				form.validate({
					errorElement: 'span',
					errorClass: 'help-block',
					rules: this.rules,
					messages: this.messages,
					highlight: _.bind(function (element) {
						var highlight = (this.options.hasOwnProperty('highlight')) ? this.options.highlight(element) : this.highlight(element);
					}, this),
					unhighlight: _.bind(function (element) {
						var unhighlight = (this.options.hasOwnProperty('unhighlight')) ? this.options.unhighlight(element) : this.unhighlight(element);
					}, this),
					errorPlacement: _.bind(function (error, element) {
						var controls = $(element).closest(this.selectors.controls);
						controls.append(error);
					}, this),
					submitHandler: _.bind(function (form) {
						// Define.
						var hidden, submission;

						// Set hidden value.
						hidden = $(form).find(this.selectors.hidden);
						hidden.attr('value', '.');

						// Submission.
						if (this.options.formModal) {
							this.dialog.modal('show');
						} else {
							// Circumvent data loss messages.
							commit.navHook = true;
							submission = (this.options.submit) ? this.options.submit(form) : form.submit();
						}
					}, this)
				});
			},

			/**
			Method initializes generic form modal.

			@method initializeFormModal
			**/
			initializeFormModal: function () {
				var confirm, form, primary, submission;

				// Execute.
				if (this.options.formModal) {
					this.dialog = $(this.selectors.formModal);
					this.dialog.modal({show: false});
					form = $(this.selectors.form);
					primary = this.dialog.find(this.selectors.formModalYes);

					// Listen on the confirm click event.
					primary.on('click', _.bind(function (e) {
						e.preventDefault();
						commit.navHook = true;
						submission = (this.options.submit) ? this.options.submit(form[0]) : form[0].submit();
					}, this));
				}
			},

			/**
			Method initializes generic form popover.

			@method initializeFormPopover
			**/
			initializeFormPopover: function () {
				if (this.options.formPopover) {
					// Define.
					var formPopover, formPopoverContent;

					// Initialize.
					formPopover = $(this.selectors.formPopover);
					formPopoverContent = $(this.selectors.formPopoverContent).html();

					// Popover.
					this.utils.popover(formPopover, formPopoverContent, {placement: 'bottom'});
				}
			},

			/**
			Method collects all forms on the page and
			focuses the first element in the returned
			array.

			@method focus
			**/
			forceFocus: function () {
				if (this.options.focus) {
					var form = $(this.selectors.form),
						inputs = form.find(':input');
					if (inputs[0]) {
						inputs[0].focus();
					}
				}
			},

			/**
			Method checks for the verify cookie.
			Toggles the primary button text when
			the verify cookie is present.

			@method verify
			**/
			verify: function () {
				if (this.options.verify) {
					// Define & Initialize.
					var form = $(this.selectors.form),
						primary = form.find(this.selectors.primary),
						back = form.find(this.selectors.back),
						condition = (commit.utils.getCookie('verification') === 'true') ? true : false;

					if (condition) {
						primary.html('Verify');
						back.hide();
					}
				}
			},

			/**
			Method initializes validation logic.

			@method initialize
			@param {Object} options Plugin configuration
			**/
			initialize: function (options) {
				_.bindAll(this);
				this.utils = commit.utils;
				this.mergeOptions(options);
				this.initializeFormPopover();
				this.initializeFormModal();
				this.validate();
				this.forceFocus();
				this.verify();
			}
		};

		self.initialize(options);
		return self;
	};
}(jQuery));