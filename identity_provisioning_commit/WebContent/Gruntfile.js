/*global exports:true, module:true, require:true */

// Grunt.
module.exports = function (grunt) {
	'use strict';

	// Grunt configuration.
	grunt.initConfig({
		pkg: grunt.file.readJSON('package.json'),

		// Cleans out directories.
		clean: {
			min: [
				'assets/js/min'
			]
		},

		// Compile less files into css.
		less: {
			dev: {
				files: {
					'assets/css/default/commit.css': 'less/commit.less'
				}
			},
			prod: {
				files: {
					'assets/css/default/commit.css': 'less/commit.less'
				},
				options: {
					compress: true
				}
			}
		},

		// Compress JavaScript files.
		uglify: {
			options: {
				compress: true,
				mangle: false,
				preserveComments: false
			},
			source: {
				files: {
					'assets/js/min/logo-url-parser-min.js': ['assets/js/src/logo-url-parser.js'],
					'assets/js/min/contact-info-min.js': ['assets/js/src/contact-info.js'],
					'assets/js/min/current-address-min.js': ['assets/js/src/current-address.js'],
					'assets/js/min/alternate-address-min.js': ['assets/js/src/alternate-address.js'],
					'assets/js/min/error-min.js': ['assets/js/src/error.js'],
					'assets/js/min/legal-name-min.js': ['assets/js/src/legal-name.js'],
					'assets/js/min/match-min.js': ['assets/js/src/match.js'],
					'assets/js/min/password-min.js': ['assets/js/src/password.js'],
					'assets/js/min/personal-info-min.js': ['assets/js/src/personal-info.js'],
					'assets/js/min/security-questions-min.js': ['assets/js/src/security-questions.js'],
					'assets/js/min/sqanswer-min.js': ['assets/js/src/sqanswer.js'],
					'assets/js/min/success-min.js': ['assets/js/src/success.js'],
					'assets/js/min/utils-min.js': ['assets/js/src/utils.js'],
					'assets/js/min/validator-min.js': ['assets/js/src/validator.js'],
					'assets/js/min/verify-info-min.js': ['assets/js/src/verify-info.js']
				}
			}
		},

		// Lint settings.
		jshint: {
			options: {
				nomen: false,
				curly: true,
				camelcase: false,
				eqeqeq: true,
				newcap: true,
				undef: true,
				trailing: true,
				strict: true,
				latedef: true,
				indent: true,
				quotmark: true
			},
			global: {
				define: true,
				window: true,
				document: true
			},
			all: [
				'assets/js/src/*.js'
			]
		},

		// Watch individual files.
		watch: {
			dev: {
				files: [
					'less/**',
					'assets/img/**',
					'assets/js/**',
					'modules/**',
					'*.jsp'
				],
				tasks: [
					'less:dev',
					'jshint'
				]
			}
		}
	});

	// Load plugins/tasks.
	grunt.loadNpmTasks('grunt-contrib');

	// Watch command. Watches less and jsp files for changes.
	grunt.registerTask('watcher', ['watch:dev']);

	// Less compile command. Compiles less to css.
	grunt.registerTask('compile', ['less:dev']);

	// Production command.
	grunt.registerTask('prod', ['clean:min', 'jshint', 'less:prod', 'uglify:source']);
};