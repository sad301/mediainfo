function _(params) {

    this.params = params;

    this.index = {};
    this.index.init = () => {
        $('input').focus((e) => {
            $(e.target).closest('.field').removeClass('error');
        });
        $('.ui.primary.button').click(() => {
            let username = $('input[name="username"]');
            let password = $('input[name="password"]');
            [username, password].forEach(el => {
                if(el.val() == '') el.closest('.field').addClass('error');
            });
            if(username.val() == '' || password.val() == '') return false;
            let auth = {
                username: username.val(),
                url: `http://${this.params.data('domain')}/api/login`,
                timestamp: Date.now()
            };
            let jsAuth = JSON.stringify(auth);
            let payload = {
                data: btoa(jsAuth),
                hash: CryptoJS.HmacSHA512(jsAuth, password.val()).toString(CryptoJS.enc.Base64)
            };
            let jq = $.ajax({
                url: auth.url,
                method: 'POST',
                data: JSON.stringify(payload),
                contentType: 'application/json',
                dataType: 'json',
                beforeSend: () => $('.ui.primary.button').addClass('loading')
            });
            jq.done(data => console.log(data));
            jq.fail(xhr => console.log(xhr.responseText));
        });
    };

}

_.q = function (params) { return new _(params); };
