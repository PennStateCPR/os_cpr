/*global jQuery:true, window:true, document:true, console:true, _:true, escape:true, unescape:true */

// Namespace.
var commit = commit || {};

// DOM Ready.
(function ($) {
	'use strict';

	/**
	The Utils class houses helper & utility methods.

	@class Utils
	@constructor
	**/
	commit.utils = {
		/**
		Method takes a string and returns the string lowercased.

		@method lowerCase
		@param {String} str String to be lowercased
		**/
		lowerCase: function (str) {
			if (str && typeof str !== 'string') {
				throw new Error('Unexpected type. Expected string and not ' + typeof str + '.');
			}
			return str.toLowerCase();
		},

		/**
		Method attaches the onbeforeunload event to the window object.

		method beforeUnload
		**/
		beforeUnload: function () {
			if (!commit.hasOwnProperty('navHook')) {
				commit.navHook = false;
			}

			window.onbeforeunload = function () {
				if (!commit.navHook) {
					return 'Data you have entered may not be saved!\n';
				}
			};
		},

		/**
		Method applies a click event to the passed selector.
		When clicked the commit.navHook property is set to
		true. This provides a mechanism for navigation-based
		buttons to circumvent the beforeunload data loss message.

		@method circumventor
		**/
		navHook: function (selector) {
			selector.on('click', _.bind(function (e) {
				commit.navHook = true;
			}, this));
		},

		/**
		Method initializes bootstrap popovers and binds
		resize and clickoutside events to each instance.

		@method popover
		@param {Object} selector jQuery DOM selector
		@param {String} content HTML formatted content
		@param {Object} options Object hash of options
		**/
		popover: function (selector, content, options) {
			// Require selector & content parameters.
			if (!selector || !content) {
				throw new Error('Undefined. Expected value for method argument.');
			}

			// Define.
			var defaults;

			// Define defaults & options.
			options = (options && _.isObject(options)) ? options : {};
			defaults = {
				title: 'Instructions',
				placement: 'right',
				html: true,
				content: content,
				container: 'body'
			};

			// Merge options.
			options = $.extend(true, {}, defaults, options);

			// Initialize popover.
			selector.popover(options);

			// Hide popover when window is resized.
			$(window).resize(function () {
				selector.popover('hide');
			});

			// Hide popover when any element is clicked.
			selector.on('clickoutside', function (e) {
				selector.popover('hide');
			});
		},

		/**
		Method resets an input control and clears bootstrap-based errors.

		@method resetSelect
		@param {Object} input jQuery DOM selector
		**/
		resetInput: function (input) {
			// Require input.
			if (!input) {
				throw new Error('Undefined. Expected value for method argument.');
			}

			input.val('');
			input.closest('.control-group')
				.removeClass('error')
				.find('.help-block').remove();
		},

		/**
		Method resets a select control and clears bootstrap-based errors.

		@method resetSelect
		@param {Object} select jQuery DOM selector
		**/
		resetSelect: function (select) {
			// Require input.
			if (!select) {
				throw new Error('Undefined. Expected value for method argument.');
			}

			select[0].selectedIndex = 0;
			select.closest('.control-group')
				.removeClass('error')
				.find('.help-block').remove();
		},

		/**
		Method sets a cookie.

		@method setCookie
		@param {String} name Name of cookie
		@param {String} value Value to set
		**/
		setCookie: function (name, value) {
			// Prevent execution when a cookie
			// of the same name and value already exist.
			if (this.getCookie(name) && this.getCookie(name) === value) {
				return;
			}

			// Define & Initialize.
			var argv = arguments,
				argc = arguments.length,
				expires = (argc > 2) ? argv[2] : null,
				path = (argc > 3) ? argv[3] : null,
				domain = (argc > 4) ? argv[4] : null,
				secure = (argc > 5) ? argv[5] : false;

			document.cookie = name + '=' + escape(value) +
				((expires === null) ? '' : ('; expires=' + expires.toGMTString())) +
				((path === null) ? '' : ('; path=' + path)) +
				((domain === null) ? '' : ('; domain=' + domain)) +
				((secure === true) ? '; secure' : '');
		},

		/**
		Method returns value of cookie.

		@method getCookieValue
		@param {Number} offset
		**/
		getCookieValue: function (offset) {
			var endstr = document.cookie.indexOf(';', offset);
			if (endstr === -1) {
				endstr = document.cookie.length;
			}
			return unescape(document.cookie.substring(offset, endstr));
		},

		/**
		Method returns a cookie.

		@method getCookie
		@param {String} name Name of cookie
		**/
		getCookie: function (name) {
			// Define & Initialize.
			var arg = name + '=',
				alen = arg.length,
				clen = document.cookie.length,
				i = 0;

			while (i < clen) {
				var j = i + alen;
				if (document.cookie.substring(i, j) === arg) {
					return this.getCookieValue(j);
				}
				i = document.cookie.indexOf(' ', i) + 1;
				if (i === 0) {
					break;
				}
			}
			return null;
		},

		/**
		Method deletes a cookie.

		@method removeCookie
		@param {String} name Name of cookie
		**/
		removeCookie: function (name) {
			var exp = new Date(),
				cval = this.getCookie(name);
			exp.setTime(exp.getTime() - 10);
			document.cookie = name + '=' + cval + '; expires=' + exp.toGMTString();
		}
	};
}(jQuery));