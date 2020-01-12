package com.example.myapplication.di.modules.authentication;

import androidx.lifecycle.ViewModel;
import com.example.myapplication.domain.authentication.AuthenticationSource;
import dagger.MapKey;

import java.lang.annotation.*;

@MapKey
@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface AuthenticationKey {
    AuthenticationSources value();
}