package com.jock.unmisa.config.validate;

import javax.validation.Payload;

public class SeverityGroup{
	
    public static interface Warning extends Payload {};

    public static interface Error extends Payload {};
}
