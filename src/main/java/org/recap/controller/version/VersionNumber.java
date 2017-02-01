package org.recap.controller.version;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by hemalathas on 17/10/16.
 */
@Controller
public class VersionNumber {

    @Value("${version.number}")
    String versionNumber;

    @RequestMapping(method = RequestMethod.GET, value = "/getVersionNumber")
    @ResponseBody
    public String getVersionNumber() {
        return versionNumber;
    }
}
