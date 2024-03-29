/**
 *
 * Magnolia and its source-code is licensed under the LGPL.
 * You may copy, adapt, and redistribute this file for commercial or non-commercial use.
 * When copying, adapting, or redistributing this document in keeping with the guidelines above,
 * you are required to provide proper attribution to obinary.
 * If you reproduce or distribute the document without making any substantive modifications to its content,
 * please use the following attribution line:
 *
 * Copyright 1993-2006 obinary Ltd. (http://www.obinary.com) All rights reserved.
 *
 */
package info.magnolia.cms.security.auth;

import java.io.IOException;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.TextOutputCallback;
import javax.security.auth.callback.UnsupportedCallbackException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Plain text callback handler
 * @author Sameer Charles
 * @version $Id: CredentialsCallbackHandler.java 3274 2006-05-28 15:13:49Z fgiust $
 */
public class CredentialsCallbackHandler implements CallbackHandler {

    /**
     * Logger
     */
    protected static Logger log = LoggerFactory.getLogger(CredentialsCallbackHandler.class);

    /**
     * user id
     */
    protected String name;

    /**
     * password
     */
    protected char[] pswd;

    /**
     * default constructor required by java security framework
     */
    public CredentialsCallbackHandler() {
        // do not instanciate with this constructor
    }

    /**
     * @param name
     * @param pswd
     */
    public CredentialsCallbackHandler(String name, char[] pswd) {
        this.name = name;
        this.pswd = pswd;
    }

    /**
     * handle name and password callback which must be set while constructing this handler
     */
    public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
        for (int i = 0; i < callbacks.length; i++) {
            if (callbacks[i] instanceof NameCallback) {
                ((NameCallback) callbacks[i]).setName(this.name);
            }
            else if (callbacks[i] instanceof PasswordCallback) {
                ((PasswordCallback) callbacks[i]).setPassword(this.pswd);
            }
            else if (callbacks[i] instanceof TextOutputCallback) {
                TextOutputCallback outputCallback = (TextOutputCallback) callbacks[i];
                switch (outputCallback.getMessageType()) {
                    case TextOutputCallback.INFORMATION:
                        log.info(outputCallback.getMessage());
                        break;
                    case TextOutputCallback.ERROR:
                        log.error(outputCallback.getMessage());
                        break;
                    case TextOutputCallback.WARNING:
                        log.warn(outputCallback.getMessage());
                        break;
                    default:
                        if (log.isDebugEnabled()) {
                            log.debug("Unsupported message type : {}", Integer
                                .toString(outputCallback.getMessageType()));
                            log.debug("Message : {}", outputCallback.getMessage());
                        }
                }
            }
        }
    }
}
