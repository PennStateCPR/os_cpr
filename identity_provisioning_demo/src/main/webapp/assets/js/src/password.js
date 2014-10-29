/*global jQuery:true, _:true, console:true */

// Namespace.
var commit = commit || {};

// DOM Ready.
(function ($) {
	'use strict';

	/**
	The commit.password namespace manages the validation
	rules for the password screen.

	@namespace password
	**/
	commit.password = {
		/**
		Property houses DOM selectors.

		@property selectors
		@type Object
		**/
		selectors: {
			form: '#setpassword',
			password: '#password',
			passwordPopover: '#passwordPopover',
			passwordPopoverContent: '#passwordPopoverContent',
			progress: '#progress',
			progressBar: '#progressBar',
			progressHidden: '#progressHidden'
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
					password: {
						required: true,
						commitPassword: true
					},
					passwordConfirmed: {
						required: true,
						equalTo: selectors.password
					}
				},
				messages: {
					password: {
						required: 'Please enter a password.'
					},
					passwordConfirmed: {
						required: 'Please enter a password'
					}
				},
				formPopover: true,
				focus: true
			});
		},

		/**
		Method initializes view-specific popovers.

		@method initializePopovers
		**/
		initializeViewPopovers: function () {
			// Define.
			var passwordPopover, passwordPopoverContent;

			// Initialize.
			passwordPopover = $(this.selectors.passwordPopover);
			passwordPopoverContent = $(this.selectors.passwordPopoverContent).html();

			// Password popover.
			this.utils.popover(passwordPopover, passwordPopoverContent);
		},

		/**
		Method calculates the strength of a user's password.

		@method onPasswordStrength
		**/
		onPasswordStrength: function (password) {
			// Define.
			var progress, progressBar, progressHidden, score, scoreMap, scoreCard;

			// Initialize.
			progress = $(this.selectors.progress);
			progressBar = $(this.selectors.progressBar);
			progressHidden = $(this.selectors.progressHidden);
			score = 0;
			scoreMap = {
				'0': {
					strength: 'weak',
					progress: 'progress progress-danger cm-width-150',
					percent: '0%'
				},
				'1': {
					strength: 'weak',
					progress: 'progress progress-danger cm-width-150',
					percent: '10%'
				},
				'2': {
					strength: 'moderate',
					progress: 'progress progress-warning cm-width-150',
					percent: '30%'
				},
				'3': {
					strength: 'moderate',
					progress: 'progress progress-warning cm-width-150',
					percent: '50%'
				},
				'4': {
					strength: 'strong',
					progress: 'progress progress-success cm-width-150',
					percent: '70%'
				},
				'5': {
					strength: 'strong',
					progress: 'progress progress-success cm-width-150',
					percent: '100%'
				}
			};

			// When password is bigger than 7 give 1 point.
			if (password.length > 7) {
				score++;
			}

			// When password has both lower and uppercase characters give 1 point.
			if ((password.match(/[a-z]/)) && (password.match(/[A-Z]/))) {
				score++;
			}

			// When password has at least one number give 1 point.
			if (password.match(/\d+/)) {
				score++;
			}

			// When password has at least one special character give 1 point.
			if (password.match(/.[!,@,#,$,%,\^,*,?,_,~,\-]/)) {
				score++;
			}

			// When password is bigger than 12 give another 1 point.
			if (password.length > 12) {
				score++;
			}

			// Set score card.
			scoreCard = scoreMap[score];

			// Apply to progress.
			progress.removeAttr('class').addClass(scoreCard.progress);
			progressBar.width(scoreCard.percent);
			progressHidden.val(scoreCard.percent);
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
		}
	};

	// Execute validation.
	commit.password.initialize();
}(jQuery));