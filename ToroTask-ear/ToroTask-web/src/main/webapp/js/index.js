$(function () {
    var user = {},
        flg = {};
    init();
    $('.upload').click(function () {
        if (flg.upd == 0) {
            upd('upload');
            flg.upd = 1
        } else {
            upd('');
            flg.upd = 0
        }
    });
    $('#login').click(function () {
        initub();
        $('#logmsk').fadeIn();
        ub(0)
    });

    $("#name").keyup(function () {
        var len = $('#name').val().length;
		expr = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
		if(!expr.test($("#name").val())){
            $('#name').css('background', 'rgb(255, 214, 190)');
            blsp();
            $('#nameal').css('color', 'rgb(255, 57, 19)').text('Correo: no valido').fadeIn()
            flg.name = 1
        } else {
            $('#name').css('background', 'rgb(255, 255, 255)');
            $('#nameal').css('color', 'rgb(17, 170, 42)').text('Correo: valido').fadeIn();
            flg.name = 0;
            tcheck()
        }
    });
    $("#pass").keyup(function () {
        var len = $('#pass').val().length;
        if (len > 0) {
            $('#pass').css('background', 'rgb(255, 255, 255)');
            flg.pass = 0;
            tcheck()
        }
    });

    function tcheck() {
        if (flg.name == 0 && flg.pass == 0) {
            $('#signupb').css('opacity', '1').css('cursor', 'pointer')
        } else {
            blsp()
        }
    }
    $('#signupb').click(function () {
        if (flg.name == 0 && flg.pass == 0) {
            $('#sumsk').fadeIn();
            $('#name, #pass, #logint, #nameal, #passal, #signupb').css('opacity', '0.2');
            $('#close').fadeIn();
    		document.formulario.submit();
    	}
    });
    $('#close').click(function () {
        init();
        initub();
        $('#close').hide()
    });

    function init() {
        flg.logt = 0
    }

    function initub() {
        flg.name = -1;
        flg.pass = -1;
        $('#sumsk').hide();
        $('#nameal').hide();
        $('#passal').hide();
        $('#name, #pass, #logint, #nameal, #passal, #signupb').css('opacity', '1');
        $('#name').css('background', 'rgb(255, 255, 255)');
        $('#pass').css('background', 'rgb(255, 255, 255)');
        $('#signupb').css('opacity', '0.2').css('cursor', 'default');
        $('#name, #pass').val('')
    }

    function upd(button) {
        location.hash = button;
        if (flg.upd == 0) {
            $('#drop').fadeIn()
        } else {
            $('#drop').fadeOut()
        }
    }

    function ub(flg) {
        if (flg == 0) {
            $('#signup').text('Sign up').css('background', '#76ABDB');
            $('#signupb').text('Sign up');
        } else {
            $('#signup').text('ACCESO').css('background', '#FFA622');
            $('#signupb').text('ENTRAR');
        }
    }

    function blsp() {
        $('#signupb').css('opacity', '0.2').css('cursor', 'default')
    }
});