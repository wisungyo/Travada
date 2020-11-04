package com.example.travada.welcomepage.onboarding

class OnboardingEndPresenter(val listener: Listener) {

    fun goToLoginPage() {
        listener.goToLoginPage()
    }

    fun goToRegisterPage() {
        listener.goToRegisterPage()
    }


    interface Listener {
        fun goToLoginPage()
        fun goToRegisterPage()
    }
}