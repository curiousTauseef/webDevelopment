package com.junjunguo.aeep.backend.api;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This file is part of appengineEndpointsStorage
 * <p/>
 * Created by <a href="http://junjunguo.com">GuoJunjun</a> on January 13, 2016.
 */
//@Api(name = "myEndpointsAPI", version = "v1",
//     namespace = @ApiNamespace(ownerDomain = Constant.API_OWNER, ownerName = Constant.API_OWNER,
//                               packagePath = Constant.API_PACKAGE_PATH))
//@ApiClass(resource = "videoServices",
//          clientIds = {Constant.ANDROID_CLIENT_ID, Constant.IOS_CLIENT_ID, Constant.WEB_CLIENT_ID},
//          audiences = {Constant.AUDIENCE_ID})
//public class VideoServices {
//    @ApiMethod(httpMethod = "GET")
//    public BlobstoreService getUploadService() {
//
//    }

public class VideoServe extends HttpServlet {

    private BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        BlobKey blobKey = new BlobKey(req.getParameter("blob-key"));
        blobstoreService.serve(blobKey, res);
    }


    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        Map<String, List<BlobKey>> blobs = blobstoreService.getUploads(req);

        List<BlobKey> blobKeys = blobs.get("myFile");

        if (blobKeys == null || blobKeys.isEmpty()) {
            res.sendRedirect("/");
        } else {
            res.sendRedirect("/videoservice?blob-key=" + blobKeys.get(0).getKeyString());
        }
    }
}

