package com.example.akshi.afpomotion;

import java.net.MalformedURLException;
import java.util.ArrayList;

/**
 * Created by akshi on 6/16/2016.
 */
public interface AFTaskCallback {

    void afData(String response) throws MalformedURLException;
}
