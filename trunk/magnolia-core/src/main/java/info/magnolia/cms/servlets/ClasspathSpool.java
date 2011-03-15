package info.magnolia.cms.servlets;

import info.magnolia.cms.util.ClasspathResourcesUtil;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.Hashtable;
import java.util.Map;


/**
 * A simple spool servlet that load resources from the classpath. A simple rule for accessible resources: only files
 * into a <code>mgnl-resources</code> folder will be loaded by this servlet (corresponding to the mapped url
 * <code>/.resources/*</code>. This servlet should be used for authoring-only resources, like rich editor images and
 * scripts. It's not suggested for public website resources. Content length and last modification date are not set on
 * files returned from the classpath.
 *
 * @author Fabrizio Giustina
 * @version $Revision: 3113 $ ($Author: nicolas $)
 */
public class ClasspathSpool extends HttpServlet {

    /**
     * Root directory for resources streamed from the classath. Only resources in this folder can be accessed.
     */
    public static final String MGNL_RESOURCES_ROOT = "/mgnl-resources";

    /**
     * Stable serialVersionUID.
     */
    private static final long serialVersionUID = 222L;

    /**
     * Logger.
     */
    private static Logger log = LoggerFactory.getLogger(Spool.class);

    /**
     * All static resource requests are handled here.
     *
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @throws IOException for error in accessing the resource or the servlet output stream
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String filePath = request.getPathInfo();

        if (StringUtils.contains(filePath, "*")) {
            streamMultipleFile(response, filePath);
        } else if (StringUtils.contains(filePath, "|")) {
            String[] paths = StringUtils.split(filePath, "|");
            streamMultipleFile(response, paths);
        } else {
            streamSingleFile(response, filePath);
        }
    }

    private Map multipleFilePathsCache;

    /**
     * @see javax.servlet.GenericServlet#init()
     */
    public void init() throws ServletException {
        super.init();
        multipleFilePathsCache = new Hashtable();
    }

    /**
     * @see javax.servlet.GenericServlet#destroy()
     */
    public void destroy() {
        super.destroy();
        multipleFilePathsCache.clear();
    }

    /**
     * Join and strem multiple files, using a "regexp-like" pattern. Only a single "*" is allowed as keyword in the
     * request URI.
     *
     * @param response
     * @param filePath
     * @throws IOException
     */
    private void streamMultipleFile(HttpServletResponse response, String filePath) throws IOException {
        if (log.isDebugEnabled())
            log.debug("aggregating files for request {}", filePath);

        String[] paths = (String[]) multipleFilePathsCache.get(filePath);
        if (paths == null) {
            final String startsWith = MGNL_RESOURCES_ROOT + StringUtils.substringBefore(filePath, "*");
            final String endssWith = StringUtils.substringAfterLast(filePath, "*");

            paths = ClasspathResourcesUtil.findResources(new ClasspathResourcesUtil.Filter() {

                public boolean accept(String name) {
                    return name.startsWith(startsWith) && name.endsWith(endssWith);
                }
            });
        }
        multipleFilePathsCache.put(filePath, paths);

        if (paths.length == 0) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        streamMultipleFile(response, paths);
    }

    /**
     * @param response
     * @param paths
     * @throws IOException
     */
    private void streamMultipleFile(HttpServletResponse response, String[] paths) throws IOException {
        ServletOutputStream out = response.getOutputStream();
        InputStream in = null;

        for (int j = 0; j < paths.length; j++) {
            try {
                String path = paths[j];
                if (!path.startsWith(MGNL_RESOURCES_ROOT)) {
                    path = MGNL_RESOURCES_ROOT + path;
                }
                in = ClasspathResourcesUtil.getStream(path);
                if (in != null) {
                    IOUtils.copy(in, out);
                }
            }
            finally {
                IOUtils.closeQuietly(in);
            }
        }

        out.flush();
        IOUtils.closeQuietly(out);
    }

    /**
     * @param response
     * @param filePath
     * @throws IOException
     */
    private void streamSingleFile(HttpServletResponse response, String filePath) throws IOException {
        InputStream in = null;
        // this method caches content if possible and checks the magnolia.develop property to avoid
        // caching during the developement process
        try {
            in = ClasspathResourcesUtil.getStream(MGNL_RESOURCES_ROOT + filePath);
        }
        catch (IOException e) {
            IOUtils.closeQuietly(in);
        }

        if (in == null) {
			if (!response.isCommitted())
				response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		}

        try {
            ServletOutputStream out = response.getOutputStream();
            IOUtils.copy(in, out);
            out.flush();
            IOUtils.closeQuietly(out);
        }
        catch (IOException e) {
            // only log at debug level
            // tomcat usually throws a ClientAbortException anytime the user stop loading the page
            if (log.isDebugEnabled())
                log.debug("Unable to spool resource due to a {} exception", e.getClass().getName()); //$NON-NLS-1$
            if (!response.isCommitted()) {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        }
        finally {
            IOUtils.closeQuietly(in);
        }
    }

}