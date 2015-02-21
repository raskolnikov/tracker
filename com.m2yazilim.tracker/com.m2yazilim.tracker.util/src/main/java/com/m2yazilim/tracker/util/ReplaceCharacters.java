package com.m2yazilim.tracker.util;

import java.text.Normalizer;
import java.text.Normalizer.Form;

public class ReplaceCharacters {

    public static String removeDiacriticalMarks(String string) {
    	String str = Normalizer.normalize(string, Form.NFD).replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
		str = str.replaceAll("ı", "i");
        return str;
    }
}
