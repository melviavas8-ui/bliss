package aethereal.resource;
import aethereal.Daamky;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class ClasspathResource implements ResourceSource {
    public final String path;
    static final String assetPrefix = "/assets/daamky/";

    @Override
    public InputStream stream() {
        try {
            String strSubstring = this.path;
            if (strSubstring.startsWith("/")) {
                strSubstring = strSubstring.substring(1);
            }
            if (!strSubstring.startsWith("assets/daamky/")) {
                strSubstring = "assets/daamky/" + strSubstring;
            }
            String str = "/" + strSubstring;
            Daamky.LOGGER.debug("Trying to load classpath resource: {}", str);
            URL resource = ClasspathResource.class.getResource(str);
            if (resource == null) {
                throw new IllegalStateException("Classpath resource not found: " + str);
            }
            Daamky.LOGGER.debug("Found resource URL: {}", resource);
            URLConnection uRLConnectionOpenConnection = resource.openConnection();
            uRLConnectionOpenConnection.setUseCaches(false);
            return uRLConnectionOpenConnection.getInputStream();
        } catch (Exception e) {
            Daamky.LOGGER.error("Failed to open classpath resource: {}", this.path, e);
            throw new RuntimeException("Failed to open classpath resource: " + this.path, e);
        }
    }

    public ClasspathResource(String str) {
        this.path = str;
    }
}
