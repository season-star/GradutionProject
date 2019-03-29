package cqu.cs.movie_rec.controller;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;


@Controller
public class ImageController {
    @Autowired
    ImageController imageController;

//    TODO:实现将对应名称的电影图片存入数据库，从而和Id对应，推荐时要出现电影图片
    @RequestMapping("/addFile")
    @ResponseBody
    public String addFile(){
        MongoClient client = new MongoClient("127.0.0.1",27017);
        DB db = client.getDB("files");
        GridFS fs = new GridFS(db);
        try {
            GridFSInputFile gridFSInputFile = fs.createFile(new File("D://test.png"));
            gridFSInputFile.setFilename("db_test.png");
            gridFSInputFile.save();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "file over";
    }


    @RequestMapping("/downFile")
    public void downFile(HttpServletResponse response){
        String fileId = "5c0f7c374fc404123403d69e";//这里可以通过参数取代
        try {
            MongoClient client = new MongoClient("127.0.0.1",27017);
            DB db = client.getDB("files");
            GridFS fs = new GridFS(db);

            GridFSDBFile gridFSDBFile = fs.findOne(new ObjectId(fileId));
            OutputStream sos = response.getOutputStream();
            response.setContentType("application/octet-stream");
            // 获取原文件名
            String name = (String) gridFSDBFile.get("filename");
            String fileName = new String(name.getBytes("GBK"), "ISO8859-1");
            // 设置下载文件名
            response.addHeader("Content-Disposition", "attachment; filename=\""	+ fileName + "\"");
            // 向客户端输出文件
            gridFSDBFile.writeTo(sos);
            sos.flush();
            sos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
