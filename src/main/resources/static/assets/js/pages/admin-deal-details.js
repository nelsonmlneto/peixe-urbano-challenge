
var AdminDealDetailsController = {

	cleanOptionForm: function () {
		$('#option-form')[0].reset();
	},

	submitOptionForm: function () {
		$('#option-form').submit();
	},

	changeOptionPriceValue: function () {
		var value = $(this).val();
		value = value.replace('.', '');
		value = value.replace(',', '.');
		$('#option-hidden-price').val(value);
	},

	changeOptionDiscountValue: function () {
		var value = $(this).val();
		value = value.replace('.', '');
		value = value.replace(',', '.');
		$('#option-hidden-discount').val(value);
	},

	initBuyOptionEvents: function() {

		$('#submit-option-form').on('click', AdminDealDetailsController.submitOptionForm);

		$('#clean-option-form').on('click', AdminDealDetailsController.cleanOptionForm);

		$('#option-price').on('change', AdminDealDetailsController.changeOptionPriceValue);

		$('#option-discount').on('change', AdminDealDetailsController.changeOptionDiscountValue);

	},

	init: function () {
		$('.money').mask('000.000.000.000.000,00', {reverse: true});
		$('.percent').mask('00,00', {reverse: true});

		AdminDealDetailsController.initBuyOptionEvents();
	}
};

$(document).ready(function() {

	AdminDealDetailsController.init();

});