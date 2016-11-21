(function () {
    var a = {
        cookiePoll: function () {
            a.triggerAutomation();
            setTimeout(function () {
                a.cookiePoll()
            }, 1E3)
        }, triggerAutomation: function () {
            var d = a.readCookie("jlwp_name"), e = a.readCookie("jlwp_name_last"), c = a.readCookie("jlwp_email"), b = a.readCookie("jlwp_company"), f = a.readCookie("jlwp_automation_id");
            "string" == typeof c && "string" == typeof d && (d = a.trim(d), e && (d += " " + a.trim(e)), jump("send", "automation", "trigger", f, {
                name: d,
                email: c,
                company: b
            }), a.clearFormCookies())
        }, clearFormCookies: function () {
            a.eraseCookie("jlwp_name");
            a.eraseCookie("jlwp_name_last");
            a.eraseCookie("jlwp_email");
            a.eraseCookie("jlwp_company");
            a.eraseCookie("jlwp_automation_id")
        }, createCookie: function (d, a, c) {
            var b = "";
            c && (b = new Date, b.setTime(b.getTime() + 864E5 * c), b = "; expires=" + b.toGMTString());
            document.cookie = d + "=" + escape(a) + b + "; path=/"
        }, readCookie: function (a) {
            a += "=";
            for (var e = document.cookie.split(";"), c = 0; c < e.length; c++) {
                for (var b = e[c]; " " == b.charAt(0);)b = b.substring(1, b.length);
                if (0 == b.indexOf(a))return unescape(b.substring(a.length, b.length))
            }
            return null
        },
        eraseCookie: function (a) {
            this.createCookie(a, "", -1)
        }, trim: function (a) {
            return "string" == typeof a ? a.replace(/^\s+|\s+$/g, "") : a
        }
    };
    a.triggerAutomation();
    var f = document.getElementsByTagName("form"), g;
    for (g in f) {
        var h = f[g].className || null;
        if (h && -1 < h.indexOf("wpcf7-form")) {
            a.cookiePoll();
            break
        }
    }
})();
