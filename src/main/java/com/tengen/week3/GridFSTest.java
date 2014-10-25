package com.tengen.week3;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;
import org.apache.log4j.Logger;

import java.io.*;
import java.util.ArrayList;

public class GridFSTest {

    private static final Logger LOGGER = Logger.getLogger(GridFSTest.class);
    public static final String FILE_PATH = "./src/main/resources/David-Gray_Please-Forgive-Me-(Live).mp4";
    public static final String FILE_COPY_PATH = "./src/main/resources/video_copy.mp4";

    public static void main(String[] args) throws IOException {
        MongoClient client = new MongoClient();
        DB db = client.getDB("course");
        FileInputStream inputStream = null;
        db.dropDatabase();

        GridFS videos = new GridFS(db, "videos"); // returns GridFS bucket named "videos"
        try {
            inputStream = new FileInputStream(new File(FILE_PATH));
        } catch (FileNotFoundException e) {
            LOGGER.error("Can't open file");
            System.exit(1);
        }

        GridFSInputFile video  = videos.createFile(inputStream, "video.mp4");

        // create some meta data for the object
        BasicDBObject meta = new BasicDBObject("description", "David Gray");
        ArrayList<String> tags = new ArrayList<String>();
        tags.add("Singing");
        tags.add("Lyric");
        meta.append("tags", tags);

        video.setMetaData(meta);
        video.save();

        LOGGER.info("Object ID in Files Collection: " +  video.get("_id"));


        LOGGER.info("Saved the file to MongoDB");
        LOGGER.info("Now lets read it back out");

        GridFSDBFile gridFile = videos.findOne(new BasicDBObject("filename", "video.mp4"));

        FileOutputStream outputStream = new FileOutputStream(new File(FILE_COPY_PATH));
        gridFile.writeTo(outputStream);

        LOGGER.info("Write the file back out");
    }

}