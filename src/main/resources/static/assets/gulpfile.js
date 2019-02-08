'use strict';

var gulp = require('gulp');
var sass = require('gulp-sass');

sass.compiler = require('node-sass');

gulp.task('sass', function () {
	return gulp.src('./sass/**/*.scss')
		.pipe(sass.sync().on('error', sass.logError))
		.pipe(gulp.dest('./css'));
});

gulp.task('sass:watch', function () {
	gulp.watch('./sass/**/*.scss', ['sass']);
});

gulp.task('bootstrap-js', function() {
	return gulp.src('./node_modules/bootstrap/dist/js/bootstrap.js')
		.pipe(gulp.dest('./js'));
});

gulp.task('jquery-js', function() {
	return gulp.src('./node_modules/jquery/dist/jquery.min.js')
		.pipe(gulp.dest('./js'));
});

gulp.task('libs-js', gulp.series('bootstrap-js', 'jquery-js'));

gulp.task('default', gulp.series('sass', 'libs-js'));