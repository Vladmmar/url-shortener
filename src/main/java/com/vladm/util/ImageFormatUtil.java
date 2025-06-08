package com.vladm.util;

import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class ImageFormatUtil {

    private static final List<String> SUPPORTED_FORMATS = List.of("jpg", "jpeg", "png", "tiff");
}
