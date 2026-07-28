package com.unlink.test;

import com.formdev.flatlaf.FlatDarkLaf;
import com.unlink.ui.login.LoginFrame;

public class LoginTest {

    public static void main(String[] args) {

        FlatDarkLaf.setup();

        new LoginFrame();
    }
}