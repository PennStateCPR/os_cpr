/*global jQuery:true, window:true, _:true, console:true */

// Namespace.
var commit = commit || {};

// DOM Ready.
(function ($, _) {
	'use strict';

	/**
	The LogoUrlParser class parses the window url for the logoUrl
	parameter. If the parameter exists, the value of the logoUrl
	parameter is leveraged to render a secondary logo and is appended
	to informational and registration links.

	@class LogoUrlParser
	@constructor
	**/
	commit.LogoUrlParser = function (options) {
		var self = {
			/**
			Property houses class defaults.

			@property defaults
			@type Object
			**/
			defaults: {
				logoId: '#serviceLogo',
				target: '.service-logo-url'
			},

			/**
			Property houses class options.

			@property options
			@type Object
			**/
			options: {},

			/**
			Property houses the url from the service provider logo.

			@property logoUrl
			@type String
			**/
			logoUrl: undefined,

			/**
			Method merges class defaults with passed in options.

			@method mergeOptions
			@param {Object} options Plugin options
			**/
			mergeOptions: function (options) {
				this.options = $.extend({}, this.defaults, options);

				// Expect the logoId property.
				if (!this.options.hasOwnProperty('logoId') && this.options.logoId) {
					throw new Error('Expected options to have a valid logoId property.');
				}

				// Expect the target property.
				if (!this.options.hasOwnProperty('target') && this.options.target) {
					throw new Error('Expected options to have a valid target property.');
				}
			},

			/**
			Method searches the passed url for the passed name.

			@method getUrlParam
			@param {String} url Url to search
			@param {String} name Property to search against
			@return {String} Value of name property
			**/
			getUrlParam: function (url, name) {
				var result = new RegExp('[\\?&]' + name + '=([^&#]*)').exec(url);
				return (result) ? result[1] : null;
			},

			/**
			Method parses the window url and searches for the logoUrl.

			@method parseUrlForLogo
			**/
			parseForLogoUrl: function () {
				// Define & initialize.
				var url = window.location.href,
					name = 'logoUrl',
					logoUrl = this.getUrlParam(url, name);

				// Store when logoUrl exists.
				if (logoUrl) {
					this.logoUrl = logoUrl;
				}
			},

			/**
			Method builds out the dynamic logo template.

			@method buildLogoTemplate
			@param {String} logoUrl Url to be added to the src attribute
			**/
			buildLogoTemplate: function (logoUrl) {
				return '<img id="serviceLogo" class="hide" src="' + logoUrl + '" alt="Logo">';
			},

			/**
			Method applies parsed logo url to image container
			stored in the dom.

			@method applyLogoUrl
			**/
			applyLogoUrl: function () {
				// Define & initialize.
				var logo = $(this.options.logoId),
					logoUrl = this.logoUrl,
					template;

				// Only move forward when logoUrl exists.
				if (logoUrl) {
					template = this.buildLogoTemplate(logoUrl);
					logo.html(template).removeClass('hide');
				}
			},

			/**
			Method appends the logo url to the informational and registration links.

			@method appendLogoUrl
			**/
			appendLogoUrl: function () {
				// Define & initialize.
				var target = $(this.options.target),
					logoUrl = this.logoUrl;

				// Only move forward when logoUrl exists.
				if (logoUrl) {
					_.each(target, function (elem) {
						var node = $(elem),
							href = node.attr('href');

						if (href) {
							node.attr('href', href + '?logoUrl=' + logoUrl);
						}
					}, this);
				}
			},

			/**
			Method initializes validation logic.

			@method init
			@param {Object} options Plugin options
			**/
			init: function (options) {
				this.mergeOptions(options);
				this.parseForLogoUrl();
				this.applyLogoUrl();
				this.appendLogoUrl();
			}
		};

		self.init(options);
		return self;
	};

	// Initialize.
	var logoParser = new commit.LogoUrlParser();
}(jQuery, _));