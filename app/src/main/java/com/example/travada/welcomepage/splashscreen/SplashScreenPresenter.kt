package com.example.travada.welcomepage.splashscreen

import com.example.travada.util.util
import com.orhanobut.hawk.Hawk

class SplashScreenPresenter(val listener: Listener) {

    fun checkIsLogin(){
        var isLogin = Hawk.get(util.SF_ISLOGIN, false)
        if (isLogin == true){
            listener.goToLoginPin()
        } else {
            listener.goToOnboarding()
        }

    }



    interface Listener {
        fun goToOnboarding()
        fun goToLoginPin()
    }
}